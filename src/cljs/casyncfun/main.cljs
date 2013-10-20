(ns casyncfun.main
  (:require [casyncfun.client.net :as net]
            [casyncfun.client.boards.functional :as game-board]
            [casyncfun.client.ui.html-board :as html-board]
            [casyncfun.client.ui.controls :as controls-ui]
            [casyncfun.client.utils :as my-utils]
            [clojure.browser.repl :as repl]
            [cljs.core.async :as async :refer [>! <! alts! chan sliding-buffer put! close!]]
            [dommy.utils :as utils]
            [dommy.core :as dommy])
  (:require-macros [cljs.core.async.macros :as am :refer [go alt!]])
  (:use-macros [dommy.macros :only [node sel sel1]]))

(.log js/console "Running ClojureScript inside of casyncfun")

;;************************************************
;; Dev stuff
;;************************************************

(defn ^:export nrepl []
  (repl/connect "http://localhost:9000/repl"))

(set! (.-nrepl js/window)
      nrepl)

(defn greet! []
  (when-let [el (sel1 :.cljs-target)]
    (dommy/set-text! el "ClojureScript Loaded!"))
  (net/example))

(set! (.-onload js/window) greet!)

;(dommy/set-text! (sel1 :.canvas-target) "")
;(html-board/build-board! :.canvas-target 2 2)

(defn set-status! [status]
  (-> (sel1 :.status)
      (dommy/set-text! status)))

(defn stop! [text]
  (my-utils/log "Got shutdown signal!")
  (set-status! (str "Stopped: " text))
  (html-board/clear-board! :.canvas-target))


(def controls-signals (controls-ui/build-controls! :.controls))

(defn new-game-board [width height ship-count min-ship-size max-ship-size]
  (-> (game-board/empty-grid width height)
      (game-board/populate-grid ship-count min-ship-size max-ship-size)))

(defn build [close-chan controls-chan target width height ship-count min-ship-size max-ship-size]
  (set-status! "Started")
  (let [game-board (new-game-board width height ship-count min-ship-size max-ship-size)
        board-elem (html-board/build-board! :.canvas-target game-board)
        play-ch (html-board/listen-to-board! :.canvas-target)]
    (go (loop [play-ch play-ch]
          (alt!
           close-chan ([v] (stop! v))
           play-ch ([[message data event]]
                      (do
                        (my-utils/log "Play at: " (:x data) (:y data))
                        (recur play-ch)))
           controls-chan ([v] (condp = v
                                :start (do (set-status! "Started via controls")
                                           (stop! "Stopped via controls")
                                           (html-board/build-board! :.canvas-target (inc (rand-int 26)) (inc (rand-int 26)))
                                           (let [new-play-ch (html-board/listen-to-board! :.canvas-target)]
                                             (recur new-play-ch)))
                                :stop  (do (stop! "Stopped via controls")
                                           (recur nil)))))))))

(def rc (chan))

(build rc controls-signals)
;(html-board/listen-to-board! :.canvas-target)
;; (go (my-utils/log "RC: " (<! rc)))
;; (go (my-utils/log "Control signal: " (<! controls-signals)))
;; (put! rc "stop please")
;; (go (>! rc "ok, really"))
