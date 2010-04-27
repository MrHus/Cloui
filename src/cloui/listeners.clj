(ns cloui.listeners
  (:import [java.awt.event ActionListener MouseListener]))

(defn extract-f 
  [k args]
  "Get the :f from the args map, else return the println function"
  (if (contains? args k) 
    (:f (args k))
    println))
    
(defn extract-args 
  [k args]
  "Get the :args from the args, if there are none return empty list"
  (if (contains? args k) 
    (:args (args k))
    '()))
    
(defmacro action
  "Create an ActionListener"
  [args]
  (let [f (extract-f :performed args)
        a (extract-args :performed args)]   
    `(proxy [ActionListener] []
       (actionPerformed [event#] (~f event# ~@a))))) 

(defmacro mouse
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
       (mouseClicked   [event#] (~f-c event# ~@a-c))
       (mouseEntered   [event#] (~f-e event# ~@a-e))
       (mousePressed   [event#] (~f-p event# ~@a-p))
       (mouseReleased  [event#] (~f-r event# ~@a-r))              
       (mouseExited    [event#] (~f-x event# ~@a-x))))) 