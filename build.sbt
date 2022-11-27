ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "scala",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.4.1",
      "com.typesafe.akka" % "akka-actor-typed_2.13" % "2.7.0",
      "com.typesafe.akka" % "akka-stream-typed_2.13" % "2.7.0",
      "com.typesafe.akka" % "akka-http_2.13" % "10.4.0",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.4.0",
      "com.h2database" % "h2" % "2.1.214",
      "org.slf4j" % "slf4j-nop" % "2.0.4",
      "io.github.hagay3" %% "skuber" % "3.0.2",
    )
  )
