import com.nyctaxi.utils.TaxiUtils
import org.apache.spark.sql.functions._

object TaxiPhase1 {
  def main(args: Array[String]): Unit = {
    val spark = TaxiUtils.getSparkSession("TaxiPhase1 - Ingestion")
    val dfRaw = TaxiUtils.loadData(spark, "data/yellow_tripdata_2024-01.parquet")

    println(s"Phase 1: Ingestion de ${dfRaw.count()} lignes.")
    dfRaw.printSchema()

    // Analyse spatiale initiale
    dfRaw.select(min("tpep_pickup_datetime"), max("tpep_pickup_datetime")).show()
    
    spark.stop()
  }
}
