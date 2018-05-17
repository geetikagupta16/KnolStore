import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {RouterModule} from "@angular/router";
import { EmployeeComponent } from './employee/employee.component';
import {EmployeeService} from "./employee/employee.service";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent
  ],
  imports: [
    BrowserModule,HttpClientModule,RouterModule.forRoot([{path:'buyproduct',component:EmployeeComponent}])
  ],
  providers: [EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
