package io.github.llfrometa89

import io.circe.{Decoder, Encoder, HCursor, Json}
import io.circe.syntax._

object CustomEncodersAndDecoders {

  def run(): Unit = {
    println()
    println(s"<<<<<<<<<<<<<<<<<<<<<<<< CustomEncodersAndDecoders >>>>>>>>>>>>>>>>>>>>>>")
    class Thing(val foo: String, val bar: Int) {
      override def toString: String = s"Thing(foo=$foo, bar=$bar)"
    }

    implicit val encodeFoo: Encoder[Thing] = new Encoder[Thing] {
      final def apply(a: Thing): Json = Json.obj(
        ("foo", Json.fromString(a.foo)),
        ("bar", Json.fromInt(a.bar))
      )
    }
    val encodeThing = new Thing("foo", 1).asJson
    println(s"encodeThing: $encodeThing")

    implicit val decodeFoo: Decoder[Thing] = new Decoder[Thing] {
      final def apply(c: HCursor): Decoder.Result[Thing] =
        for {
          foo <- c.downField("foo").as[String]
          bar <- c.downField("bar").as[Int]
        } yield {
          new Thing(foo, bar)
        }
    }

    val decodeThing = encodeThing.as[Thing]
    println(s"decodeThing: $decodeThing")

    println(s"<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>")
  }
}
