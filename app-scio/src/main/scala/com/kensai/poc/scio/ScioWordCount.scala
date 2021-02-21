package com.kensai.poc.beam

import com.spotify.scio._
import com.spotify.scio.values.SCollection
import org.slf4j.LoggerFactory

object ScioWordCount {
  val logger = LoggerFactory.getLogger(this.getClass)

  val WordRegex = "[^a-zA-Z']+"

  def main(inputArgs: Array[String]): Unit = {
    val (sc, args) = ContextAndArgs(inputArgs)
    val inputFile = args("inputFilePath")
    val outputFolder = args("output")

    logger.info(s"Count words on [$inputFile] with Scio/Beam and write into [$outputFolder]")

    val counts: SCollection[(String, Long)] = sc
      .textFile(inputFile)
      .map(_.trim)
      .filterNot(_.isEmpty)
      .flatMap(_.split(WordRegex))
      .countByValue

    counts
      .map(tuple => s"${tuple._1} : ${tuple._2}")
      .saveAsTextFile(outputFolder)

    sc.run().waitUntilFinish()
  }
}
