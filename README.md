# 🚕 Analyse Big Data des Trajets de Taxis à New York

## 1. Introduction Générale
La mobilité urbaine constitue aujourd’hui un enjeu stratégique majeur pour les grandes métropoles, tant du point de vue économique qu’environnemental. La croissance démographique, l’intensification des flux de déplacements et la congestion routière imposent une meilleure compréhension des dynamiques de transport afin d’optimiser l’offre, réduire les coûts et améliorer l’expérience des usagers. 

La ville de New York, avec son réseau dense de taxis jaunes, représente un terrain d’étude particulièrement riche pour l’analyse des déplacements urbains à grande échelle. Chaque jour, plusieurs millions de trajets sont enregistrés, générant un volume massif de données hétérogènes, caractéristique des problématiques Big Data modernes. 

Ce projet propose une analyse Big Data complète des trajets de taxis new-yorkais à l’aide du framework Apache Spark et du langage Scala. L’étude couvre l’ensemble du cycle analytique :
*   **Ingestion et exploration** des données brutes.
*   **Nettoyage et transformation** pour garantir la qualité analytique.
*   **Analyse descriptive et spatio-temporelle** pour identifier les hotspots.
*   **Étude des comportements de paiement** et des pourboires.
*   **Simulation de scénarios de ride-sharing** (covoiturage urbain).
*   **Extension avancée** (détection d’anomalies et feature engineering).
*   **Modélisation prédictive** par apprentissage automatique (Machine Learning).

Les données analysées proviennent du jeu de données officiel de la NYC Taxi and Limousine Commission (TLC), stockées au format Parquet, garantissant une lecture efficace et une manipulation optimisée de volumes massifs.

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
