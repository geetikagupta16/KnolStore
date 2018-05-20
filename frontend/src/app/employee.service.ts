import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from "@angular/common/http";

import {Observable} from "rxjs/Observable";

import {EmployeeBill} from "./employee/EmployeeBill";
import {Item, ItemDetails} from "./Item";
import {Employee} from "./bill/EmployeeTransaction";


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'my-auth-token'
  })
};

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

  postUserBill(result:EmployeeBill):Observable<any>{
    let getUrl = `${this.akkaBaseUrl}saveTransaction`;
    return this.http.post<EmployeeBill>(getUrl,result,httpOptions);
  }

  // getUserBill(id: number): Observable<> {
  //   let getUrl = `${this.akkaBaseUrl}transactionDetails/${id}`;
  //   return this.http.get<>(getUrl);
  // }
}
