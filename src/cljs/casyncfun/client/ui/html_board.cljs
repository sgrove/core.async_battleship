(ns casyncfun.client.ui.html-board
  (:require [casyncfun.client.utils :as my-utils]
            [clojure.string :as string]
            [clojure.walk :as walk]
            [dommy.utils :as utils]
            [dommy.core :as dommy]
            [cljs.core.async :as async :refer [>! <! alts! chan sliding-buffer put! close!]])
  (:require-macros [cljs.core.async.macros :as am :refer [go alt!]])
  (:use-macros [dommy.macros :only [node sel sel1]]))

(def alphabet (seq (string/upper-case " abcdefghijklmnopqrstuvwxyz")))

(def base-style {:width  "10px"
                 :height "10px"})

(def colors ["blue" "green" "red" "pink" "white" "black"])

(def status->color {100 "blue"
                    101 "red"
                    102 "yellow"})

(def unknown-style {:color :none
                    :background-color (rand-nth colors)})

(defn row [row-data id]
  [:tr (map (fn [cell x]
              [:td {:style (str "width:40px;height:40px;color:none;background-color:" (get status->color cell) ";")
                    :data-x x
                    :data-y id
                    :status cell} cell]) row-data (range))])

(defn header-row [s]
  [:tr {:style "text-align: center;"} (map (fn [content] [:td content]) s)])

(defn board [board-data]
  [:table.board
   [:tbody (map row board-data (range))]])

(defn headers [headers]
  [:thead (header-row headers)])

(defn add-row-label [row label]
  [(first row) (concat [[:td {:data-label true} label]] (second row))])

(defn add-row-labels [board offset labels]
  (update-in board offset #(map add-row-label % labels)))

(defn update-cell! [elem cell]
  (dommy/set-style! elem :background-color (get status->color cell)))

(defn update-row! [elems cells]
  (dorun (map update-cell! elems cells)))

(defn target->rows [target width]
  (let [elems  (sel [target :td])]
    ; Get rid of headers and labels
    (map (partial drop 1) (drop 1 (partition width elems)))))

(defn update-board! [target board-data]
  (let [width (count (first board-data))
        rows (target->rows target width)]
    (dorun (map update-row! rows board-data))))

(defn build-board* [board-data]
  (let [width (count (first board-data))
        board (-> (board board-data)
                  (add-row-labels [1 1] (drop 1 (range))))
        header-seq (take (inc width) alphabet)]
    (into [] (concat [] (subvec board 0 1) [(headers header-seq)] (subvec board 1)))))

(defn build-board! [target board-data]
  (-> (sel1 :.canvas-target)
      (dommy/append! (node (build-board* board-data)))))

(defn play-event [ch message event]
  (let [target (.-target event)
        x (js/parseInt (dommy/attr target "data-x"))
        y (js/parseInt (dommy/attr target "data-y"))]
    (when (every? integer? [x y])
      (go (>! ch [message {:x x :y y} event])))))

(defn listen-to-board! [target]
  (let [ch (chan)
        proxy-fn (partial play-event ch :play)]
    (.log js/console "Ch: " ch)
    (-> (sel1 target)
        (dommy/listen! :click proxy-fn))
    ch))

(defn clear-board! [target]
  (doseq [l (keys (:click (first (vals (dommy/event-listeners (sel1 target))))))]
    (dommy/unlisten! (sel1 target) :click l))
  (-> (sel1 target)
      (dommy/clear!)))
