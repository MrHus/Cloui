(ns Cloui
  (:import (javax.swing JFrame JPanel JButton JLabel JTextField)
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
  "Create a panel with any number of components"
  (let [p (JPanel.)]
    (doseq [c components] (.add p c))
    p))

(defmacro add-listener-to 
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
     (add-listener-to b# ~f)
     b#)) 

(defn label
  "Create a label"
  [t]
  (JLabel. t))

(defn text-field
  "Add a text-field"
  ([]
    (JTextField.))  
  ([#^String s]
    (JTextField. s))    
  ([#^String s c]
    (JTextField. s c)))
  
(defn gui
  []
  (frame 
    (panel
      (button
        (println "Clicked"))
      (label "Hello World")
      (text-field)
      (text-field "Hello you")
      (text-field "Hello" 40))))  