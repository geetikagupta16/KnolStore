package com.knoldus.routes

//#quick-start-server
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.knoldus.KnolStoreHTTPService
import com.knoldus.dao.components.{EmployeeDetailComponent, EmployeeTransactionComponent, ItemComponent}

//#main-class
object KnolStoreHTTPServer extends App {

  // set up ActorSystem and other dependencies here
  //#main-class
  //#server-bootstrapping
  implicit val system: ActorSystem = ActorSystem("KnolStoreHTTPServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val knolStoreHTTPService = new KnolStoreHTTPService(ItemComponent, EmployeeTransactionComponent, EmployeeDetailComponent)
  //#main-class
  // from the UserRoutes trait
  lazy val routes: Route = knolStoreHTTPService.route
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
