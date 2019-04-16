*recode existing (categorical) variable into new variable.
*assign value labels to new variable.
*rename existing (old) variable.

use "da07213_inputForRecode" , clear
recode V520131 (0=0) (1 2=1) (3/6=2) (7 8=3), gener(EDUC2)
lab def Edlab 1 "Grade School" 2 "High School" 3 "College" 0 "None"
lab val EDUC2 Edlab
rename V520131 EDUC1
saveold "da07213_Recode_ValueLabels.dta" , version(12) replace


