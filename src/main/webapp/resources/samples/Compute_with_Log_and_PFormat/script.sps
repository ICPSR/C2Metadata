*create new variable using compute with log function.
*declare print formats on the new variable.
*assign label to (new) variable.

get file='da07213_inputForLog.sav'.
compute Logpop = lg10(V520006).
print formats Logpop (f3.2).
variable labels Logpop 'Log Population of PSU'.
save outfile='da07213_computeLog.sav'.
execute.