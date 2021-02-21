package com.kensai.poc.scio

import com.spotify.scio.io._
import com.spotify.scio.testing._
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScioWordCountTest extends PipelineSpec {

  val inData: Seq[String] =
    Seq("toto titi ", "titi tutu", "")
  val expected: Seq[String] = Seq("toto : 1", "titi : 2", "tutu : 1")

  "ScioWordCount" should "work" in {
    JobTest[ScioWordCount.type]
      .args("--inputFilePath=in.txt", "--output=out.txt")
      .input(TextIO("in.txt"), inData)
      .output(TextIO("out.txt"))(coll => coll should containInAnyOrder(expected))
      .run()
  }
}
