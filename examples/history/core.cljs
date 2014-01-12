(ns examples.history.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [om.chronos :as c]))

(defn widget [data]
  (om/component
    (dom/div nil "Hello world!")))

(om/root {} widget (.getElementById js/document "app"))
