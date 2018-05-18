package com.knoldus.utils

//#json-support
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.models._
import com.knoldus.{User, Users}
import spray.json.DefaultJsonProtocol
import com.knoldus.utils.ResponseUtil._

trait JsonSupport extends SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._

  implicit val billDetails = jsonFormat5(BillDetails)
  implicit val employeeTransactionDetails = jsonFormat4(EmployeeTransactionDetails)
  implicit val items = jsonFormat3(Item)
  implicit val itemJson = jsonFormat5(ItemJson)
  implicit val employeeTransactionJson = jsonFormat4(EmployeeTransactionJson)
  implicit val listTransaction = jsonFormat1(ListTransaction)
  implicit val employeeTransaction = jsonFormat6(EmployeeTransaction)
  implicit val listEmployeeTransaction = jsonFormat1(ListEmployeeTransaction)
  implicit val itemRequestJson = jsonFormat5(ItemJson)
  implicit val userJsonFormat = jsonFormat3(User)
  implicit val usersJsonFormat = jsonFormat1(Users)
//  implicit val successJson = jsonFormat1(SuccessResponse)
//  implicit val errorJson = jsonFormat2(ErrorResponse)
}
//#json-support
