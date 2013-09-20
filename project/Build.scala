import sbt._
import Keys._

object ApplicationBuild extends Build {

  val appName         = "scala-sbt-springboot-querydsl"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "joda-time" % "joda-time" % "2.1",
    "org.springframework.scala" % "spring-scala" % "1.0.0.M2"
  )

  lazy val root = Project(
        id = "root-project",
        base = file("."),
        settings = Project.defaultSettings ++ Seq(
          libraryDependencies := appDependencies
        ))
      .settings(
        resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
        resolvers += "repo.springsource.org-milestone" at "https://repo.springsource.org/libs-milestone")
}
