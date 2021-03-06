(ns AvenidaGasteiz.prefix
    (:require [grafter.rdf :refer [prefixer]]))

;; Defines what will be useful for our next data transformations

(def base-domain (prefixer "http://opendata.euskadi.eus"))

(def base-graph (prefixer (base-domain "/graph/")))

(def base-estacion (prefixer (base-domain "/estacion/")))

(def base-medicion (prefixer (base-domain "/medicion/")))

