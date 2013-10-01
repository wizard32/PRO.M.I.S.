set title "Total Population 2000 Nodes "
set xlabel "Number of past measurements "
set ylabel "Survived Nodes"
plot "buffer.dat" with linespoints lt -1 pt 6 title "Past measurements - Survived nodes"
pause -1
set terminal postscript
set output "buffer.ps"
replot
q
