(ns example
  (:use :reload-all cloui.core)
  (:use :reload-all cloui.listeners)
  (:gen-class))

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

(defn menu-action
  "Change the label to the name of the item"
  [event lbl]
  (.setText lbl (.. event getSource getText)))

(declare lbl txt btn)

(def act (action-listener {:performed {:f greet, :args [lbl txt]}}))
(def mse (mouse-listener {:clicked {:f mouse-clicked, :args [lbl]}}))
(def ky  (key-listener {:pressed {:f key-action, :args [lbl]}}))

(def lbl (label {:text "Enter your name and press the button"}))
(def txt (textfield {:text "Type something in!", :columns 20, :listen ky}))
(def btn (button {:text "Click me!", :listen act}))                                                                          
(def box (checkbox {:text "Check me", :selected true}))

(def pnl (panel {:components [lbl txt btn box]}))
(listen pnl mse)

(def menuact (action-listener {:performed {:f menu-action, :args [lbl]}}))               
(def mnu (menu [{:text "File" :items [{:text "New" :listen menuact}, {:text "Load" :listen menuact}, {:text "Save" :listen menuact}]}
                {:text "Edit" :items [{:text "Undo" :listen menuact}]}]))                 

(defn gui
  []
  (frame {:panel pnl, :menu mnu :onclose :hide, :size [500 500], :center true, :title "Hello you"}))

(defn -main 
  "Launch the example gui"
  [& args]
  (gui))