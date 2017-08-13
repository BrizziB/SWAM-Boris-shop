import { Component } from '@angular/core';
import { ProductService} from '../API/product.service';
import { UserService} from '../API/user.service';

@Component({
  selector: 'my-app',
  templateUrl: './app.components.html'
})
export class AppComponent  {
  name = 'BoriShop';
}
