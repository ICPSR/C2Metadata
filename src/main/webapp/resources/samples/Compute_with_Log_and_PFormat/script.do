*create new variable using compute with log function.
*declare print formats on the new variable.
*assign label to (new) variable.

use  "da07213_inputForLog.dta" , clear
gener Logpop = log10(V520006)
format Logpop %3.2f
label var Logpop "Log Population of PSU"
saveold "da07213_computeLog.dta" , version(12) replace


