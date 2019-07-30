(ns chapter1.core
  (:gen-class))

(defn -main  [& args]

  (println "Chapter One Exercises:")
  (println "")

;Exercise 1.1.  Below is a sequence of expressions. What is the result printed
;by the interpreter in response to each expression? Assume that the sequence is
;to be evaluated in the order in which it is presented.

(println "EX 1.1:")
;10
;'(+ 5 3 4)
;(- 9 1)
;(/ 6 2)
;(+ (* 2 4) (- 4 6))
;(def a 3)
;(def b (+ a 1))
;(+ a b (* a b))
;(= a b)
;(if (and (> b a) (< b (* a b)))
;    b
;    a)
;(cond (= a 4) 6
;      (= b 4) (+ 6 7 a)
;      :else 25)
;(+ 2 (if (> b a) b a))
;(* (cond (> a b) a
;         (< a b) b
;         :else -1)
;   (+ a 1))
(println "10")
(println "12")
(println "8")
(println "3")
(println "6")
(println "19")
(println "False")
(println "4")
(println "16")
(println "6")
(println "16")


(println "")

;Exercise 1.3.  Define a procedure that takes three numbers as arguments and
;returns the sum of the squares of the two larger numbers.

(defn sumOfSquares [a b]
      (+ (* a a) (* b b)))

(defn sumOfLargeSquares [x y z]
  (cond (and (> x z)(> y z)) (sumOfSquares x y)
        (and (> y x)(> z x)) (sumOfSquares y z)
        (and (> x y)(> z y)) (sumOfSquares x z)
))

(println "EX 1.3:")
(println "(sumOfLargeSquares 1 2 3) = " (sumOfLargeSquares 1 2 3))
(println "")

;Exercise M1.4.  Define a procedure that takes a number as an argument and
;returns 1 if the number is positive, -1 if the number is negative, and 0 if the
;number is 0.

(defn atreya [x]
  (cond (> x 0)  1
        (< x 0 ) -1
        (= x 0) 0 ))
        

(println "EX M1.4:")
(println "(atreya 10) = " (atreya 10))
(println "(atreya 0) = " (atreya 0))
(println "(atreya -10) = " (atreya -10))
(println "")

;Exercise M1.5.  Define a procedure called true-false that takes one argument
;and returns 1 if the argument is true and 0 if it is false. Write two
;definitions of true-false, one that uses if and one that uses cond.

(println "EX M1.5:")

(defn true-false [x]
  (if(= x true) 1 0
    ))

(println "(true-false false) = " (true-false false))

(defn true-false [y]
  (cond (= y true) 1
        (= y false) 0
        ))

(println "(true-false true) = " true-false true)
(println "")

;Exercise 1.4.  Observe that our model of evaluation allows for combinations
;whose operators are compound expressions. Use this observation to describe the
;behavior of the following procedure:

(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))

(println "EX 1.4:")
(println "If b > 0, return a + b. Else return a - b.")
(println "")

;Exercise 1.5.  Ben Bitdiddle has invented a test to determine whether the
;interpreter he is faced with is using applicative-order evaluation or normal-
;order evaluation. He defines the following two procedures:

;(defn p [x] (p (+ x 1)))

;(defn test [x y]
; (if (= x 0)
;      0
;      y))

;Then he evaluates the expression

;(test 0 (p 1))

;What behavior will Ben observe with an interpreter that uses applicative-order
;evaluation? What behavior will he observe with an interpreter that uses normal-
;order evaluation? Explain your answer. (Assume that the evaluation rule for the
;special form if is the same whether the interpreter is using normal or
;applicative order: The predicate expression is evaluated first, and the result
;determines whether to evaluate the consequent or the alternative expression.)

(println "EX 1.5:")
(println "In applicative-order ,this program would go on forever because (p 1) can't be evaluated. In normal order, (p 1) will never be evaluated so it will finish.")
(println "")

; Newton's Method of computing a square root:

(defn square [x] (* x x))

(defn average [x y]
  (/ (+ x y) 2))

(defn good-enough? [guess x]
  (< (Math/abs (- (square guess) x)) 0.001))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

