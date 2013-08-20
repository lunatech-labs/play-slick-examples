package models.database

import play.api.db.slick.Config.driver.simple._

/**
 * Column definitions.
 */
class Cocktails2 extends Table[(Long, String, Option[String], Long)]("COCKTAIL") {

  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def recipe = column[Option[String]]("RECIPE", O.DBType("CLOB"))
  def mainIngredientId = column[Long]("MAIN_INGREDIENT_ID")

  def mainIngredient = foreignKey("MAIN_INGREDIENT_FK", mainIngredientId, new Ingredients)(_.id)

  def uniqueName = index("IDX_NAME", name, unique = true)

  def * = id ~ name ~ recipe ~ mainIngredientId
}
