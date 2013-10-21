(ns casyncfun.main
  (:require [clojure.browser.repl :as repl]
            [cljs.core.async :as async :refer [>! <! alts! chan sliding-buffer put! close!]])
  (:require-macros [cljs.core.async.macros :as am :refer [go alt!]]))

(.log js/console "Running ClojureScript inside of casyncfun")

(def rc (chan))

(go (.log js/console "Outside")
    (loop []
      (.log js/console "Inside")
      (let [msg (<! rc)]
        (.log js/console "Message received: " msg)
        (recur))))

(put! rc "First message")
(put! rc "Second message")
