set title "Total Population 2000 Nodes "
set xlabel "Number of past measurements "
set ylabel "Survived Nodes"
plot "buffer.dat" with linespoints lt -1  lw 2.8 pt 9 ps 2 title ""
set terminal png
set output "buffer.png"
replot
pause -1
set terminal postscript
set output "buffer.ps"
replot
q
