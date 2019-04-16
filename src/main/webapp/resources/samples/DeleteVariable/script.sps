*delete variables from a dataset.

get file="da7568_SelectCases.sav".
delete variables AGE MARITAL.
save outfile="da7568_DeleteVariables.sav".

*how do we mark up the resulting (output) DDI? At file level, or do we keep the variables listed (no IDs in the new file?) and marked as 
deleted, or both?