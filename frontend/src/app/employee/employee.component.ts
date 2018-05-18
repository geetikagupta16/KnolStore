import { Component, OnInit } from '@angular/core';


import {EmployeeService} from "../employee.service";
import {Item, ItemDetails} from "../Item";
import {EmployeeDetails} from "../Employee";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  employeeInfo: EmployeeDetails[] = new Array<EmployeeDetails>();
  itemInfo: ItemDetails[] = [];
  values :boolean = false;
  quantityValue:number = 0;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.getEmployeeInformation();
    this.getItemInformation();
  }

  getEmployeeInformation(){
    this.employeeService.getEmployeee().subscribe((data:any)=>{
        if(data.status === 201){
          this.employeeInfo.push(data.data.employee);
          console.log(this.employeeInfo)
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
          this.itemInfo = data.data.items
        //  this.itemInfo.push(data.data.item)
          console.log(this.itemInfo)
        }
        else{
          alert("Something went wrong");
        }
      },
      error => console.error(error))
  }
}
