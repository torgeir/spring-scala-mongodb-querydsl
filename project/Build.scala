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
    "org.springframework.data"  % "spring-data-mongodb" % "1.3.0.RC1",
    "org.mongodb" % "mongo-java-driver" % "2.6",
    "org.springframework" % "spring-core" % "3.0.5.RELEASE",
    "org.springframework" % "spring-context" % "3.0.5.RELEASE",
    "cglib" % "cglib" % "2.2",

    /**
     * Reactive Mongo (streaming mongo driver)
     * http://reactivemongo.org/
     */
    "org.reactivemongo" %% "reactivemongo" % "0.9",

    /**
     * Querydsl for mongodb
     */
    "com.mysema.querydsl" % "querydsl-mongodb" % "3.2.3",
    "com.mysema.querydsl" % "querydsl-scala" % "3.2.3"

    /**
     * Logging
     */
//    "org.slf4j" % "slf4j-simple" % "1.7.5"
  )

  lazy val root = Project(
      id = "root-project",
      base = file("."),
      settings = Project.defaultSettings ++ Seq(
        /* jars */
        libraryDependencies := appDependencies

        /* generated sources */
//        (sourceGenerators in Compile) <+= (sourceManaged in Compile).map { dir =>
//          println(value)
//
//          val file = dir / "bla.scala"
//          IO.write(file, """object Bla extends App { println("bla!") }""")
//          Seq(file)
//        }
      ))
      .settings(
        /** changes version of scala */
        scalaVersion := "2.10.2",

        /** spring scala is here */
        resolvers += "repo.springsource.org-milestone" at "https://repo.springsource.org/milestone",

        /** play iteratees is here */
        resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

        //      offline in Global := true

        /** Change compilation output folder */
        //      classDirectory in Compile := file("src/main/webapp/WEB-INF/classes")

        /** Change compilation order, java/scala */
        //      compileOrder := CompileOrder.Mixed
      )
}
