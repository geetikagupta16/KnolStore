import { Component, OnInit } from '@angular/core';


import {EmployeeService} from "../employee.service";
import {ItemDetails} from "../Item";
import {EmployeeDetails} from "../Employee";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
   employee: EmployeeDetails[] = [];
   item: ItemDetails[] = [];
   values :boolean = false;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
  }

  getEmployeeInformation(){
    this.employeeService.getEmployeee().subscribe((data:any)=>{
      if(data.status === 201){
        this.employee = data.data;
      }
      else{
        alert("something went wrong");
      }
      },
        error => console.error(error));

  }

  getItemInformation(){
    this.employeeService.getItem().subscribe(
      (data:any) =>{
      if(data.status === 201){
        this.item
      }
      else{
        alert("Something went wrong");
      }
    },
      error => console.error(error))
  }
}
