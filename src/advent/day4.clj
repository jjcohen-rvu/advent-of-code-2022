(ns advent.day4
  (:require [clojure.string :as string]
            [clojure.set :refer [subset? intersection]]))
(defn parse-int [x] (Integer/parseInt x))
(defn inc-snd [xs] [(first xs) (inc (second xs))])
(defn range-set [s]
  (->> (string/split s #"-")
       (map parse-int)
       inc-snd
       (apply range)
       set))
(def range-sets
  (->> (slurp "/Users/james/learn/advent/resources/day4.txt")
       (string/split-lines)
       (map (fn [x] (string/split x #",")))
       (map (fn [x] (map range-set x)))))
(defn test-subsets [[s1 s2]] (or (subset? s1 s2) (subset? s2 s1)))
(def q1
  (->> range-sets
       (filter test-subsets)
       count))
(def q2
  (->> range-sets
       (map (fn [x] (apply intersection x)))
       (filter (comp not empty?))
       count))