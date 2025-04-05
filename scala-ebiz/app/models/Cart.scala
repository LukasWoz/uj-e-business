package models

import play.api.libs.json._

case class Cart(id: Long, description: String)

object Cart {
  implicit val cartFormat: OFormat[Cart] = Json.format[Cart]
}