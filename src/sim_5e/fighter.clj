(ns sim-5e.fighter
  (:require
    [sim-5e.utils :refer :all]))

(def class-key :fighter)

(defmethod generate-pc class-key
  [level _]
  (let [main-stat (main-stat level)
        max-hp (+ 12 (* (- level 1) 8))]
    {:fighter {:damage (roll 1 8 (+ main-stat 2))
               :pc true
               :ac 18
               :init ((roll 20 0))
               :hit (roll 20 (+ main-stat (proficiency level)))
               :protection-style false
               :attack-advantage false
               :attack-disadvantage false
               :reaction true
               :defense-advantage false
               :defense-disadvantage false
               :attacks (cond
                          (< level 5) 1
                          (< level 11) 2
                          :else 3)
               :max-hp max-hp
               :hp max-hp}}))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
    :else (attack world actor players enemies)))
