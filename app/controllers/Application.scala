package controllers

import models.{Cocktail5, Cocktail}
import models.database.Cocktails
import play.api.mvc.{ Action, Controller}
import play.api.Logger
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.data.Form
import play.api.data.Forms._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  /**
   * Use logging and a view template to inspect generated SQL.
   */
  def gettingStarted = Action {
    import models.database.Cocktails
    import play.api.Logger
    import play.api.db.slick.Config.driver.simple._

    Logger.debug((new Cocktails).ddl.createStatements.mkString)
    Logger.debug(Query(new Cocktails).selectStatement)

    Ok(views.html.gettingStarted())
  }

  def columnDefinitions = Action {
    Ok(views.html.columnDefinitions())
  }

  /**
   * Render the results of a simple query.
   */
  def query = Action {
    val results = Cocktail.find
    Logger.debug(results.mkString("\n"))
    Ok(views.html.query(results))
  }

  /**
   * Render the results of a simple query, using the model layer.
   */
  def definingQueries = Action {
    Ok(views.html.definingQueries(Cocktail.findNames))
  }

  /**
   * Render the results of a simple query, using the database table directly.
   */
  def definingQueriesController = Action {
    DB.withSession { implicit session: Session =>
      val names = Query(new Cocktails).map(_.name).list
      Ok(views.html.definingQueries(names))
    }
  }

  def definingQueriesDBAction = DBAction { implicit requestSession =>
    val names = Query(new Cocktails).map(_.name).list
    Ok(views.html.definingQueries(names))
  }

  /**
   * Inserts a database row, optionally using the version of the model function that returns the inserted ID.
   */
  def insert = Action { implicit request =>
    Form(single("insert" -> nonEmptyText)).bindFromRequest.fold(
      formWithErrors => BadRequest(formWithErrors.errorsAsJson),
      insert => insert match {
        case "tuple" => {
          Cocktail5.insertTuple(Cocktail5.random)
          Redirect(routes.Application.insertingRows).flashing("success" -> s"Inserted tuple")
        }
        case "tuples" => {
          Cocktail5.insertTuples(Cocktail5.random, Cocktail5.random, Cocktail5.random)
          Redirect(routes.Application.insertingRows).flashing("success" -> s"Inserted three tuples")
        }
        case "instance" => {
          Cocktail5.insert(Cocktail5.random)
          Redirect(routes.Application.insertingRows).flashing("success" -> s"Inserted case class instance")
        }
        case "returning" => {
          val id = Cocktail5.insertReturningId(Cocktail5.random)
          Redirect(routes.Application.insertingRows).flashing("success" -> s"Inserted instance returning ID $id")
        }
      })
  }

  def insertingRows = Action { implicit request =>
    Ok(views.html.insertingRows(Cocktail5.find, Cocktail5.findSimilar))
  }

  def link = Action { implicit request =>
    val form = Form(tuple("first" -> number, "second" -> number))
    form.bindFromRequest.fold(
      formWithErrors => BadRequest(formWithErrors.errorsAsJson),
      pair => {
        Logger.debug(s"Link (${pair._1}, ${pair._2}")
        Cocktail5.link(pair._1, pair._2)
        Redirect(routes.Application.insertingRows).flashing("link-success" -> s"Inserted tuple")
      })
  }
}