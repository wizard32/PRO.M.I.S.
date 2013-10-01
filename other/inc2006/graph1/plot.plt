set title "Population size 2000 - Initial infected nodes 10 - Initial immune nodes 1 - P2P members 399 - Number of connections 50918"
set xlabel "Time"
plot "Epidemic_Curve" with linespoints title "Epidemic Curve",  "Global_Estimated_Rate" with linespoints title "Global Estimated Rate", "Local_Estimated_Rate" with linespoints title "Local Estimated Rate", "Percentage_of_Infected_Population * 100%" with linespoints title "Infected Population * 2000",  1 title "Total Population * 2000", (2000.0-399.0)/2000.0 title "Non P2P Members * 2000"
pause -1
set terminal postscript
set output "data.ps"
replot
q
