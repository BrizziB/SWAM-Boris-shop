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
var users_component_1 = require("./users.component");
var user_service_1 = require("../API/user.service");
var UserInsertionFormComponent = (function () {
    function UserInsertionFormComponent(usersComponent, userService) {
        this.usersComponent = usersComponent;
        this.userService = userService;
    }
    UserInsertionFormComponent.prototype.allFieldsAreValid = function () {
        if ((this.username !== '' && this.username !== undefined)
            && (this.password !== '' && this.password !== undefined)) {
            return true;
        }
        return false;
    };
    UserInsertionFormComponent.prototype.closeForm = function () {
        this.usersComponent.showForm = false;
    };
    UserInsertionFormComponent.prototype.addNewUser = function () {
        var _this = this;
        this.userID = this.usersComponent.index;
        if (!this.allFieldsAreValid()) {
            alert('some field has been left blank or contains illegal characters');
        }
        else {
            var body = JSON.stringify({
                userID: this.userID,
                username: this.username,
                password: this.password
            });
            this.userService.addNewUser(body)
                .then(function (user) { return _this.usersComponent.users.push(user); });
        }
    };
    return UserInsertionFormComponent;
}());
UserInsertionFormComponent = __decorate([
    core_1.Component({
        selector: 'user-insertion-tab',
        template: "<div id=\"main-form\">\n                    <h3 id=\"form-title\"> Insert User details </h3><br>\n                    <label > Username: </label><br>\n                    <input [(ngModel)]=\"this.username\" placeholder=\"username\"><br>\n                    <label > Password: </label><br>\n                    <input [(ngModel)]=\"this.password\" placeholder=\"password\"> <br>\n                    <br>\n                    <button id=\"insert-user-ok\" class=\"btn btn-info btn-sm form-btn\"\n                    (click)=\"addNewUser(); closeForm();\" > Send! </button> \n                    <button id=\"insert-user-back\" class=\"btn btn-info btn-sm form-btn\" (click)=\"closeForm();\" > Cancel </button>\n               </div>",
        styleUrls: ['./insertion-form.component.css']
    }),
    __metadata("design:paramtypes", [users_component_1.UsersComponent,
        user_service_1.UserService])
], UserInsertionFormComponent);
exports.UserInsertionFormComponent = UserInsertionFormComponent;
//# sourceMappingURL=user-insertion-form.component.js.map