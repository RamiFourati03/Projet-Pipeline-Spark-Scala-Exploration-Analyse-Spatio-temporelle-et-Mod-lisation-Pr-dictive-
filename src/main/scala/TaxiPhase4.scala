import com.nyctaxi.utils.TaxiUtils
import org.apache.spark.sql.functions._

object TaxiPhase4 {
  def main(args: Array[String]): Unit = {
    val spark = TaxiUtils.getSparkSession("TaxiPhase4 - Financial")
    val df = spark.read.parquet("data/taxi_cleaned.parquet")

    val dfPayments = df.withColumn("payment_method", when(col("payment_type") === 1, "Credit Card")
      .when(col("payment_type") === 2, "Cash")
      .otherwise("Other"))

    dfPayments.groupBy("payment_method").agg(avg("tip_amount").as("avg_tip")).show()

    spark.stop()
  }
}