;Exercise 1.6.  Alyssa P. Hacker doesn't see why if needs to be provided as a
;special form. ``Why can't I just define it as an ordinary procedure in terms of
;cond?'' she asks. Alyssa's friend Eva Lu Ator claims this can indeed be done,
;and she defines a new version of if:

(defn new-if [predicate then-clause else-clause] 
  (cond predicate 
          then-clause 
          :else else-clause))

;Eva demonstrates the program for Alyssa:

(new-if (= 2 3) 0 5)
;5

(new-if (= 1 1) 0 5)
;0

;Delighted, Alyssa uses new-if to rewrite the square-root program:

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
          guess
          (sqrt-iter (improve guess x)
                     x)))

;What happens when Alyssa attempts to use this to compute square roots? Explain.

(println "EX 1.6:")
(println "This code won't work because it will either cause a stack overflow error or become an infinite loop. The new-if evaluates both operands, and the second one requries to use recursion.")
(println "")

;Exercise 1.7.  The good-enough? test used in computing square roots will not be
;very effective for finding the square roots of very small numbers. Also, in
;real computers, arithmetic operations are almost always performed with limited
;precision. This makes our test inadequate for very large numbers. Explain these
;statements, with examples showing how the test fails for small and large
;numbers. An alternative strategy for implementing good-enough? is to watch how
;guess changes from one iteration to the next and to stop when the change is a
;very small fraction of the guess. Design a square-root procedure that uses this
;kind of end test. Does this work better for small and large numbers?
  
 (defn improve [guess x]
   (average guess (/ x guess)))
 
 (defn abs [n] (max n (- n)))
 
 (defn good-enough? [guess x]
   (< (abs(- guess (improve guess x))) (/ guess 1000)))
  
(println "EX 1.7:")

(println "This does perform better than the previous method. Previously, if you took the square root of 0.001, you would get a large amount of error.")
(println "(sqrt 0.0001) = " (sqrt 0.0001)) 
(println "")

;Exercise 1.8.  Newton's method for cube roots is based on the fact that if y is
;an approximation to the cube root of x, then a better approximation is given by
;the value: (x/(y^2) + 2y)/3
;Use this formula to implement a cube-root procedure analogous to the square-
;root procedure. (In section 1.3.4 we will see how to implement Newton's method
;in general as an abstraction of these square-root and cube-root procedures.)

(defn improve [guess x] 
  (/ (+ (/ x guess guess) guess guess) 3.0))

(defn good-enough? [guess prevGuess] (< (/ (abs (- guess prevGuess)) guess) 0.0001))

(def cuberoot-iter
  (fn [guess x]
    (loop [guess guess prevGuess -10]
      (if (good-enough? guess prevGuess)
        guess
        (recur (improve guess x) guess)))))

(defn cuberoot [x] (cuberoot-iter 1.0 x))
  
  (println "EX 1.8:")
  (println "(cuberoot 8) = " (cuberoot 8))
  (println "")
 
;Exercise M1.7.   Write a procedure power-close-to that takes as arguments two
;positive integers b and n, and returns the the smallest power of b that is
;greater than n. Use the Math/pow function to evaluate powe


(defn power-incrementer [b n power]
  (cond 
    (> (Math/pow b power) n) power
    :else (power-incrementer b n (+ power 1)))
)

(defn power-close-to [b n]
  (power-incrementer b n 0))

(println "EX M1.7:")
(println "(power-close-to 2 8) = " (power-close-to 2 8))
(println "")

;Exercise M1.8.  Rewrite the definition to power-close-to from Exercise M1.7
;above to use embedded procedure definitions. Take advantage of lexical scoping
;to remove unnecessary parameters from the embedded procedures.

(defn power-close-to [b n]
  (defn power-incrementer [power]
    (cond (> (Math/pow b power) n) power
          :else (power-incrementer (+ power 1))
      )
    )
    (power-incrementer 0)
  )

(println "EX M1.8:")
(println "(power-close-to 2 32) = " (power-close-to 2 32))
(println "")

;Exercise 1.9.  Each of the following two procedures defines a method for adding
;two positive integers in terms of the procedures inc, which increments its
;argument by 1, and dec, which decrements its argument by 1.

