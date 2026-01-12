import com.nyctaxi.utils.TaxiUtils
import org.apache.spark.sql.functions._

object TaxiPhase5 {
  def main(args: Array[String]): Unit = {
    val spark = TaxiUtils.getSparkSession("TaxiPhase5 - RideSharing")
    val df = spark.read.parquet("data/taxi_cleaned.parquet")

    val sharing = df.filter(col("trip_category") === "short_trip")
      .withColumn("pickup_window", window(col("tpep_pickup_datetime"), "5 minutes"))
      .groupBy("PULocationID", "pickup_window")
      .agg(count("*").as("group_size"), sum("fare_amount").as("total_fare"))
      .filter(col("group_size") > 1)

    sharing.show(10)
    spark.stop()
  }
}
