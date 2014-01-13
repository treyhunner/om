(ns examples.history.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [om.chronos :as c]))

(enable-console-print!)

(def app-state (atom {:text "Hello world!"}))

(defn widget [data]
  (om/component
    (dom/div nil (:text data))))

(om/root app-state widget (.getElementById js/document "app"))

(comment
  (def control (c/simple-control app-state))

  (c/record control)

  (swap! app-state assoc :text "Goodbye world!")

  (c/back control)
  (c/forward control)
  )