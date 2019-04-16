*rename variables in a dataset

get file="da7568-1.sav".
rename variables
 /(V4=CASEID)
 /(V8=SEX)
 /(V9=RACE)
 /(V10=AGE)
 /(V11=MARITAL)
 /(V12=HEALTHDEFECT)
 /(V14=READING)
 /(V15=WRITING).
save outfile="da7568-1_rename.sav".