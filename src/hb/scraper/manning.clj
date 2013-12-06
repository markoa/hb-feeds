(ns hb.scraper.manning
  (:require [net.cgrand.enlive-html :as html]
            [hb.scraper.common :as common]))

(def base-url "http://www.manning.com")

(def book-selector [:table.Releases])

(defn select-books [nodes]
  (html/select nodes book-selector))

(defn recent-releases
  "These are books where there is no span containing the word 'coming'."
  [nodes]
  (filter
    (fn [book-node]
      (let [spans (html/select [book-node] [:span])]
        (= 0 (count (filter #(not= -1 (.indexOf (html/text %) "coming")) spans)))))
    nodes))

(defn extract [node]
  (let [title (first (html/select [node] [:strong]))
        url (str base-url
                 "/"
                 (first (html/attr-values
                          (first (html/select [node] [:a]))
                          :href)))
        cover-url (str base-url
                       (first (html/attr-values
                                (first (html/select [node] [:img]))
                                :src)))
        description (first (html/select [node] [:p]))
        result (map html/text [title url cover-url description])]
    (zipmap [:title :url :cover-url :description] result)))

; API

(defn latest-books
  "Returns a map of latest book titles from manning.com."
  []
  (map extract (recent-releases (select-books (common/fetch-url base-url)))))
