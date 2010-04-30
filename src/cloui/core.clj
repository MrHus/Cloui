(ns cloui.core
  (:import  [javax.swing JFrame JPanel JButton JLabel JTextField JMenuBar JMenu JMenuItem JSlider])
  (:use [cloui.listeners :only (listen)]))

(defn frame
  "Create a frame with optional args.
   ============= Optional args =============
   :panel   The panel you want the frame to have.
   :menu    The menu you want the application to have.
   :size    The size of the frame.
   :onclose What the frame should do when closed possibles are [:exit, :hide, :nothing :dispose], default is :exit.
   :center  Should the frame center on screen, true or false.
   :listen  The listener you want the frame to register too.
   :title   The title of the frame.
   :show    Should the frame show, true or false, default is true, default is empty"
  [args]
  (let [f (JFrame.)
        closeops {:exit JFrame/EXIT_ON_CLOSE, :hide JFrame/HIDE_ON_CLOSE, :nothing JFrame/DO_NOTHING_ON_CLOSE, :dispose JFrame/DISPOSE_ON_CLOSE}]
    
    (if (contains? args :panel)
      (.add f (args :panel)))
    
    (if (contains? args :panel)
      (.setJMenuBar f (args :menu)))
        
    (if (contains? args :size)
      (.setSize f (get (args :size) 0) (get (args :size) 1)))    
      
    (if (contains? args :onclose)
      (if (contains? closeops (args :onclose))
        (.setDefaultCloseOperation f (closeops (args :onclose)))
        (throw (Error. (str "Cloui could not find close operation \"" (args :onclose) "\" see doc for possibles"))))
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

(defn menu
  "Create a menubar with Menu's and MenuItems.
  coll is a vector of maps that represent JMenu's.
  ============= JMenu args ============= 
  :text  The text of the JMenu
  :items The JMenuItems of the JMenu, is a vector of maps too.
  
  ============= JMenuItem args =============
  :text    The text of the JMenuItem
  :listen  The listener you want the JMenuItem to register too.
  
  Example of coll: 
  [{:text \"File\" :items [
     {:text \"New\"}, 
     {:text \"Load\"}, 
     {:text \"Save\" }]}
   {:text \"Edit\" :items [
     {:text \"Undo\" :listen menuact}]}]"
  [coll]
  (let [bar (JMenuBar.)]
    (doseq [jmenu coll]
      (let [menu (JMenu. (jmenu :text))]
        (doseq [item (jmenu :items)]
          (let [mi (JMenuItem. (item :text))]
            (if (contains? item :listen)
             (listen mi (item :listen)))
             (.add menu mi)))
        (.add bar menu)))     
     bar))

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
  "Create a text-field
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
    
(defn slider
  "Create a slider
  ============= Optional args =============
  :min          The minimum value of the slider.
  :max          The maximum value of the slider.
  :value        The starting value of the slider.
  :orientation  The orientation of the slider, options are [:vertical :v :horizontal :h], defaults to horizontal.
  :major-tick   The spaceing between major ticks.
  :minor-tick   The spaceing between minor ticks.
  :labels       Should the major ticks be label, true or false.
  "
  [args]
  (let [s (JSlider.)
        orientations {:v JSlider/VERTICAL, :vertical JSlider/VERTICAL, :h JSlider/HORIZONTAL, :horizontal JSlider/HORIZONTAL}]
    
    (if (contains? args :orientation)
      (if (contains? orientations (args :orientation))
        (.setOrientation s (orientations (args :orientation)))
        (throw (Error. (str "Cloui could not find orientation \"" (args :orientation) "\" see doc for possibles"))))
      (.setOrientation s JSlider/HORIZONTAL))
      
    (if (contains? args :min)
      (.setMinimum s (args :min)))
      
    (if (contains? args :max)
      (.setMaximum s (args :max)))
      
    (if (contains? args :value)
      (.setValue s (args :value)))
    
    (if (contains? args :major-tick)
      (do
        (.setMajorTickSpacing s (args :major-tick))
        (.setPaintTicks s true)))
      
    (if (contains? args :minor-tick)
      (do
        (.setMinorTickSpacing s (args :minor-tick))
        (.setPaintTicks s true)))  
    
    (if (true? (args :labels))
      (.setPaintLabels s true))
      
    s))  
   