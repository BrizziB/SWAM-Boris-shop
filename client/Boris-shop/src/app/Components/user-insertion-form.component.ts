import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UsersComponent } from './users.component';
import { UserService } from '../API/user.service';


@Component({
    selector: 'user-insertion-tab',
    template: `<div id="main-form">
                    <h3 id="form-title"> Insert User details </h3><br>
                    <label > Username: </label><br>
                    <input [(ngModel)]="this.username" placeholder="username"><br>
                    <label > Password: </label><br>
                    <input [(ngModel)]="this.password" placeholder="password"> <br>
                    <br>
                    <button id="insert-user-ok" class="btn btn-info btn-sm form-btn"
                    (click)="addNewUser(); closeForm();" > Send! </button> 
                    <button id="insert-user-back" class="btn btn-info btn-sm form-btn" (click)="closeForm();" > Cancel </button>
               </div>`,
    styleUrls: ['./insertion-form.component.css']
})
export class UserInsertionFormComponent {
    userID: number;
    username: String;
    password: String;

    constructor(private usersComponent: UsersComponent,
        private userService: UserService) { }

    allFieldsAreValid(): boolean {
        if ((this.username !== '' && this.username !== undefined)
            && (this.password !== '' && this.password !== undefined)) {
            return true;
        }
        return false;
    }

    closeForm() {
        this.usersComponent.showForm = false;
    }

    addNewUser() {
        this.userID = this.usersComponent.index;
        if (!this.allFieldsAreValid()) {
            alert('some field has been left blank or contains illegal characters');
        } else {
            let body = JSON.stringify({
                userID: this.userID,
                username: this.username,
                password: this.password
            });

            this.userService.addNewUser(body)
                .then(user => this.usersComponent.users.push(user));
        }
    }
}
