import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomOrderComponent } from './custom-order/custom-order.component';
import { MenuOrderComponent } from './menu-order/menu-order.component';
import {SnackBarMenuRoutingModule} from "./snack-bar-menu-routing.module";
import {SnackBarMenuComponent} from "./snack-bar-menu.component";
import {NgbAccordionModule} from "@ng-bootstrap/ng-bootstrap";
import { OrderDetailComponent } from './order-detail/order-detail.component';

@NgModule({
  declarations: [SnackBarMenuComponent,CustomOrderComponent, MenuOrderComponent, OrderDetailComponent],
  exports: [
    SnackBarMenuComponent
  ],
  imports: [
    CommonModule,
    SnackBarMenuRoutingModule,
    NgbAccordionModule
  ]
})
export class SnackBarMenuModule { }
