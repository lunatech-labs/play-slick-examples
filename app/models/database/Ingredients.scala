package models.database

import play.api.db.slick.Config.driver.simple._

/**
 * Column definitions: reference table for a foreign key relationship.
 */
class Ingredients extends Table[(Long, String)]("INGREDIENT") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def * = id ~ name
}