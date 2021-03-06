package com.aj.sparkkafka

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._

object SparkKafkaStream {
  def main(args: Array[String]) {
    if (args.length != 2 ) {
      System.err.println("Please pass program arguments as: <brokers> <topics>")
      System.exit(1)
    }

    val Array(brokers, topics) = args
    val sparkConf = new SparkConf().setAppName("SparkKafkaStream").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf, Seconds(10))

    //Get the Kafka Topics from the program arguments and create a set of Kafka Topics
    val topicsSet = topics.split(",").toSet
    //Set the brokers in the Kafka Parameters
    val kafkaParameters = Map[String, String]("metadata.broker.list" -> brokers)
    //Create a direct Kafka Stream using the Kafka Parameters set and the Kafka Topics
    val messagesFromKafka = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParameters, topicsSet)

    //Get the lines
    val lines = messagesFromKafka.map(_._2)
    //Print
    lines.print()
    //Save to Text Files
    val outputLocation = "output/sparkkafkastream"
    lines.saveAsTextFiles(outputLocation)

    ssc.start()
    ssc.awaitTermination()
  }
}
