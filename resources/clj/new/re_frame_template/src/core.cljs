(ns {{namespace}}.core
    (:require
     [reagent.dom :as rdom]
     [re-frame.core :as re-frame]{{#re-pressed?}}
     [re-pressed.core :as rp]{{/re-pressed?}}{{#breaking-point?}}
     [breaking-point.core :as bp]{{/breaking-point?}}
     [{{namespace}}.events :as events]{{#routes?}}
     [{{namespace}}.routes :as routes]{{/routes?}}
     [{{namespace}}.views :as views]
     [{{namespace}}.config :as config]
     ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (rdom/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn init []{{#routes?}}
  (routes/app-routes){{/routes?}}
  (re-frame/dispatch-sync [::events/initialize-db]){{#re-pressed?}}
  (re-frame/dispatch-sync [::rp/add-keyboard-event-listener "keydown"]){{/re-pressed?}}{{#breaking-point?}}
  (re-frame/dispatch-sync [::bp/set-breakpoints
                           {:breakpoints [:mobile
                                          768
                                          :tablet
                                          992
                                          :small-monitor
                                          1200
                                          :large-monitor]
                            :debounce-ms 166}]){{/breaking-point?}}
  (dev-setup)
  (mount-root))
