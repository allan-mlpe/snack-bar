import { Component, OnInit } from '@angular/core';
import {Ingredient} from "../../model/Ingredient";
import {SnackItem} from "../../model/SnackItem";
import {Order} from "../../model/Order";
import {IngredientService} from "../../services/ingredient.service";
import {OrderService} from "../../services/order.service";
import { Snack } from 'src/app/model/Snack';

@Component({
  selector: 'app-custom-order',
  templateUrl: './custom-order.component.html',
  styleUrls: ['./custom-order.component.css']
})
export class CustomOrderComponent implements OnInit {

  ingredients: Array<Ingredient> = [];
  ingredientMap: Map<string, SnackItem> = new Map();
  loading: boolean = true;
  error: string;
  partialPrice: number = 0;

  finishedOrder: Order;

  constructor(
    private ingredientService: IngredientService,
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    this.ingredientService.getIngredients()
        .subscribe(
            (data: Array<Ingredient>) => {
                this.loading = false
                this.ingredients = data;
            },
            (error) => {
                this.loading = false;
                this.error = 'Erro ao carregar os nossos ingredientes! Tente novamente mais tarde';
            }
        );
  }

  addIngredient(ingredient: Ingredient) {
     const key: string = ingredient.name;

     if (this.ingredientMap.has(key)) {
       const snackItem: SnackItem = this.ingredientMap.get(key);
       snackItem.quantity += 1

       this.partialPrice += snackItem.ingredient.price;
     } else {
      let snackItem: SnackItem = {
        ingredient: ingredient,
        quantity: 1
      };

      this.ingredientMap.set(key, snackItem);
      this.partialPrice += snackItem.ingredient.price;
     }
  }

  removeIngredient(ingredient: Ingredient) {
    const key: string = ingredient.name;

     if (this.ingredientMap.has(key)) {
       const snackItem: SnackItem = this.ingredientMap.get(key);
       const quantity: number = snackItem.quantity;
       if (quantity > 1) {
        snackItem.quantity -= 1
       } else {
        this.ingredientMap.delete(key);
       }
       this.partialPrice -= snackItem.ingredient.price;
     }
  }

  submitOrder() {
    const snackItems: Array<SnackItem> = Array.from(this.ingredientMap.values());
    
    if (snackItems && snackItems.length > 0) {
      const snack: Snack = {
        snackItems
      }
      
      this.loading = true;
  
      this.orderService.submitOrder(snack)
        .subscribe(
            (data: Order) => {
                this.finishedOrder = data;
                this.error = undefined;
                this.loading = false
            },
            (error) => {
                this.loading = false;
                this.error = 'Erro ao submeter o seu pedido! Tente novamente em instantes';
            }
        );
    }
  }

}
