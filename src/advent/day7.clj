(ns advent.day7
  (:require [clojure.string :as string])
  (:require [clojure.walk :as walk]))

(def text (string/split-lines (slurp "/Users/james/learn/advent/resources/day7.txt")))
(defn parse-int [x] (Integer/parseInt x))
(defn add-folder [path folder fs]
  (update-in fs path (fn [x] (assoc x folder {}))))

(defn add-file [path file size fs]
  (update-in fs path (fn [x] (assoc x file size))))

(def parsed-input
  (loop [filesystem {:/ {}}
         current-path [:/]
         [fst & rst] text]
    (cond
      (nil? fst) filesystem
      (string/includes? fst "$ cd ..") (recur filesystem (vec (butlast current-path)) rst)
      (string/includes? fst "$ cd /") (recur filesystem [:/] rst)
      (string/includes? fst "$ cd ") (recur filesystem (conj current-path (keyword (last (string/split fst #" ")))) rst)
      (string/includes? fst "$ ls") (recur filesystem current-path rst)
      (= "dir " (apply str (take 4 fst))) (let [folder (keyword (last (string/split fst #" ")))]
                                            (recur (add-folder current-path folder filesystem) current-path rst))
      :else (let [[size filename] (string/split fst #" ")]
              (recur (add-file current-path filename (parse-int size) filesystem) current-path rst)))))

(tree-seq map? parsed-input "/")

(clojure.pprint/pprint parsed-input)