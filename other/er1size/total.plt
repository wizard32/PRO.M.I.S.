set title "Total Population 2000 Nodes "
set xlabel "P2P Members"
set ylabel "Survived Nodes"
plot "survivors.dat" with linespoints lt -1 pt 6 title ""
pause -1
set terminal postscript
set output "total.ps"
replot
q
