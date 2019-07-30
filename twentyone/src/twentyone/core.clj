(ns twentyone.core
  (:gen-class))

(defn -main
  [& args]

; Card procedures

(defn card-value [card]
   card
)

(defn deal []
   (+ (rand-int 10) 1)
)


; Hand procedures

(defn make-hand [up-card total]
	(if (list? total)
   (cons up-card total)
   (cons up-card (list total)))
)

(defn hand-up-card [hand]
	(first hand)
)

(defn hand-total [hand]
	(first (rest hand))
)

(defn make-new-hand [first-card]
	(make-hand first-card first-card)
)

(defn hand-add-card [hand new-card]
	(make-hand (hand-up-card hand) (+ new-card (hand-total hand)))
)

; Strategy procedures

(defn user-says-y? []
  (= (read-line) "y")
)

(defn hit? [your-hand opponent-up-card]
  (println)
  (println "Opponent up card: " opponent-up-card)
  (println "Your Total: " (hand-total your-hand))
  (print "Hit? ")
  (flush)
  (user-says-y?)
)

(defn stop-at [stopValue]
    (fn [hand oponentCard] 
      (< (hand-total hand) stopValue)))



; Game procedures

(defn play-hand [strategy my-hand opponent-up-card]
  (cond (> (hand-total my-hand) 21) my-hand ; I lose... give up
        (strategy my-hand opponent-up-card) ; hit?
         (play-hand strategy
                    (hand-add-card my-hand (deal))
                    opponent-up-card)
        :else my-hand   ; stay
  )
)                


(defn twenty-one [player-strategy house-strategy]
  (let [house-initial-hand (make-new-hand (deal))  ; set up house hand            
        player-hand  ; set up initial hand, and play out
           (play-hand player-strategy  ; strategy to use
                      (make-new-hand (deal))  ; initial player hand
                      (hand-up-card house-initial-hand))] ;
                      ;information about house hand available to player
      	(if (> (hand-total player-hand) 21)
          	0                                ; ``bust'': player loses
          	(let [house-hand   ; play out house hand
                 	(play-hand house-strategy
                            house-initial-hand
                            (hand-up-card player-hand))]
            	 (cond 	(> (hand-total house-hand) 21)
                 	  		1  ; ``bust'': house loses
                  		(> (hand-total player-hand) (hand-total house-hand))
                   			1  ; house loses
                  		:else 
                  			0  ; player loses
                  )
            )
        )
    )
)  

(defn test-strategy [firstStrategy secondStrategy runNumber]
  (defn test [firstStrategy secondStrategy current winCount runNumber]
    (if (= current runNumber) ;has completed the number of cycles required, return then number of wins
      winCount
      (if (= 1 (twenty-one firstStrategy secondStrategy)) ;else, play twenty-one then call test on the new values
        (test firstStrategy secondStrategy (inc current) (inc winCount) runNumber)
        (test firstStrategy secondStrategy (inc current) winCount runNumber))))
  (test firstStrategy secondStrategy 0 0 runNumber)
)

(defn watch-player [strategyIn]
  (fn [your-hand opponent-up-card]
      (println "Opponent Up Card: " opponent-up-card)
      (println "Your Total: " (hand-total your-hand))
      (println "Hit? " (strategyIn your-hand opponent-up-card))
      (strategyIn your-hand opponent-up-card))) 

(defn louis [hand opponent-up-card]
  (cond (< (hand-total hand) 12) true
        (> (hand-total hand) 16) false
        (= (hand-total hand) 12) (< opponent-up-card 4)
        (= (hand-total hand) 16) (= opponent-up-card 10)
        (> opponent-up-card 6) true
        :else false))

(println (test-strategy (watch-player louis) (watch-player (stop-at 17)) 2))
(println (test-strategy louis (stop-at 17) 30))

;EC 

(defn christopherrobin [hand opponent-up-card]
  (cond (< (hand-total hand) 8) true
        (= (hand-total hand) 9) (and (> opponent-up-card 6) (< opponent-up-card 3))
        (= (hand-total hand) 10) (> opponent-up-card 9)
        (= opponent-up-card 11) true
        (= (hand-total hand) 11) (= opponent-up-card 11)
        (= (hand-total hand) 12) (and (> opponent-up-card 6) (< opponent-up-card 4))
        (and (> (hand-total hand) 12) (< (hand-total hand) 17)) (< opponent-up-card 7) 
        (and (> (hand-total hand) 16) (< (hand-total hand) 22)) false
        ))

(println "Christopher Robin: This strategy is one created without taking aces into account. It follows these basic principles:
-Always hit on a hand worth a value of 8 or less.
-When you have 9, double down (interpretted as not hitting) when the dealer has a 3, 4, 5 or 6. Otherwise hit.
-When you have a 10, double down (interpretted as not hitting) when the dealer has 2 through 9. Otherwise hit.
-When you have an 11, hit if the dealer has Ace. Otherwise double down.
-When you have 12, stand if the dealer has 4 through 6. Otherwise hit
-When you have 13 through 16, stand if the dealer has 2 through 6. Otherwise hit
-Always stand on hard hands valued at 17 through 21. From http://www.blackjackgala.com/basic-strategy/")

(println (test-strategy christopherrobin (stop-at 17) 30))

(defn eleven? [x]
  (if (= x 11)
    true)
  false)

(defn hand-total "Piglet" [hand]
  (if (and (> (first (rest hand)) 21) (eleven? hand))
    (if (eleven? (card-value (hand-up-card hand)))
      (make-hand 1 (rest hand))))
  (first (rest hand))
)

(println (test-strategy louis (stop-at 17) 30))

)

