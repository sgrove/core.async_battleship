(ns casyncfun.network
  (:require [goog.net.XhrIo :as xhr]
            [cljs.core.async :as async :refer [chan close!]])
  (:require-macros [cljs.core.async.macros :refer [go alt!]]))


(def base-url
  "http://en.wikipedia.org/w/api.php?action=opensearch&format=json&search=")

(defn GET [url]
  (let [ch (chan 1)]
    (xhr/send url
              (fn [event]
                (let [res (-> event .-target .getResponseText)]
                  (get (>! ch res)
                       (close! ch)))))))

(defn log [s]
  (.log js/console (str s)))

(defn example []
  (go (log (str "Got a response:" (<! (GET "/"))))))
