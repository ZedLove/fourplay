(ns fourplay.core
  (:require [fourplay.gameplay :as gp]
            [reagent.core :as r]))

;; -------------------------
;; App State
(def app-state
  (r/atom {:players {:player-1 {:name "Player 1"
                                :pieces 21}
                     :player-2 {:name "Player 2"
                                :pieces 21}}
           :turn :player-1
           :board
           ;; TODO - move to ns
           ;; [x y] {:details-map }

           {0 {[0 0] {:selected false} ;; ^
               [1 0] {:selected false} ;; |
               [2 0] {:selected false} ;; y <-- x -->
               [3 0] {:selected false} ;; |
               [4 0] {:selected false} ;; \/
               [5 0] {:selected false}}

            1 {[0 1] {:selected false}
               [1 1] {:selected false}
               [2 1] {:selected false}
               [3 1] {:selected false}
               [4 1] {:selected false}
               [5 1] {:selected false}}
            
            2 {[0 2] {:selected false}
               [1 2] {:selected false}
               [2 2] {:selected false}
               [3 2] {:selected false}
               [4 2] {:selected false}
               [5 2] {:selected false}}

            3 {[0 3] {:selected false}
               [1 3] {:selected false}
               [2 3] {:selected false}
               [3 3] {:selected false}
               [4 3] {:selected false}
               [5 3] {:selected false}}
            
            4  {[0 4] {:selected false}
                [1 4] {:selected false}
                [2 4] {:selected false}
                [3 4] {:selected false}
                [4 4] {:selected false}
                [5 4] {:selected false}}

            5 {[0 5] {:selected false}
               [1 5] {:selected false}
               [2 5] {:selected false}
               [3 5] {:selected false}
               [4 5] {:selected false}
               [5 5] {:selected false}}

            6 {[0 6] {:selected false}
               [1 6] {:selected false}
               [2 6] {:selected false}
               [3 6] {:selected false}
               [4 6] {:selected false}
               [5 6] {:selected false}}}}))

;; -------------------------
;; Board Components

(defn cell [[[x y] {:keys [selected] :as c}]]
  [:div
   (merge c
          {:class (str "cell" (when selected " selected"))
           :x x             
           :y y
           :on-click #(println (gp/click-cell app-state [x y])) ;; TODO - pass app-state here?
           })])

(defn row [r]
  [:div.row
   (map (fn [c]
          (let [[x y] (first c)]
            ^{:key (str x y)}
            [cell c]))
        (second r))])

(defn scoreboard [players turn]
  (let [{:keys [player-1 player-2]} players
        is-turn? #(= turn (first (keys %)))]
    [:h2.score
     [:span {:class (str (when (is-turn? player-1) " p1"))} (str (:name player-1))]
     [:span {:class (str (when (is-turn? player-2) " p2"))} (str (:name player-2))]]))

(defn board-component [board]
  [:div.board
   [:div.inner
    (map (fn [r]
           ^{:key (first r)}
           [row r])
         board)]])

;; -------------------------
;; Main

(defn home-page []
  (let [{:keys [board players turn] :as st} @app-state]
    [:div.wrapper
     [:h1 "Fourplay"]
     [scoreboard players turn]
     [board-component board]]))

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
