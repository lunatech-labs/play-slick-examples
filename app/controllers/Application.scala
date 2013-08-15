package controllers

import play.api.mvc._
import play.api.db.slick.Config.driver.simple._

import models.database.Cocktails
import play.api.Logger

object Application extends Controller {

  val cocktails = new Cocktails

  def index = Action {
    Ok(views.html.index())
  }

  def gettingStarted = Action {
    import play.api.Logger
    import models.database.Cocktails
    import play.api.db.slick.Config.driver.simple._

    Logger.debug(cocktails.ddl.createStatements.mkString)
    Logger.debug(Query(cocktails).selectStatement)

    Ok(views.html.gettingStarted())
  }

  def query = Action {
    import play.api.Play.current
    import play.api.db.slick.DB
    DB.withSession { implicit session: scala.slick.session.Session =>
      val results = Query(cocktails).list
      Logger.debug(results.mkString("\n"))
      Ok(views.html.query(results))
    }
  }

}