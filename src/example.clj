(ns example
  (:use :reload-all Cloui))

(defn greet
  "Greet the person from the text-field"
  [lbl txt]
  (.setText lbl (str "hello, " (.getText txt))))

(def lbl (label "Enter your name and press the button"))
(def txt (text-field "" 20))
(def btn (button {}))
(def act (action greet lbl txt))

(.addActionListener btn act)

(def pnl (panel lbl txt btn))
  
(defn gui
  []
  (frame {:panel pnl, :onclose :hide, :size [500 500], :center true, :title "Hello you"}))