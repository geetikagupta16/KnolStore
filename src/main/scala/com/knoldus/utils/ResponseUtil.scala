package com.knoldus.utils

object ResponseUtil {

  case class EmployeeTransaction(empId: Int, itemId: Int, date: String, quantity: Int, amount: Double, isPaid: Boolean = false)

  case class ItemJson(itemId: Int, itemName: String, price: Double, itemQuantity: Int, amount: Double)

  case class EmployeeTransactionJson(empId: Int, itemsPurchased: List[ItemJson], transactionDate: String, total: Double)

  case class ListEmployeeTransaction(list: List[EmployeeTransaction])

  case class EmployeeTransactionDetails(empId: Int, empName: String, totalAmount: Double, bill: List[BillDetails])

  case class ListTransaction(employeeTransactionDetails: List[EmployeeTransactionDetails])

  case class BillDetails(itemName: String, date: String, quantity: Int, price: Double, amount: Double)

}