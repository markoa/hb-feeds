# hb-feeds

A Clojure library for fetching latest programming book releases. Implemented publishers are:

- Manning (scraped)
- Pragmatic Bookshelf (scraped)

## Usage

```clojure
user=> (require '[hb-feeds.scraping.manning :as manning])

user=> (manning/latest-books)
({:description "Guides you step by step ...",
  :cover-url "http://www.manning.com/osherove2/osherove2_3d.gif",
  :url "http://www.manning.com/osherove2",
  :title "The Art of Unit Testing, Second Edition"} ...)
```

## License

Copyright © 2013 Marko Anastasov

Distributed under the MIT License.
