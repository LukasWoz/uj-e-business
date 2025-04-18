package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Category
import repositories.CategoryRepository

@Singleton
class CategoryController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def listCategories() = Action {
    Ok(Json.toJson(CategoryRepository.findAll()))
  }

  def getCategory(id: Long) = Action {
    CategoryRepository.findById(id) match {
      case Some(category) => Ok(Json.toJson(category))
      case None => NotFound(Json.obj("error" -> s"Category with id $id not found"))
    }
  }

  def addCategory() = Action(parse.json) { request =>
    request.body.validate[Category].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid category data")),
      category => CategoryRepository.add(category) match {
        case Right(c) => Created(Json.toJson(c))
        case Left(error) => Conflict(Json.obj("error" -> error))
      }
    )
  }

  def updateCategory(id: Long) = Action(parse.json) { request =>
    request.body.validate[Category].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid category data")),
      updated => CategoryRepository.update(id, updated) match {
        case Right(c) => Ok(Json.toJson(c))
        case Left(error) => NotFound(Json.obj("error" -> error))
      }
    )
  }

  def deleteCategory(id: Long) = Action {
    CategoryRepository.delete(id) match {
      case Right(deleted) => Ok(Json.toJson(deleted))
      case Left(error) => NotFound(Json.obj("error" -> error))
    }
  }
}