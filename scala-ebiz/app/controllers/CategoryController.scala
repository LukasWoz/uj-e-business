package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Category
import scala.collection.mutable.ArrayBuffer

@Singleton
class CategoryController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private val categories: ArrayBuffer[Category] = ArrayBuffer(
    Category(1, "Adventure"),
    Category(2, "Relaxation"),
    Category(3, "Cultural"),
    Category(4, "Nature")
  )

  def listCategories() = Action {
    Ok(Json.toJson(categories))
  }

  def getCategory(id: Long) = Action {
    categories.find(_.id == id) match {
      case Some(category) => Ok(Json.toJson(category))
      case None => NotFound(Json.obj("error" -> s"Category with id $id not found"))
    }
  }

  def addCategory() = Action(parse.json) { request =>
    request.body.validate[Category].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid category data")),
      newCategory => {
        if (categories.exists(_.id == newCategory.id)) {
          Conflict(Json.obj("error" -> s"Category with id ${newCategory.id} already exists"))
        } else {
          categories += newCategory
          Created(Json.toJson(newCategory))
        }
      }
    )
  }

  def updateCategory(id: Long) = Action(parse.json) { request =>
    request.body.validate[Category].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid category data")),
      updatedData => {
        categories.indexWhere(_.id == id) match {
          case -1 => NotFound(Json.obj("error" -> s"Category with id $id not found"))
          case index =>
            val updatedCategory = updatedData.copy(id = id)
            categories.update(index, updatedCategory)
            Ok(Json.toJson(updatedCategory))
        }
      }
    )
  }

  def deleteCategory(id: Long) = Action {
    categories.indexWhere(_.id == id) match {
      case -1 => NotFound(Json.obj("error" -> s"Category with id $id not found"))
      case index =>
        val removedCategory = categories.remove(index)
        Ok(Json.toJson(removedCategory))
    }
  }
}