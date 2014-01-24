package models

import models.database.{Cocktails6 => Cocktails, Similarities, Cocktails3}
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
  val similarities = new Similarities

  /**
   * Returns the results of a simple query (finder method).
   */
  def find: List[Cocktail5] = DB.withSession { implicit session: Session =>
    Query(table).list
  }

  /**
   * Returns a list of cocktail pairs that are similar.
   */
  def findSimilar: List[(Cocktail5, Cocktail5)] = DB.withSession { implicit session: Session =>
    val query = for {
      similarity <- similarities
      first <- table if first.id === similarity.firstId
      second <- table if second.id === similarity.secondId
    } yield first -> second
    query.list
  }

  def insert(cocktail: Cocktail5): Unit = DB.withSession { implicit session: Session =>
    table.insert(cocktail)
  }

  def insertReturningId(cocktail: Cocktail5): Long = DB.withSession { implicit session: Session =>
    table.forInsert.insert(cocktail)
  }

  def link(firstId: Long, secondId: Long): Unit = DB.withSession { implicit session: Session =>
//    (new Similarities).insert(firstId, secondId)
    (new Similarities).insertAll(firstId -> secondId, secondId -> firstId)
  }

  def insertTuple(cocktail: Cocktail5): Long = DB.withSession { implicit session: Session =>
    (new Cocktails3).withPrice.insert(cocktail.name, cocktail.price.getCurrencyUnit.getCode, cocktail.price.getAmount)
  }

  /**
   * Inserts three tuples using JDBC batch insert.
   */
  def insertTuples(cocktail1: Cocktail5, cocktail2: Cocktail5, cocktail3: Cocktail5): Unit = DB.withSession { implicit session: Session =>
    (new Cocktails3).withPrice.insertAll(
      (cocktail1.name, cocktail1.price.getCurrencyUnit.getCode, cocktail1.price.getAmount),
      (cocktail2.name, cocktail2.price.getCurrencyUnit.getCode, cocktail2.price.getAmount),
      (cocktail3.name, cocktail3.price.getCurrencyUnit.getCode, cocktail3.price.getAmount)
    )
  }

  /**
   * Returns a random cocktail.
   */
  def random = {
    import org.joda.money.CurrencyUnit
    import scala.util.Random._
    import java.math.RoundingMode
    val names = List("Margarita", "Caipirinha", "Piña Colada")
    val price = Money.of(CurrencyUnit.EUR, 6.0 + nextDouble * 2.0, RoundingMode.DOWN)
    Cocktail5(None, names(nextInt(names.length)), price)
  }
}