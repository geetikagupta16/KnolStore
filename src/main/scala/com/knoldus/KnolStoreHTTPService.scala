package com.knoldus

import scala.util.control.NonFatal

import akka.http.scaladsl.model.{ ContentTypes, HttpEntity, HttpResponse, StatusCodes }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.google.inject.Inject
import com.knoldus.dao.components.ItemComponent
import com.knoldus.models.Item
import scala.concurrent.ExecutionContext.Implicits.global

import com.knoldus.utils.{ ErrorResponse, JsonSupport, SuccessResponse }

class KnolStoreHTTPService @Inject() (itemComponent: ItemComponent) extends JsonSupport {

  lazy val route: Route =
    pathSingleSlash {
      get {
        complete {
          "Hello world"
        }
      }
    } ~ pathPrefix("addItem") {
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

}
