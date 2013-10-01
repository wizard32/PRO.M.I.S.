set title "Total Population 2000 Nodes "
set xlabel "Promis P2P max queries per node"
set ylabel "Survived Nodes"
set yrange[0:500]
plot "incoming.dat" with linespoints lt -1 pt 6 title ""
pause -1
set terminal postscript
set output "incoming.ps"
replot
q