;(defn + [a b]
;  (if (= a 0)
;      b
;      (inc (+ (dec a) b))))

;(defn + [a b]
;  (if (= a 0)
;      b
;      (+ (dec a) (inc b))))

;Using the substitution model, illustrate the process generated by each
;procedure in evaluating (+ 4 5). Are these processes iterative or recursive?

(println "EX 1.9:")

;(defn + [a b]
;  (if (= a 0)
;      b
;      (inc (+ (dec a) b))))

(+ 4 5)
(inc (+ (dec 4) 5))
(inc (+ 3 5))
(inc (inc (+ (dec 3) 5)))
(inc (inc (+ 2 5)))
(inc (inc (inc (+ (dec 2) 5))))
(inc (inc (inc (+ 1 5))))
(inc (inc (inc (inc (+ (dec 1) 5)))))
(inc (inc (inc (inc (+ 0 5)))))
(inc (inc (inc (inc 5))))
(inc (inc (inc 6)))
(inc (inc 7))
(inc 8)
(println "9")

;(defn + [a b]
;  (if (= a 0)
;      b
;      (+ (dec a) (inc b))))

(+ 4 5)
(+ (dec 4) (inc 5))
(+ 3 6)
(+ (dec 3) (inc 6))
(+ 2 7)
(+ (dec 2) (inc 7))
(+ 1 8)
(+ (dec 1) (inc 8))
(+ 0 9)
(println "9")
(println "")

;Exercise 1.10.  The following procedure computes a mathematical function called
;Ackermann's function.

(defn A [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
        :else (A (- x 1)
                 (A x (- y 1)))))



;What are the values of the following expressions?

(println "EX 1.10:")
(A 1 10)
(println "(A 1 10) = " (A 1 10))
(A 2 4)
(println "(A 1 10) = " (A 2 4))
(A 3 3)
(println "(A 1 10) = " (A 3 3))
(println "")

;Consider the following procedures, where A is the procedure defined above:

;(defn f [n] (A 0 n))

;(defn g [n] (A 1 n))

;(defn h [n] (A 2 n))

;(defn k [n] (* 5 n n))

;Give concise mathematical definitions for the functions computed by the
;procedures f, g, and h for positive integer values of n. For example, (k n)
;computes 5n2.

(println "Method f returns 2 * n.")
(println "Method g returns 2 raised to the n power.")
(println "Method h returns 2 raised to the n squared power.")
(println "")

(defn expt [base pow]
  (reduce * (repeat pow base)))

;Exercise 1.11.  A function f is defined by the rule that f(n) = n if n<3 and
;f(n) = f(n - 1) + 2f(n - 2) + 3f(n - 3) if n> 3. Write a procedure that
;computes f by means of a recursive process. Write a procedure that computes f
;by means of an iterative process.

 (defn fibinacciRecursive [number]
    (if (<= number 3)
      1
      (+ (fibinacciRecursive (- number 1)) (* 2 (fibinacciRecursive (- number 2))) (* 3 (fibinacciRecursive (- number 3))))))
 
 (defn fibinacciIterative [n]
    (defn auxFibinacciIterative [a b c n]
      (if (<= n 3)
        c
        (auxFibinacciIterative b c (+ a b c) (- n 1))))
    (auxFibinacciIterative 0 1 1 n))

(println "EX 1.11:")
(println "fibinacciRecursive(1) = " (fibinacciRecursive 1))
(println "fibinacciRecursive(13) = " (fibinacciRecursive 13))
(println "fibinacciIterative(1) = " (fibinacciIterative 1))
(println "fibinacciIterative(40) = " (fibinacciIterative 40))
(println "")

