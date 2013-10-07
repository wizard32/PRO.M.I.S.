set title "graph:ngce?modeltype=FixedGraph&nodes=100&neighbours=5&seed=3&version=2.0"
set ylabel 'Connectivity Probability'
set xlabel 'Nodes' 
set term postscript eps
set output "../Plots/1142516398762.eps"
plot "NGCE_Output"
set term x11
plot "NGCE_Output"
pause 15 "Hit return to continue"#Analysis Completed in: 0.078 seconds