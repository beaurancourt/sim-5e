(ns sim-5e.spells
  (:require
    [sim-5e.utils :refer :all]))

(defn- spell-level-to-num
  [spell-level]
  (case spell-level
    :spell-1 1
    :spell-2 2
    :spell-3 3
    :spell-4 4
    :spell-5 5
    :spell-6 6
    :spell-7 7
    :spell-8 8
    :spell-9 9))

(defn bless
  [world actor spell-level targets]
  (log actor " blesses " (clojure.string/join " " targets))
  (reduce (fn [world target]
            (update-in world [target :bless] (constantly true)))
          (update-in world [actor spell-level] - 1)
          targets))

(defn cure-wounds
  [world actor spell-level target]
  (let [new-hp (min (-> world target :max-hp)
                    (+ (-> world target :hp)
                       ((roll (spell-level-to-num spell-level)
                              8
                              (-> world actor :casting-mod)))))]
    (log actor " casts cure wound and heals " target " to " new-hp)
    (-> world
        (update-in [actor spell-level] - 1)
        (update-in [target :hp] (constantly new-hp)))))

(defn burning-hands
  [world actor spell-level targets]
  (reduce (fn [world target]
            (let [dex-save ((roll 20 (-> world target :dex-save)))
                  damage ((roll (+ 2 (spell-level-to-num spell-level)) 6))]
              (if (>= dex-save (-> world actor :spell-dc))
                (update-in world [target :hp] do-damage (int (/ damage 2)))
                (update-in world [target :hp] do-damage damage))))
          (update-in world [actor spell-level] - 1)
          targets))