;Exercise 1.12.  The following pattern of numbers is called Pascal's triangle
;     (see figure in text if you don't remember what it looks like).
;The numbers at the edge of the triangle are all 1, and each number inside the
;triangle is the sum of the two numbers above it. Write a procedure that
;computes elements of Pascal's triangle by means of a recursive process.



;Exercise 1.15.  The sine of an angle (specified in radians) can be computed by
;making use of the approximation sin x = x if x is sufficiently small, and the
;trigonometric identity to reduce the size of the argument of sin. (For purposes
;of this exercise an angle is considered ``sufficiently small'' if its magnitude
;is not greater than 0.1 radians.) These ideas are incorporated in the following
;procedures:

(defn cube [x] (* x x x))
(defn p [x] (- (* 3 x) (* 4 (cube x))))
(defn sine [angle]
   (if (not (> (Math/abs angle) 0.1))
       angle
       (p (sine (/ angle 3.0)))))

;a.  How many times is the procedure p applied when (sine 12.15) is evaluated?
(println "EX 1.15:")
(println "Procedure p is applied 5 tiems when (sine 12.15) is evaluated.")

;b.  What is the order of growth in space and number of steps (as a function of
;a) used by the process generated by the sine procedure when (sine a) is
;evaluated?

(println "Order of growth/number of steps = log of a")
(println "")

;Exercise 1.16.  Design a procedure that evolves an iterative exponentiation
;process that uses successive squaring and uses a logarithmic number of steps, as
;does fast-expt. (Hint: Using the observation that (bn/2)2 = (b2)n/2, keep, along
;with the exponent n and the base b, an additional state variable a, and define
;the state transformation in such a way that the product a bn is unchanged from
;state to state. At the beginning of the process a is taken to be 1, and the
;answer is given by the value of a at the end of the process. In general, the
;technique of defining an invariant quantity that remains unchanged from state to
;state is a powerful way to think about the design of iterative algorithms.)

(defn hicesar [b n a]
  (cond (= n 0) a
        (even? n) (hicesar (square b) (/ n 2) a)
        :nisodd (hicesar b (- n 1) (* a b))))

(defn hackclub [b n]
  (hicesar b n 1))

(println "EX 1.16:")
(println "2 to the 16th power = " (hackclub 2 16))
(println "")
  
  

;Exercise 1.17.  The exponentiation algorithms in this section are based on
;performing exponentiation by means of repeated multiplication. In a similar way,
;one can perform integer multiplication by means of repeated addition. The
;following multiplication procedure (in which it is assumed that our language can
;only add, not multiply) is analogous to the expt procedure:

;(defn * [a b]
;  (if (= b 0)
;      0
;      (+ a (* a (- b 1)))))

;This algorithm takes a number of steps that is linear in b. Now suppose we
;include, together with addition, operations double, which doubles an integer,
;and halve, which divides an (even) integer by 2. Using these, design a
;multiplication procedure analogous to fast-expt that uses a logarithmic number
;of steps.

;I have no idea how to do this :(

;Exercise 1.18.  Using the results of exercises 1.16 and 1.17, devise a procedure
;that generates an iterative process for multiplying two integers in terms of
;adding, doubling, and halving and uses a logarithmic number of steps.




;[OPTIONAL] 
;Exercise 1.19.   There is a clever algorithm for computing the Fibonacci numbers
;in a logarithmic number of steps. Recall the transformation of the state
;variables a and b in the fib-iter process of section 1.2.2: a  a + b and b  a.
;Call this transformation T, and observe that applying T over and over again n
;times, starting with 1 and 0, produces the pair Fib(n + 1) and Fib(n). In other
;words, the Fibonacci numbers are produced by applying Tn, the nth power of the
;transformation T, starting with the pair (1,0). Now consider T to be the special
;case of p = 0 and q = 1 in a family of transformations Tpq, where Tpq transforms
;the pair (a,b) according to a  bq + aq + ap and b bp + aq. Show that if we apply
;such a transformation Tpq twice, the effect is the same as using a single
;transformation Tp'q' of the same form, and compute p' and q' in terms of p and
;q. This gives us an explicit way to square these transformations, and thus we
;can compute Tn using successive squaring, as in the fast-expt procedure. Put
;this all together to complete the following procedure, which runs in a
;logarithmic number of steps:41

(defn fib-iter [a b p q count]
  (cond (= count 0) b
        (even? count)
         (fib-iter a
                   b
 ;                  <??>      ; compute p'
  ;                 <??>      ; compute q'
                   (/ count 2))
        :else (fib-iter (+ (* b q) (* a q) (* a p))
                        (+ (* b p) (* a q))
                        p
                        q
                        (- count 1))))

(defn fib [n]
  (fib-iter 1 0 0 1 n))


; Searching for divisors:

(defn divides? [a b]
  (= (rem b a ) 0))

(defn find-divisor [n test-divisor]
  (cond  (> (square test-divisor) n) n
       (divides? test-divisor n) test-divisor
       :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

;Exercise 1.21.  Use the smallest-divisor procedure to find the smallest divisor
;of each of the following numbers: 199, 1999, 19999.



;Exercise 1.22.  Most Lisp implementations include a primitive called runtime
;that returns an integer that specifies the amount of time the system has been
;running (measured, for example, in microseconds). The following timed-prime-test
;procedure, when called with an integer n, prints n and checks to see if n is
;prime. If n is prime, the procedure prints three asterisks followed by the
;amount of time used in performing the test.

(defn report-prime [elapsed-time]
  (println " *** ")
  (println elapsed-time))

(defn start-prime-test [n start-time]
 (if (prime? n)
      (report-prime (- (quot (System/currentTimeMillis) 1000) start-time))))

(defn timed-prime-test [n]
  (newline)
  (println n)
  (start-prime-test n (quot (System/currentTimeMillis) 1000)))

;Using this procedure, write a procedure search-for-primes that checks the
;primality of consecutive odd integers in a specified range. Use your procedure
;to find the three smallest primes larger than 1000; larger than 10,000; larger
;than 100,000; larger than 1,000,000. Note the time needed to test each prime.
;Since the testing algorithm has order of growth of (n), you should expect that
;testing for primes around 10,000 should take about 10 times as long as testing
;for primes around 1000. Do your timing data bear this out? How well do the data
;for 100,000 and 1,000,000 support the n prediction? Is your result compatible
;with the notion that programs on your machine run in time proportional to the
;number of steps required for the computation?



;Exercise 1.23.  The smallest-divisor procedure shown at the start of this
;section does lots of needless testing: After it checks to see if the number is
;divisible by 2 there is no point in checking to see if it is divisible by any
;larger even numbers. This suggests that the values used for test-divisor should
;not be 2, 3, 4, 5, 6, ..., but rather 2, 3, 5, 7, 9, .... To implement this
;change, define a procedure next that returns 3 if its input is equal to 2 and
;otherwise returns its input plus 2. Modify the smallest-divisor procedure to use
;(next test-divisor) instead of (+ test-divisor 1). With timed-prime-test
;incorporating this modified version of smallest-divisor, run the test for each
;of the 12 primes found in exercise 1.22. Since this modification halves the
;number of test steps, you should expect it to run about twice as fast. Is this
;expectation confirmed? If not, what is the observed ratio of the speeds of the
;two algorithms, and how do you explain the fact that it is different from 2?



;[OPTIONAL]
;Exercise 1.24.  Modify the timed-prime-test procedure of exercise 1.22 to use
;fast-prime? (the Fermat method on page 51 and 52 of the tesxt), and test each 
;of the 12 primes you found in that exercise. Since the Fermat test has (log n) 
;growth, how would you expect the time to test primes near 1,000,000 to compare 
;with the time needed to test primes near 1000? Do your data bear this out? Can 
;you explain any discrepancy you find?



;[OPTIONAL]
;Exercise 1.25.  Alyssa P. Hacker complains that we went to a lot of extra work
;in writing expmod. After all, she says, since we already know how to compute
;exponentials, we could have simply written

;(defn expmod [base exp m]
; (rem (fast-expt base exp) m))

;Is she correct? Would this procedure serve as well for our fast prime tester?
;Explain.




;[OPTIONAL]
;Exercise 1.26.  Louis Reasoner is having great difficulty doing exercise 1.24.
;His fast-prime? test seems to run more slowly than his prime? test. Louis calls
;his friend Eva Lu Ator over to help. When they examine Louis's code, they find
;that he has rewritten the expmod procedure to use an explicit multiplication,
;rather than calling square:

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp)
         (rem (* (expmod base (/ exp 2) m)
                       (expmod base (/ exp 2) m))
                    m)
        :else
         (rem (* base (expmod base (- exp 1) m))
                    m)))

;``I don't see what difference that could make,'' says Louis. I do.'' says Eva.
;``By writing the procedure like that, you have transformed the (log n) process
;``into a (n) process.'' Explain.


(defn sum [term a next b]
  (if (> a b)
      0
      (+ (term a)
        (sum term (next a) next b))))

(defn integral [f a b dx]
  (defn add-dx [x] (+ x dx))
  (* (sum f (+ a (/ dx 2.0)) add-dx b)
    dx))


;;Exercise 1.29.  Simpson's Rule is a more accurate method of numerical
;integration than the method illustrated above. Using Simpson's Rule, the
;integral of a function f between a and b is approximated as
;          [ see equation in the text on page 60]
;where h = (b - a)/n, for some even integer n, and yk = f(a + kh). (Increasing n
;increases the accuracy of the approximation.) defn a procedure that takes as
;arguments f, a, b, and n and returns the value of the integral, computed using
;Simpson's Rule. Use your procedure to integrate cube between 0 and 1 (with n =
;100 and n = 1000), and compare the results to those of the integral procedure
;shown above.



