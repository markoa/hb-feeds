(ns hb-feeds.scraping.manning-test
  (:require [clojure.test :refer :all]
            [hb-feeds.scraping.common :as common]
            [hb-feeds.scraping.manning :refer :all]))

(deftest base-scraping-url
  (is (= "http://www.manning.com" base-url)))

(defn get-books []
  (map extract (recent-releases (select-books (common/fetch-file "test/hb_feeds/scraping/manning.html")))))

(deftest books-have-titles
  (let [first-book (first (get-books))
        last-book  (last (get-books))]
    (is (= "Extending jQuery" (get first-book :title)))
    (is (= "AOP in .NET" (get last-book :title)))))

(deftest books-have-urls
  (let [first-book (first (get-books))]
    (is (= "http://www.manning.com/wood" (get first-book :url)))))

(deftest books-have-cover-urls
  (let [first-book (first (get-books))]
    (is (= "http://www.manning.com/wood/wood_cover90.jpg" (get first-book :cover-url)))))

(deftest books-have-descriptions
  (let [first-book (first (get-books))]
    (is (.startsWith (get first-book :description) "Teaches you to build custom extensions"))))
