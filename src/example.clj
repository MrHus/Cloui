(ns example
  (:use :reload-all cloui.core))

(defn greet
  "Greet the person from the text-field"
  [lbl txt]
  (.setText lbl (str "hello, " (.getText txt))))

(def lbl (label "Enter your name and press the button"))
(def txt (text-field "" 20))
(def btn (button {}))

(def act (action {:performed (greet lbl txt)}))
(def mse (mouse {:clicked #(println "Clicked")}))

(.addActionListener btn act)
(.addMouseListener label mse)

(def pnl (panel lbl txt btn))
  
(defn gui
  []
  (frame {:panel pnl, :onclose :hide, :size [500 500], :center true, :title "Hello you"}))