;Exercise 1.30.  The sum procedure above generates a linear recursion. The
;procedure can be rewritten so that the sum is performed iteratively. Show how to
;do this by filling in the missing expressions in the following definition:

(defn sum [term a next b]
  (defn iter [a result]
    (if (> a b)
       result
       (iter (next a) (+ (term a) result))))
 (iter a 0)
          )

(println "EX 1.30:")
(println "Sum of 2 through 5 = " (sum (fn [n] n) 2 (fn [n] (+ n 1)) 5))
(println "")


;Exercise 1.31.    a.  The sum procedure is only the simplest of a vast number of
;similar abstractions that can be captured as higher-order procedures.51 Write an
;analogous procedure called product that returns the product of the values of a
;function at points over a given range. Show how to define factorial in terms of
;product. Also use product to compute approximations to  using the formula52

(defn product "explained to me by my lord and savior connor :)" [term a nextInt b]
  (defn itermess [a result]
    (if (> a b)
      result
      (itermess (nextInt a) (* result (term a)))))
  (itermess a 1))

(println "EX 1.31a:")
(println "Factorial of 1 through 10 = " (product (fn [n] n) 1 (fn [n] (+ n 1)) 10))
(println "")

;b.  If your product procedure generates a recursive process, write one that
;generates an iterative process. If it generates an iterative process, write one
;that generates a recursive process.

