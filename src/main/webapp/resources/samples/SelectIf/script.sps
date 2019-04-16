*the following command "select if" is used to subset cases.
get file="da7568_SortReorder.sav".
select if SEX=1.
save outfile="da7568_SelectCases.sav".