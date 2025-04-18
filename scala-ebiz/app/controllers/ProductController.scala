package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Product
import repositories.ProductRepository

@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def listProducts() = Action {
    Ok(Json.toJson(ProductRepository.findAll()))
  }

  def getProduct(id: Long) = Action {
    ProductRepository.findById(id) match {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound(Json.obj("error" -> s"Product with id $id not found"))
    }
  }

  def addProduct() = Action(parse.json) { request =>
    request.body.validate[Product].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid product data")),
      product => ProductRepository.add(product) match {
        case Right(p) => Created(Json.toJson(p))
        case Left(error) => Conflict(Json.obj("error" -> error))
      }
    )
  }

  def updateProduct(id: Long) = Action(parse.json) { request =>
    request.body.validate[Product].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid product data")),
      updated => ProductRepository.update(id, updated) match {
        case Right(p) => Ok(Json.toJson(p))
        case Left(error) => NotFound(Json.obj("error" -> error))
      }
    )
  }

  def deleteProduct(id: Long) = Action {
    ProductRepository.delete(id) match {
      case Right(deleted) => Ok(Json.toJson(deleted))
      case Left(error) => NotFound(Json.obj("error" -> error))
    }
  }

  def listProductsByCategory(categoryId: Long) = Action {
    Ok(Json.toJson(ProductRepository.findByCategory(categoryId)))
  }

  def getProductByCategory(categoryId: Long, productId: Long) = Action {
    ProductRepository.findByCategoryAndId(categoryId, productId) match {
      case Some(p) => Ok(Json.toJson(p))
      case None => NotFound(Json.obj("error" -> s"Product $productId not found in category $categoryId"))
    }
  }
}