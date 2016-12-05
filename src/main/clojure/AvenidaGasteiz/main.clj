(ns AvenidaGasteiz.main
    (:require [grafter.tabular :refer [_ add-column add-columns apply-columns
                              build-lookup-table column-names columns
                              derive-column drop-rows graph-fn grep make-dataset
                              mapc melt move-first-row-to-header read-dataset
                              read-datasets rows swap swap take-rows
                              test-dataset test-dataset ]]
            [grafter.pipeline :refer [declare-pipeline]]
            [grafter.rdf]
            [grafter.rdf.io :refer [IStatement->sesame-statement]]
            [grafter.rdf :refer [s]]
            [grafter.vocabularies.qb :refer :all]
            [grafter.rdf.protocols :refer [->Quad]]
            [grafter.rdf.protocols :refer [ITripleWriteable]]
            [grafter.rdf.templater :refer [graph]]
            [grafter.rdf.formats :refer [rdf-nquads rdf-turtle]]
            [grafter.vocabularies.qb :refer :all]
            [grafter.pipeline :refer [declare-pipeline]]
            [grafter.vocabularies.rdf :refer :all]
            [grafter.vocabularies.foaf :refer :all]
            [AvenidaGasteiz.transform :refer :all]       
            [AvenidaGasteiz.prefix :refer :all]
            [clojure.string :as str]
              ))

(def make-graph
 (graph-fn [{:keys [
    ;Datos String
    observation-Benceno
    Date base-date
    CO-8h-Air-Quality varCO8AQ-CAST ;Version Castellano-Euskera
    NO2-Air-Quality  varNO2AQ-CAST  ;Version Castellano-Euskera
    PM10-Air-Quality varPM10AQ-CAST ;Version Castellano-Euskera
    PM25-Air-Quality varPM25AQ-CAST ;Version Castellano-Euskera
    ICA-estacion varICAE-CAST ;Version Castellano-Euskera
    ;Datos numéricos
    Benceno CO CO-8h Etilbenceno NO NO2 NOX Ortoxileno                
    PM10 PM25 Tolueno
    ]
             :as row }]
           ;Nombre de la 
            (graph (base-graph "AV-GASTEIZ") 
                
                  
              ["http://purl.org/dc/terms/date"
               [base-Benceno (row "Benceno")]
               [base-CO (row "CO")]
               [base-CO8h (row "CO8h")] ;COMPROBAR LUEGO
               [base-CO8hAQ  (removeSymbols (row "CO-8h-Air-Quality"))]
               [base-CO8hAQ  (removeSymbols varCO8AQ-CAST)]
               [base-Etilbenceno (row "Etilbenceno")]
               [base-NO (row "NO")]
               [base-NO2 (row "NO2")]
               [base-NO2AQ  (removeSymbols (row "NO2-Air-Quality"))]
               [base-NO2AQ  (removeSymbols varNO2AQ-CAST)]
               [base-NOX (row "NOX")]
               [base-Ortoxileno (row "Ortoxileno")]
               [base-PM10 (row "PM10")]
               [base-PM10AQ  (removeSymbols (row "PM10-Air-Quality"))]
               [base-PM10AQ  (removeSymbols varPM10AQ-CAST)]
               [base-PM25 (row "PM25")]
               [base-PM25AQ  (removeSymbols (row "PM25-Air-Quality"))]
               [base-PM25AQ  (removeSymbols varPM25AQ-CAST)]
               [base-Tolueno (row "Tolueno")]
               [base-ICAAQ  (removeSymbols (row "ICA-estacion"))]
               [base-ICAAQ (removeSymbols varICAE-CAST)]
               ]
               [observation-Benceno
                [rdf:a qb:Observation]
                [rdfs:comment (->s (str "The date the data was taken" ))]
                ["http://purl.org/dc/terms/date" (->s (str base-date ))]
                   ]
             ))) 
             
  
			   
(defn convert-data-to-data
  [data-file]
  (-> (read-dataset data-file)
    ;Creamos el dataset de los datos a cargar
    (make-dataset ["Date" "Benceno" "CO" "CO-8h" "CO-8h-Air-Quality"
                "Etilbenceno" "NO" "NO2"
                "NO2-Air-Quality" "NOX" "Ortoxileno"
                "PM10" "PM10-Air-Quality" "PM25"
                "PM25-Air-Quality" "Tolueno" "ICA-estacion"])
    ;Borra la primera fila correspondiente a los nombres de las columnas
    (drop-rows 1)
    ;Creamos nuevas columnas donde almacenar el valor en castellano de las columnas
    (derive-column :varCO8AQ-CAST "CO-8h-Air-Quality")
    (derive-column :varNO2AQ-CAST "NO2-Air-Quality")
    (derive-column :varPM10AQ-CAST "PM10-Air-Quality")
    (derive-column :varPM25AQ-CAST "PM25-Air-Quality")
    (derive-column :varICAE-CAST "ICA-estacion")
      (mapc {"Date" organizeDate
          "Benceno" parseValue
          "CO" parseValue
          "CO-8h" parseValue
          ;Version euskera
          "CO-8h-Air-Quality" makeSplitEusk
          ;Version castellano
          :varCO8AQ-CAST makeSplitCast
          "Etilbenceno" parseValue
          "NO" parseValue
          "NO2" parseValue
          ;Version euskera 
          "NO2-Air-Quality" makeSplitEusk
           ;Version castellano
          :varNO2AQ-CAST makeSplitCast
          "NOX" parseValue
          "Ortoxileno" parseValue
          "PM10" parseValue
          ;Version euskera
          "PM10-Air-Quality" makeSplitEusk
          ;Version castellano
          :varPM10AQ-CAST makeSplitCast
          "PM25" parseValue
          ;Version euskera
          "PM25-Air-Quality" makeSplitEusk
          ;Version castellano
          :varPM25AQ-CAST makeSplitCast
          "Tolueno" parseValue
          ;Version euskera
          "ICA-estacion" makeSplitCast
          ;Version castellano
          :varICAE-CAST makeSplitEusk
          })
      (derive-column :observation-Benceno [:Benceno] base-prefix)
      (derive-column :base-date [:Date])
      ))


(defn convert-data-to-graph
  [dataset]
  (-> dataset convert-data-to-data make-graph missing-data-filter))


(declare-pipeline convert-data-to-graph [Dataset -> (Seq Statement)]
                  {dataset "The data file to convert into a graph."})

;Convierte una IStatement en una statement Sesame
(defn convertidor [is]
  (map IStatement->sesame-statement (convert-data-to-graph is)))