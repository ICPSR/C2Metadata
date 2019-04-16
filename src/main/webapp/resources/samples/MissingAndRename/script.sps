
*declaring missing values on the listed variables V520041 V520042 V520043;
*renaming one variable (V520002).

get file='da07213_useForMsng.sav'.
missing values V520041 (8 thru hi) V520042 V520043 (8 thru hi, 0).
rename variables (V520002=CaseID).
frequencies CaseID V520041 V520042 V520043.
save outfile='da07213_Msng_RenameVar.sav'.
execute.

