set xlabel "Timeslots t"
set ylabel "% of Infected Population"
set title "Homogeneous graph -  2000 nodes - 20 preinfected nodes"
plot "finalFile" pt 5 ps 1.51 title "Simulator Output",(20000/(20+980*exp(-0.0005*1000*x))/1000)*100 lt 6 title "Theoretical Model"
pause -1
set term postscript 
set output "accurate.ps"
replot
