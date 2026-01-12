# Rapport de Projet : Analyse Big Data des Trajets de Taxis à New York
**Auteur :** Rami Fourati
**Année universitaire :** 2024 – 2025
**Technologies :** Apache Spark (Scala), MLlib, Streamlit

---

## 1. Introduction Générale
Ce projet propose une analyse Big Data complète des trajets de taxis new-yorkais à l’aide du framework Apache Spark et du langage Scala. L’étude couvre l’ensemble du cycle analytique, de l’ingestion brute à la modélisation prédictive, sur plus de 4 millions d'enregistrements.

## 2. Architecture du Pipeline
L'architecture repose sur une approche modulaire en 7 phases :
1. **Phase 1 :** Ingestion et exploration (Diagnostic).
2. **Phase 2 :** Nettoyage et transformations (Feature Engineering).
3. **Phase 3 :** Analyse spatio-temporelle (Hotspots).
4. **Phase 4 :** Analyse financière (Paiements et pourboires).
5. **Phase 5 :** Simulation de Ride-Sharing (Covoiturage).
6. **Phase 6 :** Extension (Détection d'anomalies).
7. **Phase 7 :** Modélisation prédictive (Machine Learning).

## 3. Synthèse des Résultats

### 3.1 Qualité des Données (Phase 1 & 2)
- **Volume Initial :** 4,145,257 trajets.
- **Anomalies Détectées :** Dates allant de 2007 à 2025, distances négatives, tarifs incohérents.
- **Résultat après Nettoyage :** 3,079,050 trajets valides (74% de rétention).
- **Distances :** 92% des trajets sont courts (< 10 km).

### 3.2 Mobilité Urbaine (Phase 3)
- **Zones Dominantes :** Midtown Center, Upper East Side, JFK Airport.
- **Pics de Trafic :** Maximum à 18h (fin de journée de bureau).
- **Vitesse :** Plus élevée entre 4h et 6h du matin, minimale entre 15h et 17h.

### 3.3 Analyse Financière (Phase 4)
- **Paiements :** Domination du paiement par carte bancaire.
- **Pourboires :** Moyenne de **26.1%** pour les paiements par carte, quasi nul en espèces.

### 3.4 Ride-Sharing & Économies (Phase 5)
Grâce à un regroupement par fenêtre de 5 minutes, nous estimons :
- **1,081,941 km** économisés.
- **6.1 millions de dollars** de réduction de coûts.

### 3.5 Prédiction ML (Phase 7)
Modèle **Random Forest Regressor** :
- **R² Score : 0.79** (Le modèle explique 79% de la variance du prix).
- **RMSE : 9.10**.

## 4. Conclusion
Le pipeline Spark a permis de transformer une masse de données brute en insights stratégiques. La simulation de ride-sharing démontre le potentiel écologique et économique d'une gestion intelligente des flux, tandis que le modèle ML fournit une base solide pour la prédiction tarifaire dynamique.
