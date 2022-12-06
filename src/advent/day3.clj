(ns advent.day3
  (:require [clojure.string :as string]
            [clojure.set :refer [intersection]]))
(def lines
  (->> (slurp "/Users/james/learn/advent/resources/day3.txt")
       (string/split-lines)))
(defn convert-to-score [n] (if (>= n 97) (- n 96) (+ (- n 64) 26)))
(defn split-line [s] (split-at (/ (count s) 2) s))
(defn convert-to-sets [vs] (map #(set %) vs))
(def result-1
  (->> lines
       (map split-line)
       (map convert-to-sets)
       (map (fn [x] (vec (apply intersection x))))
       (flatten)
       (map (comp convert-to-score int))
       (reduce +)))
(def result-2
  (->> lines
       (map set)
       (partition 3)
       (map (fn [x] (vec (apply intersection x))))
       (flatten)
       (map (comp convert-to-score int))
       (reduce +)))