import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {RouterModule} from "@angular/router";
import { EmployeeComponent } from './employee/employee.component';
import {HttpClientModule} from "@angular/common/http";
import { BillComponent } from './bill/bill.component';
import {EmployeeService} from "./employee.service";
import { ItemManagementComponent } from './item-management/item-management.component';


@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    BillComponent,
    ItemManagementComponent
  ],
  imports: [
    BrowserModule,HttpClientModule,RouterModule.forRoot([{path:'buyproduct',component:EmployeeComponent}])
  ],
  providers: [EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
