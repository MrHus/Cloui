(ns cloui.listeners
  (:import [java.awt.event ActionListener MouseListener KeyListener])
  (:import [javax.swing.event ChangeListener]))

(defn extract-f 
  [k args]
  "Get the :f from the args map, else return the println function"
  (if (contains? args k) 
    (:f (args k))
    nil?))
    
(defn extract-args 
  [k args]
  "Get the :args from the args, if there are none return empty list"
  (if (contains? args k) 
    (:args (args k))
    '()))
    
(defmacro action-listener
  "Create an ActionListener"
  [args]
  (let [f (extract-f :performed args)
        a (extract-args :performed args)]   
    `(proxy [ActionListener] []
       (actionPerformed [event#] (~f event# ~@a))))) 

(defmacro mouse-listener
  "Create a MouseListener"
  [args]
  (let [f-c (extract-f    :clicked  args)
        a-c (extract-args :clicked  args)        
        f-e (extract-f    :entered  args)
        a-e (extract-args :entered  args)
        f-p (extract-f    :pressed  args)
        a-p (extract-args :pressed  args)        
        f-r (extract-f    :released args)
        a-r (extract-args :released args)        
        f-x (extract-f    :exited   args)
        a-x (extract-args :exited   args)]         
    `(proxy [MouseListener] []
       (mouseClicked  [event#] (~f-c event# ~@a-c))
       (mouseEntered  [event#] (~f-e event# ~@a-e))
       (mousePressed  [event#] (~f-p event# ~@a-p))
       (mouseReleased [event#] (~f-r event# ~@a-r))              
       (mouseExited   [event#] (~f-x event# ~@a-x))))) 
       
(defmacro key-listener 
  "Create a KeyListener"
  [args]
  (let [f-p (extract-f    :pressed  args)
        a-p (extract-args :pressed  args)
        f-t (extract-f    :typed    args)
        a-t (extract-args :typed    args)
        f-r (extract-f    :released args)
        a-r (extract-args :released args)]
    `(proxy [KeyListener] []
       (keyPressed  [event#] (~f-p event# ~@a-p))
       (keyTyped    [event#] (~f-t event# ~@a-t))
       (keyReleased [event#] (~f-r event# ~@a-r)))))
       
(defmacro change-listener 
  "Create a StateChangedListener"
  [args]
  (let [f (extract-f    :changed  args)
        a (extract-args :changed  args)]
    `(proxy [ChangeListener] []
       (stateChanged  [event#] (~f event# ~@a)))))       
       
(defn listen
  "Make the c listen to the l"
  [c l]
  (letfn [(add-lst 
            [com]
              (cond 
                (instance? ActionListener l) (.addActionListener com l)
                (instance? MouseListener l)  (.addMouseListener  com l)
                (instance? KeyListener l)    (.addKeyListener    com l)
                (instance? ChangeListener l) (.addChangeListener com l)
                :else (throw (Error. "Listener not recognized"))))]
                
     (if (vector? c)
       (doseq [com c] (add-lst com))
       (add-lst c))))                      