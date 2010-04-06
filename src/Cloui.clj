(ns Cloui
  (:import (javax.swing JFrame JPanel JButton JLabel JTextField)
           (java.awt.event ActionListener)))

(def closeops {:exit JFrame/EXIT_ON_CLOSE, :hide JFrame/HIDE_ON_CLOSE, :nothing JFrame/DO_NOTHING_ON_CLOSE, :dispose JFrame/DISPOSE_ON_CLOSE})

(defn frame 
  [args]
  "Create a frame with optional args"
  (let [f (JFrame.)]
    
    (if (contains? args :panel)
      (.add f (args :panel)))
        
    (if (contains? args :size)
      (.setSize f (get (args :size) 0) (get (args :size) 1)))    
      
    (if (contains? args :onclose)
      (.setDefaultCloseOperation f (closeops (args :onclose)))
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE))
    
    (if (true? (args :center))
      (.setLocationRelativeTo f nil))
     
    (if (contains? args :title)
      (.setTitle f (args :title)))  
      
    (if (contains? args :show)
      (.setVisible f (args :show))
      (.setVisible f true))
      
    f))

(defn #^JPanel panel
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