(ns AvenidaGasteiz.transform
  (:require 
    [clojure.string :as str]
    [grafter.rdf.protocols :as pr]
    [grafter.rdf :refer [prefixer s]] 
    [grafter.rdf.io :as io ]
    [AvenidaGasteiz.prefix :refer [base-graph base-domain base-medicion base-estacion]]
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

(defn ->s [st] (if st (s st) ""))

;Cambia el formato de la fecha [dd/mm/yyyy ~> dd-mm-yyyy]
(defn organizeDate [date]
  (when (seq date)
    (let [[d m y] (str/split date #"/")]
      (apply str (interpose "-" [y m d] )))))

(defn dateTime
  [date]
  (when  (seq date)
    (let [d date
          ;dt (str d "T" time)
          ]
      (read-string (str "#inst " (pr-str d))))))

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
                                                  (Integer/parseInt x))))

(defn pasoEstacion [estacion]
  (def Estacion estacion)
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
  (base-medicion
    (str Estacion "/" a "/" (removeSymbols NO2AQ))))
(defn base-PM10AQ [a] 
  (base-medicion 
    (str Estacion "/" a "/" (removeSymbols PM10AQ))))
(defn base-CO8hAQ [a]
  (base-medicion
    (str Estacion "/" a "/"(removeSymbols CO8hAQ) )))
(defn base-PM25AQ [a]
  (base-medicion
    (str Estacion "/" a "/" (removeSymbols PM25AQ) )))
(defn base-ICAAQ [a]
  (base-medicion
    (str Estacion "/" a "/" (removeSymbols ICAAQ) )))
(defn base-Benceno[a] 
  (base-medicion
    (str Estacion "/" a "/" Benceno)))
(defn base-CO[a]
  (base-medicion
    (str Estacion "/" a "/" CO )))
(defn base-CO8h [a] 
  (base-medicion
    (str Estacion "/" a "/" CO8h )))
(defn base-Etilbenceno[a] 
  (base-medicion
    (str Estacion"/" a "/" Etilbenceno)))
(defn base-NO[a] 
  (base-medicion
    (str Estacion "/" a "/" NO )))
(defn base-NO2[a] 
  (base-medicion
    (str Estacion "/" a "/" NO2 )))
(defn base-NOX[a] 
  (base-medicion
    (str Estacion "/" a "/" NOX )))
(defn base-Ortoxileno[a] 
  (base-medicion
    (str Estacion "/" a "/" Ortoxileno )))
(defn base-PM10[a] 
  (base-medicion
    (str Estacion "/" a "/" PM10)))
(defn base-PM25[a] 
  (base-medicion
    (str Estacion "/" a  "/" PM25)))
(defn base-Tolueno[a] 
  (base-medicion
    (str Estacion "/" a "/" Tolueno)))
(defn base-Location[a] 
  (base-estacion
    (str (removeSymbols a))))
