package models

import models.database.{Cocktails4, Cocktails}
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import scala.slick.session.Session

case class Cocktail(id: Option[Long], name: String)


/**
 * Cocktails model data access layer, to encapsulate `models.database` table definitions.
 */
object Cocktail {

  /** Table definition */
  val table = new Cocktails
  val mappedTable = new Cocktails4

  /**
   * Returns the results of a simple query (finder method).
   */
  def find: List[(Long, String)] = DB.withSession { implicit session: Session =>
    Query(table).list
  }

  def findMapped: List[Cocktail] = DB.withSession { implicit session: Session =>
    Query(mappedTable).list
  }

  /**
   * Query for values
   */
  def findNames: List[String] = DB.withSession { implicit session: Session =>
    Query(table).map(_.name).list
  }

}