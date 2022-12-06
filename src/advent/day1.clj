(ns advent.day1
  (:require [clojure.string :as string]))

(defn sum-of-strings [xs] (reduce + (map #(Integer/parseInt %) xs)))
(defn elf-scores [n]
  (as-> (slurp "/Users/james/learn/advent/resources/day1.txt") v
    (string/split v #"\n\n")
    (map string/split-lines v)
    (map (fn [item] (sum-of-strings item)) v)
    (sort v)
    (reverse v)
    (take n v)
    (reduce + v)))