package io.github.llfrometa89

import io.circe._, io.circe.parser._

object ParsingJson {

  def run(): Unit = {
    println()
    println(s"<<<<<<<<<<<<<<<<<<<<<<<< TraversingAndModifyingJson >>>>>>>>>>>>>>>>>>>>>>")

    val rawJson: String = """
    {
      "foo": "bar",
      "baz": 123,
      "list of stuff": [ 4, 5, 6 ]
    }
    """

    parse(rawJson) match {
      case Left(failure) => println(s"Invalid JSON :( /failure: $failure")
      case Right(json)   => println(s"Yay, got some JSON! - json:$json")
    }
    println(s"<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>")
  }

}
