package com.knoldus


import spray.json._

import scala.util.control.NonFatal
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.google.inject.Inject
import com.knoldus.dao.components.{EmployeeTransactionComponent, ItemComponent}
import com.knoldus.models.Item
import com.knoldus.utils.ResponseUtil._
import com.knoldus.utils._

import scala.concurrent.ExecutionContext.Implicits.global
import com.knoldus.utils._

class KnolStoreHTTPService @Inject()(itemComponent: ItemComponent, employeeTransactionComponent: EmployeeTransactionComponent)
  extends JsonSupport {

  lazy val route: Route = pathSingleSlash {
      get {
        complete {
          "Hello world"
        }
      }
    } ~
      get {
        path("transactionDetails" / IntNumber) { id =>
          complete {
           employeeTransactionComponent.getEmployeeTransaction(id).map {
             transactionDetails => ListTransaction(transactionDetails).toJson
           }
          }
        }
      }
}

