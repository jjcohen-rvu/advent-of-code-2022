(ns advent.day8
  (:require [clojure.string :as string]))
;; (def text (slurp "/Users/james/learn/advent/resources/day8.txt"))
(def text
  "30373
25512
65332
33549
35390")
(defn ascii-to-num [a] (- (int a) 48))
(def input
  (->>
   text
   (string/split-lines)
   (map (fn [x] (vec (map ascii-to-num x))))
   vec))
(defn transpose [s] (apply mapv vector s))
(defn find-tree-tops [s]
  (loop [[fst & rst] (vec s)
         current-max -1
         output []]
    (cond
      (nil? fst) output
      (> fst current-max) (recur rst fst (conj output 1))
      :else (recur rst current-max (conj output 0)))))
(def answer-1
  (let [left->right (map find-tree-tops input)
        right->left (map reverse (map find-tree-tops (map reverse input)))
        top->bottom (transpose (map find-tree-tops (transpose input)))
        bottom->top (transpose (map reverse (map find-tree-tops (map reverse (transpose input)))))]
    (reduce
     +
     (flatten
      (map
       (fn [l1 l2 l3 l4]
         (map
          (fn [e1 e2 e3 e4] (if (.contains [e1 e2 e3 e4] 1) 1 0)) l1 l2 l3 l4))
       left->right right->left top->bottom bottom->top)))))
;; (defn viewing-distance [l start-pos]
;;   (let [[left right] (split-at (+ 1 start-pos) l)])
;;   )
(def answer-2
  ()
  )