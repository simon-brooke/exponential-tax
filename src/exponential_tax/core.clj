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
;;; For more of the argument, read my essays:
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
    [gorilla-plot.core :refer :all]
    [gorilla-repl.table :refer :all]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; An exponential series is a series in which each successive value is raised by an exponent. 
;;; 
;;; So suppose we take the numbers 1...10, and we graph them:
;; **

;; @@
(list-plot (range 0 10))

;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"0e944ecf-0a22-4b1f-a709-14ed2840b0a7","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"0e944ecf-0a22-4b1f-a709-14ed2840b0a7","field":"data.y"}}],"marks":[{"type":"symbol","from":{"data":"0e944ecf-0a22-4b1f-a709-14ed2840b0a7"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"fill":{"value":"steelblue"},"fillOpacity":{"value":1}},"update":{"shape":"circle","size":{"value":70},"stroke":{"value":"transparent"}},"hover":{"size":{"value":210},"stroke":{"value":"white"}}}}],"data":[{"name":"0e944ecf-0a22-4b1f-a709-14ed2840b0a7","values":[{"x":0,"y":0},{"x":1,"y":1},{"x":2,"y":2},{"x":3,"y":3},{"x":4,"y":4},{"x":5,"y":5},{"x":6,"y":6},{"x":7,"y":7},{"x":8,"y":8},{"x":9,"y":9}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":40,"top":10,"right":10,"left":55}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"0e944ecf-0a22-4b1f-a709-14ed2840b0a7\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"0e944ecf-0a22-4b1f-a709-14ed2840b0a7\", :field \"data.y\"}}], :marks [{:type \"symbol\", :from {:data \"0e944ecf-0a22-4b1f-a709-14ed2840b0a7\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 1}}, :update {:shape \"circle\", :size {:value 70}, :stroke {:value \"transparent\"}}, :hover {:size {:value 210}, :stroke {:value \"white\"}}}}], :data [{:name \"0e944ecf-0a22-4b1f-a709-14ed2840b0a7\", :values ({:x 0, :y 0} {:x 1, :y 1} {:x 2, :y 2} {:x 3, :y 3} {:x 4, :y 4} {:x 5, :y 5} {:x 6, :y 6} {:x 7, :y 7} {:x 8, :y 8} {:x 9, :y 9})}], :width 400, :height 247.2188, :padding {:bottom 40, :top 10, :right 10, :left 55}}}"}
;; <=

;; **
;;; Nothing surprising there.
;;; 
;;; So let's try again, and this time raise each by the exponent 1.
;; **

;; @@
(list-plot 
  (map 
  	#(math/expt % 1)
  	(range 0 10)))
;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"1fd882f6-5671-49a4-8312-bdf1d73bcdca","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"1fd882f6-5671-49a4-8312-bdf1d73bcdca","field":"data.y"}}],"marks":[{"type":"symbol","from":{"data":"1fd882f6-5671-49a4-8312-bdf1d73bcdca"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"fill":{"value":"steelblue"},"fillOpacity":{"value":1}},"update":{"shape":"circle","size":{"value":70},"stroke":{"value":"transparent"}},"hover":{"size":{"value":210},"stroke":{"value":"white"}}}}],"data":[{"name":"1fd882f6-5671-49a4-8312-bdf1d73bcdca","values":[{"x":0,"y":0},{"x":1,"y":1},{"x":2,"y":2},{"x":3,"y":3},{"x":4,"y":4},{"x":5,"y":5},{"x":6,"y":6},{"x":7,"y":7},{"x":8,"y":8},{"x":9,"y":9}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":40,"top":10,"right":10,"left":55}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"1fd882f6-5671-49a4-8312-bdf1d73bcdca\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"1fd882f6-5671-49a4-8312-bdf1d73bcdca\", :field \"data.y\"}}], :marks [{:type \"symbol\", :from {:data \"1fd882f6-5671-49a4-8312-bdf1d73bcdca\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 1}}, :update {:shape \"circle\", :size {:value 70}, :stroke {:value \"transparent\"}}, :hover {:size {:value 210}, :stroke {:value \"white\"}}}}], :data [{:name \"1fd882f6-5671-49a4-8312-bdf1d73bcdca\", :values ({:x 0, :y 0} {:x 1, :y 1} {:x 2, :y 2} {:x 3, :y 3} {:x 4, :y 4} {:x 5, :y 5} {:x 6, :y 6} {:x 7, :y 7} {:x 8, :y 8} {:x 9, :y 9})}], :width 400, :height 247.2188, :padding {:bottom 40, :top 10, :right 10, :left 55}}}"}
;; <=

