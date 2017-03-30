(ns sim-5e.spells
  (:require
    [sim-5e.utils :refer :all]))

(defn bless
  [world actor spell-level targets]
  (reduce (fn [world target]
            (update-in world [target :bless] (constantly true)))
          (update-in world [actor spell-level] - 1)
          targets))

(defn cure-wounds
  [world actor spell-level target]
  (let [new-hp (min (-> world target :max-hp)
                    (+ (-> world target :hp)
                       ((roll 8 (-> world actor :casting-mod)))))]
    (-> world
        (update-in [actor spell-level] - 1)
        (update-in [target :hp] (constantly new-hp)))))
