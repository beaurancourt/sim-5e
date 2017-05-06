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
        duelist-attack {:num 1 :sides 8 :mod (+ main-stat 2) :hit to-hit}
        duelist-quarterstaff {:num 1 :sides 6 :mod (+ main-stat 2) :hit to-hit}
        two-handed {:num 1 :sides 10 :mod main-stat :hit to-hit :two-handed true}
        number-of-attacks (cond
                            (< level 5) 1
                            (< level 11) 2
                            :else 3)]
    {:fighter {:pc true
               :ac 18
               :init ((roll 20 0))
               :hit-dice level
               :protection-style false
               :attack-advantage false
               :attack-disadvantage false
               :reaction true
               :defense-advantage false
               :defense-disadvantage false
               :attacks (mapv (constantly duelist-attack) (range number-of-attacks))
               ;:attacks (mapv (constantly melee-attack) (range number-of-attacks))
               ;:attacks (into (mapv (constantly two-handed) (range number-of-attacks))
                              ;[{:num 1 :sides 4 :mod main-stat :hit to-hit :two-handed true}])
               ;:attacks (into (mapv (constantly duelist-quarterstaff) (range number-of-attacks))
                              ;[{:num 1 :sides 4 :mod (+ main-stat 2) :hit to-hit :two-handed false}])
               ;:attacks (mapv (constantly light-attack) (range (+ number-of-attacks 1))) ; two weapon fighting
               :max-hp max-hp
               :hp max-hp}}))

(defmethod take-turn class-key
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
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
