set title "Total Population 5000 Nodes "
set xlabel "P2P Members"
set ylabel "Survived Nodes"
plot "buffer.dat" with linespoints lt -1 pt 6
pause -1
set terminal postscript
set output "buffer.ps"
replot
q
