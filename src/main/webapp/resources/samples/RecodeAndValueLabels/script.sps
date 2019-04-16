*recode existing (categorical) variable into new variable.
*assign value labels to new variable.
*rename existing (old) variable.

get file='da07213_inputForRecode'.
recode V520131 (0=0) (1,2=1) (3 thru 6=2) (7,8=3) into EDUC2.
value labels EDUC2 1 'Grade School' 2 'High School' 3 'College' 0 'None'.
rename variables (V520131=EDUC1).
save outfile='da07213_Recode_ValueLabels'.
EXECUTE.

