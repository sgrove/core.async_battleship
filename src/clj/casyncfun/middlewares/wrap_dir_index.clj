(ns casyncfun.middlewares.wrap-dir-index)

(defn wrap-dir-index [handler]
  (fn [req]
    (handler
     (update-in req [:uri]
                #(if (and (= \/ (last %))
                          (not= % "/")) (str % "index.html") %)))))
