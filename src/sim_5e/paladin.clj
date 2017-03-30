(ns sim-5e.paladin
  (:require
    [sim-5e.utils :refer :all]))

(def class-key :paladin)
(defmethod generate-pc class-key
  [level _]
  (let [main-stat (main-stat level)
        max-hp (+ 12 (* (- level 1) 8))
        casting-mod (+ 3 (proficiency level))]
    {:paladin (merge {:damage (roll 1 8 (+ main-stat 2))
                      :pc true
                      :ac 18
                      :init ((roll 20 0))
                      :hit (roll 20 (+ main-stat (proficiency level)))
                      :attacks (if (> level 4) 2 1)
                      :casting-mod casting-mod
                      :spell-dc (+ 8 casting-mod)
                      :max-hp max-hp
                      :hp max-hp}
                     (half-caster-spell-slots level))}))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
    :else (attack world actor players enemies)))
