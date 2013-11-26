package models.database

import play.api.db.slick.Config.driver.simple._

/**
 * Defining queries: additional projections
 */
class Cocktails3 extends Table[(Long, String, Option[String], String, BigDecimal)]("COCKTAIL") {
  def id = column[Long]("ID")
  def name = column[String]("NAME")
  def recipe = column[Option[String]]("RECIPE", O.DBType("CLOB"))
  def priceCurrency = column[String]("PRICE_CURRENCY")
  def priceAmount = column[BigDecimal]("PRICE_AMOUNT", O.DBType("DECIMAL(13,3)"))

  def * = id ~ name ~ recipe ~ priceCurrency ~ priceAmount
  def nameOnly = id ~ name
  def withPrice = name ~ priceCurrency ~ priceAmount
}