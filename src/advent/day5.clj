(ns advent.day5
  (:require [clojure.string :as string]
            [clojure.set :refer [subset? intersection]]))

(defn parse-int [x] (Integer/parseInt x))

(def text
  (->> (slurp "/Users/james/learn/advent/resources/day5.txt")))

(def init-state
  (as-> text s
    (string/split-lines s)
    (take 8 s)
    (map (partial drop 1) s)
    (map (partial take-nth 4) s)
    (apply mapv vector s)
    (map reverse s)
    (map (fn [x] (string/trim (apply str x))) s)
    (vec s)))

(defn process-move-line
  [s]
  (let [[_ amount _ from _ to] (string/split s #" ")]
    (map parse-int [amount from to])))

(def moves
  (->> text
       string/split-lines
       (drop 10)
       (map process-move-line)))

(defn new-state
  [state amount from to]
  (let [fr (dec from)
        t (dec to)
        from-col (nth state fr)
        [new-from-col move-chars] (split-at (- (count from-col) amount) from-col)
        ;; new-to-col (apply str (nth state t) (reverse move-chars)) ;; original
        new-to-col (apply str (nth state t) move-chars) ;; 9001
        new-state (-> state
                      (assoc fr (apply str new-from-col))
                      (assoc t new-to-col))]
    new-state))

(def game-state
  (loop [state init-state
         [[amount from to] & remaining] moves]
    (if (nil? amount)
      state
      (recur (new-state state amount from to) remaining))))

(def result (apply str (map last game-state)))