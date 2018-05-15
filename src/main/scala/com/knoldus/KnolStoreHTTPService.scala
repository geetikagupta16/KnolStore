package com.knoldus

import akka.actor.ActorSystem
import akka.event.Logging

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete

trait KnolStoreHTTPService extends JsonSupport {

  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[KnolStoreHTTPService])

  lazy val route: Route =
    pathSingleSlash {
      get {
        complete {
          "Hello world"
        }
      }
    }
}
