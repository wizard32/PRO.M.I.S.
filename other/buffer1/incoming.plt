set title "Total Population 5000 Nodes "
set xlabel "Promis P2P max queries per node"
set ylabel "Survived Nodes"
plot "incoming.dat" with linespoints lt -1 pt 6
pause -1
set terminal postscript
set output "incoming.ps"
replot
q
