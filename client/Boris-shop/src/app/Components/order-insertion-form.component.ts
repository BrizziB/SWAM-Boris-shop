import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrdersComponent } from './orders.component';
import { OrderService } from '../API/order.service';
import { UserService } from '../API/user.service';
import { ProductService } from '../API/product.service';
import { User } from '../Model/user';
import { Product } from '../Model/product';


@Component({
    selector: 'order-insertion-tab',
    templateUrl: './order-insertion-form.component.html',
    styleUrls: ['./insertion-form.component.css']
})
export class OrderInsertionFormComponent implements OnInit {
    orderID: number;
    buyer: User;
    items: Product[];
    allProducts: Product[];
    allUsers: User[];
    showUserList = false;
    showProductList = false;

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
        this.items = [];
    }

    registerUser(user: User) {
        this.buyer = user;
    }
    updateProductList(product: Product) {
        if ((<HTMLInputElement>document.getElementById('chkbx-' + product.itemID)).checked === true) {
            this.items.push(product);
        } else {
            let index: number = this.items.indexOf(product);
            if (index !== -1) {
                this.items.splice(index, 1);
            }
        }
    }

    addNewOrder(): void {
        this.orderID = this.ordersComponent.index;
        if (this.buyer === undefined || this.items.length === 0) {
            alert('order was missing the buyer or at least on item');
        } else {
            this.items.forEach(item => {
                let body = JSON.stringify({
                    buyer: this.buyer,
                    item: item
                });
                this.orderService.addNewOrder(body)
                    .then(order => this.ordersComponent.orders.push(order));
            });
        }
    }
}
