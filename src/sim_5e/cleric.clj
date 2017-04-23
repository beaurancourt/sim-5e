(ns sim-5e.cleric
  (:require
    [sim-5e.utils :refer :all]
    [sim-5e.spells :as spells]))

(def class-key :cleric)

(defmethod generate-pc class-key
  [level _]
  (let [max-hp (+ 10 (* (- level 1) 7))
        proficiency (proficiency level)
        attack-mod (+ 3 proficiency)
        casting-mod (+ (main-stat level) proficiency)
        melee-attack {:num 1 :sides 8 :mod 3 :hit attack-mod}]
    {:cleric (merge {:pc true
                     :ac 18
                     :hit-dice level
                     :init ((roll 20 0))
                     :casting-mod casting-mod
                     :spell-dc (+ 8 casting-mod)
                     :attacks [melee-attack melee-attack]
                     :reaction true
                     :attack-advantage false
                     :attack-disadvantage false
                     :defense-advantage false
                     :defense-disadvantage false
                     :max-hp max-hp
                     :hp max-hp}
                    (full-caster-spell-slots level))}))
(defn- cast-bless?
  "Cast bless if there are 3 alive players without bless"
  [world actor players enemies]
  (and
    (> (-> world actor :spell-1) 0)
    (>= (count (filter #(-> world % :bless not)
                      (alive world players)))
       3)))

(defn- cast-cure-wounds?
  [world actor players enemies]
  (and
    (> (-> world actor :spell-1) 0)
    (not= (count players) (count (alive world players)))))

(defn- bring-up-friend
  [world actor spell-level players]
  (let [target (first (filter #(-> world % :hp (<= 0)) players))]
    (spells/cure-wounds world actor spell-level target)))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
    (cast-cure-wounds? world actor players enemies) (bring-up-friend world actor :spell-1 players)
    :else (attack world actor players enemies)))

(defmethod take-short-rest class-key
  [world _]
  (if (and (> (- (-> world class-key :max-hp)
                 (-> world class-key :hp))
              10)
           (> (-> world class-key :hit-dice) 0))
    (let [rest-health ((roll 8 2))
          world (-> world
                    (update-in [class-key :hp] + rest-health)
                    (update-in [class-key :hit-dice] - 1))]
      (log class-key " rests for " rest-health)
      (take-short-rest world class-key))
    world))
