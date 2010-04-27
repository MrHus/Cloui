(ns example
  (:use :reload-all cloui.core)
  (:use :reload-all cloui.listeners))

(defn greet
  "Greet the person from the text-field"
  [event lbl txt]
  (.setText lbl (.getText txt)))

(defn mouse-clicked
  "Greet the person from the text-field"
  [event lbl]
  (.setText lbl (str (.getX event) " " (.getY event))))

(def lbl (label "Enter your name and press the button"))
(def txt (text-field "" 20))
(def btn (button {}))

(def act (action {:performed {:f greet, :args [lbl txt]}}))
(def mse (mouse { :clicked {:f mouse-clicked, :args [lbl]}
                  :pressed {:f mouse-clicked, :args [lbl]}
                  :released {:f mouse-clicked, :args [lbl]}
                  :exited {:f mouse-clicked, :args [lbl]}
                  :entered {:f mouse-clicked, :args [lbl]}}))  

(.addActionListener btn act)

(def pnl (panel lbl txt btn))

(.addMouseListener pnl mse)

(defn gui
  []
  (frame {:panel pnl, :onclose :hide, :size [500 500], :center true, :title "Hello you"}))

(defn -main 
  "Launch the example gui"
  [& args]
  (gui))