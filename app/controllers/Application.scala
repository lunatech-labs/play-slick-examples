package controllers

import play.api.mvc._
import play.api.Logger
import models.Cocktail

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

}