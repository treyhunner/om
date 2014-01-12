(ns om.chronos)

(defprotocol IAppHistory
  (-record [this])
  (-stop [this]))

(defprotocol IAppHistoryTree
  (-commit [this])
  (-branch [this name])
  (-merge [this state]))

(defprotocol IAppTimeline
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
