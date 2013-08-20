package models.database

import play.api.db.slick.Config.driver.simple._

/**
 * Getting started: simple table definition.
 */
class Cocktails extends Table[(Long, String)]("COCKTAIL") {
  def id = column[Long]("ID")
  def name = column[String]("NAME")
  def * = id ~ name
}
