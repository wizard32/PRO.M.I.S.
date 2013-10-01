set title "Total Population 2000 Nodes "
set xlabel "P2P Members"
set ylabel "Survived Nodes"
plot "survivors.dat" with linespoints lt -1 lw 2.8 pt 9 ps 2 title ""
set terminal png
set output "sf1size.png"
replot
pause -1
set terminal postscript
set output "total.ps"
replot
q
