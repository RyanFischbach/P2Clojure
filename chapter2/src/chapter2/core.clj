(ns chapter2.core
  (:gen-class))

(defn -main
  [& args]
  (println "Chapter 2 Part 1 Exercises")
  (println "-----------------------------------")
  (println "")
	
;Chapter 2

;List practice!  1.a Write a procedure called repeat2 that takes one input and
;returns a list containing two copies of the argument. Example: (repeat2 4)
;returns (4 4)

(defn repeat2 [num]
  (list num num)
  )

(println "EX 1.a:")
(println "(repeat2 4) = " (repeat2 4))
(println "")

;1.b Write a procedure called repeat3 that takes one input and returns a list
;containing three copies of the argument. Example: (repeat3 4)  returns (4 4 4)

(defn repeat3 [num]
  (list num num num)
  )

(println "EX 1.b:")
(println "(repeat3 4) = " (repeat3 4))
(println "")

;1.c Write a generalized procedure called repeat that takes two inputs and
;returns a list containing the second argument number of copies of the first
;argument. Example: (repeat 4 3)  returns (4 4 4)

(defn auxrepeatn "Explained to me by my lord and savior Connor Kennedy" [listInput number times]
(if (>= times 1)
   (cons number (auxrepeatn listInput number (- times 1)))
  listInput))

(defn repeatn [number times]
  (auxrepeatn (list) number times))

(println "EX 1.c:")
(println "(repeatn 4) = " (repeatn 4 3))
(println "")

;1.d Rewrite repeat2 and repeat3 so they use repeat

(defn repeat2 [num]
  (repeatn num 2))
  

(println "EX 1.d:")
(println "(repeat2 4) = " (repeat2 4))

(defn repeat3 [num]
  (repeatn num 3))
  
(println "(repeat3 4) = " (repeat3 4))
(println "")

;1.e Write a procedure called make-repeat that takes one paramenter and returns
;a procedure with one parameter that returns a list containing its argument
;repeated the number of times equal to the input to make-repeat. Example:
;((make-repeat 3) 4) returns (4 4 4)

(defn make-repeat [number]
  (fn [times] (repeatn times number))
  )

(println "EX 1.e:")
(println "((make-repeat 3) 4) = " ((make-repeat 3) 4))
(println "")

;2.a Write a procedure count-down-from that takes one argument, a positive
;number, and returns a list of the positive numbers from the argument to 1.
;Example: (count-down-from 4) returns (4 3 2 1)

(defn count-down-from [n]
  (if (= n 0)
    (list)
    (cons n (count-down-from (- n 1)))
    )
  )

(println "EX 2.a:")
(println "(count-down-from 4) = " (count-down-from 4))
(println "")

;2.b If your solution to 2.a was recursive, write an iterative solution, and
;vice versa.



;3.a Write a procedure add-items that takes a list of numbers and returns the
;sum of the elements. Example: (add-items (list 2 5 4)) returns 11

(defn add-items [currentSum listInput]
  (if (empty? listInput)
    currentSum
    (add-items (+ (first listInput) currentSum) (rest listInput))
    ))

(println "EX 3.a:")
(println "(add-items (list 2 5 4)) = " (add-items 0 (list 2 5 4)))
(println "")

;3.b Write a procedure add-squares that takes a list of number and returns the
;sum of the squares of the elements of the list. Example: (add-squares (list 2 5
;4)) returns 45

(defn add-squares [currentSum listInput]
  (if (empty? listInput)
    currentSum
    (add-squares (+ (* (first listInput) (first listInput)) currentSum) (rest listInput))
    ))
(println "EX 3.b:")
(println "(add-squares (list 2 5 4)) = " (add-squares 0 (list 2 5 4)))
(println "")

;3.c Write a procedure add-transformed that is a generalization of add-items and
;add-squares. It should take as arguments both a list of numbers and a procedure
;and should return the sum of the results of applying the procedure to each
;number in the list. Example (add-transformed (list 2 5 4) square) returns 45.

(defn add-transformed [currentSum listInput function]
  (if (empty? listInput)
    currentSum
    (add-transformed (+ (function (first listInput)) currentSum) (rest listInput) function)
    )
  )

(defn square [x]
  (* x x))

(println "EX 3.c:")
(println "(add-transformed (list 2 5 4) square) = " (add-transformed 0 (list 2 5 4) square))
(println "")

;3.d Rewrite add-items using add-transformed.

(defn add [x]
  x)

(defn add-items [currentSum listInput]
  (add-transformed 0 listInput add))

(println "EX 3.d:")
(println "(add-items (list 2 5 4)) = " (add-items 0 (list 2 5 4)))
(println "")

;4.a Write a procedure double-all that takes a list of numbers and returns a
;list of the given numbers multiplied by 2. Example (double-all (list 4 0 -3))
;returns (8 0 -6)

(defn double-all [listInput]
  (if (empty? listInput)
    listInput
  (cons (* 2 (first listInput)) (double-all (rest listInput)))))

(println "EX 4.a:")
(println "(double-all (list 4 0 -3)) = " (double-all (list 4 0 -3)))
(println "")

;4.b Write a more generalized procedure mul-all that takes the number by which
;to multiply as a parameter in addition to the list of numbers to be multiplied.
;Example: (mul-all (list 4 0 -3) 2) returns (8 0 -6)

(defn mul-all [listInput multiplier]
  (if (empty? listInput)
    listInput
  (cons (* multiplier (first listInput)) (mul-all (rest listInput) multiplier))
  ))

(println "EX 4.b:")
(println "(mul-all (list 4 0 -3) 2) = " (mul-all (list 4 0 -3) 2))
(println "")

;5. Write a procedure delete that takes a number and a list of numbers and
;returns a list with all instances of the given number removed. Example: (delete
;1 (list 4 1 4 2 1 3)) returns (4 4 2 3)
 
 (defn deleteIter [number listInput improvedList]
   (if (empty? listInput)
     improvedList
     (if (= number (first listInput))
       (deleteIter number (rest listInput) improvedList)
      (cons (first listInput) (deleteIter number (rest listInput) improvedList)))
     ))
 
 (defn delete [number listInput]
   (deleteIter number listInput (list)))

(println "EX 5:")
(println "(delete 1 (list 4 1 4 2 1 3)) = " (delete 1 (list 4 1 4 2 1 3)))
(println "")

;EXTRA CREDIT: 
;EC1. Write a procedure last-item that returns the list that
;contains only the last element of a given list. Example: (last-item (list 23 72
;149 34)) returns (34)

(defn last-item [listInput]
  (if (= (count listInput) 1)
    (first listInput)
   (last-item (rest listInput))))

(println "EC1:")
(println "(last-item (list 23 72 149 34)) = " (last-item (list 23 72149 34)))
(println "")

;EC2. Write a procedure reverse that takes a list as an argument and returns a
;list of the same elements in reverse order. Example: (reverse (list 1 4 9 16
;25)) returns (25 16 9 4 1))

