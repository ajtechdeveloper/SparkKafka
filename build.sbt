name := "SparkKafka"

version := "1.0"

organization := "com.aj"

scalaVersion := "2.11.8"
scalacOptions := Seq("-deprecation", "-unchecked", "-encoding", "utf8", "-Xlint")
libraryDependencies ++= {
  val sparkVersion = "2.2.0"
  val kafkaClientVersion = "0.8.2.1"
  Seq(
    "org.apache.spark"  %% "spark-core"                      % sparkVersion,
    "org.apache.spark"  % "spark-streaming_2.11"                 % sparkVersion,
    "org.apache.spark"  % "spark-streaming-kafka-0-8_2.11" % sparkVersion,
    "org.apache.spark"  %% "spark-sql"                 % sparkVersion,
    "org.apache.spark"  %% "spark-hive"                % sparkVersion,
    "org.apache.spark"  %% "spark-repl"                % sparkVersion,
    "org.apache.kafka"  % "kafka-clients"              % kafkaClientVersion
  )
}
