(ns clj.new.re-frame-template.options.kondo
  (:require [clj.new.re-frame-template.options.helpers :as helpers]))

(def option "+kondo")

(defn files [data]
  [[".clj-kondo/config.edn" (helpers/render "clj-kondo/config.edn" data)]])
