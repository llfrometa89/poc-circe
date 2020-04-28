package io.github.llfrometa89

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

abstract class UnitTestSpec
    extends AnyFlatSpec
    with Matchers
    with OptionValues
    with Inside
    with Inspectors
    with MockitoSugar
    with ArgumentMatchersSugar
    with BeforeAndAfter
    with BeforeAndAfterAll
