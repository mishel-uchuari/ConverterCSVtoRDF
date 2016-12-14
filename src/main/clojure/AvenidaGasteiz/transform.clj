(ns AvenidaGasteiz.transform
  (:require 
    [clojure.string :as str]
    [grafter.rdf.protocols :as pr]
  ;  [grafter.rdf :refer [prefixer s]] 
    [grafter.rdf.io :as io ]
    [AvenidaGasteiz.prefix :refer [base-graph base-domain base-value]]
    )
)



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
      (apply str (interpose "-" [y m d] )))))


(defn removeSymbols
  [st]
  (let [replace clojure.string/replace]
    
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
     )
   )



;Convertidor a Float o Integer
(defmulti parseValue class)
(defmethod parseValue :default            [x] x)
(defmethod parseValue nil                 [x] nil)
(defmethod parseValue java.lang.Character [x] (Character/getNumericValue x))
(defmethod parseValue java.lang.String    [x] (if (= "" x)
                                                nil
                                                (if (.contains x ".")
                                                  (Double/parseDouble x)
                                                  (Integer/parseInt x)))
  )



(defn langSp
  [st]
    (io/s st :es))

(defn langEn
  [st]
    (io/s st :en))

(defn langVq
  [st]
    (io/s st :eu))


(defn missing-data-filter [triples]
                               (remove #(nil? (pr/object %)) triples))
(defn base-date[a] 
    (str "/" a ))

;(defn ->s [st] (if st (s st) ""))

(def CO8hAQ "CO-8h-AirQuality")
(def NO2AQ "NO2-AirQuality")
(def PM10AQ "PM10-AirQuality")
(def PM25AQ "PM25-AirQuality")
(def ICAAQ "Ica-Estacion-AirQuality")

(def CO "CO")
(def CO8h "CO8h")
(def Etilbenceno "Etilbenceno")
(def NO "NO")
(def NO2 "NO2")
(def NOX "NOX")
(def Ortoxileno "Ortoxileno")
(def PM10 "PM10")
(def PM25 "PM25")
(def Tolueno "Tolueno")
(def Benceno "Benceno")


(defn base-NO2AQ [a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" (removeSymbols NO2AQ))))
(defn base-PM10AQ [a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" (removeSymbols PM10AQ))))
(defn base-CO8hAQ [a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" (removeSymbols CO8hAQ) )))
(defn base-PM25AQ [a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" (removeSymbols PM25AQ) )))
(defn base-ICAAQ [a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" (removeSymbols ICAAQ) )))
(defn base-Benceno[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" Benceno )))
(defn base-CO[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" CO )))
(defn base-CO8h [a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" CO8h )))
(defn base-Etilbenceno[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" Etilbenceno)))
(defn base-NO[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" NO )))
(defn base-NO2[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" NO2 )))
(defn base-NOX[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" NOX )))
(defn base-Ortoxileno[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" Ortoxileno )))
(defn base-PM10[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" PM10)))
(defn base-PM25[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" PM25)))
(defn base-Tolueno[a] 
  (base-domain 
    (str "/AV-GASTEIZ/" a "/" Tolueno)))
