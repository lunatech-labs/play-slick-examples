package models.database

import play.api.db.slick.Config.driver.simple._

/**
 * A link table used to link similar cocktails.
 */
class Similarities extends Table[(Long, Long)]("SIMILARITY") {

  def firstId = column[Long]("FIRST_COCKTAIL_ID")
  def secondId = column[Long]("SECOND_COCKTAIL_ID")
  def * = firstId ~ secondId
  def pk = primaryKey("pk_myTable2", firstId ~ secondId)
}