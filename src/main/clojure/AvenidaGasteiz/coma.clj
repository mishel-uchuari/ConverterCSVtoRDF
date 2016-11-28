(ns AvenidaGasteiz.coma)

(use 'clojure.java.io)



(defn pruebas [datos]
  
(def s (slurp datos))
(def sr (clojure.string/replace s "," "."))
(spit datos sr)
(def s (slurp datos))
(def sj (clojure.string/replace s ";" ","))

(spit datos sj)
)


;
;
;(ns AvenidaGasteiz.coma)
;
;(use 'clojure.java.io)
;
;
;
;(defn pruebas [datos]
;  
;(def s (slurp datos))
;
;(def sr (clojure.string/replace s "," "."))
;
;(spit "./data/ataun-coma-result-2015.csv" sr)
;(def s (slurp "./data/ataun-coma-result-2015.csv"))
;
;(def sj (clojure.string/replace s ";" ","))
;(spit "./data/ataun-coma-result-2014.csv" sj)
;
;)