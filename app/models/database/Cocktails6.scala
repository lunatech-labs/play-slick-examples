package models.database

import play.api.db.slick.Config.driver.simple._
import models.{ Cocktail5 => Cocktail }
import org.joda.money.{CurrencyUnit, Money}
import java.math.RoundingMode

/**
 * Case class custom mapping extracted to separate functions.
 */
class Cocktails6 extends Table[Cocktail]("COCKTAIL") {

  def id = column[Long]("ID", O.AutoInc)
  def name = column[String]("NAME")
  def priceCurrency = column[String]("PRICE_CURRENCY")
  def priceAmount = column[BigDecimal]("PRICE_AMOUNT", O.DBType("DECIMAL(13,3)"))

  def * = id.? ~ name ~ priceCurrency ~ priceAmount <> (mapRow _, unMapRow _)
  def forInsert = * returning id

  private def mapRow(id: Option[Long], name: String, currency: String, amount: BigDecimal): Cocktail = {
    Cocktail(id, name, money(currency, amount))
  }

  private def unMapRow(cocktail: Cocktail) = {
    val currency = cocktail.price.getCurrencyUnit.getCode
    val amount: BigDecimal = cocktail.price.getAmount
    val tuple = (cocktail.id, cocktail.name, currency, amount)
    Some(tuple)
  }

  private def money(currencyCode: String, amount: BigDecimal): Money = {
    val currency = CurrencyUnit.of(currencyCode)
    Money.of(currency, amount.bigDecimal, RoundingMode.DOWN)
  }
}
