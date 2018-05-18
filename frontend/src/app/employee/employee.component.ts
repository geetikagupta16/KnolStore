import { Component, OnInit } from '@angular/core';


import {EmployeeService} from "../employee.service";
import {Item, ItemDetails} from "../Item";
import {EmployeeDetails} from "../Employee";
import {EmployeeBill, ItemPurchased} from "./EmployeeBill";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  employeeInfo: EmployeeDetails[] = new Array<EmployeeDetails>();
  itemInfo: Item[] = [];
  values :boolean = false;
  quantityValue:number = 0;
   amountValue:number
  arr:boolean[] = new Array(15)
  quantity:number[]  = new Array(15)
 employeeBill:EmployeeBill;
   index:number
  selectedemployeeId: any;
   showTable:boolean = false;

  constructor(private employeeService: EmployeeService,public datepipe:DatePipe) { }

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


  calculateTotal() {
    for (let i = 0; i < this.itemInfo.length; i++) {
      let sum=0
      if (this.arr[i] === true) {
        sum = sum + (this.itemInfo[i].itemPrice*this.quantity[i]);
        }
    }
  }
  postItemInfo(eId){

     let itemToBePurchased: ItemPurchased[] = new Array<ItemPurchased>(this.itemInfo.length);
     for(let i = 0 ; i <this.itemInfo.length; i++)
     {  if(this.arr[i] === true){
       itemToBePurchased[i] = {
         itemId: this.itemInfo[i].itemId,
         itemName: this.itemInfo[i].itemName,
         price: this.itemInfo[i].itemPrice,
         itemQuantity:this.quantity[i],
         amount:this.itemInfo[i].itemPrice * this.quantity[i]

       };
     }
     }

     let employeeTransaction: EmployeeBill = {
       empId:this.employeeInfo[this.index].employeeId,
       transactionDate:this.datepipe.transform(new Date(), 'yyyy-MM-dd'),
       total:100,
       itemsPurchased:itemToBePurchased

    };
     console.log(employeeTransaction)

     this.employeeService.postUserBill(employeeTransaction).subscribe(data =>
     {alert("Form successfully Saved")},
       error => {alert("Something went wrong")});
  }
}
