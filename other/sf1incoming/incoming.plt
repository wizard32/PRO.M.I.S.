set title "Total Population 2000 Nodes "
set xlabel "Promis P2P max queries per node"
set ylabel "Survived Nodes"
set yrange[0:500]
plot "finalRate" with linespoints lt -1 lw 2.8 pt 9 ps 2 title ""
set terminal png
set output "sf1incoming.png"
replot
pause -1
set terminal postscript
set output "incoming.ps"
replot
q
