package controllers

import models.Cocktail
import models.database.Cocktails
import play.api.mvc.{ Action, Controller}
import play.api.Logger
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._

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

  def definingQueriesDBAction = DBAction { implicit requestSession: DBSessionRequest =>
    val names = Query(new Cocktails).map(_.name).list
    Ok(views.html.definingQueries(names))
  }

}