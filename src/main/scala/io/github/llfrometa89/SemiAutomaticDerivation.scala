package io.github.llfrometa89

import io.circe._, io.circe.generic.semiauto._
import io.circe.parser.decode
import io.circe.generic.JsonCodec, io.circe.syntax._
import io.circe.{Decoder, Encoder}

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

    //@JsonCodec
    @JsonCodec case class Bar(i: Int, s: String)
    val encodeJsonBar = Bar(13, "Qux").asJson
    println(s"encodeJsonBar: $encodeJsonBar")

    //forProductN helper methods
    case class User(id: Long, firstName: String, lastName: String)
    implicit val decodeUser: Decoder[User] =
      Decoder.forProduct3("id", "first_name", "last_name")(User.apply)
    println(s"decodeUser: $decodeUser")

    implicit val encodeUser: Encoder[User] =
      Encoder.forProduct3("id", "first_name", "last_name")(u => (u.id, u.firstName, u.lastName))
    println(s"encodeUser: $encodeUser")

    println(s"<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>")
  }
}
