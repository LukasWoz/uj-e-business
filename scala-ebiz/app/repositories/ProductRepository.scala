package repositories

import models.Product
import play.api.libs.json._
import scala.io.Source
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets
import scala.collection.mutable.ListBuffer

object ProductRepository {
  private val filePath = "conf/products.json"
  private val products: ListBuffer[Product] = loadProducts()

  private def loadProducts(): ListBuffer[Product] = {
    val source = Source.fromFile(filePath)
    val jsonStr = try source.mkString finally source.close()

    Json.parse(jsonStr).validate[List[Product]] match {
      case JsSuccess(prodList, _) => ListBuffer.from(prodList)
      case JsError(errors) =>
        println(s"[ProductRepository] Error parsing JSON: $errors")
        ListBuffer.empty
    }
  }

  private def saveProducts(): Unit = {
    val json = Json.prettyPrint(Json.toJson(products.toList))
    Files.write(Paths.get(filePath), json.getBytes(StandardCharsets.UTF_8))
  }

  def findAll(): List[Product] = products.toList

  def findById(id: Long): Option[Product] = products.find(_.id == id)

  def add(product: Product): Either[String, Product] = {
    if (products.exists(_.id == product.id))
      Left(s"Product with id ${product.id} already exists")
    else {
      products += product
      saveProducts()
      Right(product)
    }
  }

  def update(id: Long, updated: Product): Either[String, Product] = {
    products.indexWhere(_.id == id) match {
      case -1 => Left(s"Product with id $id not found")
      case idx =>
        val updatedProduct = updated.copy(id = id)
        products.update(idx, updatedProduct)
        saveProducts()
        Right(updatedProduct)
    }
  }

  def delete(id: Long): Either[String, Product] = {
    products.indexWhere(_.id == id) match {
      case -1 => Left(s"Product with id $id not found")
      case idx =>
        val removed = products.remove(idx)
        saveProducts()
        Right(removed)
    }
  }

  def findByCategory(categoryId: Long): List[Product] =
    products.filter(_.categoryId == categoryId).toList

  def findByCategoryAndId(categoryId: Long, productId: Long): Option[Product] =
    products.find(p => p.id == productId && p.categoryId == categoryId)
}