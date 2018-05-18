import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {RouterModule} from "@angular/router";
import { EmployeeComponent } from './employee/employee.component';
import {HttpClientModule} from "@angular/common/http";
import { BillComponent } from './bill/bill.component';
import {EmployeeService} from "./employee.service";
import { ItemManagementComponent } from './item-management/item-management.component';
import { AdditemComponent } from './item-management/additem/additem.component';
import {FormsModule} from "@angular/forms";
import {AdditemService} from "./item-management/additem/additem.service";


@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    BillComponent,
    ItemManagementComponent,
    AdditemComponent
  ],
  imports: [
    BrowserModule,FormsModule,HttpClientModule,RouterModule.forRoot(
      [
        {path:'buyproduct',
          component:EmployeeComponent
        },
        {
          path :'items-management',
          component: ItemManagementComponent,
          children: [
            {
              path: 'add-item',
              component: AdditemComponent
            }
          ]
        }
        ]
    )
  ],
  providers: [EmployeeService,AdditemService],
  bootstrap: [AppComponent]
})
export class AppModule { }
