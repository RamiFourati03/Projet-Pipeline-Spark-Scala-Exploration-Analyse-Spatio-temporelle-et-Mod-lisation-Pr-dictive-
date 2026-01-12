# 🚕 NYC Taxi Analysis & Price Predictor

An end-to-end Big Data pipeline for NYC Taxi data using Apache Spark (Scala) and Streamlit. Features 7 phases of processing: from ingestion and advanced cleaning to spatio-temporal analysis, ride-sharing simulations, and fare prediction using Random Forest (MLlib).

## 📊 Project Architecture
The project follows a modular batch processing architecture:
1. **Ingestion**: Reading Parquet files from NYC TLC.
2. **Cleaning**: Handling invalid dates (2007-2025 outliers), negative fares, and zero distances.
3. **Spatio-Temporal**: Joining with Zone Lookup to find hotspots in Manhattan.
4. **Financial**: Analyzing tips (26% average for Credit Card).
5. **Ride-Sharing**: Simulating window-based grouping for cost reduction.
6. **Feature Engineering**: Creating `is_peak_hour` and frequency variables.
7. **Machine Learning**: Random Forest model for fare prediction (R² ~ 0.79).

## 🏗️ File Structure
```
📂 src/main/scala/
├── TaxiPhase1.scala  (Ingestion)
├── TaxiPhase2.scala  (Cleaning)
├── TaxiPhase3.scala  (Spatio-Temporal)
├── TaxiPhase4.scala  (Financial)
├── TaxiPhase5.scala  (Ride-Sharing)
├── TaxiPhase6_Ext.scala (ML Ready Data)
├── TaxiPhase7_ML.scala (Random Forest)
└── TaxiUtils.scala   (Shared Utilities)
📂 data/               (Dataset storage)
📄 dashboard.py        (Streamlit UI)
📄 build.sbt           (SBT Config)
```

## 🚀 How to Run
1. **Prepare Data**: Place `yellow_tripdata_2024-01.parquet` in `/data`.
2. **Run Analysis**:
   ```bash
   spark-submit --class TaxiPhase2 target/scala-2.12/nyc_taxi_pipeline_2.12-1.0.jar
   ```
3. **Launch Dashboard**:
   ```bash
   streamlit run dashboard.py
   ```

## 👥 Auteur
- **Rami Fourati**
- **Assistant Antigravity** (Intégration)
