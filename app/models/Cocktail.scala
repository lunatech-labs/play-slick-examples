package models

import models.database.Cocktails
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import scala.slick.session.Session


/**
 * Cocktails model data access layer, to encapsulate `models.database` table definitions.
 */
object Cocktail {

  /** Table definition */
  val table = new Cocktails

  /**
   * Returns the results of a simple query (finder method).
   */
  def find: List[(Long, String)] = DB.withSession { implicit session: scala.slick.session.Session =>
    Query(Cocktail.table).list
  }

  /**
   * Query for values
   */
  def findNames: List[String] = DB.withSession { implicit session: Session =>
    Query(table).map(_.name).list
  }
}