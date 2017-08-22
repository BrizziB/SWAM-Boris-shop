import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../Model/Order';
import { OrderService } from '../API/order.service';

@Component({
    selector: 'product-tab',
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

    orders: Order[];
    showForm: false;
    index: number;

    constructor(
        private orderService: OrderService,
        private router: Router) { }

    setMaxIndex(): void {
        let max = 0;
        this.orders.forEach(order => {
            if (order.orderID > max) {
                max = order.orderID;
            }
        });
        this.index = max + 1;
    }

    getOrders(): void {
        this.orderService.getOrders().then(orders => this.orders = orders).then(orders => this.setMaxIndex());
    }

    ngOnInit(): void {
        this.getOrders();
    }

}
