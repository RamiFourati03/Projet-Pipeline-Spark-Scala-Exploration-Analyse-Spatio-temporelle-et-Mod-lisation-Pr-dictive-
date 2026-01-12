import com.nyctaxi.utils.TaxiUtils
import org.apache.spark.sql.functions._

object TaxiPhase3 {
  def main(args: Array[String]): Unit = {
    val spark = TaxiUtils.getSparkSession("TaxiPhase3 - SpatioTemporal")
    val df = spark.read.parquet("data/taxi_cleaned.parquet")
    val dfZones = TaxiUtils.loadZones(spark, "data/taxi_zone_lookup.csv")

    val dfWithZones = df
      .join(dfZones.withColumnRenamed("LocationID", "PULocationID"), Seq("PULocationID"), "left")
      .withColumnRenamed("Zone", "Pickup_Zone")
      .join(dfZones.withColumnRenamed("LocationID", "DOLocationID"), Seq("DOLocationID"), "left")
      .withColumnRenamed("Zone", "Dropoff_Zone")

    println("Top 5 Pickup Zones:")
    dfWithZones.groupBy("Pickup_Zone").count().orderBy(desc("count")).show(5)

    spark.stop()
  }
}
