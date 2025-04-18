package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Cart, Product}
import repositories.{CartRepository, ProductRepository}

@Singleton
class CartController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def listCarts() = Action {
    val cartViews = CartRepository.findAll().map { cart =>
      Json.obj(
        "id" -> cart.id,
        "items" -> cart.productIds.flatMap(ProductRepository.findById)
      )
    }
    Ok(Json.toJson(cartViews))
  }

  def getCart(id: Long) = Action {
    CartRepository.findById(id) match {
      case Some(cart) =>
        val items = cart.productIds.flatMap(ProductRepository.findById)
        Ok(Json.obj("id" -> cart.id, "items" -> items))
      case None =>
        NotFound(Json.obj("error" -> s"Cart with id $id not found"))
    }
  }

  def addCart() = Action(parse.json) { request =>
    request.body.validate[Cart].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid cart data")),
      cart => CartRepository.add(cart) match {
        case Right(c) =>
          val items = c.productIds.flatMap(ProductRepository.findById)
          Created(Json.obj("id" -> c.id, "items" -> items))
        case Left(error) => Conflict(Json.obj("error" -> error))
      }
    )
  }

  def updateCart(id: Long) = Action(parse.json) { request =>
    request.body.validate[Cart].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid cart data")),
      updated => CartRepository.update(id, updated) match {
        case Right(c) =>
          val items = c.productIds.flatMap(ProductRepository.findById)
          Ok(Json.obj("id" -> c.id, "items" -> items))
        case Left(error) => NotFound(Json.obj("error" -> error))
      }
    )
  }

  def deleteCart(id: Long) = Action {
    CartRepository.delete(id) match {
      case Right(deleted) =>
        val items = deleted.productIds.flatMap(ProductRepository.findById)
        Ok(Json.obj("id" -> deleted.id, "items" -> items))
      case Left(error) => NotFound(Json.obj("error" -> error))
    }
  }
}