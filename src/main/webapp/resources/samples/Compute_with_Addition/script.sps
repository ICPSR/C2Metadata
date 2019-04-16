*compute using the + sign adds values only when it finds valid values on all of the source variables.

get file='da07213_UseForComputes.sav'.
compute Partycare1 = V520041+V520042+V520043.
Variable labels Partycare1 'Care who wins elections - Index 1'.
save outfile='da07213_ComputePlus.sav'.
execute.