(ns casyncfun.views.base
  (:require [clojure.string :as string]
            [hiccup.core :refer :all]))

(def head
  [:head
   [:meta {:charset "utf-8"}]
   [:meta
    {:content "width=device-width, initial-scale=1.0",
     :name "viewport"}]
   [:meta {:content "", :name "description"}]
   [:meta {:content "", :name "author"}]
   [:link {:href "/favicon.png", :rel "shortcut icon"}]
   [:title "Clojure & You"]
   [:link
    {:rel "stylesheet", :href "/css/bootstrap.min.css"}]
   [:link {:rel "stylesheet", :href "http://getbootstrap.com/examples/offcanvas/offcanvas.css"}]
   "<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->"
   "<!--[if lt IE 9]>\n
      <script src=\"/js/html5shiv.js\"></script>\n
      <script src=\"/js/respond.min.js\"></script>\n
    <![endif]-->"])

(def navbar
  [:div.navbar.navbar-fixed-top.navbar-inverse
     {:role "navigation"}
    [:div.container
     [:div.navbar-header
      [:button.navbar-toggle
       {:data-target ".navbar-collapse",
        :data-toggle "collapse",
        :type "button"}
       [:span.icon-bar]
       [:span.icon-bar]
       [:span.icon-bar]]
      [:a.navbar-brand {:href "#"} "Project name"]]
     [:div.collapse.navbar-collapse
      [:ul.nav.navbar-nav
       [:li.active [:a {:href "#"} "Home"]]
       [:li [:a {:href "#about"} "About"]]
       [:li [:a {:href "#contact"} "Contact"]]
       [:li.cljs-target {:style "color: white;"}]]]]])

(def footer
  [:footer.row
   [:div.large-12.columns
    [:p
     "COPYRIGHT ©   | "
     [:a
      {:target "_blank" :href "/blog/index.html"}
      "BLOG"]
     " | "
     [:a {:href "/team"} "TEAM"]
     " | "
     [:a {:href "/jobs"} "JOBS"]
     " | "
     [:a {:href "/faq"} "FAQ"]
     " | "
     [:a {:href "/help"} "HELP"]]]])

(def footer-scripts
  (list [:script {:src "/js/bin-debug/main.js"}]))
