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
var router_1 = require("@angular/router");
var product_service_1 = require("../API/product.service");
var ProductsComponent = (function () {
    function ProductsComponent(productService, router) {
        this.productService = productService;
        this.router = router;
    }
    ProductsComponent.prototype._getProducts = function () {
        return this.products;
    };
    ProductsComponent.prototype.allFieldsAreValid = function (product) {
        if ((product.description !== '' && product.description !== undefined)
            && (product.price.toString() !== '' && typeof +product.price === 'number') && !(isNaN(+product.price))
            && (product.quantity.toString() !== '' && typeof +product.quantity === 'number') && !(isNaN(+product.quantity))) {
            return true;
        }
        return false;
    };
    ProductsComponent.prototype.setMaxIndex = function () {
        var max = 0;
        this.products.forEach(function (product) {
            if (product.itemID > max) {
                max = product.itemID;
            }
        });
        this.index = max + 1;
    };
    ProductsComponent.prototype.getProducts = function () {
        var _this = this;
        this.productService.getProducts().then(function (products) { return _this.products = products; }).then(function (products) { return _this.setMaxIndex(); });
    };
    ProductsComponent.prototype.removeProduct = function (product) {
        var _this = this;
        this.productService
            .deleteProduct(product.itemID, product.productLinker)
            .then(function () {
            _this.products = _this.products.filter(function (p) { return p !== product; });
        });
    };
    ProductsComponent.prototype.saveProduct = function (product) {
        var _this = this;
        // il salvataggio lo faccio su ogni campo, 
        // anche se ne viene modificato uno, non è ottimizzato ma con così pochi campi direi che può andare
        var selectedProduct = this.products.find(function (item) { return item.itemID === product.itemID; });
        if (!this.allFieldsAreValid(selectedProduct)) {
            alert('some field has been left blank or contains illegal characters');
        }
        else {
            var body = JSON.stringify({
                itemID: selectedProduct.itemID,
                description: selectedProduct.description,
                price: selectedProduct.price,
                quantity: selectedProduct.quantity,
                discount: selectedProduct.discount,
                conditions: selectedProduct.conditions
            });
            this.productService.updateProduct(body).then(function (product) { return _this.disactivateSaveButton(product.itemID); });
        }
    };
    ProductsComponent.prototype.activateSaveButton = function (itemID) {
        document.getElementById('btn-save-prod-' + itemID).classList.remove('disabled');
    };
    ProductsComponent.prototype.disactivateSaveButton = function (itemID) {
        document.getElementById('btn-save-prod-' + itemID).classList.add('disabled');
    };
    ProductsComponent.prototype.ngOnInit = function () {
        this.getProducts();
    };
    return ProductsComponent;
}());
ProductsComponent = __decorate([
    core_1.Component({
        selector: 'product-tab',
        templateUrl: './products.component.html',
        styleUrls: ['./products.component.css']
    }),
    __metadata("design:paramtypes", [product_service_1.ProductService,
        router_1.Router])
], ProductsComponent);
exports.ProductsComponent = ProductsComponent;
//# sourceMappingURL=products.component.js.map