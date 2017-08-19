"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var forms_1 = require("@angular/forms");
var http_1 = require("@angular/http");
var app_component_1 = require("./Components/app.component");
var products_component_1 = require("./Components/products.component");
var users_component_1 = require("./Components/users.component");
var orders_component_1 = require("./Components/orders.component");
var product_insertion_form_component_1 = require("./Components/product-insertion-form.component");
var user_insertion_form_component_1 = require("./Components/user-insertion-form.component");
var product_service_1 = require("./API/product.service");
var user_service_1 = require("./API/user.service");
var order_service_1 = require("./API/order.service");
var app_routing_module_1 = require("./app-routing.module");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [
            platform_browser_1.BrowserModule,
            forms_1.FormsModule,
            app_routing_module_1.AppRoutingModule,
            http_1.HttpModule
        ],
        declarations: [
            app_component_1.AppComponent,
            products_component_1.ProductsComponent,
            users_component_1.UsersComponent,
            orders_component_1.OrdersComponent,
            product_insertion_form_component_1.ProductInsertionFormComponent,
            user_insertion_form_component_1.UserInsertionFormComponent
        ],
        providers: [product_service_1.ProductService, user_service_1.UserService, order_service_1.OrderService],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map