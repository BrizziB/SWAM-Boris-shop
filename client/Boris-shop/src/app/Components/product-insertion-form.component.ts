import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ProductsComponent } from './products.component';
import { ProductService } from '../API/product.service';


@Component({
    selector: 'product-insertion-tab',
    template: `<div id="main-form">
                    <h3 id="form-title"> Insert Product details </h3><br>
                    <label > product Description: </label><br>
                    <input [(ngModel)]="this.description" placeholder="description"><br>
                    
                    <label > product Price: </label><br>
                    <input [(ngModel)]="this.price" placeholder="price"> <label> € </label><br>
                    
                    <label > quantity in stock: </label><br>
                    <input  [(ngModel)]="this.quantity" placeholder="quantity">
                    
                    <label > Discont(optional, between 0 and 1): </label><br>
                    <input  [(ngModel)]="this.discount" placeholder="discount">
                    
                    <label > Conditions(only id reconditioned): </label><br>
                    <input  [(ngModel)]="this.conditions" placeholder="conditions">
                    
                    <br>
                    <button id="insert-product-ok" class="btn btn-info btn-sm form-btn"
                    (click)="addNewProduct(); closeForm();" > Send! </button> 
                    <button id="insert-product-back" class="btn btn-info btn-sm form-btn" (click)="closeForm();" > Cancel </button>
               </div>`,
    styleUrls: ['./insertion-form.component.css']
})
export class ProductInsertionFormComponent {
    itemID: number;
    description: String;
    price: String;
    quantity: number;
    discount: number;
    conditions: string;

    constructor(private productComponent: ProductsComponent,
        private productService: ProductService) { }

    closeForm() {
        this.productComponent.showForm = false;
    }

    allFieldsAreValid(): boolean {
        if ((this.description !== '' && this.description !== undefined)
            && (this.price.toString() !== '' && typeof +this.price === 'number' && !(isNaN(+this.price)) )
            && (this.quantity.toString() !== '' && typeof +this.quantity === 'number' && !(isNaN(+this.quantity)) )) {
            return true;
        }
        return false;
    }

    addNewProduct(): void {
        this.itemID = this.productComponent.index;

        if (!this.allFieldsAreValid()) {
            alert('some field has been left blank or contains illegal characters');
        }else {
            let body = JSON.stringify({
                itemID: this.itemID,
                description: this.description,
                price: this.price,
                quantity: this.quantity,
                discount: this.discount,
                conditions: this.conditions
            });

            this.productService.addNewProduct(body)
                .then( product => {this.productComponent.products.push(product);
                    this.productComponent.setMaxIndex();
                });
        }

    }
}
