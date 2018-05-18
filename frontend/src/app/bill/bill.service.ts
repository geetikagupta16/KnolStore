import { Injectable } from '@angular/core';

import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class BillService {

  constructor(private http: HttpClient) { }
  // getBill(employeeId: number): Observable<ItemData> {
  //   //let getUrl = `${this.appService.akkaBaseUrl}getItem`
  //   let getUrl = '/assets/Bill.json'
  //   return this.http.get<ItemData>(getUrl);
  // }

}
