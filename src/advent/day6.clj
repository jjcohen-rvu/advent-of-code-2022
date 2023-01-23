(ns advent.day6)
(defn search
  [n]
  (loop [text (->> (slurp "/Users/james/learn/advent/resources/day6.txt")) acc n]
    (if (= (count (distinct (take n text))) n) acc (recur (rest text) (inc acc)))))
(prn (search 4))
(prn (search 14))