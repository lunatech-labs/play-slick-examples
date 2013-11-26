package models.database

import play.api.db.slick.Config.driver.simple._
import models.{ Cocktail5 => Cocktail }
import org.joda.money.{CurrencyUnit, Money}
import java.math.RoundingMode

/**
 * Case class custom mapping.
 */
class Cocktails5 extends Table[Cocktail]("COCKTAIL") {

  def id = column[Long]("ID")
  def name = column[String]("NAME")
  def priceCurrency = column[String]("PRICE_CURRENCY")
  def priceAmount = column[BigDecimal]("PRICE_AMOUNT", O.DBType("DECIMAL(13,3)"))

  def * = id.? ~ name ~ priceCurrency ~ priceAmount <> (
    c => Cocktail(c._1, c._2, money(c._3, c._4)),
    (c: Cocktail) => {
      Some((c.id, c.name, c.price.getCurrencyUnit.getCode, c.price.getAmount))
    })

  private def money(currencyCode: String, amount: BigDecimal): Money = {
    val currency = CurrencyUnit.of(currencyCode)
    Money.of(currency, amount.bigDecimal, RoundingMode.DOWN)
  }
}
