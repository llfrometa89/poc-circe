package io.github.llfrometa89

import io.circe.parser.decode
import io.circe.syntax._
import io.circe.{Decoder, Encoder}
import io.circe.generic.auto._, io.circe.shapes._

object ADTsEncodingAndDecodingShapeless {
  def run(): Unit = {
    println()
    println(s"<<<<<<<<<<<<<<<<<<<<<<<< ADTsEncodingAndDecodingShapeless >>>>>>>>>>>>>>>>>>>>>>")

    //ADTs encoding and decoding
    import ShapesDerivation._

    val decodeJsonEvent = decode[Event]("""{ "i": 1000 }""")
    println(s"decodeJsonEvent: $decodeJsonEvent")

    val encodeJsonEvent = (Foo(100): Event).asJson.noSpaces
    println(s"encodeJsonEvent: $encodeJsonEvent")

    println(s"<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>")
  }
}

object ShapesDerivation {
  import shapeless.{Coproduct, Generic}

  sealed trait Event

  case class Foo(i: Int)               extends Event
  case class Bar(s: String)            extends Event
  case class Baz(c: Char)              extends Event
  case class Qux(values: List[String]) extends Event

  implicit def encodeAdtNoDiscr[A, Repr <: Coproduct](
      implicit
      gen: Generic.Aux[A, Repr],
      encodeRepr: Encoder[Repr]
  ): Encoder[A] = encodeRepr.contramap(gen.to)

  implicit def decodeAdtNoDiscr[A, Repr <: Coproduct](
      implicit
      gen: Generic.Aux[A, Repr],
      decodeRepr: Decoder[Repr]
  ): Decoder[A] = decodeRepr.map(gen.from)

}
