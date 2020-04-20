(ns clj.new.re-frame-template.options.cider
  (:require [clj.new.re-frame-template.options.helpers :as helpers]))

(def option "+cider")

(defn files [data]
  [[".dir-locals.el" (helpers/render "dir-locals.el" data)]])
