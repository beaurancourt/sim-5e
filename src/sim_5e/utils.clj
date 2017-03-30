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

(defmulti take-turn
  (fn [_ actor _ _] actor))

(defn log
  [& s]
  (spit "log.txt" (str (apply str s) "\n") :append true))

(defn do-damage
  [current-hp damage]
  (max 0 (- current-hp damage)))

(defn attack
  [world attacker players goblins]
  (reduce (fn [world _]
              (let [target (if (players attacker)
                             (pick-first-enemy world goblins)
                             (pick-random-target world players))
                    bless (if (-> world attacker :bane)
                            (* -1 ((roll 4 0)))
                            0)
                    attack-roll (+ ((-> world attacker :hit)) bless)]
                (if (>= attack-roll (-> world target :ac))
                  (let [damage ((-> world attacker :damage))]
                    (log attacker " hits " target " for " damage)
                    (update-in world [target :hp] do-damage damage))
                  (do
                    (log attacker " misses " target)
                    world))))
            world
            (range (or (-> world attacker :attacks) 1))))

(defn full-caster-spell-slots
  [level]
  {:spell-1 (cond
              (< level 2) 2
              (< level 3) 3
              :else 4)
   :spell-2 (cond
              (< level 3) 0
              (< level 4) 2
              :else 3)
   :spell-3 (cond
              (< level 5) 0
              (< level 6) 2
              :else 3)
   :spell-4 (cond
              (< level 7) 0
              (< level 8) 1
              (< level 9) 2
              :else 3)
   :spell-5 (cond
              (< level 9) 0
              (< level 10) 1
              (< level 18) 2
              :else 3)
   :spell-6 (cond
              (< level 11) 0
              (< level 19) 1
              :else 2)
   :spell-7 (cond
              (< level 13) 0
              (< level 20) 1
              :else 2)
   :spell-8 (cond
              (< level 15) 0
              :else 1)
   :spell-9 (cond
              (< level 17) 0
              :else 1)})

(defn half-caster-spell-slots
  [level]
  {:spell-1 (cond
              (< level 2) 0
              (< level 3) 2
              (< level 5) 3
              :else 4)
   :spell-2 (cond
              (< level 5) 0
              (< level 7) 2
              :else 3)
   :spell-3 (cond
              (< level 9) 0
              (< level 11) 2
              :else 3)
   :spell-4 (cond
              (< level 13) 0
              (< level 15) 1
              (< level 17) 2
              :else 3)
   :spell-5 (cond
              (< level 17) 0
              (< level 19) 1
              :else 2)})
