package com.kensai.poc.spark

import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object SparkApp {
  val logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      println("Invalid call")
      println("Usage: SparkApp inputFile outputFile")
      System.exit(0)
    }

    val inputFile = args(0)
    val outputBasePath = args(1)

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val now = LocalDateTime.now().format(formatter)
    val outputFolder = s"$outputBasePath-$now"
    logger.info(s"Count words on [$inputFile] with Spark and write into [$outputFolder]")

    val spark = SparkSession.builder
      .appName("Spark Word Count")
      .master("local")
      .getOrCreate()
    val sc = spark.sparkContext
    SparkWordCount.countWords(sc, inputFile, outputFolder)
  }

}
