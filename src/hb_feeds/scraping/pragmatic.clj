(ns scraping.pragmatic
  (:require [net.cgrand.enlive-html :as html])
  (:use [clojure.contrib.str-utils :only [re-sub re-gsub]]))

(def base-url "http://pragprog.com/categories/all")

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(def book-selector [:div.book])

(defn books []
  (html/select (fetch-url base-url) book-selector))

(def title-selector [:h2])

(def url-selector [:a])

(defn extract [node]
  (let [title  (first (html/select [node] title-selector))
        url    (first (html/select [node] url-selector))
        result (map html/text [title url])]
    (zipmap [:title :url] (map #(re-gsub #"\n" "" %) result))))

(defn print-books []
  (map extract (books)))
