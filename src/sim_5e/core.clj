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
                     {(keyword (str "goblin" index))
                      {:damage (roll 1 12 4)
                       :pc false
                       :ac 18
                       :init goblin-init
                       :attack-advantage false
                       :attack-disadvantage false
                       :defense-advantage false
                       :defense-disadvantage false
                       :attacks 2
                       :hit (roll 20 6)
                       :hp ((roll 5 8 20))}}))
            base-world
            (range goblins))))

(defn simulate-round
  [world players goblins init-order round]
  (reduce (fn [world actor]
            (take-turn world actor players goblins))
          world
          init-order))

(defn simulate-fight
  [world players goblins init-order round]
  (loop [world world
         round round]
    (log "round# " round)
    (if (and (seq (alive world players))
             (seq (alive world goblins)))
      (recur (simulate-round world players goblins init-order round) (inc round))
      {:hp (sum (map #(-> world % :hp (max 0)) players))
       :ko (count (filter #(-> world % :hp (< 0)) players))})))

(defn- simulate
  [goblin-count]
  (loop [total 0
         ko 0
         rounds 0]
    (let [world (generate-world goblin-count 5)
          [player-list goblins] ((juxt filter remove) #(-> world % :pc) (keys world))
          players (set player-list)
          init-order (sort-by #(-> world % :init (* -1)) (keys world))
          resources (simulate-fight world players goblins init-order 0)]
      (log "simulation# " rounds)
      (if (< rounds 100)
        (recur (+ total (:hp resources)) (+ ko (:ko resources)) (inc rounds))
        {:hp (float (/ total rounds)) :ko (float (/ ko rounds))}))))

(defn -main
  []
  (spit "log.txt" "")
  (println 1 (simulate 1))
  (println 2 (simulate 2))
  (println 3 (simulate 3))
  (println 4 (simulate 4)))
(-main)
