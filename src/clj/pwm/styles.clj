(ns pwm.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule at-keyframes]]
            [garden.selectors :as sel]
            [clojure.string :as str]))

(def freqs
  (sort
   (set
    (concat
     (map #(read-string (str "0." %)) (range 1 10))
     (for [num (range 1 10)
           denom [2 3 4 8]
           :when (> denom num)]
       (float (/ num denom)))
     (range 1 11)))))

(def widths (range 1 100 5))

(defstyles screen
  [:span.blink
   {:display "inline-block"
    :width "1em"
    :margin "1em"}]

  (for [w widths
        f freqs]
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
