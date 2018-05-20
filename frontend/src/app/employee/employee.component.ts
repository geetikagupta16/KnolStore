import { Component, OnInit } from '@angular/core';


import {EmployeeService} from "../employee.service";
import {EmployeeBill, ItemPurchased} from "./EmployeeBill";
import {DatePipe} from "@angular/common";
import {Employee, EmployeeDetails} from "../Employee";
import {Item, ItemDetails} from "../Item";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  employeeInfo: Employee[] = new Array<Employee>();
  itemInfo: ItemDetails[] = [];
  values :boolean = false;
  quantityValue:number = 0;
   amountValue:number
  arr:boolean[] = new Array(15)
  quantity:number[]  = new Array(15)
 employeeBill:EmployeeBill;
   index:number
  selectedemployeeId: any[] = [];
   selectedItem:any[] = [];
   //showTable:boolean = false;

  constructor(private employeeService: EmployeeService,public datepipe:DatePipe) { }

  ngOnInit() {
    this.getEmployeeInformation();
    this.getItemInformation();
  }
 // data => this.employees = data

  getEmployeeInformation(){
    this.employeeService.getEmployeee().subscribe((data:any)=>{
           // console.log(" " +data.employees)
         //this.employeeInfo.push(data.employees);
     this.employeeInfo = data.employees
      this.selectedemployeeId = data.employees
          console.log(this.employeeInfo)

      },
      error => console.error(error));

  }

  getItemInformation(){
    this.employeeService.getItem().subscribe(
      (data:any) =>{

          //this.itemInfo.push(data)
        //  this.itemInfo.push(data.data.item)
        this.itemInfo = data.items
        this.selectedItem = data.items
          console.log(this.itemInfo)

      },
      error => console.error(error))
  }
abc(){
    console.log(this.selectedemployeeId[this.index].empId);
}
/*
  calculateTotal() {
    for (let i = 0; i < this.itemInfo.length; i++) {
      let sum=0
      if (this.arr[i] === true) {
        sum = sum + (this.itemInfo[i].itemPrice*this.quantity[i]);
        }
    }
  }*/
  postItemInfo(){

     let itemToBePurchased: ItemPurchased[] = new Array<ItemPurchased>(this.itemInfo.length);
     for(let i = 0 ; i <this.itemInfo.length; i++)
     {  if(this.arr[i] === true){
       itemToBePurchased[i] = {
         itemId: this.selectedItem[i].itemId,
         itemName: this.selectedItem[i].itemName,
         price: this.selectedItem[i].price,
         itemQuantity:this.quantity[i],
         amount:this.selectedItem[i].price * this.quantity[i]

       };
     }
     }

     let employeeTransaction: EmployeeBill = {
      empId:this.selectedemployeeId[this.index].empId,
       itemsPurchased:itemToBePurchased,
       transactionDate:this.datepipe.transform(new Date(), 'yyyy-MM-dd'),
       total:100
    };
     console.log(employeeTransaction)

     this.employeeService.postUserBill(employeeTransaction).subscribe(data =>
     {alert("Form successfully Saved")},
       error => {alert("Something went wrong")});
  }
}
