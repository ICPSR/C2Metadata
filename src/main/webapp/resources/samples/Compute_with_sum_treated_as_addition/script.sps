*SPSS also allows specifying the number of values that should be valid when using compute with "sum".
*this feature does not appear to be available in Stata.
*if the number of values is the same as the number of variables involved, the resulting variable will
*be identical with the variable computed using the + sign.

get file='da07213_UseForComputes.sav'.
compute Partycare3 = sum.3(V520041 to V520043).
Variable labels Partycare3 'Care who wins elections - Index 3'.
save outfile='da07213_ComputeSumPlus.sav'.
execute.