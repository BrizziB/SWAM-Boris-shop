import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../Model/Order';
import { OrderService } from '../API/order.service';

@Component({
    selector: 'product-tab',
    templateUrl: './orders.component.html',
    /* styleUrls: ['./orders.component.css'] */
})
export class OrdersComponent implements OnInit { 

    orders: Order[];

    getOrders(): void{ //poi invece dovrà ritornare un array di Order

    }

    ngOnInit(): void {
        this.getOrders();
    }

}
