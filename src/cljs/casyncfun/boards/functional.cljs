(ns casyncfun.client.boards.functional)

(def HORIZONTAL 0)
(def VERTICAL   1)
(def EMPTY      100)
(def DESTROYED  101)
(def MISSED     102)
(def ACTIVE     "active")
(def GAME-OVER  "game over")

(defn horizontal? [orientation]
  (= orientation HORIZONTAL))

(defn row [grid y]
  (nth grid y))

(defn column [grid x]
  (mapv #(nth % x) grid))

(defn ship-fits? [grid x y size orientation]
  (if (horizontal? orientation)
    (> (count (nth grid y))
       (+ x size))
    (> (count grid)
       (+ y size))))

(defn empty-grid [width height]
  (let [cells (repeat EMPTY)
        rows  (repeat (vec (take width cells)))]
    (vec (take height rows))))

(defn value-at [grid x y]
  (-> grid
      (nth y)
      (nth x)))

(defn set-value [grid x y value]
  (assoc-in grid [y x] value))

(defn missed-value? [value]
  (= value MISSED))

(defn empty-value? [value]
  (= value EMPTY))

(defn ship-value? [value]
  (> value 200))

(defn ship-collision? [grid x y size orientation]
  (if (horizontal? orientation)
    (let [row (row grid y)]
      (not (every? empty-value? (take size (drop (dec x) row)))))
    (let [column (column grid x)]
      (not (every? empty-value? (take size (drop (dec y) column)))))))

(defn ship-can-be-placed? [grid x y size orientation]
  (and (ship-fits? grid x y size orientation)
       (not (ship-collision? grid x y size orientation))))

(defn set-ship
  [grid x y size orientation id]
  (if (horizontal? orientation)
    (let [row     (row grid y)
          pre     (take x row)
          ship    (take size (repeat id))
          post    (drop (+ x size) row)
          new-row (vec (concat pre ship post))]
      (assoc-in grid [y] new-row))
    (let [column (column grid x)
          pre        (take y column)
          ship       (take size (repeat id))
          post       (drop (+ y size) column)
          new-column (concat pre ship post)]
      (mapv (fn [row value]
              (assoc row x value))
            grid new-column))))

(defn ship-at? [grid x y]
  (> (value-at grid x y) 200))

(defn random-ship [grid min-ship-size max-ship-size]
  {:pre [(> max-ship-size min-ship-size)]}
  (let [y           (rand-int (count grid))
        x           (rand-int (count (get grid y)))
        size        (+ (rand-int (- max-ship-size min-ship-size)) min-ship-size)
        orientation (rand-nth [HORIZONTAL VERTICAL])]
    {:x x :y y :size size :orientation orientation}))

(defn populate-grid
  [grid ship-count min-ship-size max-ship-size]
  (loop [grid grid
         ship-count ship-count]
    (if (pos? ship-count)
      (let [ship     (random-ship grid min-ship-size max-ship-size)
            id       (+ ship-count 200)
            {:keys   [x y size orientation]} ship
            success? (ship-can-be-placed? grid x y size orientation)
            grid     (if success? (set-ship grid x y size orientation id)
                         grid)]
        (if success?
          (recur grid (dec ship-count))
          (recur grid ship-count)))
      grid)))

(defn missed-count [grid]
  (count (filter missed-value? (flatten grid))))

(defn undestroyed-ships-exist? [grid]
  (let [grid (flatten grid)]
    (some ship-value? grid)))

(defn fire [grid x y]
  (if (ship-value? (value-at grid x y))
    (set-value grid x y DESTROYED)
    (set-value grid x y MISSED)))

(defn game-over? [grid]
  (not (undestroyed-ships-exist? grid)))

(defn game-status [grid]
  (if (undestroyed-ships-exist? grid)
    ACTIVE
    GAME-OVER))

(defn print-grid [grid]
  (doseq [row grid]
    (println row))
  grid)
