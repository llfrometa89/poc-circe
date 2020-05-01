import sbt._

object Dependencies {

  object Versions {
    val circe        = "0.13.0"
    val scalaTest    = "3.1.0"
    val mockitoScala = "1.11.4"
  }

  object Libraries {

    def circe(artifact: String): ModuleID = "io.circe" %% artifact % Versions.circe

    val scalaTest    = "org.scalatest" %% "scalatest"     % Versions.scalaTest    % Test
    val mockitoScala = "org.mockito"   %% "mockito-scala" % Versions.mockitoScala % Test

    val circeDeps =
      Seq(
        circe("circe-core"),
        circe("circe-generic"),
        circe("circe-parser"),
        circe("circe-shapes"),
        circe("circe-optics")
      )
  }
}
