import sbt._

object Dependencies {

  object Versions {
    val circe     = "0.12.3"
    val scalaTest = "3.1.0"
  }

  object Libraries {

    def circe(artifact: String): ModuleID = "io.circe" %% artifact % Versions.circe

    val circeDeps =
      Seq(circe("circe-core"), circe("circe-generic"), circe("circe-parser"))

    val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest % Test

  }
}
