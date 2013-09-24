import java.io.File
import play.api.db.slick.plugin.TableScanner
import play.api.libs.Files
import play.api.{Mode, Application, GlobalSettings}

object Global extends GlobalSettings {

  private val configKey = "slick"
  private val ScriptDirectory = "conf/evolutions/"
  private val CreateScript = "create-database.sql"
  private val DropScript = "drop-database.sql"
  private val ScriptHeader = "-- SQL DDL script\n-- Generated file - do not edit\n\n"

/**
 * Creates SQL DDL scripts on application start-up.
 */
  override def onStart(application: Application) {

    if (application.mode != Mode.Prod) {
      application.configuration.getConfig(configKey).foreach { configuration =>
        configuration.keys.foreach { database =>
          val databaseConfiguration = configuration.getString(database).getOrElse{
            throw configuration.reportError(database, "Missing values for key " + database, None)
          }
          val packageNames = databaseConfiguration.split(",").toSet
          val ddls = TableScanner.reflectAllDDLMethods(packageNames, application.classloader)

          val scriptDirectory = application.getFile(ScriptDirectory + database)
          Files.createDirectory(scriptDirectory)

          writeScript(ddls.map(_.createStatements), scriptDirectory, CreateScript)
          writeScript(ddls.map(_.dropStatements), scriptDirectory, DropScript)
        }
      }
    }
  }

  /**
   * Writes the given DDL statements to a file.
   */
  private def writeScript(ddlStatements: Seq[Iterator[String]], directory: File, fileName: String): Unit = {
    val createScript = new File(directory, fileName)
    val createSql = ddlStatements.flatten.mkString("\n\n")
    Files.writeFileIfChanged(createScript, ScriptHeader + createSql)
  }
}