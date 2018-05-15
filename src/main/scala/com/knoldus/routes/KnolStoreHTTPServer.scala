package com.knoldus.routes

//#quick-start-server
import scala.concurrent.Await
import scala.concurrent.duration.Duration

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.knoldus.KnolStoreHTTPService

//#main-class
object KnolStoreHTTPServer extends App with KnolStoreHTTPService {

  // set up ActorSystem and other dependencies here
  //#main-class
  //#server-bootstrapping
  implicit val system: ActorSystem = ActorSystem("KnolStoreHTTPServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  //#main-class
  // from the UserRoutes trait
  lazy val routes: Route = route
  //#main-class

  //#http-server
  Http().bindAndHandle(routes, "localhost", 8080)

  println(s"Server online at http://localhost:8080/")

  Await.result(system.whenTerminated, Duration.Inf)
  //#http-server
  //#main-class
}
//#main-class
//#quick-start-server
