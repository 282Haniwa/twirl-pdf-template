import scala.sys.process._

val cleanDest = taskKey[Unit]("clean dest")

val destDir = "dest"

name := "twirlHelloWorld"
version := "1.0"

scalaVersion := "2.13.2"

scalacOptions := Seq("-deprecation", "-encoding", "utf8")

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.1"

cleanFiles += baseDirectory.value / destDir

lazy val root = (project in file("."))
  .enablePlugins(SbtTwirl)
  .settings(
    cleanDest := {
      println(s"rm -rf ${destDir}")
      Process(s"rm -rf ${destDir}") !
    }
  )
