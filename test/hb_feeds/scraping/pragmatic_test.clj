(ns hb-feeds.scraping.pragmatic-test
  (:require [clojure.test :refer :all]
            [hb-feeds.scraping.pragmatic :refer :all]))

(deftest base-scraping-url
  (is (= "http://pragprog.com/categories/all" base-url)))
