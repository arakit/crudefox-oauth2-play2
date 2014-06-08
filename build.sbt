name := "crudefox-oauth2-play2"

version := "1.0-SNAPSHOT"

organization := "jp.crudefox"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.json" % "json" % "20140107"
)

play.Project.playScalaSettings


//resolvers
resolvers ++= Seq(
        "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

