(ns hb-feeds.scraping.pragmatic
  (:require [net.cgrand.enlive-html :as html]))

(def base-url "http://pragprog.com/categories/all")

(defn fetch-url
  "Load a HTML resource from URL, based on enlive-html/html-resource."
  [url]
  (html/html-resource (java.net.URL. url)))

(defn fetch-file
  "Load a HTML resource from file, based on enlive-html/html-resource.
  Use for testing."
  [path]
  (html/html-resource (.toURL (java.io.File. path))))

(def book-selector [:div.book])

(defn select-books [nodes]
  (html/select nodes book-selector))

(defn extract
  "node should be a book node retrieved via select-books."
  [node]
  (let [title       (first (html/select [node] [:h4]))
        url         (first (html/attr-values (first (html/select [node] [:a])) :href))
        cover-url   (first (html/attr-values (first (html/select [node] [:div.thumbnail :img])) :src))
        description (first (html/select [node] [:div.details :p]))
        result      (map html/text [title url cover-url description])]
    (zipmap [:title :url :cover-url :description] result)))

; API

(defn latest-books
  "Returns a map of latest book titles from pragprog.com."
  []
  (map extract (select-books (fetch-url base-url))))
