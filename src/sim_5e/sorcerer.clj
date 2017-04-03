(ns sim-5e.sorcerer
  (:require
    [sim-5e.utils :refer :all]
    [sim-5e.spells :as spells]))

(def class-key :sorcerer)

(defmethod generate-pc class-key
  [level _]
  (let [main-stat (main-stat level)
        firebolt-dice (cond
                        (< level 5) 1
                        (< level 11) 2
                        (< level 17) 3
                        :else 4)
        fire-bolt-mod (if (< level 6) 0 main-stat)
        proficiency (proficiency level)
        casting-mod (+ main-stat proficiency)
        max-hp (+ 10 (* (- level 1) 7))]
    {:sorcerer (merge {:pc true
                       :ac 15
                       :init ((roll 20 1))
                       :casting-mod casting-mod
                       :reaction true
                       :spell-dc (+ 8 casting-mod)
                       :attack-advantage false
                       :attack-disadvantage false
                       :defense-advantage false
                       :defense-disadvantage false
                       :attacks [{:num firebolt-dice
                                  :sides 10
                                  :mod fire-bolt-mod
                                  :hit (+ main-stat proficiency)}]
                       :max-hp max-hp
                       :hp max-hp}
                      (full-caster-spell-slots level))}))

(defn- burning-hands?
  [world actor players enemies]
  (and
    (> (-> world actor :spell-1) 2)))

(defn- twin-haste?
  [world actor players enemies]
  (and
    (> (-> world actor :spell-3) 0)
    (-> world actor :concentrating not)
    (= (->> [:paladin :fighter] (alive world) (remove #(-> world % :hasted))) [:paladin :fighter])))

(defn- fireball?
  [world actor players enemies]
  (and
   (> (-> world actor :spell-3) 0)))

(defn- slow?
  [world actor players enemies]
  (and
    (> (-> world actor :spell-3) 0)
    (-> world actor :concentrating not)
    (empty? (filter #(-> world % :slowed) enemies))))

(defn- conjure-animals?
  [world actor]
  (and
    (> (-> world actor :spell-3) 0)
    (-> world actor :concentrating not)))

(defn- magic-missile-2?
  [world actor]
  (> (-> world actor :spell-2) 0))

(defn- magic-missile-1?
  [world actor]
  (> (-> world actor :spell-1) 0))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
    true (attack world actor players enemies)
    (magic-missile-2? world actor) (spells/magic-missile world actor :spell-2 (pick-first-enemy world enemies))
    (magic-missile-1? world actor) (spells/magic-missile world actor :spell-1 (pick-first-enemy world enemies))
    (fireball? world actor players enemies) (spells/fireball world actor :spell-3 enemies)
    (conjure-animals? world actor) (spells/conjure-animals world actor :spell-3)
    (slow? world actor players enemies) (spells/slow world actor :spell-3 enemies)
    (twin-haste? world actor players enemies) (spells/twin-haste world actor :spell-3 [:paladin :fighter])
    (> (-> world actor :spell-1) 0) (spells/burning-hands world actor :spell-1 enemies)
    :else (attack world actor players enemies)))
