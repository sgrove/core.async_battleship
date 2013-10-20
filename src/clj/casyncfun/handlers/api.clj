(ns casyncfun.handlers.api
  (:use [compojure.core]
        [ring.adapter.jetty])
  (:require [clojure.walk :as walk]
            [clojure.string :as string]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [ring.middleware.params :as params]
            [casyncfun.support.cors :as cors]
            [ring.middleware.stacktrace :as stacktrace]))

(defroutes cors-routes
  (OPTIONS "/api/v1/cors-test" []
           {:headers cors/headers
            :body "OK"}))

(defroutes unauthenticated-routes
  (GET "/api/v1/test" [name]
    {:body {:greeting (str "Hello, " name)}})
  (POST "/api/v1/cors-test" [name]
    {:headers cors/headers
     :body {:name name}}))

(def all-routes
  (routes (handler/api cors-routes)
          (handler/api unauthenticated-routes)))

(def api-routes
  (-> all-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-params)
      (params/wrap-params)
      (middleware/wrap-json-response)
      (stacktrace/wrap-stacktrace)))
