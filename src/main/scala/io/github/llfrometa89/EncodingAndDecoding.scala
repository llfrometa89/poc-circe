package io.github.llfrometa89

import io.circe.syntax._
import io.circe.parser.decode

object EncodingAndDecoding {

  def run(): Unit = {
    println()
    println(s"<<<<<<<<<<<<<<<<<<<<<<<< EncodingAndDecoding >>>>>>>>>>>>>>>>>>>>>>")

    val encodeJson = List(1, 2, 3).asJson
    println(s"encodeJson:$encodeJson")

    val decodeJson = encodeJson.as[List[Int]]
    println(s"decodeJson:$decodeJson")

    val rawJson: String                = """
    [ 4, 5, 6 ]
    """
    val decodeJsonWithParserDependency = decode[List[Int]](rawJson)
    println(s"decodeJsonWithParserDependency:$decodeJsonWithParserDependency")

    println(s"<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>")
  }
}
