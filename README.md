# exponential-tax

Clojure designed to compute values for exponential land tax

## Usage 

    (use 'exponential-tax.core)
    (sample-taxes 0.5 1.05 holding-sizes)

## Discussion

Exponential land tax is an idea for a tax which would have the effect of breaking up large estates.

The core of the idea is that you pay a small amount on your first hectare of land, a little bit more on your next hectare, a little bit more on your next, and so on. There are two key numbers in this idea: the constant, which is the amount of money you pay on the first hectare, and the exponent, which is the power the number of hectares we've counted so far is raised to to calculate the little bit more.

## License

Copyright © 2014 Simon Brooke

Distributed under the Gnu General Public License either version 2.0 or (at
your option) any later version.
