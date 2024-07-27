name := "ProjectEMP"

version := "0.1"

scalaVersion := "2.12.1"  // Ensure this matches your project's Scala version

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.8.6",
  "com.typesafe.akka" %% "akka-http" % "10.5.3",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.3",
  "com.typesafe.akka" %% "akka-stream" % "2.8.6",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.6",
  "io.spray" %% "spray-json" % "1.3.6",
  "org.json4s" %% "json4s-native" % "4.0.7",
  "org.json4s" %% "json4s-ext" % "4.0.7",
  "de.heikoseeberger" %% "akka-http-json4s" % "1.39.2",
  "ch.qos.logback" % "logback-classic" % "1.5.6"
)
