import { Component, OnInit } from '@angular/core';
import {EmployeeTransactionData} from "./EmployeeTransaction";

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {
  employeeTransaction: EmployeeTransactionData;

  constructor() { }

  ngOnInit() {
  }
  getEmployeeBil(){


  }

}
