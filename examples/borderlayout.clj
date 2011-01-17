(ns converter
  (:use :reload-all cloui.core))

(def north  (button :text "north"))
(def south  (button :text "south"))
(def east   (button :text "east"))
(def west   (button :text "west"))
(def center (button :text "center"))

; Both versions do the same thing:
(def pnl (panel :layout :border, :components {:south [south] :north [north] :east [east] :west [west] :center [ center]}))
;(def pnl (panel :layout :border, :components {:page-end [south] :page-start [north] :line-start [east] :line-end [west] :center [ center]}))

(defn gui
  []
  (frame :panel pnl, :onclose :exit, :size [500 500], :center true, :title "Borderlayout"))    
  
(defn -main 
  "Launch the converter gui"
  [& args]
  (gui))
  
(gui)  