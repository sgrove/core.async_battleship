(ns casyncfun.handlers.content
  (:use [compojure.core])
  (:require [compojure.route :as route]
            [casyncfun.middlewares.wrap-dir-index :refer [wrap-dir-index]]
            [casyncfun.views.content :as content]))

(defroutes content-routes*
  (GET "/" [] content/home)
  (GET "/canvas" [] content/canvas)
  (GET "/help" [] content/help)
  (route/resources "/"))

(def content-routes
  (wrap-dir-index content-routes*))

