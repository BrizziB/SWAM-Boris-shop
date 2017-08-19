import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Product } from '../Model/Product';

import { PRODUCTS } from './mock.products';

@Injectable()
export class ProductService {

    private headers = new Headers({ 'Content-Type': 'application/json' });
    private productsUrl = 'http://localhost:8080/boris-shop/products';

    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getProducts(): Promise<Product[]> {
        return this.http.get(this.productsUrl + '/list')
            .toPromise()
            .then(response => response.json() as Product[])
            .catch(this.handleError);
    }

    deleteProduct(productID: number): Promise<void> {
        const url = `${this.productsUrl}/delete/${productID}`;
        return this.http.delete(url, { headers: this.headers })
            .toPromise()
            .then(() => null)
            .catch(this.handleError);
    }

    addNewProduct(body: String): Promise<Product> {
        const url = `${this.productsUrl}/add`;
        return this.http
            .post(url, body, { headers: this.headers })
            .toPromise()
            .then(res => res.json() as Product)
            .catch(this.handleError);
    }

    updateProduct(body: String): Promise<Product> {
        const url = `${this.productsUrl}/update`;
        return this.http
            .put(url, body, {headers: this.headers})
            .toPromise()
            .then(res => res.json() as Product)
            .catch(this.handleError);

    }
}
