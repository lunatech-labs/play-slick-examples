import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-slick-examples"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "mysql" % "mysql-connector-java" % "5.1.18",
    "org.joda" % "joda-money" % "0.9",
    "com.typesafe.play" %% "play-slick" % "0.5.0.8"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
