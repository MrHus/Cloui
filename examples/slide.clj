(ns slide
  (:use cloui.core)
  (:use cloui.listeners))

(defn update-lbl
  "Update the label"
  [event lbl]
  (.setText lbl (str (.getValue (.getSource event)))))  
                                          
(def lbl (label :text "Watch me change"))
(def sldv (slider :min 0, :max 100, :orientation :vertical, :value 20))
(def sldh (slider :min 0, :max 100, :orientation :h, :value 50, :major-tick 50, :minor-tick 25, :labels true))

(def lst (change-listener :changed (update-lbl event lbl)))

(listen [sldv, sldh] lst)

(def pnl (panel :components [sldv sldh lbl]))

(defn gui
  []
  (frame :center true, :size [500 500], :onclose :exit, :title "Sliders Palace", :panel pnl))  
  
(defn -main 
  "Launch the slide gui"
  [& args]
  (gui))  
  
(gui)   