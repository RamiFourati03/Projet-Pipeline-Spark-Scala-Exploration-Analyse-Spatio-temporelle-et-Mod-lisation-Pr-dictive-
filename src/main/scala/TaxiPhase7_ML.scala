import com.nyctaxi.utils.TaxiUtils
import org.apache.spark.sql.functions._
import org.apache.spark.ml.regression.RandomForestRegressor
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.evaluation.RegressionEvaluator

object TaxiPhase7_ML {
  def main(args: Array[String]): Unit = {
    val spark = TaxiUtils.getSparkSession("TaxiPhase7 - ML")
    val df = spark.read.parquet("data/taxi_ml_ready.parquet")

    val assembler = new VectorAssembler()
      .setInputCols(Array("trip_distance", "hour", "is_peak_hour"))
      .setOutputCol("features")

    val mlData = assembler.transform(df.select("fare_amount", "trip_distance", "hour", "is_peak_hour").withColumnRenamed("fare_amount", "label"))
    val Array(train, test) = mlData.randomSplit(Array(0.8, 0.2))

    val rf = new RandomForestRegressor().setLabelCol("label").setFeaturesCol("features")
    val model = rf.fit(train)
    val predictions = model.transform(test)

    val evaluator = new RegressionEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("r2")
    println(s"R2 Score: ${evaluator.evaluate(predictions)}")

    spark.stop()
  }
}
