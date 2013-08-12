package controllers

import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index())
  }
  
  def gettingStarted = Action {
    import play.api.Logger
    import models.database.Cocktails
    import play.api.db.slick.Config.driver.simple._

    Logger.debug(Cocktails.ddl.createStatements.mkString)
    Logger.debug(Query(Cocktails).selectStatement)

    Ok(views.html.gettingStarted())
  }

}