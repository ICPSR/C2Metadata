*declaring missing values on the listed variables V520041 V520042 V520043;
*renaming one variable (V520002).

use "da07213_useForMsng.dta" , clear
recode V520041 (8/max =.) 
recode V520042 V520043 (0 8/max =.)
rename V520002 CaseID

tab1  V520041 V520042 V520043
saveold  "da07213_Msng_RenameVar.dta" , version(12) replace

