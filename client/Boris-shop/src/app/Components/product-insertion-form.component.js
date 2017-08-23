"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var products_component_1 = require("./products.component");
var product_service_1 = require("../API/product.service");
var ProductInsertionFormComponent = (function () {
    function ProductInsertionFormComponent(productComponent, productService) {
        this.productComponent = productComponent;
        this.productService = productService;
    }
    ProductInsertionFormComponent.prototype.closeForm = function () {
        this.productComponent.showForm = false;
    };
    ProductInsertionFormComponent.prototype.allFieldsAreValid = function () {
        if ((this.description !== '' && this.description !== undefined)
            && (this.price.toString() !== '' && typeof +this.price === 'number' && !(isNaN(+this.price)))
            && (this.quantity.toString() !== '' && typeof +this.quantity === 'number' && !(isNaN(+this.quantity)))) {
            return true;
        }
        return false;
    };
    ProductInsertionFormComponent.prototype.addNewProduct = function () {
        var _this = this;
        this.itemID = this.productComponent.index;
        if (!this.allFieldsAreValid()) {
            alert('some field has been left blank or contains illegal characters');
        }
        else {
            var body = JSON.stringify({
                itemID: this.itemID,
                description: this.description,
                price: this.price,
                quantity: this.quantity
            });
            this.productService.addNewProduct(body)
                .then(function (product) { return _this.productComponent.products.push(product); });
        }
    };
    return ProductInsertionFormComponent;
}());
ProductInsertionFormComponent = __decorate([
    core_1.Component({
        selector: 'product-insertion-tab',
        template: "<div id=\"main-form\">\n                    <h3 id=\"form-title\"> Insert Product details </h3><br>\n                    <label > product Description: </label><br>\n                    <input [(ngModel)]=\"this.description\" placeholder=\"description\"><br>\n                    <label > product Price: </label><br>\n                    <input [(ngModel)]=\"this.price\" placeholder=\"price\"> <label> \u20AC </label><br>\n                    <label > quantity in stock: </label><br>\n                    <input  [(ngModel)]=\"this.quantity\" placeholder=\"quantity\">\n                    <br>\n                    <button id=\"insert-product-ok\" class=\"btn btn-info btn-sm form-btn\"\n                    (click)=\"addNewProduct(); closeForm();\" > Send! </button> \n                    <button id=\"insert-product-back\" class=\"btn btn-info btn-sm form-btn\" (click)=\"closeForm();\" > Cancel </button>\n               </div>",
        styleUrls: ['./insertion-form.component.css']
    }),
    __metadata("design:paramtypes", [products_component_1.ProductsComponent,
        product_service_1.ProductService])
], ProductInsertionFormComponent);
exports.ProductInsertionFormComponent = ProductInsertionFormComponent;
//# sourceMappingURL=product-insertion-form.component.js.map