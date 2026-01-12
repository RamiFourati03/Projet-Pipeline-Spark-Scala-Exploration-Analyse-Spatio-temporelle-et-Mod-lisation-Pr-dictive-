import com.nyctaxi.utils.TaxiUtils
import org.apache.spark.sql.functions._

object TaxiPhase6_Extension {
  def main(args: Array[String]): Unit = {
    val spark = TaxiUtils.getSparkSession("TaxiPhase6 - Extension")
    val df = spark.read.parquet("data/taxi_cleaned.parquet")

    val dfEnriched = df
      .withColumn("is_peak_hour", when(col("hour").between(7, 9) || col("hour").between(17, 19), 1).otherwise(0))
      .withColumn("tip_percentage", when(col("total_amount") > 0, (col("tip_amount") / col("total_amount")) * 100).otherwise(0))

    dfEnriched.write.mode("overwrite").parquet("data/taxi_ml_ready.parquet")
    spark.stop()
  }
}
