package io.github.llfrometa89

import io.circe._, io.circe.generic.semiauto._
import io.circe.parser.decode
import io.circe.syntax._

object SemiAutomaticDerivation {

  def run(): Unit = {
    println()
    println(s"<<<<<<<<<<<<<<<<<<<<<<<< SemiAutomaticDerivation >>>>>>>>>>>>>>>>>>>>>>")

    case class Foo(a: Int, b: String, c: Boolean)
    implicit val fooDecoder: Decoder[Foo] = deriveDecoder
    implicit val fooEncoder: Encoder[Foo] = deriveEncoder

    val encodeJson = Foo(1, "2", c = false).asJson
    println(s"encodeJson: $encodeJson")

    val decodeJson = encodeJson.as[Foo]
    println(s"decodeJson: $decodeJson")

    val rawJson: String =
      """
      {
        "a": 123,
        "b": "bar",
        "c": false
      }
      """
    val decodeJsonWithParserDependency = decode[Foo](rawJson)
    println(s"decodeJsonWithParserDependency: $decodeJsonWithParserDependency")

    println(s"<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>")
  }
}
