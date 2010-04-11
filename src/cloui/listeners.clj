(ns cloui.listeners
  (:import [java.awt.event ActionListener MouseListener]))

(defmacro action
  "Create an ActionListener"
  [args]
  (let [f (args :performed)]
   `(proxy [ActionListener] []
      (actionPerformed [e#] ~f))))
        
(defmacro mouse
  "Create a MouseListener"
  [args]
  (let [clicked (if (contains? :clicked) (args :clicked) nil)
        entered (if (contains? :entered) (args :entered) nil)
        exited  (if (contains? :exited)  (args :exited) nil)
        pressed (if (contains? :pressed) (args :pressed) nil)
        released(if (contains? :released)(args :released) nil)]
   `(proxy [MouseListener] []
     (mouseClicked [e#] ~clicked)
     (mouseEntered [e#] ~entered)
     (mouseExited  [e#] ~exited)
     (mousePressed [e#] ~pressed)
     (mouseReleased[e#] ~released))))