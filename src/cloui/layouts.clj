(ns cloui.layouts
  (:import [java.awt BorderLayout]))

(defn border-layout
  "Addes the component too the panel on the choosen area.
  ============= Components keys =============
  :north or :page-start   The components the layout should add to the top.
  :south or :page-end     The components the layout should add to the bottom.
  :east  or :line-start   The components the layout should add to the left.
  :west  or :line-end     The components the layout should add to the right.
  :center                 The components the layout should add to the center."
  [p components]
  (do
    (doseq [c (:north components)]      (.add p c BorderLayout/PAGE_START))
    (doseq [c (:page-start components)] (.add p c BorderLayout/PAGE_START))
    (doseq [c (:south components)]      (.add p c BorderLayout/PAGE_END))  
    (doseq [c (:page-end components)]   (.add p c BorderLayout/PAGE_END))
    (doseq [c (:east components)]       (.add p c BorderLayout/LINE_START))
    (doseq [c (:line-start components)] (.add p c BorderLayout/LINE_START))
    (doseq [c (:west components)]       (.add p c BorderLayout/LINE_END))
    (doseq [c (:line-end components)]   (.add p c BorderLayout/LINE_END)))
    (doseq [c (:center components)]     (.add p c BorderLayout/CENTER)))