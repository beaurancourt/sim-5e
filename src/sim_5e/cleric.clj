(ns sim-5e.cleric
  (:require
    [sim-5e.utils :refer :all]
    [sim-5e.spells :as spells]))

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
(defn- cast-bless?
  "Cast bless if there are 3 alive players without bless"
  [world players enemies]
  (= (count (filter #(-> world % :bless not)
                    (alive world players)))
     3))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (cast-bless? world players enemies) (spells/bless world actor :spell-1 (take 3 (alive world players)))
    :else (attack world actor players enemies)))
