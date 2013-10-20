(ns casyncfun.support.cors)

(def headers
  {"Access-Control-Allow-Origin" "*"
   "Access-Control-Allow-Methods" "PUT, POST, GET, OPTIONS, DELETE"
   "Access-Control-Max-Age"       "1000"
   "Access-Control-Allow-Headers" "Content-Type"})
