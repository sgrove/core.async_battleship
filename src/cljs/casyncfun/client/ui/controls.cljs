(ns casyncfun.client.ui.controls
  (:require [casyncfun.client.utils :as my-utils]
            [clojure.string :as string]
            [cljs.core.async :as async :refer [>! <! alts! chan sliding-buffer put! close!]]
            [dommy.utils :as utils]
            [dommy.core :as dommy])
  (:require-macros [cljs.core.async.macros :refer [go alt!]])
  (:use-macros [dommy.macros :only [node sel sel1]]))

(def start-button [:button.start "Start new game"])
(def stop-button [:button.start "Stop"])

(defn build-controls! [target]
  (let [signals (chan)
        signal! (fn [ch signal] (put! ch signal))
        start-button (-> (node start-button)
                         (dommy/listen! :click (partial signal! signals :start)))
        stop-button (-> (node stop-button)
                        (dommy/listen! :click (partial signal! signals :stop)))]
    (-> (sel1 target)
        (dommy/set-text! "")
        (dommy/append! start-button)
        (dommy/append! stop-button))
    signals))

;(build-controls! :.controls)


