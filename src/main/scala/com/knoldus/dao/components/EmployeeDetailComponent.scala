package com.knoldus.dao.components
import com.knoldus.dao.connection.{DBComponent, MySqlDbComponent}
import com.knoldus.models.Employee
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

trait EmployeeDetailComponent extends EmployeeTable with DBComponent {

  def getAllEmployee: Future[List[Employee]] = {
    db.run(employeeQuery.to[List].result)
  }

}

object EmployeeDetailComponent extends EmployeeDetailComponent with MySqlDbComponent

trait EmployeeTable {

  class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee_details") {

    def empId = column[Int]("emp_id", O.PrimaryKey)
    def empName = column[String]("emp_name")

    def * = (empId, empName) <> (Employee.tupled, Employee.unapply)
  }

  val employeeQuery = TableQuery[EmployeeTable]
}
