# hb-scraper

A Clojure library for fetching latest programming book releases. Implemented publishers are:

- Manning
- Pragmatic Bookshelf

## Installation

Add the following dependency to your `project.clj` file:

    [hb-scraper "0.1.0"]

The library is published on [Clojars](https://clojars.org/hb-scraper). Latest version is 0.1.0.

## Usage

```clojure
user=> (require '[hb.scraper.manning :as manning])

user=> (manning/latest-books)
({:description "Guides you step by step ...",
  :cover-url "http://www.manning.com/osherove2/osherove2_3d.gif",
  :url "http://www.manning.com/osherove2",
  :title "The Art of Unit Testing, Second Edition"} ...)
```

## License

Copyright © 2013 Marko Anastasov

Distributed under the MIT License.
