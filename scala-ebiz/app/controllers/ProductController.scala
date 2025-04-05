package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  // simple endpoint
  def index() = Action { implicit request =>
    Ok("Product controller is working properly")
  }
}