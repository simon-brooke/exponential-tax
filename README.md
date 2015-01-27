# exponential-tax

Clojure code designed to compute values for exponential land tax

## Usage 

    (use 'exponential-tax.core)
    (sample-taxes 0.5 1.05 holding-sizes)

Where:

* 0.5 is the *constant*
* 1.05 is the *exponent*
* holding sizes is a list of lists formatted thus:

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

## Discussion

Exponential land tax is an idea for a tax which would have the effect of breaking up large estates.

The core of the idea is that you pay a small amount on your first hectare of land, a little bit more on your next hectare, a little bit more on your next, and so on. There are two key numbers in this idea: the constant, which is the amount of money you pay on the first hectare, and the exponent, which is the power the number of hectares we've counted so far is raised to to calculate the little bit more.

See my essays:

* [Me and you and the Duke of Buccleuch](http://blog.journeyman.cc/2014/12/me-and-you-and-duke-of-buccleuch.html)
* [Draft submission to the English parliament's enquiry anent Scottish land reform](http://blog.journeyman.cc/2013/10/draft-submission-to-english-parliaments.html)

## License

Copyright © 2014 Simon Brooke

Distributed under the Gnu General Public License either version 2.0 or (at
your option) any later version.
