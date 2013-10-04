package models.database

import play.api.db.slick.Config.driver.simple._

/**
 * Defining queries: additional projections
 */
class Cocktails3 extends Table[(Long, String, Option[String])]("COCKTAIL") {
  def id = column[Long]("ID")
  def name = column[String]("NAME")
  def recipe = column[Option[String]]("RECIPE", O.DBType("CLOB"))

  def * = id ~ name ~ recipe
  def nameOnly = id ~ name
}