;Don't have to do this one.

;Exercise 1.32.  a. Show that sum and product (exercise 1.31) are both special
;cases of a still more general notion called accumulate that combines a
;collection of terms, using some general accumulation function:

;(accumulate combiner null-value term a next b)

;Accumulate takes as arguments the same term and range specifications as sum and
;product, together with a combiner procedure (of two arguments) that specifies
;how the current term is to be combined with the accumulation of the preceding
;terms and a null-value that specifies what base value to use when the terms run
;out. Write accumulate and show how sum and product can both be defined as simple
;calls to accumulate.

(defn accumulate "Also roughly explained to me by Connor"[a b term next combiner]
  (defn itermess2 [a result]
    (if (> a b)
      result
      (itermess2 (next a) (combiner result (term a)))))
  (itermess2 (+ 1 a) a))

(println "EX 1.32:")
(println "Factorial of numbers 1 through 10 = " (accumulate 1 10 (fn [n] n) (fn [n] (+ n 1)) (fn [n k] (* n k))))
(println "Sum of numbers 1 through 10 = " (accumulate 1 10 (fn [n] n) (fn [n] (+ n 1)) (fn [n k] (+ n k))))
(println "")

;b. If your accumulate procedure generates a recursive process, write one that
;generates an iterative process. If it generates an iterative process, write one
;that generates a recursive process.



;Exercise 1.33.  You can obtain an even more general version of accumulate
;(exercise 1.32) by introducing the notion of a filter on the terms to be
;combined. That is, combine only those terms derived from values in the range
;that satisfy a specified condition. The resulting filtered-accumulate
;abstraction takes the same arguments as accumulate, together with an additional
;predicate of one argument that specifies the filter. Write filtered-accumulate
;as a procedure. Show how to express the following using filtered-accumulate:

