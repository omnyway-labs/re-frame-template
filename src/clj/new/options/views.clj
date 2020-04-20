(ns clj.new.re-frame-template.options.views
  (:require [clj.new.re-frame-template.options.helpers :as helpers]
            [clj.new.re-frame-template.options.re-com :as re-com]))

(defn file [name data]
  [ ["src/{{sanitized}}/views.cljs"
     (helpers/render (str "src/" name ".cljs")
                     data)] ])


(defn view-cljs [options data]
  (cond (helpers/option? re-com/option options)
        (file "views_recom" data)

        :else
        (file "views" data) ))