;; **
;;; Nothing's changed. So what's all this about?
;;; 
;;; Well, if we increase the exponent just a bit, we change the angle of the graph.
;;; 
;;; Exponents make numbers increase more rapidly
;; **

;; @@
(list-plot 
  (map 
  	#(math/expt % 2)
  	(range 0 10)))
;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"50689ba9-dfbc-4579-898f-e05d09d4bd4c","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"50689ba9-dfbc-4579-898f-e05d09d4bd4c","field":"data.y"}}],"marks":[{"type":"symbol","from":{"data":"50689ba9-dfbc-4579-898f-e05d09d4bd4c"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"fill":{"value":"steelblue"},"fillOpacity":{"value":1}},"update":{"shape":"circle","size":{"value":70},"stroke":{"value":"transparent"}},"hover":{"size":{"value":210},"stroke":{"value":"white"}}}}],"data":[{"name":"50689ba9-dfbc-4579-898f-e05d09d4bd4c","values":[{"x":0,"y":0},{"x":1,"y":1},{"x":2,"y":4},{"x":3,"y":9},{"x":4,"y":16},{"x":5,"y":25},{"x":6,"y":36},{"x":7,"y":49},{"x":8,"y":64},{"x":9,"y":81}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":40,"top":10,"right":10,"left":55}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"50689ba9-dfbc-4579-898f-e05d09d4bd4c\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"50689ba9-dfbc-4579-898f-e05d09d4bd4c\", :field \"data.y\"}}], :marks [{:type \"symbol\", :from {:data \"50689ba9-dfbc-4579-898f-e05d09d4bd4c\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 1}}, :update {:shape \"circle\", :size {:value 70}, :stroke {:value \"transparent\"}}, :hover {:size {:value 210}, :stroke {:value \"white\"}}}}], :data [{:name \"50689ba9-dfbc-4579-898f-e05d09d4bd4c\", :values ({:x 0, :y 0} {:x 1, :y 1} {:x 2, :y 4} {:x 3, :y 9} {:x 4, :y 16} {:x 5, :y 25} {:x 6, :y 36} {:x 7, :y 49} {:x 8, :y 64} {:x 9, :y 81})}], :width 400, :height 247.2188, :padding {:bottom 40, :top 10, :right 10, :left 55}}}"}
;; <=

;; **
;;; ## Summing an exponential series
;;; 
;;; My proposal for exponential land tax is based on the idea that you pay *a little bit on the first hectare, a bit more on the next, a bit more on the next, and so on*. It's based on that because I started with the legend of the [grains of rice on a chess-board](http://www.singularitysymposium.com/exponential-growth.html).
;;; 
;;; So the amount of tax you pay is <span><span style="font-size: large;">&Sigma;</span><sub>1..n</sub>(cn)<sup>e</sup></span>, where *n* is the number of hectares you own.
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
;;; A summed exponential series increases even more rapidly:
;; **

;; @@
(list-plot
  (map
  	#(summed-exponential-series 1 2 %)
  	(range 0 100 10)))
