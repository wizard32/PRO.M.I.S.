set ylabel "Infected Population * 100%"
set xlabel "Time"
plot "Final" title "PROMISim results" with points 4, \
(25000/(25+975*exp(-0.0001*1000*x))/1000) with lines linewidth 3  title "Analytical Solution"
set terminal postscript
set output "proximity.eps"
replot
pause -1
