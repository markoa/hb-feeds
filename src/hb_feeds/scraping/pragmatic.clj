(ns hb-feeds.scraping.pragmatic
  (:require [net.cgrand.enlive-html :as html])
  (:use [clojure.contrib.str-utils :only [re-sub re-gsub]]))

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

(defn books [nodes]
  (html/select nodes book-selector))

(def title-selector [:h4])

(def url-selector [:a])

(defn extract [node]
  (let [title  (first (html/select [node] title-selector))
        url    (first (html/attr-values (first (html/select [node] url-selector)) :href))
        result (map html/text [title url])]
    (zipmap [:title :url] (map #(re-gsub #"\n" "" %) result))))
