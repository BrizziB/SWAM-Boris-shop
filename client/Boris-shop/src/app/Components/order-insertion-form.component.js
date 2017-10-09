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
var orders_component_1 = require("./orders.component");
var order_service_1 = require("../API/order.service");
var user_service_1 = require("../API/user.service");
var product_service_1 = require("../API/product.service");
var OrderInsertionFormComponent = (function () {
    function OrderInsertionFormComponent(ordersComponent, orderService, productService, userService) {
        this.ordersComponent = ordersComponent;
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.showUserList = false;
        this.showProductList = false;
    }
    OrderInsertionFormComponent.prototype.closeForm = function () {
        this.ordersComponent.showForm = false;
    };
    OrderInsertionFormComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.productService.getProducts().then(function (products) { return _this.allProducts = products; });
        this.userService.getUsers().then(function (users) { return _this.allUsers = users; });
        this.items = [];
    };
    OrderInsertionFormComponent.prototype.registerUser = function (user) {
        this.buyer = user;
    };
    OrderInsertionFormComponent.prototype.updateProductList = function (product) {
        if (document.getElementById('chkbx-' + product.itemID).checked === true) {
            this.items.push(product);
        }
        else {
            var index = this.items.indexOf(product);
            if (index !== -1) {
                this.items.splice(index, 1);
            }
        }
    };
    OrderInsertionFormComponent.prototype.addNewOrder = function () {
        var _this = this;
        this.orderID = this.ordersComponent.index;
        if (this.buyer === undefined || this.items.length === 0) {
            alert('select the buyer and at least one product');
        }
        else {
            this.items.forEach(function (item) {
                var body = JSON.stringify({
                    buyer: _this.buyer,
                    item: item
                });
                _this.orderService.addNewOrder(body)
                    .then(function (order) { return _this.ordersComponent.orders.push(order); });
            });
        }
    };
    return OrderInsertionFormComponent;
}());
OrderInsertionFormComponent = __decorate([
    core_1.Component({
        selector: 'order-insertion-tab',
        templateUrl: './order-insertion-form.component.html',
        styleUrls: ['./insertion-form.component.css']
    }),
    __metadata("design:paramtypes", [orders_component_1.OrdersComponent,
        order_service_1.OrderService,
        product_service_1.ProductService,
        user_service_1.UserService])
], OrderInsertionFormComponent);
exports.OrderInsertionFormComponent = OrderInsertionFormComponent;
//# sourceMappingURL=order-insertion-form.component.js.map