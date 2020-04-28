import Dependencies._

name := "poc-circe"

version := "1.0.0"

scalaVersion := "2.13.2"

lazy val commonScalacOptions = Seq(
  "-feature",
  "-language:higherKinds",
  "-encoding",
  "UTF-8",
  "-deprecation",
  "-unchecked",
  "-Wunused:imports,patvars,locals",
  "-Wnumeric-widen",
  "-Xlint:-unused"
)

libraryDependencies ++= Libraries.circeDeps ++ Seq(
  Libraries.scalaTest
)

scalacOptions ++= commonScalacOptions

enablePlugins(JavaAppPackaging)
