(ns clj.new.re-frame-template.options.test
  (:require [clj.new.re-frame-template.options.helpers :as helpers]))

(def option "+test")

(defn files [data]
  [["test/cljs/{{sanitized}}/core_test.cljs" (helpers/render "test/cljs/core_test.cljs" data)]])
