package com.kensai.poc.spark

import org.apache.spark.SparkContext

object SparkWordCount {

  def countWords(sc: SparkContext, inputFile: String, outputFolder: String): Unit = {
    val textFile = sc.textFile(inputFile)

    val counts = textFile
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.saveAsTextFile(outputFolder)
  }
}
