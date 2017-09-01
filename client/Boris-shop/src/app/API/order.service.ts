import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { Order } from '../Model/Order';

@Injectable()
export class OrderService {
    private headers = new Headers({ 'Content-Type': 'application/json' });
    private ordersUrl = 'http://localhost:8080/boris-shop/orders';

    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getOrders(): Promise<Order[]> {
        return this.http.get(this.ordersUrl + '/list')
            .toPromise()
            .then(response => response.json() as Order[])
            .catch(this.handleError);
    }

    addNewOrder(body: String): Promise<Order> {
        const url = `${this.ordersUrl}/add`;
        return this.http
            .post(url, body, { headers: this.headers })
            .toPromise()
            .then(res => res.json() as Order)
            .catch(this.handleError);
    }

    advanceOrderStatus(orderID: number): Promise<any> {
        const url = `${this.ordersUrl}/advance/${orderID}`;
        return this.http.put(url, { headers: this.headers })
        .toPromise()
        .then(res => res.json())
        .catch(this.handleError);
    }
}
