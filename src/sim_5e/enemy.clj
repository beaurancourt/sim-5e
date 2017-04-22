(ns sim-5e.enemy
  (:require
    [sim-5e.utils :refer :all]))

(defn template
  [init enemy-name]
  {enemy-name {:pc false
               :ac 18
               :init init
               :reaction true
               :attack-advantage false
               :attack-disadvantage false
               :defense-advantage false
               :defense-disadvantage false
               :dex-save 1
               :wis-save 0
               :attacks [{:num 1 :sides 12 :mod 4 :hit 6}
                         {:num 1 :sides 12 :mod 4 :hit 6}]
               :hp ((roll 5 8 20))}})

(defmethod take-turn :default
  [world actor players enemies]
  (cond
    (<= (-> world actor :hp) 0) world
    :else (attack world actor players enemies)))
