(ns pwm.app
  (:require [reagent.core :as reagent :refer [atom]]))

(defn led [& classes]
  [:span.blink {:class classes
                :dangerouslySetInnerHTML {:__html "&nbsp;"}}])

(defn root []
  [:div
   (for [freq ["1-8" "1-4" "1-2" "1" "2" "4"]]
     ^{:key freq}
     [:div
      (for [width (range 10 91 10)]
        ^{:key (str freq "-" width)}
        [led (str "f" freq "w" width)])])])

(defn init []
  (reagent/render-component [root]
                            (.getElementById js/document "container")))
