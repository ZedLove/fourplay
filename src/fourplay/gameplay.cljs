(ns fourplay.gameplay)

(defn click-cell [st [x y]]
  (let [cell-below-selected? (or (= y 6)
                              (get-in @st [:board (inc y) [x (inc y)] :selected]))]
    (println "valid?" cell-below-selected?)
    (if cell-below-selected?
      ;; also check if target cell selected
      (swap! st update-in [:board y [x y] :selected] (fn [s] true))
      st)))
