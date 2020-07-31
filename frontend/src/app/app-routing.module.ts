import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PromosComponent} from "./promos/promos.component";

const routes: Routes = [
  { path: 'promos', component: PromosComponent },
  { path: '',
    loadChildren: () => import('./snack-bar-menu/snack-bar-menu.module').then(m => m.SnackBarMenuModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
