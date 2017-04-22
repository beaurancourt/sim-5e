(ns sim-5e.utils)

(defn sum [coll] (reduce + 0 coll))

(defn roll
  ([attack-map]
   (if-not (:two-handed attack-map)
     (roll (:num attack-map) (:sides attack-map) (:mod attack-map))
     (fn []
       (+ (sum (map (fn [_]
                      (let [r ((roll (:sides attack-map) 0))]
                        (if (< r 3)
                          ((roll (:sides attack-map) 0))
                          r)))
                    (range (:num attack-map))))
          (:mod attack-map)))))
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

(defn advantage
  [world attacker target]
  (case [(-> world attacker :attack-advantage)
         (-> world attacker :attack-disadvantage)
         (-> world target :defense-advantage)
         (-> world target :defense-disadvantage)]
    [true true true true] :neither
    [false true true true] :disadvantage
    [true false true true] :advantage
    [false false true true] :neither
    [true true false true] :advantage
    [false true false true] :neither
    [true false false true] :advantage
    [false false false true] :advantage
    [true true true false] :disadvantage
    [false true true false] :disadvantage
    [true false true false] :neither
    [false false true false] :disadvantage
    [true true false false] :neither
    [false true false false] :disadvantage
    [true false false false] :advantage
    [false false false false] :neither))

(defn attack
  [world attacker players goblins]
  (reduce (fn [world attack-map]
            (let [target (if (-> world attacker :pc)
                           (pick-first-enemy world goblins)
                           (pick-random-target world (filter #(-> world % :pc) (keys world))))

                  bless (if (-> world attacker :bless)
                          ((roll 4 0))
                          0)
                  bane (if (-> world attacker :bane)
                         ((roll 4 0))
                         0)

                  interposing (->> [:cleric]
                                   (remove #(= target %))
                                   (filter #(and
                                              (players target)
                                              (-> world % :protection-style)
                                              (-> world % :reaction)))
                                   first)
                  world (if interposing
                          (do
                            (log interposing " uses protection style against " attacker " to protect " target)
                            (-> world
                                (update-in [attacker :attack-disadvantage] (constantly true))
                                (update-in [interposing :reaction] (constantly false))))
                          world)

                  natural-roll (case (advantage world attacker target)
                                 :advantage (max ((roll 20 0)) ((roll 20 0)))
                                 :disadvantage (min ((roll 20 0)) ((roll 20 0)))
                                 :neither ((roll 20 0)))

                  base-roll (+ natural-roll (:hit attack-map))

                  world (if interposing
                          (update-in world [attacker :attack-disadvantage] (constantly false))
                          world)

                  attack-roll (-> base-roll (+ bless) (- bane))
                  shield-bonus (or (-> world target :shield-bonus) 0)
                  ac (+ (-> world target :ac) shield-bonus)
                  [world ac] (if (and
                                   (= target :sorcerer)
                                   (= shield-bonus 0)
                                   (> (-> world :sorcerer :spell-1) 0)
                                   (>= attack-roll ac)
                                   (< attack-roll (+ ac 5)))
                               (do
                                 (log "the sorcerer uses shield to block " attacker)
                                 [(-> world
                                      (update-in [:sorcerer :shield-bonus] (constantly 5))
                                      (update-in [:sorcerer :spell-1] - 1))
                                  (+ ac 5)])
                               [world ac])]
              (cond
                (= natural-roll 20)
                (let [damage ((roll (update-in attack-map [:num] * 2)))]
                  (log attacker " crits " target " for " damage)
                  (update-in world [target :hp] do-damage damage))

                (>= attack-roll ac)
                (let [damage ((roll attack-map))]
                  (log attacker " hits " target " for " damage
                       (if (< (- attack-roll bless) ac) " #blessed" ""))
                  (update-in world [target :hp] do-damage damage))

                :else
                (do (log attacker " misses " target)
                    world))))
          world
          (-> world attacker :attacks)))

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
