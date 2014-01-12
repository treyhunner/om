(ns om.chronos)

(defprotocol IHistory
  (-record [this])
  (-stop [this]))

(defprotocol IHistoryTree
  (-commit [this])
  (-branch [this name])
  (-merge [this state]))

(defprotocol ITimeline
  (-previous [this])
  (-next [this])
  (-switch [this name]))

(defprotocol IStep
  (-forward [this] [this steps])
  (-back [this] [this steps]))

(defprotocol IBookmark
  (-bookmark [this name])
  (-jump [this name])
  (-list [this]))

;; =============================================================================
;; API

(defn record [control]
  (-record control))

;; =============================================================================
;; SimpleControl

(deftype SimpleControl [app-state history ^:mutable idx tree bookmarks pred key]
  IHistory
  (-record [_]
    (add-watch app-state key
      (fn [_ _ old new]
        (when (pred old new)
          (swap! history conj new)))))
  (-stop [_] (remove-watch app-state key))
  IStep
  (-forward [_]
    (set! idx (inc idx))
    (reset! app-state (nth history idx)))
  (-back [_]
    (set! idx (dec idx))
    (reset! app-state (nth history idx))))

(defn simple-control
  ([app-state] (simple-control app-state (fn [old new] true)))
  ([app-state pred]
    (SimpleControl.
      app-state (atom [@app-state]) 0 (atom (sorted-map)) (atom {})
      pred (gensym))))

