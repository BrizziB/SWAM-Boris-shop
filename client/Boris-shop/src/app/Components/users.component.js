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
var user_service_1 = require("../API/user.service");
var UsersComponent = (function () {
    function UsersComponent(userService, router) {
        this.userService = userService;
        this.router = router;
        this.showForm = false;
    }
    UsersComponent.prototype.allFieldsAreValid = function (user) {
        if ((user.username !== '' && user.username !== undefined)
            && (user.password !== '' && user.password !== undefined)) {
            return true;
        }
        return false;
    };
    UsersComponent.prototype.setMaxIndex = function () {
        var max = 0;
        this.users.forEach(function (user) {
            if (user.userID > max) {
                max = user.userID;
            }
        });
        this.index = max + 1;
    };
    UsersComponent.prototype.getUsers = function () {
        var _this = this;
        this.userService.getUsers().then(function (users) { return _this.users = users; }).then(function (users) { return _this.setMaxIndex(); });
    };
    UsersComponent.prototype.removeUser = function (user) {
        var _this = this;
        this.userService
            .deleteUser(user.userID)
            .then(function () {
            _this.users = _this.users.filter(function (u) { return u !== user; });
        });
    };
    UsersComponent.prototype.saveUser = function (user) {
        var _this = this;
        // il salvataggio lo faccio su ogni campo, 
        // anche se ne viene modificato uno, non è ottimizzato ma con così pochi campi direi che può andare
        var selectedProduct = this.users.find(function (item) { return item.userID === user.userID; });
        if (!this.allFieldsAreValid(selectedProduct)) {
            alert('some field has been left blank or contains illegal characters');
        }
        else {
            var body = JSON.stringify({
                userID: selectedProduct.userID,
                username: selectedProduct.username,
                password: selectedProduct.password
            });
            this.userService.updateUser(body).then(function (user) { return _this.disactivateSaveButton(user.userID); });
        }
    };
    UsersComponent.prototype.activateSaveButton = function (userID) {
        document.getElementById('btn-save-user-' + userID).classList.remove('disabled');
    };
    UsersComponent.prototype.disactivateSaveButton = function (userID) {
        document.getElementById('btn-save-user-' + userID).classList.add('disabled');
    };
    UsersComponent.prototype.ngOnInit = function () {
        this.getUsers();
    };
    return UsersComponent;
}());
UsersComponent = __decorate([
    core_1.Component({
        selector: 'user-tab',
        templateUrl: './users.component.html',
        styleUrls: ['./users.component.css']
    }),
    __metadata("design:paramtypes", [user_service_1.UserService,
        router_1.Router])
], UsersComponent);
exports.UsersComponent = UsersComponent;
//# sourceMappingURL=users.component.js.map