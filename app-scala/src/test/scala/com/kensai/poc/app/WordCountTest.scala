package com.kensai.poc.app

import org.junit.runner.RunWith
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class WordCountTest extends AnyFlatSpec with GivenWhenThen {

  "countWords" should "return result as expected" in {
    Given("Lines with words")
    val lines = Seq("Hello world", "Hello you", "Hell")

    When("combine(left, right)")
    val result = WordCount.countWords(lines)

    Then("Result is combined")
    val expected = Seq(
      ("Hello", 2),
      ("world", 1),
      ("you", 1),
      ("Hell", 1)
    )
    result shouldBe expected
  }

}
