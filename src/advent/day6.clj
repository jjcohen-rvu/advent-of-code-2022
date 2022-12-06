(ns advent.day6
  (:require [clojure.string :as string]
            [clojure.set :refer [subset? intersection]]))

;; (defn parse-int [x] (Integer/parseInt x))

(def text (->> (slurp "/Users/james/learn/advent/resources/day6.txt")))

;; (def text "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")

(def offset 14)

(def search
  (loop [t text
         acc offset]
    (if
     (= (count (distinct (take offset t))) offset)
      acc
      (recur (rest t) (inc acc)))))

