set title "Total Population 2000  Nodes "
set xlabel "Activation Threshold"
set ylabel "Survived Nodes"
set xrange [0.10:2]
plot "finalRate" with linespoints lt -1 lw 2.8 pt 9 ps 2  title ""
set terminal png
set output "sf2threshold.png"
replot
pause -1
set terminal postscript
set output "threshold.ps"
replot
q
