(ns example
  (:use :reload-all cloui.core)
  (:use :reload-all cloui.listeners)
  (:import  [java.awt.event KeyEvent]))

(defn greet
  "Greet the person from the text-field"
  [event lbl txt]
  (.setText lbl (.getText txt)))

(defn mouse-clicked
  "Add the coordinates to the label"
  [event lbl]
  (.setText lbl (str (.getX event) " " (.getY event))))

(defn key-action
  "Add the strokes to the label"
  [event lbl]
  (.setText lbl (str (.getKeyChar event))))

(def lbl (label "Enter your name and press the button"))
(def txt (text-field "" 20))
(def btn (button {}))

(def pnl (panel lbl txt btn))   

(def act (action-listener {:performed {:f greet, :args [lbl txt]}}))
(def mse (mouse-listener {:clicked {:f mouse-clicked, :args [lbl]}}))
(def ky  (key-listener {:pressed {:f key-action, :args [lbl]}}))  

(.addActionListener btn act)
(.addKeyListener txt ky)
(.addMouseListener pnl mse)

(defn gui
  []
  (frame {:panel pnl, :onclose :hide, :size [500 500], :center true, :title "Hello you"}))

(defn -main 
  "Launch the example gui"
  [& args]
  (gui))