package com.knoldus

import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{ Matchers, WordSpec }

class KnolStoreHTTPServiceSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest
    with KnolStoreHTTPService {
  //#test-top

  lazy val routes = route

  //#actual-test
  "KnolStore route " should {
    "return hello world (GET / )" in {
      // note that there's no need for the host part in the uri:
      val request = HttpRequest(uri = "/")

      request ~> routes ~> check {
        status should ===(StatusCodes.OK)

        // and no entries should be in the list:
        entityAs[String] should ===("""Hello world""")
      }
    }
    //#actual-test


  }
}
