(ns advent.day6
  (:require [clojure.string :as string]
            [clojure.set :refer [subset? intersection]]))
(def offset 14)
(def search
  (loop [t (->> (slurp "/Users/james/learn/advent/resources/day6.txt"))
         acc offset]
    (if
     (= (count (distinct (take offset t))) offset)
      acc
      (recur (rest t) (inc acc)))))