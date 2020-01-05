(define (square x)
    (* x x))
(square 22)

(display (square 2))

(define (sum-of-squares x y)
    (+ (square x) (square y)))
(sum-of-squares 3 4)

(define (f a)
    (sum-of-squares (+ a 1) (* a 2)))


; (
;     define (abs x)
;     (
;         cond ((> x 0) x)
;         ((= x 0)   0)
;         ((< x 0) (-x))
;     )
; )


(define (abs x)
    (cond ((< x 0) (-x))
        (else x)) )
        







(exit)
    