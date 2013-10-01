set title "Initial infected nodes 10 - Initial immune nodes 1 - P2P members  - Number of connections 651871"
set xrange[0:200]
set xlabel "Time"
set y2label "Total Population 5000 Nodes"
set ylabel "Rate"
set y2tics border nomirror 
set pointsize 1.5
set yrange[0:3]
set y2range[0:15000]
plot "Epidemic_Curve" with linespoints title "Epidemic Curve",  "Global_Estimated_Rate" with linespoints title "Global Estimated Rate" lt -1 pt 6, "Local_Estimated_Rate" with linespoints title "Local Estimated Rate", "Infected_Population" with linespoints title "Infected Population",  1 title "Total Population" with linespoints lt 1 pointsize 0.5, (5000.0-.0)/5000.0 title "Non P2P Members" with linespoints lt 7 pointsize 0.5
pause 15
set terminal postscript
set output "data.ps"
replot
q
