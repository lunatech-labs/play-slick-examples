package models

import models.database.Cocktails
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB


/**
 * Cocktails model data access layer, to encapsulate `models.database` table definitions.
 */
object Cocktail {

  /** Table definition */
  val table = new Cocktails

  /**
   * Returns the results of a simple query (finder method).
   */
  def find = DB.withSession { implicit session: scala.slick.session.Session =>
    Query(Cocktail.table).list
  }
}