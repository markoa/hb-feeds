(ns hb-feeds.scraping.common
  (:require [net.cgrand.enlive-html :as html]))

(defn fetch-url
  "Load a HTML resource from URL, based on enlive-html/html-resource."
  [url]
  (html/html-resource (java.net.URL. url)))

(defn fetch-file
  "Load a HTML resource from file, based on enlive-html/html-resource.
  Use for testing."
  [path]
  (html/html-resource (.toURL (java.io.File. path))))
