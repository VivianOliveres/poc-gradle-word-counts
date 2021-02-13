package com.kensai.poc.app

object WordCount {

  def countWords(lines: Seq[String]): Seq[(String, Int)] =
    lines
      .flatMap(_.split("\\W+"))
      .foldLeft(Map.empty[String, Int]) { (count, word) =>
        count + (word -> (count.getOrElse(word, 0) + 1))
      }
      .toSeq
      .sortBy(-_._2)

}
