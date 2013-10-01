set title "Total Population 2000 Nodes "
set xlabel "Incoming Local Rates per Node"
set ylabel "Survived Nodes"
set yrange[0:550]
plot "incoming.dat" with linespoints lt -1 pt 6 ps 1.5 title "" 
pause -1
set terminal postscript
set output "incoming.ps"
replot
q
