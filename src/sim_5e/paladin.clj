(ns sim-5e.paladin
  (:require
    [sim-5e.utils :refer :all]))

(def class-key :paladin)

(defmethod generate-pc class-key
  [level _]
  (let [main-stat (main-stat level)
        max-hp (+ 12 (* (- level 1) 8))
        casting-mod (+ 3 (proficiency level))
        number-of-attacks (if (> level 4) 2 1)
        melee-attack {:num 1 :sides 8 :mod (+ main-stat 2) :hit (+ main-stat (proficiency level))}]
    {:paladin (merge {:pc true
                      :ac 18
                      :hit-dice level
                      :init ((roll 20 0))
                      :attacks (mapv (constantly melee-attack) (range number-of-attacks))
                      :protection-style false
                      :attack-advantage false
                      :reaction true
                      :attack-disadvantage false
                      :defense-advantage false
                      :defense-disadvantage false
                      :casting-mod casting-mod
                      :spell-dc (+ 8 casting-mod)
                      :max-hp max-hp
                      :hp max-hp}
                     (half-caster-spell-slots level))}))

(defn- smite?
  [world actor]
  (> (-> world actor :spell-1) 0))

(defn- smite
  [world attacker players goblins]
  (reduce (fn [world _]
              (let [target (if (players attacker)
                             (pick-first-enemy world goblins)
                             (pick-random-target world players))

                    bless (if (-> world attacker :bless)
                            ((roll 4 0))
                            0)
                    bane (if (-> world attacker :bane)
                           ((roll 4 0))
                           0)

                    base-roll (case (advantage world attacker target)
                                :advantage (max ((-> world attacker :hit)) ((-> world attacker :hit)))
                                :disadvantage (min ((-> world attacker :hit)) ((-> world attacker :hit)))
                                :neither ((-> world attacker :hit)))

                    attack-roll (-> base-roll (+ bless) (- bane))
                    ac (-> world target :ac)]
                (if (>= attack-roll ac)
                  (let [damage (+ ((-> world attacker :damage)) ((roll 3 8 0)))]
                    (log attacker " smites " target " for " damage
                         (if (< (- attack-roll bless) ac) " #blessed" ""))
                    (-> world
                        (update-in [target :hp] do-damage damage)
                        (update-in [attacker :spell-1] - 1)))
                  (do
                    (log attacker " misses " target)
                    world))))
            world
            (range (or (-> world attacker :attacks) 1))))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
    ;(smite? world actor) (smite world actor players enemies)
    :else (attack world actor players enemies)))

(defmethod take-short-rest class-key
  [world _]
  (if (and (> (- (-> world class-key :max-hp)
                 (-> world class-key :hp))
              12)
           (> (-> world class-key :hit-dice) 0))
    (let [rest-health ((roll 10 2))]
      (log class-key " rests for " rest-health)
      (take-short-rest (-> world
                           (update-in [class-key :hp] + rest-health)
                           (update-in [class-key :hit-dice] - 1))
                       class-key))
    world))
