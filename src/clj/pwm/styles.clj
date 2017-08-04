(ns pwm.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule at-keyframes]]
            [garden.selectors :as sel]
            [clojure.string :as str]))

(def periods
  (sort
   (set
    (concat
     (map #(/ % 10) (range 1 10))
     (for [num (range 1 10)
           denom [2 3 4 8]
           :when (> denom num)]
       (/ num denom))
     (range 1 11)))))

(def widths (range 5 100 5))

(defstyles screen
  [:span.blink
   {:display "inline-block"
    :border "1px black solid"
    :height "2em"
    :width "2em"
    :margin "4%"
    :box-shadow "1px 1px 3px rgba(0,0,0,0.2)"}]

  (for [w widths
        p periods]
    [(str/replace (format ".f%sw%s" p w) #"/" "-")
     {:animation-duration (str (float p) "s")
      :animation-name (str "blink" w)
      :animation-iteration-count "infinite"}])

  (for [w widths]
    (at-keyframes (str "blink" w)
      [:from {:background-color "white"}]
      [(str (dec w) "%") {:background-color "white"}]
      [(str w "%") {:background-color "green"}]
      ["99%" {:background-color "green"}]
      [:to {:background-color "white"}])))
