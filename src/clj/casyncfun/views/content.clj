(ns casyncfun.views.content
  (:require [clojure.string :as string]
            [hiccup.core :refer :all]
            [casyncfun.views.base :as base]))

(def scripts
  nil)

(def intro
  [:div.intro
   [:p
    "Clojure, Compojure, & ClojureScript Starter"]
   [:p
    [:a.learn-more
     {:href "/"}
     "Learn More"]]])

(def documentation-links-data
  [["http://compojure.org"
    "Compojure Docs"]
   ["http://github.com/clojure/clojurescript"
    "ClojureScript Repo"]])

(defn documentation-link [url title]
  [:a {:href url} title])

(def documentation-links
  (map (fn [[url title]] [:li (documentation-link url title)]) documentation-links-data))

(def popular-widget
  [:div.popular.widget
   [:h4 "Documentation"]
   [:ul documentation-links]])

(def sidebar
  [:div#sidebar.col-xs-6.col-sm-3.sidebar-offcanvas
   {:role "navigation"}
   [:div.well.sidebar-nav
    [:ul.nav
     [:li "Getting Started"]
     documentation-links]]])

(defn body [content & [options]]
  [:body
   base/navbar
   [:div.container
    [:div.row.row-offcanvas.row-offcanvas-right
     content
     (when (:sidebar options) sidebar)]
    [:hr]
    base/footer]
   base/footer-scripts])

(def defaults {:sidebar true})

(defn page [content & [options]]
  (list [:html {:lang "en"}
         base/head
         (body content (merge defaults options))]))

(def help*
  (list
   [:h1 "Getting Started Help"]
   [:p
    "For discussions, see the"
    [:a {:href "github"} "github page"]
    "."]))

(def help
  (html (page help*)))

(def home*
  [:div.col-xs-12.col-sm-9
   [:p.pull-right.visible-xs
    [:button.btn.btn-primary.btn-xs
     {:data-toggle "offcanvas", :type "button"}
     "Toggle nav"]]
   [:div.jumbotron {:style "background: no-repeat url(\"/images/clojure.svg\")"}
    [:h1 "C3"]
    [:p "So good, you'll swear it's alien technology."]
    [:p.cljs-target]]
   [:div.row
    [:div.col-6.col-sm-6.col-lg-4
     [:h2 "Dive in!"]
     [:p
      "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
     [:p [:a.btn.btn-default {:href "#"} "View details »"]]]
    "<!--/span-->"
    [:div.col-6.col-sm-6.col-lg-4
     [:h2 "Heading"]
     [:p
      "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
     [:p [:a.btn.btn-default {:href "#"} "View details »"]]]
    "<!--/span-->"
    [:div.col-6.col-sm-6.col-lg-4
     [:h2 "Heading"]
     [:p
      "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
     [:p [:a.btn.btn-default {:href "#"} "View details »"]]]
    "<!--/span-->"
    [:div.col-6.col-sm-6.col-lg-4
     [:h2 "Heading"]
     [:p
      "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
     [:p [:a.btn.btn-default {:href "#"} "View details »"]]]
    "<!--/span-->"
    [:div.col-6.col-sm-6.col-lg-4
     [:h2 "Heading"]
     [:p
      "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
     [:p [:a.btn.btn-default {:href "#"} "View details »"]]]
    "<!--/span-->"
    [:div.col-6.col-sm-6.col-lg-4
     [:h2 "Heading"]
     [:p
      "Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. "]
     [:p [:a.btn.btn-default {:href "#"} "View details »"]]]
    "<!--/span-->"]
   "<!--/row-->"])

(def home
  (html (page home*)))

(def canvas*
  (list
   [:h1 "Canvas!"]
   [:p "For the love of an angel"]
   [:div.controls]
   [:div.status]
   [:div.canvas-target]))

(def canvas
  (html (page canvas* {:sidebar false})))

(def not-found*
  (list [:h1 "We couldn't find that page!"]
        [:p
         "What about checking out the "
         [:a {:href "/"} "homepage"]
         " instead?"]
        [:p "In the meantime, here's some nice music to relax with"]
        [:iframe
         {:allowfullscreen "allowfullscreen",
          :frameborder "0",
          :src "http://www.youtube.com/embed/5NRuD9Y0JQ0",
          :height "315",
          :width "560"}]))

(def not-found
  (html (page not-found*)))
