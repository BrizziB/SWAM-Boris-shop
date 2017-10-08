import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../Model/Product';
import { ProductService } from '../API/product.service';

@Component({
    selector: 'product-tab',
    templateUrl: './products.component.html',
    styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
    products: Product[];
    index: number;
    showForm: false;
    constructor(
        private productService: ProductService,
        private router: Router) { }

    _getProducts(): Product[] {
        return this.products;
    }

    allFieldsAreValid(product: Product): boolean {
        if ((product.description !== '' && product.description !== undefined)
            && (product.price.toString() !== '' && typeof +product.price === 'number') && !(isNaN(+product.price))
            && (product.quantity.toString() !== '' && typeof +product.quantity === 'number') && !(isNaN(+product.quantity))) {
            return true;
        }
        return false;
    }

    setMaxIndex(): void {
        let max = 0;
        this.products.forEach(product => {
            if (product.itemID > max) {
                max = product.itemID;
            }
        });
        this.index = max + 1;
    }

    getProducts(): void {
        this.productService.getProducts().then(products => this.products = products).then(products => this.setMaxIndex());
    }

    removeProduct(product: Product): void {
        this.productService
            .deleteProduct(product.itemID, product.productLinker)
            .then(() => {
                this.products = this.products.filter(p => p !== product);
            });
    }
    saveProduct(product: Product): void {
        // il salvataggio lo faccio su ogni campo, 
        // anche se ne viene modificato uno, non è ottimizzato ma con così pochi campi direi che può andare
        let selectedProduct = this.products.find(item => item.itemID === product.itemID);

        if (!this.allFieldsAreValid(selectedProduct)) {
            alert('some field has been left blank or contains illegal characters');
        } else {
            let body = JSON.stringify({
                itemID: selectedProduct.itemID,
                description: selectedProduct.description,
                price: selectedProduct.price,
                quantity: selectedProduct.quantity,
                discount: selectedProduct.discount,
                conditions: selectedProduct.conditions
            });

            this.productService.updateProduct(body).then(product => this.disactivateSaveButton(product.itemID));
        }
    }

    activateSaveButton(itemID: number): void {
        document.getElementById('btn-save-prod-' + itemID).classList.remove('disabled');
    }
    disactivateSaveButton(itemID: number): void {
        document.getElementById('btn-save-prod-' + itemID).classList.add('disabled');
    }

    ngOnInit(): void {
        this.getProducts();
    }

}
