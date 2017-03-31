(ns sim-5e.core
  (:require
    [sim-5e.paladin :as paladin]
    [sim-5e.cleric :as cleric]
    [sim-5e.fighter :as fighter]
    [sim-5e.sorcerer :as sorcerer]
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
                      {:damage (roll 1 12 4)
                       :pc false
                       :ac 18
                       :init goblin-init
                       :reaction true
                       :attack-advantage false
                       :attack-disadvantage false
                       :defense-advantage false
                       :defense-disadvantage false
                       :dex-save 1
                       :wis-save 0
                       :attacks 2
                       :hit (roll 20 6)
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

(defn simulate-fight
  [world players goblins init-order round]
  (loop [world world
         round round]
    (log "round# " round)
    (if (and (seq (alive world players))
             (seq (alive world goblins)))
      (recur (simulate-round world players goblins init-order round) (inc round))
      {:hp (sum (map #(-> world % :hp (max 0)) players))})))

(defn- simulate
  [goblin-count]
  (loop [total 0
         rounds 0]
    (let [world (generate-world goblin-count 5)
          [player-list goblins] ((juxt filter remove) #(-> world % :pc) (keys world))
          players (set player-list)
          init-order (sort-by #(-> world % :init (* -1)) (keys world))
          resources (simulate-fight world players goblins init-order 0)]
      (log "simulation# " rounds)
      (if (< rounds 3000)
        (recur (+ total (:hp resources)) (inc rounds))
        {:hp (float (/ total rounds))}))))

(defn -main
  []
  (spit "log.txt" "")
  (println 2 (simulate 2))
  (println 3 (simulate 3))
  (println 4 (simulate 4))
  (println 5 (simulate 5)))
