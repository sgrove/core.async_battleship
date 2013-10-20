(ns casyncfun.cljs-repl
  (:require [cemerick.piggieback]
            [cljs.repl.browser]))

(defn start-cljs-repl! []
  (cemerick.piggieback/cljs-repl
   :repl-env (doto (cljs.repl.browser/repl-env :port 9000)
               cljs.repl/-setup)))
