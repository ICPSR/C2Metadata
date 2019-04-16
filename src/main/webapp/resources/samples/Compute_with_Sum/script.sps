*compute using the "sum" function will generate a sum even if one or more of the source variables have missing
*values. The latter will be ignored and the sum will be calculated from whatever valid values are available.

get file='da07213_UseForComputes.sav'.
compute Partycare2 = sum(V520041 to V520043).
Variable labels Partycare2 'Care who wins elections - Index 2'.
save outfile='da07213_ComputeSum.sav'.
execute.