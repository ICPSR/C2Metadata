*executing a compute that uses the if_then condition to create a new variable that combines categorical values 
*from two source variables.
*declaring missing values on the new variable.
*assigning value labels on the new variable.

get file='da07213_useForIf_Then.sav'.
compute SEXRACE = 0.
if (V520128 = 1 and V520129 = 1) SEXRACE=1.
if (V520128 = 1 and V520129 = 2) SEXRACE=2.
if (V520128 = 2 and V520129 = 1) SEXRACE=3.
if (V520128 = 2 and V520129 = 2) SEXRACE=4.
missing values SEXRACE (0).
value labels SEXRACE 1 'White Male' 2 'Black Male' 3 'White Female' 4 'Black Female'.
save outfile='da07213_IfThen_compute.sav'.
execute. 





