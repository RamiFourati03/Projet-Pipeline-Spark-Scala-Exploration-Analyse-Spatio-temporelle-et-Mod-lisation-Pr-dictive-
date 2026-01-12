import com.nyctaxi.utils.TaxiUtils
import org.apache.spark.sql.functions._

object TaxiPhase2 {
  def main(args: Array[String]): Unit = {
    val spark = TaxiUtils.getSparkSession("TaxiPhase2 - Cleaning")
    val dfRaw = TaxiUtils.loadData(spark, "data/yellow_tripdata_2024-01.parquet")

    val dfCleaned = TaxiUtils.cleanData(dfRaw)
      .withColumn("trip_category", when(col("trip_distance") * 1.60934 < 10, "short_trip").otherwise("long_trip"))

    dfCleaned.write.mode("overwrite").parquet("data/taxi_cleaned.parquet")
    println(s"Phase 2 Terminée: ${dfCleaned.count()} trajets valides sauvegardés.")

    spark.stop()
  }
}
