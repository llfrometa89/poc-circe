package io.github.llfrometa89

import io.circe.generic.auto._, io.circe.syntax._

object AutomaticDerivation {

  def run(): Unit = {
    println()
    println(s"<<<<<<<<<<<<<<<<<<<<<<<< AutomaticDerivation >>>>>>>>>>>>>>>>>>>>>>")
    case class Person(name: String)
    case class Greeting(salutation: String, person: Person, exclamationMarks: Int)

    val encodeJsonBar = Greeting("Hey", Person("Chris"), 3).asJson
    println(s"encodeJsonBar: $encodeJsonBar")

    println(s"<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>")
  }
}