;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"9561dadd-b4b7-4acf-ac78-fddfb3dc8810","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"9561dadd-b4b7-4acf-ac78-fddfb3dc8810","field":"data.y"}}],"marks":[{"type":"symbol","from":{"data":"9561dadd-b4b7-4acf-ac78-fddfb3dc8810"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"fill":{"value":"steelblue"},"fillOpacity":{"value":1}},"update":{"shape":"circle","size":{"value":70},"stroke":{"value":"transparent"}},"hover":{"size":{"value":210},"stroke":{"value":"white"}}}}],"data":[{"name":"9561dadd-b4b7-4acf-ac78-fddfb3dc8810","values":[{"x":0,"y":0},{"x":1,"y":285},{"x":2,"y":2470},{"x":3,"y":8555},{"x":4,"y":20540},{"x":5,"y":40425},{"x":6,"y":70210},{"x":7,"y":111895},{"x":8,"y":167480},{"x":9,"y":238965}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":40,"top":10,"right":10,"left":55}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"9561dadd-b4b7-4acf-ac78-fddfb3dc8810\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"9561dadd-b4b7-4acf-ac78-fddfb3dc8810\", :field \"data.y\"}}], :marks [{:type \"symbol\", :from {:data \"9561dadd-b4b7-4acf-ac78-fddfb3dc8810\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 1}}, :update {:shape \"circle\", :size {:value 70}, :stroke {:value \"transparent\"}}, :hover {:size {:value 210}, :stroke {:value \"white\"}}}}], :data [{:name \"9561dadd-b4b7-4acf-ac78-fddfb3dc8810\", :values ({:x 0, :y 0} {:x 1, :y 285} {:x 2, :y 2470} {:x 3, :y 8555} {:x 4, :y 20540} {:x 5, :y 40425} {:x 6, :y 70210} {:x 7, :y 111895} {:x 8, :y 167480} {:x 9, :y 238965})}], :width 400, :height 247.2188, :padding {:bottom 40, :top 10, :right 10, :left 55}}}"}
;; <=

;; **
;;; So this is the principle on which the exponential tax idea works. People with little land pay very little indeed, but at each increase in scale, the tax gets relatively higher. There are no steps where the rate suddenly changes, as there is at present with income tax. There's no way that anyone, at any scale, can say *"this is not fair because those people's tax is not calculated in the same way as mine is"*.
;;; 
;;; It's a simple set of rules, it applies to everyone everywhere - to a suburban garden as mush as to a grouse moor - and it makes it progressively harder to own holdings as they get larger.
;;; 
;;; ## Formatting money
;;; 
;;; It may be convenient to be able to format amounts of money, so here's a function to do that.
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
    :columns ['Holding 'Tax]))
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
;;; The constant is the amount the first hectare is taxed. It can be very small - a few pennies will do to start. See what the consequences are when you change this. 
;;; 
;;; *(After changing it, re-evaluate the whole worksheet - \[ctrl\]-\[shift\]-\[enter\])*.
;; **

;; @@
(def c 0.25)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;exponential-tax.core/c</span>","value":"#'exponential-tax.core/c"}
;; <=

;; **
;;; ### The exponent
;;; 
;;; The exponent is the amount each successive hectare's tax is multiplied by. It, too, can be surprisingly small - it needs to be above one, but it doesn't need to be much above one. Again, see what happens when you change this.
;;; 
;;; *(After changing it, re-evaluate the whole worksheet - \[ctrl\]-\[shift\]-\[enter\])*.
;; **

