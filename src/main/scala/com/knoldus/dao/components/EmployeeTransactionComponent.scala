package com.knoldus.dao.components

import java.sql.Date

import com.knoldus.dao.connection.{ DBComponent, MySqlDbComponent }
import com.knoldus.utils.ResponseUtil._
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.knoldus.models.EmployeeTransaction

trait EmployeeTransactionComponent extends EmployeeTransactionTable with DBComponent with EmployeeDetailComponent with ItemComponent {

  def addEmployeeTransaction(transaction: List[EmployeeTransaction]): Future[Option[Int]] = {
    db.run(employeeTransactionQuery ++= transaction)
  }

  def getEmployeeTransaction(empId: Int): Future[EmployeeTransactionDetails] = {

    db.run(employeeTransactionQuery.filter(empTransaction => empTransaction.empId === empId && !empTransaction.isPaid)
      .join(employeeQuery).on((EmployeeTransaction, Employee) => EmployeeTransaction.empId === Employee.empId)
      .join(itemQuery).on((EmployeeTransaction, Item) => EmployeeTransaction._1.itemId === Item.itemId).to[List].result)
      .map { record =>
        record.groupBy(_._1._2.empId).map {
          innerRecord =>
            val (id, list) = innerRecord
            val listOfBill = list.map(bill => BillDetails(bill._2.itemName, bill._1._1.date.toString, bill._1._1.quantity, bill._2.price, bill._1._1.amount))
            val totalAmount = listOfBill.map(_.amount).sum
            EmployeeTransactionDetails(id, list.map(_._1._2.empName).head, totalAmount, listOfBill)
        }.toList.head
      }
  }

}

object EmployeeTransactionComponent extends EmployeeTransactionComponent with MySqlDbComponent

trait EmployeeTransactionTable {

  val employeeTransactionQuery = TableQuery[EmployeeTransactionTable]

  class EmployeeTransactionTable(tag: Tag) extends Table[EmployeeTransaction](tag, "employee_transaction") {

    def * = (empId, itemId, date, quantity, amount, isPaid) <> (EmployeeTransaction.tupled, EmployeeTransaction.unapply)

    def empId = column[Int]("emp_id")

    def itemId = column[Int]("item_id")

    def date = column[Date]("transaction_date")

    def quantity = column[Int]("quantity")

    def amount = column[Double]("amount")

    def isPaid = column[Boolean]("is_paid")

  }

}

