package com.knoldus.models

import java.sql.Date

case class EmployeeTransaction(empId: Int, itemId: Int, date: Date, quantity: Int, amount: Double, isPaid: Boolean = false)

