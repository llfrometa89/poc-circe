package io.github.llfrometa89

import io.circe._, io.circe.parser._
import io.circe.optics.JsonPath._

object Optics {

  def run(): Unit = {
    println()
    println(s"<<<<<<<<<<<<<<<<<<<<<<<< Optics >>>>>>>>>>>>>>>>>>>>>>")

    val json: Json = parse("""
    {
      "order": {
        "customer": {
          "name": "Custy McCustomer",
          "contactDetails": {
            "address": "1 Fake Street, London, England",
            "phone": "0123-456-789"
          }
        },
        "items": [{
          "id": 123,
          "description": "banana",
          "quantity": 1
        }, {
          "id": 456,
          "description": "apple",
          "quantity": 2
        }],
        "total": 123.45
      }
    }
    """).getOrElse(Json.Null)

    //Traversing JSON
    //=> Using a Cursor
    val phoneNumFromCursor: Option[String] =
      json.hcursor.downField("order").downField("customer").downField("contactDetails").get[String]("phone").toOption

    println(s"phoneNumFromCursor: $phoneNumFromCursor")

    //=> Using Monocle Optics
    val _phoneNum                = root.order.customer.contactDetails.phone.string
    val phoneNum: Option[String] = _phoneNum.getOption(json)

    println(s"phoneNum: $phoneNum")

    //Traversing JSON
    //=> Using a Cursor
    val itemsFromCursor: Vector[Json] =
      json.hcursor.downField("order").downField("items").focus.flatMap(_.asArray).getOrElse(Vector.empty)

    println(s"itemsFromCursor: $itemsFromCursor")

    val quantities: Vector[Int] =
      itemsFromCursor.flatMap(_.hcursor.get[Int]("quantity").toOption)

    println(s"itemsFromCursor/quantities: $quantities")

    //=> Using Monocle Optics
    val items: List[Int] =
      root.order.items.each.quantity.int.getAll(json)

    println(s"items: $items")

    //Modifying JSON
    val doubleQuantities: Json => Json =
      root.order.items.each.quantity.int.modify(_ * 2)
    val modifiedJson = doubleQuantities(json)

    println(s"modifiedJson: $modifiedJson")

    //Recursively modifying JSON
    import io.circe.optics.JsonOptics._
    import monocle.function.Plated
    val jsonResult = Plated.transform[Json] { j =>
      j.asNumber match {
        case Some(n) => Json.fromString(n.toString)
        case None    => j
      }
    }(json)

    println(s"jsonResult: $jsonResult")

    println(s"<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>")
  }
}
