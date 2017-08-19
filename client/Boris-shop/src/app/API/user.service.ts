import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { User } from '../Model/User';

import { USERS } from './mock.users';

@Injectable()
export class UserService {

    private headers = new Headers({ 'Content-Type': 'application/json' });
    private usersUrl = 'http://localhost:8080/boris-shop/users';

    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getUsers(): Promise<User[]> {
        return this.http.get(this.usersUrl + '/list')
            .toPromise()
            .then(response => response.json() as User[])
            .catch(this.handleError);
    }

    deleteUser(userID: number): Promise<void> {
        const url = `${this.usersUrl}/delete/${userID}`;
        return this.http.delete(url, { headers: this.headers })
            .toPromise()
            .then(() => null)
            .catch(this.handleError);
    }

    addNewUser(body: String): Promise<User> {
        const url = `${this.usersUrl}/add`;
        return this.http
            .post(url, body, { headers: this.headers })
            .toPromise()
            .then(res => res.json() as User)
            .catch(this.handleError);
    }

    updateUser(body: String): Promise<User> {
        const url = `${this.usersUrl}/update`;
        return this.http
            .put(url, body, { headers: this.headers })
            .toPromise()
            .then(res => res.json() as User)
            .catch(this.handleError);

    }
}
