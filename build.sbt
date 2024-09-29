ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.10" // Spark 3.x 通常与 Scala 2.12.x 兼容

lazy val root = (project in file("."))
  .settings(
    name := "SimpleWordCount",

    // Spark 核心库和 SQL 库
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.4.1", // Spark 3.x 版本
      "org.apache.spark" %% "spark-sql" % "3.4.1"
    )
  )
