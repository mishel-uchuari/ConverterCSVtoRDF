(ns AvenidaGasteiz.transform
  (:require 
    [clojure.string :as str]
     [grafter.rdf.protocols :as pr]
       [grafter.rdf :refer [prefixer s]]
    [AvenidaGasteiz.prefix :refer [base-id base-graph base-domain base-element]]
    )
)

(defn toInteger
  "An example transformation function that converts a string to an integer"
  [s]
  (Integer/parseInt (str/trim s)))

(defn toFloat
  "An example transformation function that converts a string to an integer"
  [s]
  (Float/parseFloat (str/trim s)))

;Elimina los espacios en blanco en un string
(defn removeBlanks [string]
  (when (seq string)
    (str/replace string " " "")
  ))

;Metodo hace split a un string determinado
;Version castellano
(defn makeSplitCast[string]
(def pepi (str/split string #"/"))
(nth pepi 0)
)

;Metodo hace split a un string determinado
;Version euskera
(defn makeSplitEusk[string]
(def pepi (str/split string #"/"))
(get pepi 1)
)


;Cambia el formato de la fecha [dd/mm/yyyy ~> dd-mm-yyyy]
(defn organizeDate [date]
  (when (seq date)
    (let [[d m y] (str/split date #"/")]
      (apply str (interpose "-" [d m y] )))))


(defn removeSymbols
  [st]
  (let [replace clojure.string/replace]
    (base-id 
      (-> (str st)
          clojure.string/trim
          (replace "(" "-")
          (replace ")" "")
          (replace "  " "")
          (replace "," "-")
          (replace "." "")
          (replace " " "-")
          (replace "/" "-")
          (replace "'" "")
          (replace "---" "-")
          (replace "--" "-")
          )
     ))
   )

(defn base-data[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a )))
  
;Conviertidor a Float o Integer
(defmulti parseValue class)
(defmethod parseValue :default            [x] x)
(defmethod parseValue nil                 [x] nil)
(defmethod parseValue java.lang.Character [x] (Character/getNumericValue x))
(defmethod parseValue java.lang.String    [x] (if (= "" x)
                                                nil
                                                (if (.contains x ".")
                                                  (Double/parseDouble x)
                                                  (Integer/parseInt x))))


(defn missing-data-filter [triples]
                               (remove #(nil? (pr/object %)) triples))
(defn base-date[a] 
    (str "/" a ))

(defn ->s [st] (if st (s st) ""))

(def base-CO8hAQ (base-element "CO-8h-Air-Quality"))
(def base-NO2AQ (base-element "NO2-Air-Quality"))
(def base-PM10AQ (base-element "PM10-Air-Quality"))
(def base-PM25AQ (base-element "PM25-Air-Quality"))
(def base-ICAAQ (base-element "ICA-estacion"))

(def base-Benceno (base-element "Benceno-mg-m3"))
(def base-CO (base-element "CO-mg-m3"))
(def base-CO8h (base-element "CO-8h-mg-m3"))
(def base-Etilbenceno (base-element "Etilbenceno-microg-m3"))
(def base-NO (base-element "NO-microg-m3"))
(def base-NO2 (base-element "NO2-microg-m3"))
(def base-NOX (base-element "NOX-microg-m3"))
(def base-Ortoxileno (base-element "Ortoxileno-microg-m3"))
(def base-PM10 (base-element "PM10-microg-m3"))
(def base-PM25 (base-element "PM25-microg-m3"))
(def base-Tolueno (base-element "Tolueno-microg-m3"))
