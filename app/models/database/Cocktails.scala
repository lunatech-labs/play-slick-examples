package models.database

import play.api.db.slick.Config.driver.simple._

object Cocktails extends Table[(Long, String)]("COCKTAIL") {
  def id = column[Long]("ID")
  def name = column[String]("NAME")
  def * = id ~ name
}