set title "Total Population 200 Nodes "
set xlabel "Promis P2P max queries per node"
set ylabel "Survived Nodes"
plot "buffer.dat" with linespoints lt -1 pt 6
pause -1
set terminal postscript
set output "buffer.ps"
replot
q
