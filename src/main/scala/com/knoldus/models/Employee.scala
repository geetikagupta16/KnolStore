package com.knoldus.models

case class Employee(empId: Int, empName: String)

case class EmployeeList(employees: List[Employee])
