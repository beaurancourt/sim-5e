(ns sim-5e.paladin
  (:require
    [sim-5e.utils :refer :all]))

(defmethod take-turn :default
  [world actor players enemies]
  (attack world actor players enemies))
