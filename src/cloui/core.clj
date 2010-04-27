(ns cloui.core
  (:import  [javax.swing JFrame JPanel JButton JLabel JTextField])
  (:use [cloui.listeners :only (listen)]))

(def closeops {:exit JFrame/EXIT_ON_CLOSE, :hide JFrame/HIDE_ON_CLOSE, :nothing JFrame/DO_NOTHING_ON_CLOSE, :dispose JFrame/DISPOSE_ON_CLOSE})

(defn frame
  "Create a frame with optional args.
   ============= Optional args =============
   :panel   The panel you want the frame to have.
   :size    The size of the frame.
   :onclose What the frame should do when closed possibles are [:exit, :hide, :nothing :dispose], default is :exit.
   :center  Should the frame center on screen, true or false.
   :listen  The listener you want the frame to register too.
   :title   The title of the frame.
   :show    Should the frame show, true or false, default is true, default is empty"
  [args]
  (let [f (JFrame.)]
    
    (if (contains? args :panel)
      (.add f (args :panel)))
        
    (if (contains? args :size)
      (.setSize f (get (args :size) 0) (get (args :size) 1)))    
      
    (if (contains? args :onclose)
      (if (contains? closeops (args :onclose))
        (.setDefaultCloseOperation f (closeops (args :onclose)))
        (throw (Error. (str "Cloui could not find close operation \"" (args :onclose) "\" see docs for possibles"))))
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
  "Create a panel with any number of components.
  ============= Optional args =============
   :components   The components the panel should add to itself.
   :listen       The listener you want the panel to register too."
  [args]
  (let [p (JPanel.)]
    (if (contains? args :components)
      (doseq [c (args :components)] (.add p c)))
      
    (if (contains? args :listen)
      (listen p (args :listen)))
      
    p))

(defn button
  "Create a button.
   ============= Optional args =============
   :text   The text ontop of the button.
   :listen The listener you want the button to register too."
  [args]               
  (let [b (JButton.)]
    (if (contains? args :text)
      (.setText b (args :text)))
    
    (if (contains? args :listen)
      (listen b (args :listen)))  
      
    b))

(defn label
  "Create a label.
   ============= Optional args =============
   :text   The default text of the label.
   :listen The listener you want the label to register too."
  [args]
  (let [l (JLabel.)]
    (if (contains? args :text)
      (.setText l (args :text)))
      
    (if (contains? args :listen)
      (listen l (args :listen)))  
      
    l))

(defn textfield
  "Add a text-field
  ============= Optional args =============
   :text    The default text of the textfield.
   :columns The amount of columns of the textfield.
   :listen  The listener you want the textfield to register too."
  [args]
  (let [t (JTextField.)]
    (if (contains? args :text)
      (.setText t (args :text)))
    
    (if (contains? args :columns)
      (.setColumns t (args :columns)))
      
    (if (contains? args :listen)
      (listen t (args :listen)))
        
    t))      