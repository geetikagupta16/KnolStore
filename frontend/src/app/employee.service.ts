import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";

import {Observable} from "rxjs/Observable";
import {EmployeeData} from "./employee/EmployeeDetails";
import {ItemData} from "./employee/Item";


@Injectable()
export class EmployeeService {

  constructor(private http: HttpClient) { }

  getEmployeee(): Observable<EmployeeData> {
    //let getUrl = `${this.appService.akkaBaseUrl}getEmployee`
    let getUrl ='/assets/Item.json'
    return this.http.get<EmployeeData>(getUrl);
  }

  getItem(): Observable<ItemData> {
    //let getUrl = `${this.appService.akkaBaseUrl}getItem`
    let getUrl = '/assets/Employee.json'
    return this.http.get<ItemData>(getUrl);
  }
}
