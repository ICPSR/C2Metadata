*create a new variable that counts "yes" answers to four source variables to 
create a total count of the sources used by respondents to get information about the political campaign.
*assign variable label to this new variable.

get file='da07213_useForDo_Repeat.sav'.
compute Info=0.
do repeat a=V520173 to V520176.
if ((a ge 1) and (a le 3)) Info=Info+1.
end repeat.
var label Info 'Number of Information Sources'.
save outfile='da07213_DoRepeat_loop.sav'.
execute.








