*create a new dataset that includes aggregations of Age (variable V520142) and INCOME, sorted by Region of Interview.
get file='da07213_useForAggregate.sav'.
aggregate
 /outfile='07213_aggregate.sav'.
 /break=V520005
 /MED_INC=median(INCOME)
 /MEAN_AGE=mean (V520142).
execute.
