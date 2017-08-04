(ns pwm.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule at-keyframes]]
            [garden.selectors :as sel]
            [clojure.string :as str]))

(def frequencies
  (sort
   (concat
    (for [num (range 1 10)
          denom (range 2 11)
          :when (> denom num)]
      (/ num denom))
    (range 1 11))))

(def widths (range 1 100))

(defstyles screen
  [:span.blink
   {:display "inline-block"
    :width "1em"
    :margin "1em"}]

  (for [f frequencies
        w widths]
    [(str/replace (format ".f%sw%s" f w) #"/" "-")
     {:animation-duration (str (float f) "s")
      :animation-name (str "blink" w)
      :animation-iteration-count "infinite"}])

  (for [w widths]
    (at-keyframes (str "blink" w)
      [:from {:background-color "white"}]
      [(str (dec w) "%") {:background-color "white"}]
      [(str w "%") {:background-color "black"}]
      ["99%" {:background-color "black"}]
      [:to {:background-color "white"}])))
