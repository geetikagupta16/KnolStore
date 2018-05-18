package com.knoldus

import java.sql.Date

import spray.json._
import scala.util.control.NonFatal

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.google.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

import akka.http.scaladsl.model.StatusCodes
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import com.knoldus.dao.components.{ EmployeeTransactionComponent, ItemComponent }
import com.knoldus.models.{ EmployeeTransaction, Item }
import com.knoldus.utils.ResponseUtil._
import com.knoldus.utils._
import scala.concurrent.Future
import java.text.SimpleDateFormat

import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import spray.json._
import com.knoldus.utils.JsonSupport

class KnolStoreHTTPService @Inject() (
  itemComponent: ItemComponent,
  employeeTransactionComponent: EmployeeTransactionComponent) extends JsonSupport {

  lazy val route: Route =
    cors() {
      pathPrefix("item") {
        addItem
      }
    } ~ employeeTransactions

  def employeeTransactions: Route =
    cors() {
      get {
        path("transactionDetails" / IntNumber) { id =>
          complete {
            employeeTransactionComponent.getEmployeeTransaction(id)
          }
        }
      }
    } ~ saveEmployeeTransaction()

  def saveEmployeeTransaction(): Route = cors() {
    pathPrefix("saveTransaction") {
      post {
        entity(as[EmployeeTransactionJson]) { employeeTransactionData =>
          complete {
            val format: SimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy")
            val empTransactions: List[EmployeeTransaction] = employeeTransactionData
              .itemsPurchased
              .map { itemPurchased =>
                EmployeeTransaction(
                  employeeTransactionData.empId,
                  itemPurchased.itemId,
                  new Date(format.parse(employeeTransactionData.transactionDate).getTime),
                  itemPurchased.itemQuantity,
                  itemPurchased.amount)
              }
            employeeTransactionComponent.addEmployeeTransaction(empTransactions).map { response =>
              Response(
                "Transaction for employee has been saved successfully !!",
                StatusCodes.InternalServerError.intValue)
            }.recover {
              case NonFatal(exception) =>
                exception.printStackTrace()
                Response(
                  "Could not store data in database",
                  StatusCodes.InternalServerError.intValue)
            }
          }
        }
      }
    }
  }

  def addItem: Route = cors() {
    post {
      entity(as[Item]) { itemData =>
        complete {
          itemComponent.addItem(itemData)
            .map { result =>
              Response(
                "Record Added successfully !!",
                StatusCodes.InternalServerError.intValue)
            }.recover {
              case NonFatal(exception) =>
                exception.printStackTrace()
                Response(
                  "Could not get record from database",
                  StatusCodes.InternalServerError.intValue)
            }
        }
      }
    }
  }
}

