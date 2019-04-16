*compute using the + sign adds values only when it finds valid values on all of the source variables.

use  "da07213_UseForComputes.dta" , clear
gener Partycare1 = V520041+V520042+V520043
lab var Partycare1 "Care who wins elections - Index 1"


saveold "da07213_ComputePlus.dta"  , replace version(12) 

