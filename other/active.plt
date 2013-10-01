set title "Total Population  Nodes "
set xlabel "Minimum Active Nodes"
set ylabel "Survived Nodes"
plot "survivors.dat" with linespoints lt -1 pt 6
pause -1
set terminal postscript
set output "active.ps"
replot
q
