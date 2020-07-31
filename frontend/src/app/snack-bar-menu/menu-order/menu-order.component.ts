import { Component, OnInit } from '@angular/core';
import {Snack} from "../../model/Snack";
import {SnackService} from "../../services/snack.service";
import {OrderService} from "../../services/order.service";
import { Order } from 'src/app/model/Order';

@Component({
  selector: 'app-menu-order',
  templateUrl: './menu-order.component.html',
  styleUrls: ['./menu-order.component.css']
})
export class MenuOrderComponent implements OnInit {

  snacks: Array<Snack> = [];
  prices: Map<String, number> = new Map();
  loading: boolean = true;
  error: string;
  choosenSnack: Snack;
  finishedOrder: Order;

  constructor(
    private snackService: SnackService,
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    this.snackService.getMenuSnacks()
        .subscribe(
            (data: Array<Snack>) => {
                this.snacks = data;
                this.calculatePrices();
                this.loading = false
            },
            (error) => {
                this.loading = false;
                this.error = 'Erro ao carregar o nosso cardápio! Tente novamente mais tarde';
            }
        );
  }

  calculatePrices() {
    this.snacks.forEach(snack => {
      const price = snack.snackItems
        .reduce((total, item) => item.ingredient.price + total, 0);

      this.prices.set(snack.description, price);
    })
  }

  printIngredients(snack: Snack): string {
    return snack.snackItems.map(i => i.ingredient.name).join(', ');
  }

  isChoosenSnack(snack: Snack): boolean {
    return this.choosenSnack && snack.id === this.choosenSnack.id;
  }

  chooseSnack(snack: Snack) {
    this.choosenSnack = snack;
  }

  unmarkSnack() {
    this.choosenSnack = undefined;
  }

  submitOrder() {
    this.loading = true;
    this.choosenSnack.id = undefined;

    this.orderService.submitOrder(this.choosenSnack)
      .subscribe(
          (data: Order) => {
              this.finishedOrder = data;
              this.loading = false;
              this.error = undefined;
          },
          (error) => {
              this.loading = false;
              this.error = 'Erro ao submeter o seu pedido! Tente novamente em instantes';
          }
      );
  }
}
