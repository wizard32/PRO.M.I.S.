set title "Initial infected nodes 10 - Initial immune nodes 1 - P2P members 1500 - Number of connections 154484"
set xrange[0:150]
set xlabel "Time"
set y2label "Total Population 2000 Nodes"
set ylabel "Rate"
set y2tics border nomirror 
set pointsize 1.5
set yrange[0:2]
set y2range[0:4000]
plot "Epidemic_Curve" with linespoints title "Epidemic Curve",  "Global_Estimated_Rate" with linespoints title "Global Estimated Rate" lt -1 pt 6, "Local_Estimated_Rate" with linespoints title "Local Estimated Rate", "Infected_Population" with linespoints title "Infected Population",  1 title "Total Population" with linespoints lt 1 pointsize 0.5, (2000.0-1500.0)/2000.0 title "Non P2P Members" with linespoints lt 7 pointsize 0.5
pause -1
set terminal postscript
set output "data.ps"
replot
q