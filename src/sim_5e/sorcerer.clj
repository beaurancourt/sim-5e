(ns sim-5e.sorcerer
  (:require
    [sim-5e.utils :refer :all]))

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
    {:sorcerer (merge {:damage (roll firebolt-dice 10 fire-bolt-mod)
                       :pc true
                       :ac 15
                       :init ((roll 20 1))
                       :hit (roll 20 casting-mod)
                       :casting-mod casting-mod
                       :spell-dc (+ 8 casting-mod)
                       :attack-advantage false
                       :attack-disadvantage false
                       :defense-advantage false
                       :defense-disadvantage false
                       :attacks 1
                       :max-hp max-hp
                       :hp max-hp}
                      (full-caster-spell-slots level))}))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
    :else (attack world actor players enemies)))
