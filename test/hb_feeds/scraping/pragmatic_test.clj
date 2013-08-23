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

(defn get-books []
  (map extract (books (fetch-file "test/hb_feeds/scraping/pragmatic.html"))))

(deftest books-should-have-titles
  (let [first-book (first (get-books))
        last-book  (last (get-books))]
    (is (= "Seven Web Frameworks in Seven Weeks" (get first-book :title)))
    (is (= "The Pragmatic Programmer" (get last-book :title)))))

(deftest books-should-have-urls
  (let [first-book (first (get-books))
        last-book  (last (get-books))]
    (is (= "http://pragprog.com/book/7web/seven-web-frameworks-in-seven-weeks" (get first-book :url)))
    (is (= "http://pragprog.com/book/tpp/the-pragmatic-programmer" (get last-book :url)))))
