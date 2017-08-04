(ns pwm.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule at-keyframes]]
            [garden.selectors :as sel]))

(defstyles screen
  [:.blink
   {:display "inline-block"
    :width "1em"}]

  [:.f1w50
   {:animation-duration "1s"
    :animation-name "blink50"
    :animation-iteration-count "infinite"}]

  (at-keyframes "blink50"
    [:from {:background-color "white"}]
    ["49%" {:background-color "white"}]
    ["50%" {:background-color "black"}]
    ["99%" {:background-color "black"}]
    [:to {:background-color "white"}]))
