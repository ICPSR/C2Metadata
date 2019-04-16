*when using the "sum.N" function in SPSS, if the number of values specified after the period is smaller 
*than the total number of variables in the command, the sum will be calculated only when N valid values are found.
*also including a "formats" command in this example.

get file='da07213_UseForComputes.sav'.
compute Partycare4 = sum.2(V520041 to V520043).
Variable labels Partycare4 'Care who wins elections - Index 4'.
formats Partycare4 (f2).
save outfile='da07213_ComputeSumN_Formats.sav'.
execute.