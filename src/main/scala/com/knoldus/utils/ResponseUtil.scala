package com.knoldus.utils

import com.knoldus.models.EmployeeTransaction

object ResponseUtil {

  case class ItemJson(itemId: Int, itemName: String, price: Double, itemQuantity: Int, amount: Double)

  case class EmployeeTransactionJson(empId: Int, itemsPurchased: List[ItemJson], transactionDate: String, total: Double)

  case class ListEmployeeTransaction(list: List[EmployeeTransaction])

  case class EmployeeTransactionDetails(empId: Int, empName: String, totalAmount: Double, bill: List[BillDetails])

  case class ListTransaction(employeeTransactionDetails: EmployeeTransactionDetails)

  case class BillDetails(itemName: String, date: String, quantity: Int, price: Double, amount: Double)

  case class Response(message: String, status: Int)

}