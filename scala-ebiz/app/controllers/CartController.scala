package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Cart
import scala.collection.mutable.ArrayBuffer

@Singleton
class CartController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private val carts: ArrayBuffer[Cart] = ArrayBuffer(
    Cart(1, "Cart for user A"),
    Cart(2, "Cart for user B")
  )

  def listCarts() = Action {
    Ok(Json.toJson(carts))
  }

  def getCart(id: Long) = Action {
    carts.find(_.id == id) match {
      case Some(cart) => Ok(Json.toJson(cart))
      case None => NotFound(Json.obj("error" -> s"Cart with id $id not found"))
    }
  }

  def addCart() = Action(parse.json) { request =>
    request.body.validate[Cart].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid cart data")),
      newCart => {
        if (carts.exists(_.id == newCart.id)) {
          Conflict(Json.obj("error" -> s"Cart with id ${newCart.id} already exists"))
        } else {
          carts += newCart
          Created(Json.toJson(newCart))
        }
      }
    )
  }

  def updateCart(id: Long) = Action(parse.json) { request =>
    request.body.validate[Cart].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid cart data")),
      updatedData => {
        carts.indexWhere(_.id == id) match {
          case -1 => NotFound(Json.obj("error" -> s"Cart with id $id not found"))
          case index =>
            val updatedCart = updatedData.copy(id = id)
            carts.update(index, updatedCart)
            Ok(Json.toJson(updatedCart))
        }
      }
    )
  }

  def deleteCart(id: Long) = Action {
    carts.indexWhere(_.id == id) match {
      case -1 => NotFound(Json.obj("error" -> s"Cart with id $id not found"))
      case index =>
        val removedCart = carts.remove(index)
        Ok(Json.toJson(removedCart))
    }
  }
}