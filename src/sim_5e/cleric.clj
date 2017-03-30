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
        casting-mod (+ (main-stat level) proficiency)]
    {:cleric (merge {:damage (roll 8 3)
                     :pc true
                     :ac 18
                     :init ((roll 20 0))
                     :hit (roll 20 attack-mod)
                     :casting-mod casting-mod
                     :spell-dc (+ 8 casting-mod)
                     :attacks 1
                     :max-hp max-hp
                     :hp max-hp}
                    (full-caster-spell-slots level))}))
(defn- cast-bless?
  "Cast bless if there are 3 alive players without bless"
  [world players enemies]
  (= (count (filter #(-> world % :bless not)
                    (alive world players)))
     3))

(defn- cast-cure-wounds?
  [world players enemies]
  (not= (count players) (count (alive world players))))

(defn- bring-up-friend
  [world actor spell-level players]
  (let [target (first (filter #(-> world % :hp (<= 0)) players))]
    (spells/cure-wounds world actor spell-level target)))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
    (cast-cure-wounds? world players enemies) (bring-up-friend world actor :spell-1 players)
    (cast-bless? world players enemies) (spells/bless world actor :spell-1 (take 3 (alive world players)))
    :else (attack world actor players enemies)))
