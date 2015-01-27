(ns exponential-tax.core
  (:require [clojure.math.numeric-tower :as math]))

(defn summed-exponential-series 
  "Sum an exponential series from 1 to limit.
   
   `constant`: the constant by which integers in the range are multiplied;
   `exponent`: the exponent to which they are raised;
   `limit`: the limit of the range to be summed."
  [constant exponent limit]
  (reduce + (map #(math/expt (* constant %) exponent) (range limit))))

(defn format-money-amount 
  "Format the number passed as argument as an amount of money (pounds and pence).
   
   `n`: the amount of money."
  [n]
  (format "£ %,12.2f" n))

(def holding-sizes
  [["Average croft" 5]
   ["Average farm" 101]
   ["Glasgow Airport" 300]
   ["Edinburgh Airport" 400]
   ["Grangemouth Refinery" 700]
   ["Thousand hectares" 1000]
   ["Ten thousand hectares" 10000]
   ["Countess of Sutherland" 33000]
   ["Earl of Seafield" 40000]
   ["Captain Alwynn Farquharson" 51800]
   ["Duke of Westminster" 54000]
   ["Duke of Atholl" 58700]
   ["Duke of Buccleuch" 109000]])
   
(defn sample-taxes
  "Prints sample taxable amounts for a table of holdings."
  [constant exponent holdings]
  (map #(print (format "\n%s: £ %,.2f" 
                (first %) 
                (summed-exponential-series constant exponent (second %))))
       holdings))