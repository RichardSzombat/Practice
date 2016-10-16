(ns project-euler.problem31)

(def coins '(1 2 5 10 20 50 100 200))
(def combinations (atom {}))

(defn init-combinations
  []
  (loop [amount 1]
    (when (<= amount 200) 
      (do 
        (map #(let [amount-combinations (get @combinations amount)
                      coin-count (quot amount %)] 
                  (println % amount coin-count)
                  (when (= (mod amount %) 0) 
                    (assoc @combinations (+ amount-combinations coin-count)))) 
               coins)
          (recur (inc amount)))
   )
  )
)

(defn solve
  []
  (do
    (init-combinations)
    (println @combinations)
;    (loop [amount 0]
;      (if (= amount 201) (get @combinations 200)
;        (do
;         (map #(let [amount-combinations (get @combinations amount) 
;                     coin-combination (get @combinations (- amount %))] 
;                     (if (= % amount) (assoc @combinations amount (inc amount-combinations)) 
;                         ((when (some? coin-combination) 
;                          (assoc @combinations amount (+ amount-combinations coin-combinations)))))) 
;              coins) 
;         (recur (inc amount)) 
;          )
;     )
    )
  )