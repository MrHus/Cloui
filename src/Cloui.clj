(ns Cloui
  (:import (javax.swing JFrame JPanel JButton JLabel)
           (java.awt.event ActionListener)))

(defn frame 
  [#^JPanel p]
  "Create a frame with a panel"
  (let [f (JFrame.)]
    (doto f
      (.add p)
      (.setVisible true)
      (.setSize 500 500)
      (.setDefaultCloseOperation JFrame/HIDE_ON_CLOSE))
   f))

(defn panel
  [& components]
  "Add a panel with any number of components"
  (let [p (JPanel.)]
    (doseq [c components] (.add p c))
    p))

(defmacro on-click-do 
  "Make something do f."
  [b f]
  `(.addActionListener ~b 
	  (proxy [ActionListener] []
		  (actionPerformed [evt#]
			  ~f))))

(defmacro button
  "Create a button with a function"
  [f]               
  `(let [b# (JButton. "click")]
     (on-click-do b# ~f)
     b#)) 

(defn label
  "Add a label"
  [t]
  (JLabel. t))
  
(defn gui
  []
  (frame 
    (panel
      (button
        (println "Clicked"))
      (label "Hello World"))))  