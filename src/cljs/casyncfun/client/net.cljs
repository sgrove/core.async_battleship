(ns casyncfun.client.net
  (:require [casyncfun.client.utils :as my-utils]
            [clojure.string :as string]
            [cljs.core.async :as async :refer [>! <! alts! chan sliding-buffer put! close!]]
            [goog.net.XhrIo :as xhr]
            [goog.net.Jsonp :as jsonp])
  (:require-macros [cljs.core.async.macros :as am :refer [go alt!]]))


(def base-url
  "http://en.wikipedia.org/w/api.php?action=opensearch&format=json&search=")

(defn GET [url]
  (let [private-ch (chan 1)]
    (xhr/send url
              (fn [event]
                (let [res (-> event .-target .getResponseText)]
                  (go (>! private-ch res)
                      (close! private-ch)))))
    private-ch))

(defn example []
  (go (let [text (<! (GET "/"))]
        (my-utils/log (str "Textual reponse: " text)))))

(defn call-jsonp [url & [payload]]
  (my-utils/log (str "Calling: " url))
  (let [ch (chan 1)]
    (->  (goog.Uri. url)
         (goog.net.Jsonp. )
         (.send (or (clj->js payload) (js-obj))
                (fn [reply]
                  (go (>! ch reply)
                      (close! ch)))))
    ch))

(defn search-terms [terms]
  (let [results (chan)
        split-matches #(string/split % #",")
        chans (map (comp call-jsonp (partial str base-url)) terms)]
    chans))

(def results (chan))

;; (go
;;  (let [chs (search-terms ["techno" "trance" "music" "docs" "space" "scooters"])
;;        t (async/merge chs)]
;;    (.log js/console "T: " t)
;;    (loop [matches (<! t)]
;;      (when matches
;;        (>! results matches)
;;        (recur (<! t))))))

;; (go (.log js/console (map clj->js (<! (async/take! 100 results)))))
;; (go (.log js/console  (<! (async/take! 10 results))))
;; (go (.log js/console (clj->js (<! results))))
