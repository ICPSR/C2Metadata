*example of the "sort cases" command and using the "keep" subcommand to subset a data file.

use "da07213-0001.dta" , clear
sort  V520005

keep  V520005 V520006 V520041 V520042 V520043 V520128 V520129 V520142 V520160
order V520005 V520006 V520041 V520042 V520043 V520128 V520129 V520142 V520160

saveold "da07213_useFor_MatchFiles.dta" , version(12) replace


