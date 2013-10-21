(ns casyncfun.main
  (:require [clojure.browser.repl :as repl]
            [cljs.core.async :as async :refer [>! <! alts! chan sliding-buffer put! close!]])
  (:require-macros [cljs.core.async.macros :as am :refer [go alt!]]))

(.log js/console "Running ClojureScript inside of casyncfun")

(defn ^:export nrepl []
  (repl/connect "http://localhost:9000/repl"))

(set! (.-nrepl js/window)
      nrepl)

(def rc (chan))
(def c2 (chan 100))

(def counter (atom 0))

(defn mark-status! [status]
  (swap! counter inc)
  (let [el (.getElementById js/document "status")
        text (str (.-innerHTML el) "<li>" @counter ". " status "</li>")]
    (set! (.-innerHTML el) text)))

(defn test []
  (let []
    (mark-status! "CLJS Loaded")

    (go (mark-status! "Outside")
        (loop []
          (mark-status! "Inside, never gets here.")
          (let [msg (<! rc)]
            (mark-status! (str "Message received: " msg))
            (recur))))

    (go (mark-status! (<! c2)))

    (put! c2 "My message")

    (put! rc "First message")
    (put! rc "Second message")

    (put! c2 "Another message")

    (put! rc "First message")
    (put! rc "Second message")))


(set! (.-onload js/window) test)
