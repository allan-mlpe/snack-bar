import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomOrderComponent} from "./custom-order/custom-order.component";
import {SnackBarMenuComponent} from "./snack-bar-menu.component";

const routes: Routes = [
  { path: '', component: SnackBarMenuComponent },
  { path: 'custom', component: CustomOrderComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SnackBarMenuRoutingModule { }
