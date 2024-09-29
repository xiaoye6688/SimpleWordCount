import org.apache.spark.sql.SparkSession

object WordCount {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("SimpleWordCount")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._

    spark.read.textFile("simple/zhaolongxiao.txt")
      .flatMap(_.split("\\s+"))
      .groupByKey(identity)
      .count()
      .collect()
      .foreach { case (word, count) => println(s"$word: $count") }

    spark.stop()
  }
}
