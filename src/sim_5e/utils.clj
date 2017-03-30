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

(defn attack
  [world attacker players goblins]
  (if (> (-> world attacker :hp) 0)
    (reduce (fn [world _]
              (let [target (if (players attacker)
                             (pick-first-enemy world goblins)
                             (pick-random-target world players))
                    bless (if (-> world attacker :bane)
                            (* -1 ((roll 4 0)))
                            0)
                    attack-roll (+ ((-> world attacker :hit)) bless)]
                (if (>= attack-roll (-> world target :ac))
                  (let [damage ((-> world attacker :damage))
                        updated-world (update-in world [target :hp] - damage)]
                    (if (and (= target :cleric) (< ((roll 20 2)) 10))
                      (reduce (fn [world player]
                                (update-in world [player :bless] (constantly false)))
                              updated-world
                              [:paladin :cleric :fighter])
                      updated-world))
                  world)))
            world
            (range (or (-> world attacker :attacks) 1)))
    world))
