(ns casyncfun.client.utils)

(def logging-enabled? true)

(defn log [& msg]
  (when logging-enabled?
    (.apply (.-log js/console) js/console (clj->js msg))))
