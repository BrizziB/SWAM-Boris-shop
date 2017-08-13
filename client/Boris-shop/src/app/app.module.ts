import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppComponent }  from './Components/app.component';
import { ProductsComponent} from './Components/products.component';
import { UsersComponent} from './Components/users.component';

import { ProductService } from './API/product.service';
import { UserService } from './API/user.service';


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
    UsersComponent
   ],
  providers: [ ProductService, UserService ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }