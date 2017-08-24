import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../Model/User';
import { UserService } from '../API/user.service';

@Component({
    selector: 'user-tab',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
    users: User[];
    index: number;
    showForm = false;
    constructor(
        private userService: UserService,
        private router: Router) { }


    _getUsers(): User[] {
        return this.users;
    }

    allFieldsAreValid(user: User): boolean {
        if ((user.username !== '' && user.username !== undefined)
            && (user.password !== '' && user.password !== undefined)) {
            return true;
        }
        return false;
    }

    setMaxIndex(): void {
        let max = 0;
        this.users.forEach(user => {
            if (user.userID > max) {
                max = user.userID;
            }
        });
        this.index = max + 1;
    }

    getUsers(): void {
        this.userService.getUsers().then(users => this.users = users).then(users => this.setMaxIndex());
    }

    removeUser(user: User): void {
        this.userService
            .deleteUser(user.userID)
            .then(() => {
                this.users = this.users.filter(u => u !== user);
            });
    }

    saveUser(user: User): void {
        // il salvataggio lo faccio su ogni campo, anche se ne viene modificato uno.
        //  non è ottimizzato ma con così pochi campi direi che può andare
        let selectedUser = this.users.find(item => item.userID === user.userID);

        if (!this.allFieldsAreValid(selectedUser)) {
            alert('some field has been left blank or contains illegal characters');
        } else {
            let body = JSON.stringify({
                userID: selectedUser.userID,
                username: selectedUser.username,
                password: selectedUser.password
            });

            this.userService.updateUser(body).then(user => this.disactivateSaveButton(user.userID));
        }
    }

    activateSaveButton(userID: number): void {
        document.getElementById('btn-save-user-' + userID).classList.remove('disabled');
    }
    disactivateSaveButton(userID: number): void {
        document.getElementById('btn-save-user-' + userID).classList.add('disabled');
    }

    ngOnInit(): void {
        this.getUsers();
    }

}
