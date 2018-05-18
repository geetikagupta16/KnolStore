package com.knoldus

import java.sql.Date
import java.text.SimpleDateFormat

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import com.google.inject.Inject
import com.knoldus.dao.components.{EmployeeDetailComponent, EmployeeTransactionComponent, ItemComponent}
import com.knoldus.models.{EmployeeList, EmployeeTransaction, Item, ItemsList}
import com.knoldus.utils.JsonSupport
import com.knoldus.utils.ResponseUtil._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal

class KnolStoreHTTPService @Inject()(
                                      itemComponent: ItemComponent,
                                      employeeTransactionComponent: EmployeeTransactionComponent,
                                      employeeComponent: EmployeeDetailComponent) extends JsonSupport {

  lazy val route: Route =
    cors() {
      pathPrefix("item") {
        addItem ~ getItems ~ updateItems
      } ~
      pathPrefix("employee") {
        getEmployees ~ approveTransaction
      }
    } ~ employeeTransactions ~ saveEmployeeTransaction()

  def employeeTransactions: Route =
    cors() {
      get {
        path("transactionDetails" / IntNumber) { id =>
          complete {
            employeeTransactionComponent.getEmployeeTransaction(id)
          }
        }
      }
    }

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

  def addItem: Route =
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

  def getItems: Route =
    get {
      path("getItems") {
        complete {
          itemComponent.getAllItems.map {
            itemsList => ItemsList(itemsList)
          }
        }
      }
    }

  def updateItems: Route =
    pathPrefix("updateItem" / IntNumber) { id => {
      put {
        entity(as[Item]) { item => {
          complete {
            itemComponent.updateItem(id, item).map {
              case _ => Response("item updated successfully", StatusCodes.Created.intValue)
            }
          }
        }

        }
      }
    }
    }

  def getEmployees: Route =
    get {
      path("getEmployees") {
        complete {
          employeeComponent.getAllEmployee.map {
            employeeList => EmployeeList(employeeList)
          }
        }
      }
    }

  def approveTransaction: Route =
    get {
      path("approveTransaction" / IntNumber) { empId =>
        complete {
          employeeTransactionComponent.approveEmployeeTransaction(empId).map {
            _ => Response("transactions approved successfully", StatusCodes.Created.intValue)
          }
        }
      }
    }

}

