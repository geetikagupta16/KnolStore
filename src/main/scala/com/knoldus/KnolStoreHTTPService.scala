package com.knoldus


import spray.json._

import scala.util.control.NonFatal

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.google.inject.Inject
import com.knoldus.dao.components.ItemComponent
import com.knoldus.models.{EmployeeTransaction, EmployeeTransactionJson, Item}
import scala.concurrent.ExecutionContext.Implicits.global

import com.knoldus.utils.{ErrorResponse, JsonSupport, SuccessResponse}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import com.knoldus.dao.components.{EmployeeTransactionComponent, ItemComponent}
import com.knoldus.models.Item
import com.knoldus.utils.ResponseUtil._
import com.knoldus.utils._

import com.knoldus.utils._
import scala.concurrent.Future
class KnolStoreHTTPService @Inject()(itemComponent: ItemComponent, employeeTransactionComponent: EmployeeTransactionComponent)
  extends JsonSupport {

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
            employeeTransactionComponent.getEmployeeTransaction(id).map {
              transactionDetails => ListTransaction(transactionDetails).toJson
            }
          }
        }
      }
    } ~ saveEmployeeTransaction()

  def saveEmployeeTransaction(): Route = {
    pathPrefix("/saveTransaction") {
      post {
        entity(as[EmployeeTransactionJson]) { employeeTransactionData =>
          complete {
            val empTransactions: List[EmployeeTransaction] = employeeTransactionData.itemsPurchased
              .map { itemPurchased =>
                EmployeeTransaction(employeeTransactionData.empId,
                  itemPurchased.itemId,
                  employeeTransactionData.transactionDate,
                  itemPurchased.itemQuantity,
                  itemPurchased.amount)
              }
            employeeTransactionComponent.addEmployeeTransaction(empTransactions).map{ response =>
              Response(SuccessResponse("Transaction for employee has  been saved successfully !!"))
            }.recover {
              case NonFatal(exception) => Response(ErrorResponse("Could not store data in database", StatusCodes.InternalServerError.intValue))
            }
          }
        }
      }
    }
  }

  def addItem: Route = ???
  post {
    entity(as[Item]) { itemData =>
      complete {
        itemComponent.addItem(itemData)
        itemData /*.map { result => HttpResponse(status = StatusCodes.Created.intValue, entity = HttpEntity(entity = SuccessResponse("Record Added successfully !!")))*/
      } /*.recover {
              case NonFatal(exception) => HttpResponse(status = StatusCodes.InternalServerError.intValue, entity = HttpEntity(ContentTypes.`application/json`, ErrorResponse("Could not get record from database", StatusCodes.InternalServerError.intValue)))
            }*/
    }
  }
  }

