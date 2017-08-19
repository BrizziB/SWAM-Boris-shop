import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppComponent }  from './Components/app.component';
import { ProductsComponent} from './Components/products.component';
import { UsersComponent} from './Components/users.component';
import { OrdersComponent } from './Components/orders.component';
import { ProductInsertionFormComponent } from './Components/product-insertion-form.component';
import { UserInsertionFormComponent } from './Components/user-insertion-form.component'


import { ProductService } from './API/product.service';
import { UserService } from './API/user.service';
import { OrderService } from './API/order.service';


import { AppRoutingModule} from './app-routing.module';

// Imports for loading & configuring the in-memory web api - quando poi testi con il server vero non servono
import { InMemoryWebApiModule } from 'angular-in-memory-web-api';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpModule
 ],
  declarations: [
    AppComponent,
    ProductsComponent,
    UsersComponent,
    OrdersComponent,
    ProductInsertionFormComponent,
    UserInsertionFormComponent
   ],
  providers: [ ProductService, UserService, OrderService ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
