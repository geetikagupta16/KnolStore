import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";

import {Observable} from "rxjs/Observable";

import {EmployeeBill} from "./employee/EmployeeBill";
import {Item, ItemDetails} from "./Item";
import {Employee} from "./bill/EmployeeTransaction";




@Injectable()
export class EmployeeService {
  akkaBaseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getEmployeee(): Observable<Employee[]> {
    //`${this.appService.akkaBaseUrl}item/addItem`;
    let getUrl =`${this.akkaBaseUrl}employee/getEmployees`
    //let getUrl = '/assets/Employee.json'
    return this.http.get<Employee[]>(getUrl);
  }

  getItem(): Observable<ItemDetails[]> {

    let getUrl = `${this.akkaBaseUrl}item/getItems`
    //let getUrl = '/assets/Item.json'
    return this.http.get<ItemDetails[]>(getUrl);
  }

  postUserBill(result:EmployeeBill):Observable<EmployeeBill>{
    let getUrl = `${this.akkaBaseUrl}saveTransaction`
    return this.http.get<EmployeeBill>(getUrl);
  }
}
