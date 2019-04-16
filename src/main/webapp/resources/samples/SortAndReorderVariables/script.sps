*the "sort by name" command rearranges the variables in the dataset in alphabetical order.
*the "add files" command applied to the working file only with the "keep" subcommand allows 
*customized reordering: some variables are kept in the initial order, and some are ordered a
*alphabetically (as a result of the "sort by name" command).

get file="da7568_add.sav".
sort variables by name.
add files file *
 /KEEP CASEID SEX RACE all.
save outfile="da7568_SortReorder.sav".