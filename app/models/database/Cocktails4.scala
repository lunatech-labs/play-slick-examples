package models.database

import play.api.db.slick.Config.driver.simple._
import models.Cocktail

/**
 * Case class mapping.
 */
class Cocktails4 extends Table[Cocktail]("COCKTAIL") {
  def id = column[Long]("ID")
  def name = column[String]("NAME")
  def * = id.? ~ name <> (Cocktail.apply _, Cocktail.unapply _)
}
