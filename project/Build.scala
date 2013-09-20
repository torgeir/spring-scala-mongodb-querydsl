import sbt._
import Keys._

object AppBuild extends Build {

  val appName         = "scala-sbt-springboot-querydsl"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    /**
     * Spring scala (spring adapted for easier use in scala)
     * http://spring.io/blog/2012/12/10/introducing-spring-scala/
     * https://github.com/spring-projects/spring-scala
     */
    "org.springframework.scala" %% "spring-scala" % "1.0.0.RC1",

    /**
     * Reactive Mongo (streaming mongo driver)
     * http://reactivemongo.org/
     */
    "org.reactivemongo" %% "reactivemongo" % "0.9",

    /**
     * Querydsl for mongodb
     */
    "com.mysema.querydsl" % "querydsl-mongodb" % "2.7.2"
  )

  lazy val root = Project(
        id = "root-project",
        base = file("."),
        settings = Project.defaultSettings ++ Seq(
          libraryDependencies := appDependencies
        ))
      .settings(
        scalaVersion := "2.10.2",
        // spring scala is here
        resolvers += "repo.springsource.org-milestone" at "https://repo.springsource.org/milestone",
        // play iteratees is here
        resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

  )
}
