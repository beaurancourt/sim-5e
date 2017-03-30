(ns sim-5e.core
  (:require
    [sim-5e.paladin :as paladin]
    [sim-5e.cleric :as cleric]
    [sim-5e.fighter :as fighter]
    [sim-5e.sorcerer :as sorcerer]
    [sim-5e.utils :refer :all]))

(defn- generate-pc
  [player-level pc-class]
  (case pc-class
    :paladin (paladin/generate-paladin player-level)
    :cleric (cleric/generate-cleric player-level)
    :fighter (fighter/generate-fighter player-level)
    :sorcerer (sorcerer/generate-sorcerer player-level)))

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
                       :attacks 2
                       :hit (roll 20 6)
                       :hp ((roll 5 8 20))}}))
            base-world
            (range goblins))))

(defn- alive
  [world actors]
  (filter #(> (-> world % :hp) 0) actors))

(defn- pick-player-target
  [world goblins]
  (or (first (alive world goblins)) :goblin0))

(defn- pick-goblin-target
  [world players]
  (rand-nth (or (seq (alive world players)) [:paladin])))

(defn- bless-players
  [world]
  (reduce (fn [world player]
            (update-in world [player :bless] (constantly true)))
          world
          [:paladin :cleric :fighter]))

(defn- bane-enemies
  [world]
  (reduce (fn [world player]
            (update-in world [player :bane] (constantly true)))
          world
          [:goblin1 :goblin2 :goblin3]))

(defn- attack
  [world attacker players goblins]
  (if (> (-> world attacker :hp) 0)
    (reduce (fn [world _]
              (let [target (if (players attacker)
                             (pick-player-target world goblins)
                             (pick-goblin-target world players))
                    bless (if (-> world attacker :bane)
                            (* -1 ((roll 4 0)))
                            0)
                    attack-roll (+ ((-> world attacker :hit)) bless)]
                (if (>= attack-roll (-> world target :ac))
                  (let [damage ((-> world attacker :damage))
                        updated-world (update-in world [target :hp] - damage)]
                    (if (and (= target :cleric) (< ((roll 20 2)) 10))
                      (reduce (fn [world player]
                                (update-in world [player :bless] (constantly false)))
                              updated-world
                              [:paladin :cleric :fighter])
                      updated-world))
                  world)))
            world
            (range (or (-> world attacker :attacks) 1)))
    world))

(defn simulate-round
  [world players goblins init-order round bless]
  (reduce (fn [world actor]
            (let [target (if (players actor)
                           (pick-player-target world goblins)
                           (pick-goblin-target world players))]
              (if (and (= actor :cleric)
                       (= round 0)
                       bless)
                (bless-players world)
                (attack world actor players goblins))))
          world
          init-order))

(defn simulate-fight
  [world players goblins init-order round bless]
  (loop [world world
         round round]
    (if (and (seq (alive world players))
             (seq (alive world goblins)))
      (recur (simulate-round world players goblins init-order round bless) (inc round))
      {:hp (sum (map #(-> world % :hp (max 0)) players))
       :ko (count (filter #(-> world % :hp (< 0)) players))})))

(defn- test-bless
  [goblin-count bless]
  (loop [total 0
         ko 0
         rounds 0]
    (let [world (generate-world goblin-count 5)
          [player-list goblins] ((juxt filter remove) #(-> world % :pc) (keys world))
          players (set player-list)
          init-order (sort-by #(-> world % :init (* -1)) (keys world))
          resources (simulate-fight world players goblins init-order 0 bless)]
      (if (< rounds 3000)
        (recur (+ total (:hp resources)) (+ ko (:ko resources)) (inc rounds))
        {:hp (float (/ total rounds)) :ko (float (/ ko rounds))}))))

(defn -main
  []
  (println 1 (test-bless 1 true) (test-bless 1 false))
  (println 2 (test-bless 2 true) (test-bless 2 false))
  (println 3 (test-bless 3 true) (test-bless 3 false))
  (println 4 (test-bless 4 true) (test-bless 4 false))
  (println 5 (test-bless 5 true) (test-bless 5 false))
  (println 6 (test-bless 6 true) (test-bless 6 false))
  (println 7 (test-bless 7 true) (test-bless 7 false)))