;I think reverse is reserved because its blue (so I won't use it).

(defn reverseIter [listInput reverseList]
  (if (empty? listInput)
    reverseList
    (reverseIter (rest listInput) (cons (first listInput) reverseList))
    ))

(defn reverseFunction [listInput]
  (reverseIter listInput (list)))

(println "EC2:")
(println "(reverse (list 1 4 9 16 25)) = " (reverse (list 1 4 9 16 25)))
(println "")

(println" Chapter 2 Part 2 Exercises ")
(println "-----------------------------------")
(println "")

; 6. Rewrite list-ref (from class) using the vector data structure.

;original
;(defn list-ref [items n]
  ;(if (= n 0)
    ;(first items)
    ;(list-ref (rest items) (- n 1))))
    
(defn list-ref [items n]
  (get (vec items) n))

(println "EX 6:")
(println "(list-ref (list -2 -1 0) 1) = " (list-ref (list -2 -1 0) 1))
(println "")

; 7. Write a function dotproduct that takes two vectors, treats them as
; mathematical vectors, and computes their dot product. Given two mathematical
; vectors (x0, y0, z0) and (x1, y1, z1), the dot product would be x0*x1 + y0*y1
; + z0+z1. Your function should work for any length of vector. BONUS: Have your
; function work with vectors of different lengths, assuming a value of 0 for any
; missing values. Example: (dotproduct [1 2 3] [2 1 2]) returns 10.

(defn dotproductIter [vector1 vector2 totalSum]
  (if (empty? vector1)
    totalSum
    (dotproductIter (rest vector1) (rest vector2) (+ totalSum (* (first vector1) (first vector2))))))

(defn dotproduct [vector1, vector2]
  (dotproductIter vector1 vector2 0))

(println "EX 7:")
(println "(dotproduct [1 2 3] [2 1 2]) = " (dotproduct [1 2 3] [2 1 2]))
(println "")

; 8. Write a function makemap which takes a vector of keys and a vector of
; values and constructs a map from them. Example: (makemap [1 2 3] ["one" "two"
; "three"]) returns {1 "one", 2 "two", 3 "three"}).

(defn makemap [vectorKeys vectorValues] 
  (if (empty? vectorKeys)
    (hash-map)
    (assoc (makemap (rest vectorKeys) (rest vectorValues)) (first vectorKeys) (first vectorValues))))

(println "EX 8:")
(println "I don't know where this went wrong. See code.")
;(println "(makemap [1 2 3] ["one" "two" "three"]) = " (makemap [1 2 3] ["one" "two" "three"]))
(println "")

