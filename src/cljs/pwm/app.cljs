(ns pwm.app
  (:require [reagent.core :as reagent :refer [atom]]))

(defn led [& classes]
  [:span.blink {:class classes
          :dangerouslySetInnerHTML {:__html "&nbsp;"}}])

(defn root []
  [:div
   [led :f1w50]])
(defn init []
  (reagent/render-component [root]
                            (.getElementById js/document "container")))
