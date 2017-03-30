(ns sim-5e.utils)

(defn sum [coll] (reduce + 0 coll))

(defn roll
  ([dice modifier]
   (fn [] (+ (rand-int dice) modifier 1)))
  ([n dice modifier]
   (fn []
     (reduce (fn [total _] (+ total (rand-int dice)))
             (+ n modifier)
             (range n)))))

(defn proficiency
  [level]
  (-> level (- 1) (/ 4) int (+ 2)))

(defn main-stat
  [level]
  (cond
    (< level 4) 3
    (< level 8) 4
    :else 5))

(defn alive
  [world actors]
  (filter #(> (-> world % :hp) 0) actors))

(defn pick-first-enemy
  [world enemies]
  (or (first (alive world enemies)) (first enemies)))

(defn pick-random-target
  [world target]
  (rand-nth (or (seq (alive world target)) [:paladin])))

(defmulti generate-pc
  (fn [_ pc-class] pc-class))

(defn log
  [& s]
  (spit "log.txt" (str (apply str s) "\n") :append true))