; 9. Write a function squaremap that takes a number and generates a map where
; the keys are the numbers from 1 to n, and the values are the squares of the
; keys. Example: (squaremap 3) returns { 1 1, 2 4, 3 9}.

(defn squaremapIter [mapInput number maxTimes]
  (if (> number maxTimes)
    mapInput
    (squaremapIter (assoc mapInput number (* number number)) (inc number) maxTimes)
    ))

(defn squaremap [maxTimes]
  (squaremapIter (hash-map) 1 maxTimes))

(println "EX 9:")
(println "(squaremap 3) = " (squaremap 3))
(println "")

; 10. Write a function funkymap that takes a number and a function and generates
; a map where the keys are the numbers from 1 to n, and the values are the
; result of the function evaluated on the keys. Example: (funkymap 3 cube)
; returns { 1 1, 2 8, 3 27}.

(defn funkymapIter [mapInput function number maxTimes]
  (if (> number maxTimes)
    mapInput
    (funkymapIter (assoc mapInput number (function number)) function (inc number) maxTimes)
    ))

(defn funkymap [maxTimes, function]
  (funkymapIter (hash-map) function 1 maxTimes))

(defn cube [number]
  (* number number number))

(println "EX 10:")
(println "(funkymap 3 cube) = " (funkymap 3 cube))
(println "")

; EC3. Write a function playingcard that takes a two letter string representing
; a playing card and creates a Map with keys of :rank, :suit, and :value with
; the appropriate values. Assume the letter T will designate the 10 card.
; Example (playingcard "DQ") returns {:suit Diamonds, :rank Queen}.


; EC4. Write a function handtotal that takes a list of two letter strings
; representing playing cards and returns the total value of that hand. Assume
; face cards have a value of 10, and aces haves a value of 11. Example
; (handtotal ("SJ" "HA")) returns 21.

; Chapter 2 Part 3

(println" Chapter 2 Part 3 Exercises ")
(println "-----------------------------------")
(println "")

; 11. Rewrite mul-all using map (should be a very short function!).

(defn mul-all [listInput multiplier]
  (map (fn [number] (* number multiplier)) listInput))

(println "EX 11:")
(println "(mul-all (list 4 0 -3) 2) = " (mul-all (list 4 0 -3) 2))
(println "")

; 12. Rewrite delete using filter (should be a very short function!).

(defn delete [listInput number]
  (filter (fn [criteria] (not (= criteria number))) listInput))

(println "EX 12:")
(println "(delete 1 (list 4 1 4 2 1 3)) = " (delete (list 4 1 4 2 1 3) 1))
(println "")

; 13. Rewrite add-items using reduce (should be a very short function!).

(defn add-items [listInput]
  (reduce + listInput))

(println "EX 13:")
(println "(add-items (list 2 5 4)) = " (add-items (list 2 5 4)))
(println "")

; 14. Rewrite add-transformed using map and reduce (still pretty short).

(defn add-transformed [function listInput]
  (reduce + (map function listInput)))

(println "EX 14:")
(println "(add-transformed (list 2 5 4) square) = " (add-transformed (fn [x] (* x x)) (list 2 5 4) ))
(println "")

; 15. Rewrite dotproduct using map and reduce (still pretty short).

(defn dotproduct [vector1 vector2]
  (reduce + (map (fn [number1 number2] (* number1 number2)) vector1 vector2)))

(println "EX 15")
(println "[1 2 3 4] [2 1 2] = " (dotproduct [1 2 3 4] [2 1 2]))
(println "")

; 16. Write a function that returns the sum of the first n even cubes (use take
; and iterate in your function!). Example (evencubes 3) returns 288.

(defn addevencubes [number]
  (reduce + (take number (filter even? (map cube (iterate inc 1))))))

(println "EX 16:")
(println "(addevencubes 3) = " (addevencubes 3))
(println "")

; 17. Make up your own function that uses map, filter, and reduce. Bonus if it
; also uses take and iterate. Describe your function in a println statement and
; print a demonstration of your function in action.

(defn addoddsquares [number]
  (reduce + (take number (filter odd? (map square (iterate inc 0))))))

(println "EX 17:")
(println "(addoddsquares 4) = " (addoddsquares 4))
(println "")

; EC5. Write a function apply-all that takes a list of functions and an item as
; arguments and returns a list of the results of applying each procedure to the
; item. Example: (apply-all (list sqrt square cube factorial fib) 4) returns (2
; 16 64 24 3). Also (apply-all (list length first rest last reverse) (list 1 2 3
; 4)) returns (4 1 (2 3 4) (4) (4 3 2 1)).



; EC6. Write a function leibnizPi that returns the value of Pi calculated using
; the first n terms of the leibniz series formula for Pi. 
; This formula is Pi = 4 * Sum(n=0->oo) (-1)^n/(2n+1).

)