;; @@
(def e 1.01)
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
;;; {"type":"list-like","open":"<center><table>","close":"</table></center>","separator":"\n","items":[{"type":"list-like","open":"<tr><th>","close":"</th></tr>","separator":"</th><th>","items":[{"type":"html","content":"<span class='clj-symbol'>Holding</span>","value":"Holding"},{"type":"html","content":"<span class='clj-symbol'>Tax</span>","value":"Tax"}],"value":"[Holding Tax]"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Average croft&quot;</span>","value":"\"Average croft\""},{"type":"html","content":"<span class='clj-string'>&quot;£         2.49&quot;</span>","value":"\"£         2.49\""}],"value":"(\"Average croft\" \"£         2.49\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Average farm&quot;</span>","value":"\"Average farm\""},{"type":"html","content":"<span class='clj-string'>&quot;£     1,297.38&quot;</span>","value":"\"£     1,297.38\""}],"value":"(\"Average farm\" \"£     1,297.38\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Glasgow Airport&quot;</span>","value":"\"Glasgow Airport\""},{"type":"html","content":"<span class='clj-string'>&quot;£    11,648.76&quot;</span>","value":"\"£    11,648.76\""}],"value":"(\"Glasgow Airport\" \"£    11,648.76\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Edinburgh Airport&quot;</span>","value":"\"Edinburgh Airport\""},{"type":"html","content":"<span class='clj-string'>&quot;£    20,786.02&quot;</span>","value":"\"£    20,786.02\""}],"value":"(\"Edinburgh Airport\" \"£    20,786.02\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Grangemouth Refinery&quot;</span>","value":"\"Grangemouth Refinery\""},{"type":"html","content":"<span class='clj-string'>&quot;£    64,083.53&quot;</span>","value":"\"£    64,083.53\""}],"value":"(\"Grangemouth Refinery\" \"£    64,083.53\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Thousand hectares&quot;</span>","value":"\"Thousand hectares\""},{"type":"html","content":"<span class='clj-string'>&quot;£   131,306.64&quot;</span>","value":"\"£   131,306.64\""}],"value":"(\"Thousand hectares\" \"£   131,306.64\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Ten thousand hectares&quot;</span>","value":"\"Ten thousand hectares\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 13,448,681.32&quot;</span>","value":"\"£ 13,448,681.32\""}],"value":"(\"Ten thousand hectares\" \"£ 13,448,681.32\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Countess of Sutherland&quot;</span>","value":"\"Countess of Sutherland\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 148,225,575.11&quot;</span>","value":"\"£ 148,225,575.11\""}],"value":"(\"Countess of Sutherland\" \"£ 148,225,575.11\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Earl of Seafield&quot;</span>","value":"\"Earl of Seafield\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 218,199,133.76&quot;</span>","value":"\"£ 218,199,133.76\""}],"value":"(\"Earl of Seafield\" \"£ 218,199,133.76\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Captain Alwynn Farquharson&quot;</span>","value":"\"Captain Alwynn Farquharson\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 366,874,682.18&quot;</span>","value":"\"£ 366,874,682.18\""}],"value":"(\"Captain Alwynn Farquharson\" \"£ 366,874,682.18\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Duke of Westminster&quot;</span>","value":"\"Duke of Westminster\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 398,865,731.73&quot;</span>","value":"\"£ 398,865,731.73\""}],"value":"(\"Duke of Westminster\" \"£ 398,865,731.73\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Duke of Atholl&quot;</span>","value":"\"Duke of Atholl\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 471,713,710.41&quot;</span>","value":"\"£ 471,713,710.41\""}],"value":"(\"Duke of Atholl\" \"£ 471,713,710.41\")"},{"type":"list-like","open":"<tr><td>","close":"</td></tr>","separator":"</td><td>","items":[{"type":"html","content":"<span class='clj-string'>&quot;Duke of Buccleuch&quot;</span>","value":"\"Duke of Buccleuch\""},{"type":"html","content":"<span class='clj-string'>&quot;£ 1,636,615,303.84&quot;</span>","value":"\"£ 1,636,615,303.84\""}],"value":"(\"Duke of Buccleuch\" \"£ 1,636,615,303.84\")"}],"value":"#gorilla_repl.table.TableView{:contents ((\"Average croft\" \"£         2.49\") (\"Average farm\" \"£     1,297.38\") (\"Glasgow Airport\" \"£    11,648.76\") (\"Edinburgh Airport\" \"£    20,786.02\") (\"Grangemouth Refinery\" \"£    64,083.53\") (\"Thousand hectares\" \"£   131,306.64\") (\"Ten thousand hectares\" \"£ 13,448,681.32\") (\"Countess of Sutherland\" \"£ 148,225,575.11\") (\"Earl of Seafield\" \"£ 218,199,133.76\") (\"Captain Alwynn Farquharson\" \"£ 366,874,682.18\") (\"Duke of Westminster\" \"£ 398,865,731.73\") (\"Duke of Atholl\" \"£ 471,713,710.41\") (\"Duke of Buccleuch\" \"£ 1,636,615,303.84\")), :opts (:columns [Holding Tax])}"}
;; <=

;; **
;;; ## That's all folks!
;;; 
;;; Hope this made you think.
;; **
