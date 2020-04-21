(ns clj.new.re-frame-template.options.helpers
  (:use [clj.new.templates :only [renderer sanitize]]
        [clojure.java.io :as io]))

(def template-name "re-frame-template")

(def render-text (renderer template-name))

(defn resource-input
  "Get resource input stream. Useful for binary resources like images."
  [resource-path]
  (-> (str "clj/new/" (sanitize template-name) "/" resource-path)
      io/resource
      io/input-stream))

(defn render
  "Render the content of a resource"
  ([resource-path]
   (resource-input resource-path))
  ([resource-path data]
   (render-text resource-path data)))

(defn option? [option-name options]
  (boolean
   (some #{option-name} options)))
