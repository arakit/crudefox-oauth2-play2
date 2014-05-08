name := "crudefox-oauth2-play2"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.json" % "json" % "20140107"
)

play.Project.playScalaSettings
