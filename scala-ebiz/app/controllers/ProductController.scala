package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Product
import scala.collection.mutable.ArrayBuffer

@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val products: ArrayBuffer[Product] = ArrayBuffer(
    Product(1, "Swimming with sharks", 499.99, 1),
    Product(2, "Mountain Adventure", 699.99, 4),
    Product(3, "City Explorer", 299.99, 3),
    Product(4, "Lion treat", 999.99, 4),
    Product(5, "Cultural Journey", 399.99, 3),
    Product(6, "Island Retreat", 599.99, 2),
    Product(7, "Poison Tasting Tour", 349.99, 2),
    Product(8, "Historical Voyage", 449.99, 3),
    Product(9, "Drowning Experience", 899.99, 1),
    Product(10, "Deadly Adventure", 549.99, 1)
  )

  def listProducts() = Action {
    Ok(Json.toJson(products))
  }

  def getProduct(id: Long) = Action {
    products.find(_.id == id) match {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound(Json.obj("error" -> s"Product with id $id not found"))
    }
  }

  def addProduct() = Action(parse.json) { request =>
    request.body.validate[Product].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid product data")),
      newProduct => {
        if (products.exists(_.id == newProduct.id)) {
          Conflict(Json.obj("error" -> s"Product with id ${newProduct.id} already exists"))
        } else {
          products += newProduct
          Created(Json.toJson(newProduct))
        }
      }
    )
  }

  def updateProduct(id: Long) = Action(parse.json) { request =>
    request.body.validate[Product].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid product data")),
      updatedData => {
        products.indexWhere(_.id == id) match {
          case -1 => NotFound(Json.obj("error" -> s"Product with id $id not found"))
          case index =>
            val updatedProduct = updatedData.copy(id = id)
            products.update(index, updatedProduct)
            Ok(Json.toJson(updatedProduct))
        }
      }
    )
  }

  def deleteProduct(id: Long) = Action {
    products.indexWhere(_.id == id) match {
      case -1 => NotFound(Json.obj("error" -> s"Product with id $id not found"))
      case index =>
        val removedProduct = products.remove(index)
        Ok(Json.toJson(removedProduct))
    }
  }

  def listProductsByCategory(categoryId: Long) = Action {
    val filteredProducts = products.filter(_.categoryId == categoryId)
    Ok(Json.toJson(filteredProducts))
  }

  def getProductByCategory(categoryId: Long, productId: Long) = Action {
    products.find(p => p.id == productId && p.categoryId == categoryId) match {
        case Some(product) => Ok(Json.toJson(product))
        case None => NotFound(Json.obj("error" -> s"Product with id $productId in category $categoryId not found"))
    }
  }  
}