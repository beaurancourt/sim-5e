(ns sim-5e.fighter
  (:require
    [sim-5e.utils :refer :all]))

(def class-key :fighter)

(defmethod generate-pc class-key
  [level _]
  (let [main-stat (main-stat level)
        max-hp (+ 12 (* (- level 1) 8))
        two-hand-damage
        (fn []
          (let [roll1 ((roll 6 0))
                roll2 ((roll 6 0))
                bonus (+ main-stat 10)]
            (+ (if (< roll1 3) ((roll 6 0)) roll1)
               (if (< roll2 3) ((roll 6 0)) roll2)
               bonus)))
        melee-attack {:num 1 :sides 8 :mod (+ main-stat 2) :hit (+ main-stat (proficiency level))}
        number-of-attacks (cond
                            (< level 5) 1
                            (< level 11) 2
                            :else 3)]
    {:fighter {:pc true
               :ac 18
               :init ((roll 20 0))
               :protection-style false
               :attack-advantage false
               :attack-disadvantage false
               :reaction true
               :defense-advantage false
               :defense-disadvantage false
               :attacks (mapv (constantly melee-attack) (range number-of-attacks))
               :max-hp max-hp
               :hp max-hp}}))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
    :else (attack world actor players enemies)))
