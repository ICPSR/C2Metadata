*example of "match files* command.

get file='da07213_aggregate2.sav'.
get file='da07213_useFor_MatchFiles.sav'.
match files
 /file='da07213_aggregate2.sav'
 /file='da07213_useFor_MatchFiles.sav'
 /by V520005.
save outfile='da07213_MatchFiles.sav'.
execute.
