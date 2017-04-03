(ns sim-5e.core
  (:require
    [sim-5e.paladin :as paladin]
    [sim-5e.cleric :as cleric]
    [sim-5e.fighter :as fighter]
    [sim-5e.sorcerer :as sorcerer]
    [sim-5e.spells :as spells]
    [sim-5e.enemy]
    [sim-5e.utils :refer :all]))

(defn- generate-world
  [goblins player-level]
  (let [base-world (reduce #(merge %1 (generate-pc player-level %2))
                           {}
                           [:paladin :cleric :fighter :sorcerer])
        goblin-init ((roll 20 9))]
    (reduce (fn [world index]
              (merge world
                     {(keyword (str "orog" index))
                      {:pc false
                       :ac 18
                       :init goblin-init
                       :reaction true
                       :attack-advantage false
                       :attack-disadvantage false
                       :defense-advantage false
                       :defense-disadvantage false
                       :dex-save 1
                       :wis-save 0
                       :attacks [{:num 1 :sides 12 :mod 4 :hit 6}
                                 {:num 1 :sides 12 :mod 4 :hit 6}]
                       :hp ((roll 5 8 20))}}))
            base-world
            (range goblins))))

(defn- end-of-round-cleanup
  [world]
  (reduce (fn [world actor]
            (-> world
                (update-in [actor :reaction] (constantly true))
                (update-in [actor :shield-bonus] (constantly 0))))
          world
          (keys world)))

(defn simulate-round
  [world players goblins init-order round]
  (end-of-round-cleanup
    (reduce (fn [world actor]
              (take-turn world actor players goblins))
            world
            init-order)))

(defn pre-combat-actions
  [world players]
  (-> world
      ;(spells/shield-of-faith :cleric :spell-1 :fighter)
      ;(spells/conjure-animals :sorcerer :spell-3)
      ;(spells/bless :cleric :spell-1 [:fighter :paladin :cleric])
      ))

(defn simulate-fight
  [world players goblins round]
  (loop [world world
         round round]
    (log "round# " round)
    (let [init-order (sort-by #(-> world % :init (* -1)) (keys world))]
      (if (and (seq (alive world players))
               (seq (alive world goblins)))
        (recur (simulate-round world players goblins init-order round) (inc round))
        {:hp (sum
               (map #(-> world % :hp (max 0))
                    (filter #(-> world % :summoned not) players)))}))))

(defn- simulate
  [goblin-count]
  (loop [total 0
         rounds 0]
    (log "simulation# " rounds)
    (let [world (generate-world goblin-count 5)
          [player-list goblins] ((juxt filter remove) #(-> world % :pc) (keys world))
          players (set player-list)
          world (pre-combat-actions world players)
          init-order (sort-by #(-> world % :init (* -1)) (keys world))
          resources (simulate-fight world players goblins 0)]
      (if (< rounds 3000)
        (recur (+ total (:hp resources)) (inc rounds))
        {:hp (float (/ total rounds))}))))

(defn -main
  []
  (spit "log.txt" "")
  (println 3 (simulate 3))
  (println 4 (simulate 4))
  (println 5 (simulate 5))
  (println 6 (simulate 6))
  )
