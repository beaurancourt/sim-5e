(ns sim-5e.spells)

(defn bless
  [world actor spell-level targets]
  (println targets)
  (reduce (fn [world target]
            (update-in world [target :bless] (constantly true)))
          (update-in world actor spell-level - 1)
          targets))
