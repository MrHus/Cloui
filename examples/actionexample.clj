(ns actionexample
  (:use cloui.core)
  (:use cloui.listeners))
  
(defn update-lbl
  "Update the label"
  [event lbl]
  (.setText lbl "clicked on the button"))  
 
(def btn (button :text "Click me"))                                          
(def lbl (label :text "Watch me change"))

(def lst (action-listener :performed  (update-lbl event lbl)))
(listen [btn] lst)

(def pnl (panel :components [btn lbl]))

(defn gui
  []
  (frame :center true, :size [500 500], :onclose :exit, :title "Button Palace", :panel pnl))  
  
(defn -main 
  "Launch the actionexample gui"
  [& args]
  (gui))
  
(gui)      