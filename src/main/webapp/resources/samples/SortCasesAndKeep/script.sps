*example of the "sort cases" command and using the "keep" subcommand to subset a data file.

get file='da07213-0001.sav'.
sort cases by V520005.
save outfile='da07213_useFor_MatchFiles.sav'
 /keep = V520005 V520006 V520041 V520042 V520043 V520128 V520129 V520142 V520160. 
execute.