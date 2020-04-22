(ns clj.new.re-frame-template
  (:require [clj.new.templates :refer [renderer project-data name-to-path sanitize-ns ->files]]
            [clj.new.re-frame-template.options.base :as base]
            [clj.new.re-frame-template.options.kondo :as kondo]
            [clj.new.re-frame-template.options.re-com :as re-com]
            [clj.new.re-frame-template.options.test :as test]
            [clj.new.re-frame-template.options.views :as views]
            [clj.new.re-frame-template.options.helpers :as helpers]
            [clj.new.re-frame-template.options.cider :as cider]
            [clojure.set :as set]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Files & Data for Template

(defn app-files [data options]
  (concat
   (base/files data)
   (views/view-cljs options data)

   ;; debug
   ;;

   ;; development
   (when (helpers/option? kondo/option options) (kondo/files data))
   (when (helpers/option? test/option options) (test/files data))

   ;; full-stack
   (when (helpers/option? cider/option options) (cider/files data))

   ;; misc.
   (when (helpers/option? re-com/option options) (re-com/assets data))))


(defn template-data [name options]
  (let [data (merge {;; :ns-name   (sanitize-ns name)
                     ;; :sanitized (name-to-path name)

                     ;; debug
                     :re-frisk? (helpers/option? "+re-frisk" options)
                     :10x?      (helpers/option? "+10x" options)

                     ;; development
                     :cider?   (helpers/option? cider/option options)
                     :kondo?   (helpers/option? kondo/option options)
                     :test?    (helpers/option? test/option options)

                     ;; full-stack

                     ;; misc.
                     :re-com?     (helpers/option? re-com/option options)
                     :re-pressed? (helpers/option? "+re-pressed" options)
                     :breaking-point? (helpers/option? "+breaking-point" options)}
                    (project-data name))]
    (clojure.pprint/pprint data))
  )



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Check Options

(def available-set
  #{;; debug
    "+re-frisk"
    "+10x"

    ;; development
    cider/option
    kondo/option
    test/option

    ;; misc.
    re-com/option
    "+re-pressed"
    "+breaking-point"})



(defn check-available [options]
  (let [options-set (into #{} options)
        abort?      (not (set/superset? available-set
                                        options-set))]
    (when abort?
      (println "\nError: invalid profile(s)\n")
      (System/exit 1))))


(defn check-options
  "Check the user-provided options"
  [options]
  (doto options
    check-available))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Main


(defn re-frame-template
  "Generate Scaffold for re-frame using tools.deps chain"
  [name & options]
  (let [data   (template-data name options)]
    (check-options options)
    (println "Generating fresh 'clj new' re-frame-template project.")
    (apply ->files data
           (app-files data options))))
