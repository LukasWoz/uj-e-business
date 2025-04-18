package repositories

import models.Category
import play.api.libs.json._
import scala.io.Source
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets
import scala.collection.mutable.ListBuffer

object CategoryRepository {
  private val filePath = "conf/categories.json"
  private val categories: ListBuffer[Category] = loadCategories()

  private def loadCategories(): ListBuffer[Category] = {
    val source = Source.fromFile(filePath)
    val jsonStr = try source.mkString finally source.close()

    Json.parse(jsonStr).validate[List[Category]] match {
      case JsSuccess(catList, _) => ListBuffer.from(catList)
      case JsError(errors) =>
        println(s"[CategoryRepository] Error parsing JSON: $errors")
        ListBuffer.empty
    }
  }

  private def saveCategories(): Unit = {
    val json = Json.prettyPrint(Json.toJson(categories.toList))
    Files.write(Paths.get(filePath), json.getBytes(StandardCharsets.UTF_8))
  }

  def findAll(): List[Category] = categories.toList

  def findById(id: Long): Option[Category] = categories.find(_.id == id)

  def add(category: Category): Either[String, Category] = {
    if (categories.exists(_.id == category.id))
      Left(s"Category with id ${category.id} already exists")
    else {
      categories += category
      saveCategories()
      Right(category)
    }
  }

  def update(id: Long, updated: Category): Either[String, Category] = {
    categories.indexWhere(_.id == id) match {
      case -1 => Left(s"Category with id $id not found")
      case idx =>
        val updatedCategory = updated.copy(id = id)
        categories.update(idx, updatedCategory)
        saveCategories()
        Right(updatedCategory)
    }
  }

  def delete(id: Long): Either[String, Category] = {
    categories.indexWhere(_.id == id) match {
      case -1 => Left(s"Category with id $id not found")
      case idx =>
        val removed = categories.remove(idx)
        saveCategories()
        Right(removed)
    }
  }
}