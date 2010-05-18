;; A simple farenheit to celsius converter

(ns converter
  (:use :reload-all cloui.core)
  (:use :reload-all cloui.listeners))

(defn fahrenheit-to-celcius
  [v]
  (/ (- v 32) 1.8))

(defn celcius-to-fahrenheit
  [v]
  (+ (* 1.8 v) 32))

(defn parse [s]
  (try (Double/parseDouble (.trim s))
    (catch NumberFormatException e nil)))

(defn convert
  "Call the convert based on the source of the event"
  [event txtf txtc]
  (if (= (.getSource event) txtf)
    (.setText txtc (str (fahrenheit-to-celcius (parse (.getText txtf)))))
    (.setText txtf (str (celcius-to-fahrenheit (parse (.getText txtc)))))))

(def lblf (label :text "Fahrenheit: "))
(def txtf (textfield :columns 20))

(def lblc (label :text "Celcius: "))
(def txtc (textfield :columns 20))

(def lst (key-listener :released {:f convert :args [txtf txtc]}))  

(listen [txtf txtc] lst)

(def pnl (panel :components [lblf txtf lblc txtc]))
  
(defn gui
  []
  (frame :panel pnl, :onclose :exit, :size [300 500], :center true, :title "Cloverter"))    
  
(defn -main 
  "Launch the converter gui"
  [& args]
  (gui))    