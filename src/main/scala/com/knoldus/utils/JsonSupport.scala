package com.knoldus.utils

//#json-support
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.models.Item
import com.knoldus.{ User, Users }
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._

  implicit val userJsonFormat = jsonFormat3(User)
  implicit val usersJsonFormat = jsonFormat1(Users)
  implicit val itemJson = jsonFormat3(Item)
  implicit val successJson = jsonFormat1(SuccessResponse)
  implicit val errorJson = jsonFormat2(ErrorResponse)
}
//#json-support
