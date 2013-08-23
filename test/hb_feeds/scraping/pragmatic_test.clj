(ns hb-feeds.scraping.pragmatic-test
  (:require [clojure.test :refer :all]
            [hb-feeds.scraping.pragmatic :refer :all]))

(deftest base-scraping-url
  (is (= "http://pragprog.com/categories/all" base-url)))

(deftest fetching-from-file
  (let [nodes (fetch-file "test/hb_feeds/scraping/pragmatic.html")
        head (nth (get (nth nodes 1) :content) 1)
        title (first (get (nth (get head :content) 4) :content))]
    (is (= "The Pragmatic Bookshelf | Our Titles" title))))
