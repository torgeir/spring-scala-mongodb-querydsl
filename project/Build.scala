import sbt._
import Keys._

object AppBuild extends Build {

  val appName = "scala-sbt-springboot-querydsl"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "gd.wa" % "querydsl-domain" % "0.0.1-SNAPSHOT",

    /**
     * Querydsl for mongodb
     */
    "com.mysema.querydsl" % "querydsl-mongodb" % "3.2.3",
    "com.mysema.querydsl" % "querydsl-scala" % "3.2.3",
    "com.mysema.querydsl" % "querydsl-apt" % "3.2.3",

    "com.google.code.morphia" % "morphia" % "0.104",
    "org.mongodb" % "mongo-java-driver" % "2.10.1" ,

    /**
     * Spring scala (spring adapted for easier use in scala)
     * http://spring.io/blog/2012/12/10/introducing-spring-scala/
     * https://github.com/spring-projects/spring-scala
     */
    "org.springframework.scala" %% "spring-scala" % "1.0.0.RC1" /*withSources() withJavadoc()*/
//    "org.springframework.data" % "spring-data-mongodb" % "1.3.0.RC1"

    //    "org.springframework" % "spring-core" % "3.0.5.RELEASE",
    //    "org.springframework" % "spring-context" % "3.0.5.RELEASE",
    //    "cglib" % "cglib" % "2.2",

    /**
     * Reactive Mongo (streaming mongo driver)
     * http://reactivemongo.org/
     */
    //    "org.reactivemongo" %% "reactivemongo" % "0.9",


    /**
     * Logging
     */
    //    "org.slf4j" % "slf4j-simple" % "1.7.5"
  )

  lazy val root = Project(
    id = "root-project",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      libraryDependencies := appDependencies
//      scalaSource in Compile <<= (sourceDirectory in Compile)(_ / "extra")
    ))
    .settings(
    /** changes version of scala */
    scalaVersion := "2.10.2",

    /** spring scalae */
    resolvers += "repo.springsource.org-milestone" at "https://repo.springsource.org/milestone",
    /** play iteratees */
    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    /** morphia  */
    resolvers += "morphia @ google" at "http://morphia.googlecode.com/svn/mavenrepo/",
    /** search local maven repo */
    resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
  )
}
