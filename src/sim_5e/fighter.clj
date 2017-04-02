(ns sim-5e.fighter
  (:require
    [sim-5e.utils :refer :all]))

(def class-key :fighter)

(defmethod generate-pc class-key
  [level _]
  (let [main-stat (main-stat level)
        max-hp (+ 12 (* (- level 1) 8))
        proficiency (proficiency level)
        to-hit (+ main-stat proficiency)
        light-attack {:num 1 :sides 6 :mod main-stat :hit to-hit}
        melee-attack {:num 1 :sides 8 :mod (+ main-stat 0) :hit to-hit}
        two-handed {:num 2 :sides 6 :mod main-stat :hit to-hit :two-handed false}
        number-of-attacks (cond
                            (< level 5) 1
                            (< level 11) 2
                            :else 3)]
    {:fighter {:pc true
               :ac 19
               :init ((roll 20 0))
               :protection-style false
               :attack-advantage false
               :attack-disadvantage false
               :reaction true
               :defense-advantage false
               :defense-disadvantage false
               ;:attacks (mapv (constantly two-handed) (range number-of-attacks))
               :attacks (mapv (constantly melee-attack) (range number-of-attacks))
               ;:attacks (mapv (constantly light-attack) (range (+ number-of-attacks 1))) ; two weapon fighting
               :max-hp max-hp
               :hp max-hp}}))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
    :else (attack world actor players enemies)))