;a. the sum of the squares of the prime numbers in the interval a to b (assuming
;that you have a prime? predicate already written)

(defn filter-accumulate "Explained by Clark kind of"[start a b term next combiner filterer]
  (defn itermess3 [a result]
    (if (> a b)
      result
      (itermess3 (next a) (if (filterer a) (combiner result (term a)) result))))
  (itermess3 a start)
  )

(println "EX 1.33a:")
(println "Sum of prime squares from 1 through 10 = " (filter-accumulate 0 1 10 (fn [n] (* n n)) (fn [n] (+ n 1)) (fn [a b] (+ a b)) prime?))
(println "")

;b. the product of all the positive integers less than n that are relatively
;prime to n (i.e., all positive integers i < n such that GCD(i,n) = 1).

;NOPE

;Exercise 1.34.  Suppose we define the procedure

(defn f [g]
  (g 2))

;Then we have

(f square)
;4

(f #(* % (+ % 1)))
;6
; or less ninja way:  (f (fn [x] (* x (+ x 1))))

;What happens if we (perversely) ask the interpreter to evaluate the combination
;(f f)? Explain.




;Exercise 1.41.  Define a procedure double that takes a procedure of one argument
;as argument and returns a procedure that applies the original procedure twice.
;For example, if inc is a procedure that adds 1 to its argument, then (double
;inc) should be a procedure that adds 2. What value is returned by

;(((double (double double)) inc) 5)

(defn twice [function] 
  (fn [input] (function(function input)))
)

(println "EX 1.41:")
(println "((twice fibinacciRecursive) 1) = " ((twice fibinacciRecursive) 1))
(println "")

;Exercise 1.42.  Let f and g be two one-argument functions. The composition f
;after g is defined to be the function x  f(g(x)). Define a procedure compose
;that implements composition. For example, if inc is a procedure that adds 1 to
;its argument,

;((compose square inc) 6)
;49

(defn compose [function1 function2]
  (fn [input] (function1(function2 input)))
)

(println "EX 1.42:")
(println "((compose square inc) 6) = " ((compose square inc) 6))
(println "")


;Exercise 1.43.  If f is a numerical function and n is a positive integer, then
;we can form the nth repeated application of f, which is defined to be the
;function whose value at x is f(f(...(f(x))...)). For example, if f is the
;function x   x + 1, then the nth repeated application of f is the function x   x
;+ n. If f is the operation of squaring a number, then the nth repeated
;application of f is the function that raises its argument to the 2nth power.
;Write a procedure that takes as inputs a procedure that computes f and a
;positive integer n and returns the procedure that computes the nth repeated
;application of f. Your procedure should be able to be used as follows:

;((repeated square 2) 5) 
;625

;Hint: You may find it convenient to use compose from exercise 1.42.

(defn repeated [function counter]
  (if (or (< counter 1) (= counter 1))
    function
    (compose function (repeated function (- counter 1)))))

(println "EX 1.43:")
(println "((repeated square 2) 5) = " ((repeated square 2) 5))
(println "")

;Exercise M1.23.   For each of the following expressions, what must f be in
;order for the evaluation of the expression to not cause an error? For each
;exression, give a defintion of f such that evaluating the expression will not
;cause an error, and say what the expression's value will be, given your
;definition.

; f

; (f)

; (f 3)

; ((f))

; (((f)) 3)





;Exercise 1.44.  The idea of smoothing a function is an important concept in
;signal processing. If f is a function and dx is some small number, then the
;smoothed version of f is the function whose value at a point x is the average of
;f(x - dx), f(x), and f(x + dx). Write a procedure smooth that takes as input a
;procedure that computes f and returns a procedure that computes the smoothed f.
;It is sometimes valuable to repeatedly smooth a function (that is, smooth the
;smoothed function, and so on) to obtained the n-fold smoothed function. Show how
;to generate the n-fold smoothed function of any given function using smooth and
;repeated from exercise 1.43.





;Exercise 1.46.  Several of the numerical methods described in this chapter are
;instances of an extremely general computational strategy known as iterative
;improvement. Iterative improvement says that, to compute something, we start
;with an initial guess for the answer, test if the guess is good enough, and
;otherwise improve the guess and continue the process using the improved guess as
;the new guess. Write a procedure iterative-improve that takes two procedures as
;arguments: a method for telling whether a guess is good enough and a method for
;improving a guess. Iterative-improve should return as its value a procedure that
;takes a guess as argument and keeps improving the guess until it is good enough.
;Rewrite the sqrt procedure of section 1.1.7 in terms of iterative-improve.









;[OPTIONAL - ALL FOLLOWING]
;Exercise 1.35.  Show that the golden ratio  (section 1.2.2) is a fixed point of
;the transformation x   1 + 1/x, and use this fact to compute  by means of the
;fixed-point procedure.



;Exercise 1.36.  Modify fixed-point so that it prints the sequence of
;approximations it generates, using the newline and display primitives shown in
;exercise 1.22. Then find a solution to xx = 1000 by finding a fixed point of x
;log(1000)/log(x). (Use Scheme's primitive log procedure, which computes natural
;logarithms.) Compare the number of steps this takes with and without average
;damping. (Note that you cannot start fixed-point with a guess of 1, as this
;would cause division by log(1) = 0.)



;Exercise 1.37.  a. An infinite continued fraction is an expression of the form

;As an example, one can show that the infinite continued fraction expansion with
;the Ni and the Di all equal to 1 produces 1/, where  is the golden ratio
;(described in section 1.2.2). One way to approximate an infinite continued
;fraction is to truncate the expansion after a given number of terms. Such a
;truncation -- a so-called k-term finite continued fraction -- has the form

;Suppose that n and d are procedures of one argument (the term index i) that
;return the Ni and Di of the terms of the continued fraction. Define a procedure
;cont-frac such that evaluating (cont-frac n d k) computes the value of the
;k-term finite continued fraction. Check your procedure by approximating 1/ using

;(cont-frac (fn i [] 1.0)
 ;          (fn i [] 1.0)
;           k)

;for successive values of k. How large must you make k in order to get an
;approximation that is accurate to 4 decimal places?

;b. If your cont-frac procedure generates a recursive process, write one that
;generates an iterative process. If it generates an iterative process, write one
;that generates a recursive process.



;Exercise 1.38.  In 1737, the Swiss mathematician Leonhard Euler published a
;memoir De Fractionibus Continuis, which included a continued fraction expansion
;for e - 2, where e is the base of the natural logarithms. In this fraction, the
;Ni are all 1, and the Di are successively 1, 2, 1, 1, 4, 1, 1, 6, 1, 1, 8, ....
;Write a program that uses your cont-frac procedure from exercise 1.37 to
;approximate e, based on Euler's expansion.



;Exercise 1.39.  A continued fraction representation of the tangent function was
;published in 1770 by the German mathematician J.H. Lambert:

;where x is in radians. Define a procedure (tan-cf x k) that computes an
;approximation to the tangent function based on Lambert's formula. K specifies
;the number of terms to compute, as in exercise 1.37.



;Exercise 1.40.  Define a procedure cubic that can be used together with the
;newtons-method procedure in expressions of the form

;(newtons-method cubic a b c) 1)prin()())

;to approximate zeros of the cubic x3 + ax2 + bx + c.




;Exercise 1.45.  We saw in section 1.3.3 that attempting to compute square roots
;by naively finding a fixed point of y  x/y does not converge, and that this can
;be fixed by average damping. The same method works for finding cube roots as
;fixed points of the average-damped y  x/y2. Unfortunately, the process does not
;work for fourth roots -- a single average damp is not enough to make a fixed-
;point search for y  x/y3 converge. On the other hand, if we average damp twice
;(i.e., use the average damp of the average damp of y  x/y3) the fixed-point
;search does converge. Do some experiments to determine how many average damps
;are required to compute nth roots as a fixed-point search based upon repeated
;average damping of y  x/yn-1. Use this to implement a simple procedure for
;computing nth roots using fixed-point, average-damp, and the repeated procedure
;of exercise 1.43. Assume that any arithmetic operations you need are available
;as primitives.)


  
)
