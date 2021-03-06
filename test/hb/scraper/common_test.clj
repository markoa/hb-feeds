(ns hb.scraper.manning-test
  (:require [clojure.test :refer :all]
            [hb.scraper.common :as common]))

(deftest fetching-from-file
  (let [nodes (common/fetch-file "test/hb/scraper/pragmatic.html")
        head (nth (get (nth nodes 1) :content) 1)
        title (first (get (nth (get head :content) 4) :content))]
    (is (= "The Pragmatic Bookshelf | Our Titles" title))))
