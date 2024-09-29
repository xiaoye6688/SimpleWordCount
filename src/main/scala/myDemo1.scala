import org.apache.spark.sql.SparkSession

object WordCount {
  def main(args: Array[String]): Unit = {
    // 创建 SparkSession
    val spark = SparkSession.builder
      .appName("SimpleWordCount")
      .master("local[*]")  // 本地运行模式，使用所有可用的 CPU 核心
      .getOrCreate()

    // 隐式转换，提供 Dataset 中的 Encoder 支持
    import spark.implicits._

    // 设置日志级别为WARN
    spark.sparkContext.setLogLevel("WARN")

    // 指定文本文件路径
    val inputFile = "simple/chenmengjuan.txt"  // 替换为你的实际文件路径

    // 读取文本文件
    val textFile = spark.read.textFile(inputFile)

    // 拆分每行文本为单词
    val words = textFile.flatMap(line => line.split("\\s+"))

    // 统计每个单词出现的次数
    val wordCounts = words.groupByKey(identity).count()

    // 打印结果
    wordCounts.show()

    // 关闭 SparkSession
    spark.stop()
  }
}
