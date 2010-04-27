(ns cloui.core
  (:import  [javax.swing JFrame JPanel JButton JLabel JTextField])
  (:use [cloui.listeners :only (listen)]))

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
      (.setDefaultCloseOperation f JFrame/EXIT_ON_CLOSE))
    
    (if (true? (args :center))
      (.setLocationRelativeTo f nil))
    
    (if (contains? args :listen)
      (listen f (args :listen)))
    
    (if (contains? args :title)
      (.setTitle f (args :title)))
      
    (if (contains? args :show)
      (.setVisible f (args :show))
      (.setVisible f true))
      
    f))

(defn panel
  [args]
  "Create a panel with any number of components"
  (let [p (JPanel.)]
    (if (contains? args :components)
      (doseq [c (args :components)] (.add p c)))
      
    (if (contains? args :listen)
      (listen p (args :listen)))
      
    p))

(defn button
  "Create a button with a function"
  [args]               
  (let [b (JButton.)]
    (if (contains? args :text)
      (.setText b (args :text)))
    
    (if (contains? args :listen)
      (listen b (args :listen)))  
      
    b))

(defn label
  "Create a label"
  [args]
  (let [l (JLabel.)]
    (if (contains? args :text)
      (.setText l (args :text)))
      
    (if (contains? args :listen)
      (listen l (args :listen)))  
      
    l))

(defn textfield
  "Add a text-field"
  [args]
  (let [t (JTextField.)]
    (if (contains? args :text)
      (.setText t (args :text)))
    
    (if (contains? args :columns)
      (.setColumns t (args :columns)))
      
    (if (contains? args :listen)
      (listen t (args :listen)))
        
    t))
         