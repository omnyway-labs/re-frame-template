(ns clj.new.re-frame-template.options.base
  (:require [clj.new.re-frame-template.options.helpers :as helpers]))

(defn files [data]
  [[".gitignore" (helpers/render "gitignore" data)]
   ["README.md" (helpers/render "README.md" data)]
   ["deps.edn" (helpers/render "deps.edn" data)]
   ["project.clj" (helpers/render "project.clj" data)]
   ["shadow-cljs.edn" (helpers/render "shadow-cljs.edn" data)]
   ["package.json" (helpers/render "package.json" data)]
   ["karma.conf.js" (helpers/render "karma.conf.js" data)]
   ["dev/cljs/user.cljs" (helpers/render "dev/cljs/user.cljs" data)]
   ["resources/public/index.html" (helpers/render "resources/public/index.html" data)]
   ["src/{{sanitized}}/core.cljs" (helpers/render "src/core.cljs" data)]
   ["src/{{sanitized}}/config.cljs" (helpers/render "src/config.cljs" data)]
   ["src/{{sanitized}}/db.cljs" (helpers/render "src/db.cljs" data)]
   ["src/{{sanitized}}/subs.cljs" (helpers/render "src/subs.cljs" data)]
   ["src/{{sanitized}}/events.cljs" (helpers/render "src/events.cljs" data)]])
