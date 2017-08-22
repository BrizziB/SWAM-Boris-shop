import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductsComponent} from './Components/products.component';
import { UsersComponent } from './Components/users.component';
import { OrdersComponent } from './Components/orders.component';

const routes: Routes = [
  { path: 'products', component: ProductsComponent},
  { path: 'users', component: UsersComponent},
  { path: 'orders', component: OrdersComponent}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
