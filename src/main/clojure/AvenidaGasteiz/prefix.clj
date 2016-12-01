(ns AvenidaGasteiz.prefix
    (:require [grafter.rdf :refer [prefixer]]))

;; Defines what will be useful for our next data transformations

(def base-domain (prefixer "http://opendata.euskadi.eus"))

(def base-graph (prefixer (base-domain "/graph/")))

(def base-id (prefixer (base-domain "/resource/")))

(def base-element (prefixer (base-domain "/element/")))

(def base-sensor (prefixer (base-domain "/sensor/")))