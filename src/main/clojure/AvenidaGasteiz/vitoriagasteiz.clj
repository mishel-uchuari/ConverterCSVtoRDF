(ns AvenidaGasteiz.vitoriagasteiz
    (:require [grafter.tabular :refer [_ add-column add-columns apply-columns
                              build-lookup-table column-names columns
                              derive-column drop-rows graph-fn grep make-dataset
                              mapc melt move-first-row-to-header read-dataset
                              read-datasets rows swap swap take-rows
                              test-dataset test-dataset ]]
            [grafter.pipeline :refer [declare-pipeline]]
            [grafter.rdf :as rdf]
            [grafter.rdf.io :as io]
           ; [grafter.rdf :refer [xsd:dateTime]]
            [grafter.vocabularies.qb :refer :all]
            [grafter.rdf.protocols :refer [->Quad]]
            [grafter.rdf.protocols :refer [ITripleWriteable]]
            [grafter.rdf.templater :refer [graph]]
            [grafter.rdf.formats :refer [rdf-nquads rdf-turtle]]
            [grafter.vocabularies.qb :refer :all]
            [grafter.pipeline :refer [declare-pipeline]]
            [grafter.vocabularies.rdf :refer :all]
            [AvenidaGasteiz.transform :refer :all]       
            [AvenidaGasteiz.prefix :refer :all]
            [clojure.string :as str]
              )
     )

(pasoEstacion "AV-GASTEIZ")

(def make-graph
 (graph-fn [{:keys [
   ;Datos observaciones
   observation-CO
    observation-Benceno observation-CO8h observation-C8hAQ 
    observation-CO observation-CO8h 
    observation-C8hAQ observation-Etilbenceno observation-NO
    observation-NO2 observation-NO2AQ observation-NOX 
    observation-Ortoxileno observation-PM10 observation-PM10AQ 
    observation-PM25 observation-PM25AQ observation-Tolueno
    observation-ICAAQ Date dateValue
     ;Datos String
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
            (graph (base-graph "AirQuality") 
             
               [observation-Benceno
                [rdf:a qb:Observation]
                 [rdfs:comment (langEn (str "The value of Benceno in a determinate date") )]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "Benceno")]
                   ]
                [observation-CO
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of CO in a determinate date") )]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/mg.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "CO")]
                   ]
                [observation-CO8h
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of CO8h in a determinate date"))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/mg.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "CO8h")]
                   ]
                [observation-C8hAQ
                 [rdf:a qb:Observation]
                 [rdfs:comment (langEn (str "CO Air Quality in a determinate date" ))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (langVq (str (removeSymbols (row "CO-8h-Air-Quality"))))]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (langSp (str (removeSymbols varCO8AQ-CAST)))]
                   ]
                 [observation-Etilbenceno
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of Etilbenceno in a determinate date"))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "Etilbenceno")]
                   ]
                   [observation-NO
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of NO in a determinate date"))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "NO")]
                   ]
                    [observation-NO2
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of NO2 in a determinate date"))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "NO2")]
                   ]
                   [observation-NO2AQ
                 [rdf:a qb:Observation]
                 [rdfs:comment (langEn (str "NO2 Air Quality in a determinate date" ))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (langVq (str (removeSymbols (row "NO2-Air-Quality"))))]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (langSp (str (removeSymbols varNO2AQ-CAST)))]
                   ]
                    [observation-NOX
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of NOX in a determinate date"))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "NOX")]
                   ]
                    [observation-Ortoxileno
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of Ortoxileno in a determinate date"))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "Ortoxileno")]
                   ]
                   [observation-Ortoxileno
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of Ortoxileno in a determinate date"))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "Ortoxileno")]
                   ]
                    [observation-PM10
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of PM10 in a determinate date"))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "PM10")]
                   ]   
                  [observation-PM10AQ 
                 [rdf:a qb:Observation]
                 [rdfs:comment (langEn (str "PM10 Air Quality in a determinate date" ))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (langVq (str (removeSymbols (row "PM10-Air-Quality"))))]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (langSp (str (removeSymbols varPM25AQ-CAST)))]
                   ] 
                   [observation-PM25
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of PM25 in a determinate date"))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "PM25")]
                   ]  
                    [observation-PM25AQ 
                 [rdf:a qb:Observation]
                 [rdfs:comment (langEn (str "PM10 Air Quality in a determinate date" ))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (langVq (str (removeSymbols (row "PM25-Air-Quality"))))]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (langSp (str (removeSymbols varPM10AQ-CAST)))]
                   ] 
                    [observation-Tolueno
                 [rdf:a qb:Observation]
                 [rdfs:comment  (langEn (str "The value of Tolueno in a determinate date"))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure" "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (row "Tolueno")]
                   ]
                    [observation-ICAAQ
                 [rdf:a qb:Observation]
                 [rdfs:comment (langEn (str "PM10 Air Quality in a determinate date" ))]
                 ["http://purl.org/dc/terms/date" dateValue]
                 ["http://www.w3.org/2003/01/geo/wgs84_pos#location" "http://opendata.euskadi.eus/estacion/AV-GASTEIZ"]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (langVq (str (removeSymbols (row "ICA-estacion"))))]
                 ["http://purl.org/linked-data/sdmx/2009/measure#obsValue" (langSp (str (removeSymbols varICAE-CAST)))]
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
      (derive-column :dateValue [:Date] dateTime)
      (derive-column :observation-Benceno [:Date] base-Benceno)
      (derive-column :observation-CO [:Date] base-CO)
      (derive-column :observation-CO8h [:Date] base-CO8h)
      (derive-column :observation-C8hAQ [:Date] base-CO8hAQ)
      (derive-column :observation-Etilbenceno [:Date] base-Etilbenceno)
      (derive-column :observation-NO [:Date] base-NO)
      (derive-column :observation-NO2 [:Date] base-NO2)
      (derive-column :observation-NO2AQ [:Date] base-NO2AQ)
      (derive-column :observation-NOX [:Date] base-NOX)
      (derive-column :observation-Ortoxileno [:Date] base-Ortoxileno)
      (derive-column :observation-PM10 [:Date] base-PM10)
      (derive-column :observation-PM10AQ [:Date] base-PM10AQ)
      (derive-column :observation-PM25 [:Date] base-PM25)
      (derive-column :observation-PM25AQ [:Date] base-PM25AQ)
      (derive-column :observation-Tolueno [:Date] base-Tolueno)
      (derive-column :observation-ICAAQ [:Date] base-ICAAQ)
      ))


(defn convert-data-to-graph
  [dataset]
  (-> dataset convert-data-to-data make-graph missing-data-filter))


(declare-pipeline convert-data-to-graph [Dataset -> (Seq Statement)]
                  {dataset "The data file to convert into a graph."})

;Convierte una IStatement en una statement Sesame
(defn convertidor [is]
  (map io/IStatement->sesame-statement (convert-data-to-graph is)))