(ns sim-5e.cleric
  (:require
    [sim-5e.utils :refer :all]))

(def class-key :cleric)

(defmethod generate-pc class-key
  [level _]
  (let [main-stat 3
        max-hp (+ 10 (* (- level 1) 7))]
    {:cleric (merge {:damage (roll 8 3)
                     :pc true
                     :ac 18
                     :init ((roll 20 0))
                     :hit (roll 20 (+ main-stat (proficiency level)))
                     :attacks 1
                     :max-hp max-hp
                     :hp max-hp}
                    (full-caster-spell-slots level))}))


(defmethod take-turn class-key
  [world actor players enemies]
  (attack world actor players enemies))
