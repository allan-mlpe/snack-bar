import { Component, OnInit, Input } from '@angular/core';
import { Order } from 'src/app/model/Order';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {

  @Input()
  order: Order;

  constructor() { }

  ngOnInit(): void {
  }

  printIngredients() {
    return this.order.snack.snackItems
        .map(item => `${item.ingredient.name} (${item.quantity})`)
        .join(', ')
  }
}
