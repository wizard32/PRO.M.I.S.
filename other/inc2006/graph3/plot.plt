set title "Population size 2000 - Initial infected nodes 10 - Initial immune nodes 1 - P2P members 199 - Number of connections 163341"
set xlabel "Time"
plot "Epidemic_Curve" with linespoints title "Epidemic Curve",  "Global_Estimated_Rate" with linespoints title "Global Estimated Rate" lt 6, "Local_Estimated_Rate" with linespoints title "Local Estimated Rate", "Percentage_of_Infected_Population * 100%" with linespoints title "Infected Population * 2000",  1 title "Total Population * 2000" lt 0, (2000.0-199.0)/2000.0 title "Non P2P Members * 2000" lt 0
pause -1
set terminal postscript
set output "data.ps"
replot
q
