(ns advent.day2 (:require [clojure.string :as string])) 

(defn convert-cipher [cipher]
  (condp = cipher
    "A"   "rock"
    "B"   "paper"
    "C"   "scissors"
    "X"   "rock"
    "Y"   "paper"
    "Z"   "scissors"
    :else ""))

(defn cipher-to-score [v]
  (condp = v
    "rock"     1
    "paper"    2
    "scissors" 3
    :else      0))

(defn convert-scores [v]
  (let [score (cipher-to-score (last v))]
    (case v
      (("rock" "paper")) (+ score 6)
      (("paper" "scissors")) (+ score 6)
      (("scissors" "rock")) (+ score 6)

      (("rock" "rock")) (+ 3 score)
      (("paper" "paper")) (+ 3 score)
      (("scissors" "scissors")) (+ 3 score)

      (("paper" "rock")) score
      (("scissors" "paper")) score
      (("rock" "scissors")) score)))

(defn rps-scores
  []
  (as-> (slurp "/Users/james/learn/advent/resources/day2.txt") v
    (string/split-lines v)
    (map (fn [x] (string/split x #" ")) v)
    (map (fn [x] (map convert-cipher x)) v)
    (map convert-scores v)
    (reduce + v)))

(defn convert-cipher-2 [cipher]
  (condp = cipher
    "A"   "rock"
    "B"   "paper"
    "C"   "scissors"
    "X"   "lose"
    "Y"   "draw"
    "Z"   "win"
    :else ""))

(defn convert-strategy
  [s]
  (case s
    (("rock" "lose")) ["rock" "scissors"]
    (("rock" "draw")) ["rock" "rock"]
    (("rock" "win")) ["rock" "paper"]

    (("paper" "lose")) ["paper" "rock"]
    (("paper" "draw")) ["paper" "paper"]
    (("paper" "win")) ["paper" "scissors"]

    (("scissors" "lose")) ["scissors" "paper"]
    (("scissors" "draw")) ["scissors" "scissors"]
    (("scissors" "win")) ["scissors" "rock"]))

(defn rps-re-scores
  []
  (as-> (slurp "/Users/james/learn/advent/resources/day2.txt") v
    (string/split-lines v)
    (map (fn [x] (string/split x #" ")) v)
    (map (fn [x] (map convert-cipher-2 x)) v)
    (map convert-strategy v)
    (map convert-scores v)
    (reduce + v)))