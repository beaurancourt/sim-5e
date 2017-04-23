(ns sim-5e.core
  (:require
    [sim-5e.paladin :as paladin]
    [sim-5e.cleric :as cleric]
    [sim-5e.fighter :as fighter]
    [sim-5e.sorcerer :as sorcerer]
    [sim-5e.spells :as spells]
    [sim-5e.enemy :as enemy]
    [sim-5e.utils :refer :all]))

(defn- generate-world
  [goblins player-level player-list]
  (let [base-world (into {}
                         (map #(generate-pc player-level %)
                              player-list))
        goblin-init ((roll 20 9))]
    (into base-world
          (map #(enemy/template goblin-init (keyword (str "orog" %)))
               (range goblins)))))

(defn- generate-player-world
  [player-level player-list]
  (into {}
        (map #(generate-pc player-level %)
             player-list)))

(defn- add-enemies-to-world
  [world enemy-count]
  (let [enemy-init ((roll 20 2))]
    (into world
          (map #(enemy/template enemy-init (keyword (str "orog" %)))
               (range enemy-count)))))

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
  [world players goblins]
  (loop [world (pre-combat-actions world players)
         round 0]
    (log "round# " round)
    (let [init-order (sort-by #(-> world % :init (* -1)) (keys world))]
      (if (and (seq (alive world players))
               (seq (alive world goblins)))
        (recur (simulate-round world players goblins init-order round) (inc round))
        world))))
(defn- strip-enemies
  [world players]
  (select-keys world players))

(defn- simulate-adventuring-day
  [players enemy-count]
  (loop [world (generate-player-world 5 players)
         encounter-number 0]
    (if (< encounter-number 1)
      (let [world (-> world
                      (strip-enemies players)
                      (add-enemies-to-world enemy-count))
            enemies (remove #(-> world % :pc) (keys world))]
        (recur (simulate-fight world players enemies) (inc encounter-number)))
      {:hp (sum (map #(-> world % :hp (max 0))
                     (filter #(-> world % :summoned not) players)))})))

(defn- simulate
  [goblin-count]
  (loop [total 0
         runs 0]
    (log "simulation# " runs)
    (let [players #{:paladin :cleric :fighter :sorcerer}
          resources (simulate-adventuring-day players goblin-count)]
      (if (< runs 30)
        (recur (+ total (:hp resources)) (inc runs))
        {:hp (float (/ total runs))}))))

(defn -main
  []
  (spit "log.txt" "")
  (println 3 (simulate 3))
  (println 4 (simulate 4))
  (println 5 (simulate 5))
  (println 6 (simulate 6))
  )
