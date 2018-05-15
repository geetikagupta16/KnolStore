package com.knoldus.dao.components

import java.sql.Date

import com.knoldus.dao.connection.{ DBComponent, MySqlDbComponent }
import com.knoldus.models.EmployeeTransaction
import slick.jdbc.MySQLProfile.api._

trait EmployeeTransactionComponent extends EmployeeTransactionTable with DBComponent {

}

object EmployeeTransactionComponent extends EmployeeTransactionComponent with MySqlDbComponent

trait EmployeeTransactionTable {

  class EmployeeTransactionTable(tag: Tag) extends Table[EmployeeTransaction](tag, "employee_transaction") {

    def empId = column[Int]("emp_id")
    def itemId = column[Int]("item_id")
    def date = column[Date]("date")
    def quantity = column[Int]("quantity")
    def amount = column[Double]("amount")
    def isPaid = column[Boolean]("is_paid")

    def * = (empId, itemId, date, quantity, amount, isPaid) <> (EmployeeTransaction.tupled, EmployeeTransaction.unapply)

  }

  val employeeTransactionQuery = TableQuery[EmployeeTransactionTable]
}

