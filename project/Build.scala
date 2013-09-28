import sbt._
import Keys._

object Build extends Build {

  val commonSettings = Seq(
    scalaVersion := "2.10.2",
    /** local .m2 folder */
    resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    /** spring scala */
    resolvers += "repo.springsource.org-milestone" at "https://repo.springsource.org/milestone",
    /** play iteratees */
    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/repo/",
    resolvers += "Typesafe releases" at "http://repo.typesafe.com/typesafe/releases/",
    /** morphia  */
    resolvers += "morphia @ google" at "http://morphia.googlecode.com/svn/mavenrepo/"
  )

  val dependencies = Seq(
    /** scala */
    "org.scala-lang" % "scala-compiler" % "2.10.2",
    "org.scala-lang" % "scala-library"  % "2.10.2",

    /** sbt */
    //"org.scala-sbt" %% "sbt" % "0.13.0" withSources(),

    /** querydsl for mongodb */
    "com.mysema.querydsl" % "querydsl-mongodb" % "3.2.3",
    "com.mysema.querydsl" % "querydsl-scala"   % "3.2.3",
    "com.mysema.querydsl" % "querydsl-apt"     % "3.2.3",
    "com.mysema.querydsl" % "querydsl-codegen" % "0.5.9",

    /** google morphia mongo driver */
    "com.google.code.morphia" % "morphia" % "0.104",
    "org.mongodb" % "mongo-java-driver" % "2.10.1",

    /** logging */
    "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",
    "org.slf4j" % "slf4j-simple" % "1.7.5",

    /**
     * Spring scala (spring adapted for easier use in scala)
     * http://spring.io/blog/2012/12/10/introducing-spring-scala/
     * https://github.com/spring-projects/spring-scala
     */
    "org.springframework.scala" %% "spring-scala"        % "1.0.0.RC1", /* withSources() withJavadoc()*/
    "org.springframework.data"   % "spring-data-mongodb" % "1.3.0.RC1"
    //"org.springframework" % "spring-core" % "3.0.5.RELEASE",
    //"org.springframework" % "spring-context" % "3.0.5.RELEASE",
    //"cglib" % "cglib" % "2.2",
  )

  lazy val domain = Project(
    id = "domain",
    base = file("domain"),
    settings = Project.defaultSettings ++ Seq(
      libraryDependencies := dependencies
    ))
    .settings(commonSettings: _*)

  lazy val root = Project(
    id = "root-project",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(

      libraryDependencies := dependencies,

      unmanagedSourceDirectories in Compile <++= baseDirectory {
        base =>
          Seq(base / "domain")
      },

      sourceGenerators in Compile <+=
        (sourceManaged in Compile, fullClasspath in Compile in domain, mainClass in Compile in domain, runner, streams)
          map createQuerydslClasses
    ))
    .settings(commonSettings: _*)
    .dependsOn(domain)


  def createQuerydslClasses(base: File,
                            cp: Classpath,
                            mainClass: Option[String],
                            run: ScalaRun,
                            s: TaskStreams): Seq[File] = {
    val scala: File = base / "scala"

    // TODO sbt plugin for GenericExporter instead?
    run.run(mainClass.get, cp.files, Seq(scala.getAbsolutePath), s.log)

    IO.createDirectory(scala)
    (scala ** "*.scala").get
  }
}
