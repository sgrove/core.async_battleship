(ns casyncfun.client.utils)

(defn log [& msg]
  (.apply (.-log js/console) js/console (clj->js msg)))
