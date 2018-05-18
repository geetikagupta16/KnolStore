import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";

import {Observable} from "rxjs/Observable";
import {EmployeeData} from "./Employee";
import {ItemData} from "./Item";
import {EmployeeBill} from "./employee/EmployeeBill";




@Injectable()
export class EmployeeService {
  akkaBaseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getEmployeee(): Observable<EmployeeData> {
    `${this.appService.akkaBaseUrl}item/addItem`;
    let getUrl =`${this.akkaBaseUrl}employee/getEmployees`
    //let getUrl = '/assets/Employee.json'
    return this.http.get<EmployeeData>(getUrl);
  }

  getItem(): Observable<ItemData> {

    let getUrl = `${this.akkaBaseUrl}/items/getItems`
    //let getUrl = '/assets/Item.json'
    return this.http.get<ItemData>(getUrl);
  }

  postUserBill(result:EmployeeBill):Observable<EmployeeBill>{
    let getUrl = `${this.akkaBaseUrl}saveTransaction`
    return this.http.get<EmployeeBill>(getUrl);
  }
}
