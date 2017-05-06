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
                              (-> world actor :healing-mod)))))]
    (log actor " casts " spell-level " cure wound and heals " target " from " (-> world target :hp) " to " new-hp)
    (-> world
        (update-in [actor spell-level] - 1)
        (update-in [target :hp] (constantly new-hp)))))

(defn burning-hands
  [world actor spell-level targets]
  (reduce (fn [world target]
            (let [dex-save ((roll 20 (-> world target :dex-save)))
                  saved (>= dex-save (-> world actor :spell-dc))
                  damage-roll ((roll (+ 2 (spell-level-to-num spell-level)) 6 0))
                  damage (if (>= dex-save (-> world actor :spell-dc))
                           (int (/ damage-roll 2))
                           damage-roll)]
              (log actor " casts burning hands on " target " for " damage)
              (update-in world [target :hp] do-damage damage)))
          (update-in world [actor spell-level] - 1)
          targets))

(defn twin-haste
  [world actor spell-level targets]
  (reduce (fn [world target]
            (log actor " casts haste on " target)
            (-> world
                (update-in [target :ac] + 2)
                (update-in [target :attacks] + 1)
                (update-in [target :hasted] (constantly true))))
          (-> world
              (update-in [actor spell-level] - 1)
              (update-in [actor :concentrating] (constantly true)))
          targets))

(defn fireball
  [world actor spell-level targets]
  (reduce (fn [world target]
            (let [dex-save ((roll 20 (-> world target :dex-save)))
                  saved (>= dex-save (-> world actor :spell-dc))
                  damage-roll ((roll (+ 5 (spell-level-to-num spell-level)) 6 0))
                  damage (if (>= dex-save (-> world actor :spell-dc))
                           (int (/ damage-roll 2))
                           damage-roll)]
              (log actor " casts fireball on " target " for " damage)
              (update-in world [target :hp] do-damage damage)))
          (update-in world [actor spell-level] - 1)
          targets))

(defn slow
  [world actor spell-level targets]
  (reduce (fn [world target]
            (let [wis-save ((roll 20 (-> world target :wis-save)))]
              (if (< wis-save (-> world actor :spell-dc))
                (do
                  (log :sorcerer " hits " target " with slow")
                  (-> world
                      (update-in [target :ac] - 2)
                      (update-in [target :attacks] (constantly 1))))
                (do
                  (log :sorcerer " misses " target " with slow")
                  world))))
          (-> world
              (update-in [actor spell-level] - 1)
              (update-in [actor :concentrating] (constantly true)))
          targets))

(defn conjure-animals
  [world actor spell-level]
  (log actor " conjures 8 wolves")
  (let [wolf-init ((roll 20 2))]
    (reduce (fn [world index]
              (merge world
                     {(keyword (str "wolf" index))
                      {:pc true
                       :ac 13
                       :init wolf-init
                       :reaction true
                       :attack-advantage false
                       :attack-disadvantage true
                       :defense-advantage false
                       :defense-disadvantage false
                       :dex-save 1
                       :wis-save 0
                       :attacks [{:num 2 :sides 4 :mod 2 :hit 4}]
                       :hp ((roll 2 8 2))}}))
            (-> world
                (update-in [actor spell-level] - 1)
                (update-in [actor :concentrating] (constantly true)))
            (range 8))))

(defn shield-of-faith
  [world actor spell-level target]
  (log actor " casts shield of faith on " target)
  (-> world
      (update-in [actor spell-level] - 1)
      (update-in [actor :concentrating] (constantly true))
      (update-in [target :ac] + 2)))

(defn magic-missile
  [world actor spell-level target]
  (let [number-of-missiles (+ 2 (spell-level-to-num spell-level))
        missile-damage ((roll number-of-missiles 4 number-of-missiles))]
    (log actor " hits " target " with a magic missile for " missile-damage)
    (-> world
        (update-in [actor spell-level] - 1)
        (update-in [target :hp] do-damage missile-damage))))
