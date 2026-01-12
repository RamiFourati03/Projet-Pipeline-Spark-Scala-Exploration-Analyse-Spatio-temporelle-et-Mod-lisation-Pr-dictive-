package com.nyctaxi.utils

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.DataFrame

object TaxiUtils {
  def getSparkSession(appName: String): SparkSession = {
    SparkSession.builder()
      .appName(appName)
      .master("local[*]")
      .getOrCreate()
  }

  def loadData(spark: SparkSession, path: String): DataFrame = {
    spark.read.parquet(path)
  }

  def loadZones(spark: SparkSession, path: String): DataFrame = {
    spark.read.option("header", "true").csv(path)
  }

  def cleanData(df: DataFrame): DataFrame = {
    df.filter(col("trip_distance") > 0 && col("fare_amount") > 0 && col("passenger_count") > 0)
      .filter(col("tpep_pickup_datetime") >= "2024-01-01" && col("tpep_pickup_datetime") < "2024-02-01")
      .filter(col("tpep_dropoff_datetime") > col("tpep_pickup_datetime"))
      .withColumn("trip_duration", (unix_timestamp(col("tpep_dropoff_datetime")) - unix_timestamp(col("tpep_pickup_datetime"))) / 60.0)
      .filter(col("trip_duration") > 1 && col("trip_duration") < 180)
      .withColumn("average_speed", (col("trip_distance") * 1.60934) / (col("trip_duration") / 60.0))
      .withColumn("hour", hour(col("tpep_pickup_datetime")))
      .withColumn("day_of_week", date_format(col("tpep_pickup_datetime"), "EEEE"))
  }
}
