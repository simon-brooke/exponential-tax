;; gorilla-repl.fileformat = 1

;; **
;;; # Exponential Tax worksheet
;;; 
;;; This worksheet illustrates the effect of an exponential land tax on holdings of different sizes.
;;; 
;;; ## Background
;;; 
;;; In Scotland (and in many third world countries), ownership of land is highly concentrated in the hands of a very few, very rich people. This is bad from many points of view; this document isn't really the place to make those arguments.
;;; 
;;; For more of the argument, read:
;;; 
;;; * [Quarter of a million crofts](http://blog.journeyman.cc/2014/12/quarter-of-million-crofts.html)
;;; * [The Law of Freedom in a Web Page](http://blog.journeyman.cc/2015/03/the-law-of-freedom-in-web-page-or-true.html)
;;; * [Me and you and the Duke of Buccleuch](http://blog.journeyman.cc/2014/12/me-and-you-and-duke-of-buccleuch.html)
;;; 
;;; So, having made that introduction, onto the code.
;;; 
;;; ## First, some definitions
;;; 
;;; ### The namespace
;;; 
;;; First of all, we need to declare a namespace and the libraries on which this code depends.
;; **

;; @@
(ns exponential-tax.core
  (:require 
    [clojure.math.numeric-tower :as math]
    [gorilla-repl.table :refer :all]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; ## Summing an exponential series
;;; 
;;; My proposal for exponential land tax is based on the idea that you pay *a little bit on the first hectare, a bit more on the next, a bit more on the next, and so on*. It's based on that because I started with the legend of the [grains of rice on a chess-board](http://www.singularitysymposium.com/exponential-growth.html).
;;; 
;;; So the amount of tax you pay is <span><span style="font-size: large;">&Sigma;</span><sub>1..n</sub>(cn)<sup>e</sup></span>, where *n* is the number of hectares you own.
;;; 
;;; Actually, this is almost a purposeless complication; simply applying the exponential function to the number of hectares in a holding scales almost as rapidly. But I think the concept of *a little bit for the first hectare, a bit more for the next, and so on* is easy for folk to grasp.
;;; 
;;; But to be able to compute this, we need to be able to construct and sum exponential series. So here's a function to do that.
;; **

;; @@
(defn summed-exponential-series 
  "Sum an exponential series from 1 to limit.
   
   `constant`: the constant by which integers in the range are multiplied;
   `exponent`: the exponent to which they are raised;
   `limit`: the limit of the range to be summed."
  [constant exponent limit]
  (reduce 
    + 
    (map 
      #(math/expt 
         (* constant %) 
         exponent) 
      (range limit))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;exponential-tax.core/summed-exponential-series</span>","value":"#'exponential-tax.core/summed-exponential-series"}
;; <=

;; **
;;; ## Formatting money
;;; 
;;; It may be convenient to be able to format amounts of money, so here's a function to do this.
;; **

;; @@
(defn format-money-amount 
  "Format the number passed as argument as an amount of money (pounds and pence).
   
   `n`: the amount of money."
  [n]
  (format "£ %,12.2f" n))

;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;exponential-tax.core/format-money-amount</span>","value":"#'exponential-tax.core/format-money-amount"}
;; <=

;; **
;;; ## Print out sample taxes given a table of holdings
;;; 
;;; Since we need to know who will pay how much tax, we're going to want to be able to format a table of taxes due.
;; **

;; @@
(defn sample-taxes
  "Prints sample taxable amounts for a table of holdings."
  [constant exponent holdings]
  (table-view
    (map 
      #(list (first %) 
             (format-money-amount
                (summed-exponential-series
                  constant 
                  exponent 
                  (second %))))
       	holdings)
    :columns ["Holding" "Tax"]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;exponential-tax.core/sample-taxes</span>","value":"#'exponential-tax.core/sample-taxes"}
;; <=

;; **
;;; ## And then, of course, we need the actual sizes of holdings
;;; 
;;; These numbers are, as far as I can establish, more or less accurate. Add holdings you're interested in to this list.
;; **

;; @@
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
   
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;exponential-tax.core/holding-sizes</span>","value":"#'exponential-tax.core/holding-sizes"}
;; <=

;; **
;;; 
;; **

;; **
;;; ## The Variables
;;; 
;;; There are two key variables in this scheme, the constant and the exponent.
;;; 
;;; ### The constant
;;; 
;;; The constant is the amount the first hectare is taxed. It can be very small - a few pennies will do to start.
;; **

;; @@
(def c 1)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;exponential-tax.core/c</span>","value":"#'exponential-tax.core/c"}
;; <=

;; **
;;; ### The exponent
;;; 
;;; The exponent is the amount each successive hectare's tax is multiplied by. It, too, can be surprisingly small - it needs to be above one, but it doesn't need to be much above one.
;; **

;; @@
(def e 1.05)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;exponential-tax.core/e</span>","value":"#'exponential-tax.core/e"}
;; <=

;; **
;;; ## Right, let's put that all together!
;; **

;; @@
(sample-taxes c e holding-sizes)
;; @@
;; =>
;;; {"type":"list-like","open":"<center><table>","close":"</table></center>","separator":"\n","items":[{"type":"list-like","open":"<tr><th>","close":"</th></tr>","separator":"</th><th>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Holding&quot;</span>","value":"\"Holding\""},{"type":"html","content":"<span class='clj-string'>&quot;Tax&quot;</span>","value":"\"Tax\""}],"value":"[\"Holding\" \"Tax\"]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Average croft&quot;</span>","value":"\"Average croft\""},{"type":"html","content":"<span class='clj-string'>&quot;£        10.53&quot;</span>","value":"\"£        10.53\""}],"value":"(\"Average croft\" \"£        10.53\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Average farm&quot;</span>","value":"\"Average farm\""},{"type":"html","content":"<span class='clj-string'>&quot;£     6,204.08&quot;</span>","value":"\"£     6,204.08\""}],"value":"(\"Average farm\" \"£     6,204.08\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Glasgow Airport&quot;</span>","value":"\"Glasgow Airport\""},{"type":"html","content":"<span class='clj-string'>&quot;£    58,191.38&quot;</span>","value":"\"£    58,191.38\""}],"value":"(\"Glasgow Airport\" \"£    58,191.38\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Edinburgh Airport&quot;</span>","value":"\"Edinburgh Airport\""},{"type":"html","content":"<span class='clj-string'>&quot;£   105,040.07&quot;</span>","value":"\"£   105,040.07\""}],"value":"(\"Edinburgh Airport\" \"£   105,040.07\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Grangemouth Refinery&quot;</span>","value":"\"Grangemouth Refinery\""},{"type":"html","content":"<span class='clj-string'>&quot;£   331,177.47&quot;</span>","value":"\"£   331,177.47\""}],"value":"(\"Grangemouth Refinery\" \"£   331,177.47\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Thousand hectares&quot;</span>","value":"\"Thousand hectares\""},{"type":"html","content":"<span class='clj-string'>&quot;£   688,336.48&quot;</span>","value":"\"£   688,336.48\""}],"value":"(\"Thousand hectares\" \"£   688,336.48\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Ten thousand hectares&quot;</span>","value":"\"Ten thousand hectares\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 77,303,938.64&quot;</span>","value":"\"£ 77,303,938.64\""}],"value":"(\"Ten thousand hectares\" \"£ 77,303,938.64\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Countess of Sutherland&quot;</span>","value":"\"Countess of Sutherland\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 893,688,616.49&quot;</span>","value":"\"£ 893,688,616.49\""}],"value":"(\"Countess of Sutherland\" \"£ 893,688,616.49\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Earl of Seafield&quot;</span>","value":"\"Earl of Seafield\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 1,325,738,877.59&quot;</span>","value":"\"£ 1,325,738,877.59\""}],"value":"(\"Earl of Seafield\" \"£ 1,325,738,877.59\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Captain Alwynn Farquharson&quot;</span>","value":"\"Captain Alwynn Farquharson\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 2,252,234,219.56&quot;</span>","value":"\"£ 2,252,234,219.56\""}],"value":"(\"Captain Alwynn Farquharson\" \"£ 2,252,234,219.56\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Duke of Westminster&quot;</span>","value":"\"Duke of Westminster\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 2,452,703,794.50&quot;</span>","value":"\"£ 2,452,703,794.50\""}],"value":"(\"Duke of Westminster\" \"£ 2,452,703,794.50\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Duke of Atholl&quot;</span>","value":"\"Duke of Atholl\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 2,910,359,665.77&quot;</span>","value":"\"£ 2,910,359,665.77\""}],"value":"(\"Duke of Atholl\" \"£ 2,910,359,665.77\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Duke of Buccleuch&quot;</span>","value":"\"Duke of Buccleuch\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 10,350,620,262.71&quot;</span>","value":"\"£ 10,350,620,262.71\""}],"value":"(\"Duke of Buccleuch\" \"£ 10,350,620,262.71\")"}],"value":"#gorilla_repl.table.TableView{:contents ((\"Average croft\" \"£        10.53\") (\"Average farm\" \"£     6,204.08\") (\"Glasgow Airport\" \"£    58,191.38\") (\"Edinburgh Airport\" \"£   105,040.07\") (\"Grangemouth Refinery\" \"£   331,177.47\") (\"Thousand hectares\" \"£   688,336.48\") (\"Ten thousand hectares\" \"£ 77,303,938.64\") (\"Countess of Sutherland\" \"£ 893,688,616.49\") (\"Earl of Seafield\" \"£ 1,325,738,877.59\") (\"Captain Alwynn Farquharson\" \"£ 2,252,234,219.56\") (\"Duke of Westminster\" \"£ 2,452,703,794.50\") (\"Duke of Atholl\" \"£ 2,910,359,665.77\") (\"Duke of Buccleuch\" \"£ 10,350,620,262.71\")), :opts (:columns [\"Holding\" \"Tax\"])}"}
;; <=

;; @@

;; @@
