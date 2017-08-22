import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrdersComponent } from './orders.component';
import { OrderService } from '../API/order.service';
import { UserService } from '../API/user.service';
import { ProductService } from '../API/product.service';
import { User } from '../Model/user';
import { Product } from '../Model/product';
import { Status } from '../Model/order';




@Component({
    selector: 'order-insertion-tab',
    templateUrl: './order-insertion-form.component.html',
    styleUrls: ['./insertion-form.component.css']
})
export class OrderInsertionFormComponent implements OnInit {
    orderID: number;
    buyer: User;
    item: Product;
/*     status: Status; */
    allProducts: Product[];
    allUsers: User[];

    constructor(private ordersComponent: OrdersComponent,
        private orderService: OrderService,
        private productService: ProductService,
        private userService: UserService) { }

    closeForm() {
        this.ordersComponent.showForm = false;

    }

    ngOnInit() {
        this.productService.getProducts().then(products => this.allProducts = products);
        this.userService.getUsers().then(users => this.allUsers = users);
    }

    registerUser(user: User) {
        this.buyer = user;
        /* user.userID = null; */
    }
    registerProduct(product: Product) {
        this.item = product;
        /* product.itemID = null; */
    }

    addNewOrder(): void {
        this.orderID = this.ordersComponent.index;
/*         this.status = Status.REQUESTED; */

        let body = JSON.stringify({
            //orderID: this.orderID,
            buyer: this.buyer,
            item: this.item/* ,
            status: this.status */
        });

        this.orderService.addNewOrder(body)
            .then(order => this.ordersComponent.orders.push(order));
    }

}
