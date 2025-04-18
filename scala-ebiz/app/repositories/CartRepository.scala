package repositories

import models.Cart
import play.api.libs.json._
import scala.io.Source
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets
import scala.collection.mutable.ListBuffer

object CartRepository {
  private val filePath = "conf/carts.json"
  private val carts: ListBuffer[Cart] = loadCarts()

  private def loadCarts(): ListBuffer[Cart] = {
    val source = Source.fromFile(filePath)
    val jsonStr = try source.mkString finally source.close()

    Json.parse(jsonStr).validate[List[Cart]] match {
      case JsSuccess(cartList, _) => ListBuffer.from(cartList)
      case JsError(errors) =>
        println(s"[CartRepository] Error parsing JSON: $errors")
        ListBuffer.empty
    }
  }

  private def saveCarts(): Unit = {
    val json = Json.prettyPrint(Json.toJson(carts.toList))
    Files.write(Paths.get(filePath), json.getBytes(StandardCharsets.UTF_8))
  }

  def findAll(): List[Cart] = carts.toList

  def findById(id: Long): Option[Cart] = carts.find(_.id == id)

  def add(cart: Cart): Either[String, Cart] = {
    if (carts.exists(_.id == cart.id))
      Left(s"Cart with id ${cart.id} already exists")
    else {
      carts += cart
      saveCarts()
      Right(cart)
    }
  }

  def update(id: Long, updated: Cart): Either[String, Cart] = {
    carts.indexWhere(_.id == id) match {
      case -1 => Left(s"Cart with id $id not found")
      case idx =>
        val updatedCart = updated.copy(id = id)
        carts.update(idx, updatedCart)
        saveCarts()
        Right(updatedCart)
    }
  }

  def delete(id: Long): Either[String, Cart] = {
    carts.indexWhere(_.id == id) match {
      case -1 => Left(s"Cart with id $id not found")
      case idx =>
        val removed = carts.remove(idx)
        saveCarts()
        Right(removed)
    }
  }
}
