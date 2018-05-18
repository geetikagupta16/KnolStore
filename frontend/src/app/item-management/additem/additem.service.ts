import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AddItem} from "./AddItem";
import {Observable} from "rxjs/Observable";
import {EmployeeService} from "../../employee.service";


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'my-auth-token'
  })
}
@Injectable()
export class AdditemService {

  constructor(private appService: EmployeeService,private http:HttpClient) { }

    postItemInformation(productInformation: AddItem[]):Observable<any> {
    console.log(productInformation)

      let postUrl = `${this.appService.akkaBaseUrl}addItem`;

    return this.http.post<AddItem[]>(postUrl, productInformation, httpOptions);
  }
}
