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
    advanceOrderStatus(order: Order): void {
        this.orderService.advanceOrderStatus(order.orderID).then(status => order.status = status);

    }

    getOrders(): void {
        this.orderService.getOrders().then(orders => this.orders = orders).then(orders => this.setMaxIndex());
    }

    removeOrder(order: Order): void {
        this.orderService
            .deleteOrder(order.orderID)
            .then(() => {
                this.orders = this.orders.filter(o => o !== order);
            });
    }

    ngOnInit(): void {
        this.getOrders();
    }

}
