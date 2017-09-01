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
var order_service_1 = require("../API/order.service");
var OrdersComponent = (function () {
    function OrdersComponent(orderService, router) {
        this.orderService = orderService;
        this.router = router;
    }
    OrdersComponent.prototype.setMaxIndex = function () {
        var max = 0;
        this.orders.forEach(function (order) {
            if (order.orderID > max) {
                max = order.orderID;
            }
        });
        this.index = max + 1;
    };
    OrdersComponent.prototype.advanceOrderStatus = function (order) {
        this.orderService.advanceOrderStatus(order.orderID).then(function (status) { return order.status = status; });
    };
    OrdersComponent.prototype.getOrders = function () {
        var _this = this;
        this.orderService.getOrders().then(function (orders) { return _this.orders = orders; }).then(function (orders) { return _this.setMaxIndex(); });
    };
    OrdersComponent.prototype.ngOnInit = function () {
        this.getOrders();
    };
    return OrdersComponent;
}());
OrdersComponent = __decorate([
    core_1.Component({
        selector: 'product-tab',
        templateUrl: './orders.component.html',
        styleUrls: ['./orders.component.css']
    }),
    __metadata("design:paramtypes", [order_service_1.OrderService,
        router_1.Router])
], OrdersComponent);
exports.OrdersComponent = OrdersComponent;
//# sourceMappingURL=orders.component.js.map