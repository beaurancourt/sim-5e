(ns sim-5e.cleric
  (:require
    [sim-5e.utils :refer :all]))

(defmethod generate-pc :cleric
  [level _]
  (let [main-stat 3
        max-hp (+ 10 (* (- level 1) 7))]
    {:cleric {:damage (roll 8 3)
              :pc true
              :ac 18
              :init ((roll 20 0))
              :hit (roll 20 (+ main-stat (proficiency level)))
              :attacks 1
              :max-hp max-hp
              :hp max-hp}}))
