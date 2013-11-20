package models

import models.database.{ Cocktails5 => Cocktails }
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import scala.slick.session.Session
import org.joda.money.Money

case class Cocktail5(id: Option[Long], name: String, price: Money)


/**
 * Cocktails model data access layer, to encapsulate `models.database` table definitions.
 */
object Cocktail5 {

  /** Table definition */
  val table = new Cocktails

  /**
   * Returns the results of a simple query (finder method).
   */
  def find: List[Cocktail5] = DB.withSession { implicit session: Session =>
    Query(table).list
  }


}