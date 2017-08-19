"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var OrdersComponent = (function () {
    function OrdersComponent() {
    }
    OrdersComponent.prototype.getOrders = function () {
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
    })
], OrdersComponent);
exports.OrdersComponent = OrdersComponent;
//# sourceMappingURL=orders.component.js.map