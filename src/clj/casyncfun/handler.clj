(ns casyncfun.handler
    (:require [compojure.handler :as handler]
              [compojure.route :as route]
              [compojure.response :as response]
              [casyncfun.handlers.api :as api]
              [casyncfun.handlers.content :as content]
              [casyncfun.views.content :as content-views])
    (:use [compojure.core]
          [ring.adapter.jetty]))

(defroutes not-found
  (route/not-found content-views/not-found))

(def app-routes
  (routes (var content/content-routes)
          (var api/api-routes)
          not-found))

(def app
  (-> (handler/site app-routes)))

(defonce servers (atom {}))

(defn create-server! [port background? & [ssl?]]
  (println (str "Creating server on port " port (when background? " in the background") " with " app))
  (run-jetty (var app) {:port port :join? (not background?)}))

(defn stop! [name]
  (.stop (get @servers name)))

(defn restart! [name]
  (.stop  (get @servers name))
  (.start (get @servers name)))

(defn quick-start! [& [port background? ssl?]]
  (swap! servers assoc :default (create-server! (or port 3000) (or background? true) ssl?)))

(defn start-named! [name & [port background? ssl?]]
  (swap! servers assoc name (create-server! (or port 3000) (or background? true) ssl?)))

(defn -main [& args]
  (start-named! :command-line (or (when-let [port (first args)] (Integer/parseInt port)) 3000) false false))

;(start-named! :auto-start 3000 true false)

