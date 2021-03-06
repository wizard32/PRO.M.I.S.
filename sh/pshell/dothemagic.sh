 #if [[ $# -lt 2 ]] ;
 #   then echo "Usage:  dothemagic [xtimeslots xtimeslots]" 1>&2; exit
  #fi


cd ../../other/plots
pwd
rm -f ./plot.plt
rm -f ./peer.plt
rm -f ./Infected_Population
rm -f ./Epidemic_Curve
rm -f ./Global_Estimated_Rate
rm -f ./Local_Estimated_Rate
rm -f ./P2P
rm -f ./Unconstraint_Model
rm -f ./data.ps
rm -f ./peer.pdf
rm -f ./peer.ps
rm -f ./statsfile

OSVERSION=`uname`
echo $OSVERSION
HOST=`hostname`

#if `test  $OSVERSION = "Linux"`
if `test  $HOST = "G4"`
then
	echo "Working on G4"
	#rename the files in a proper style
	CURVE=`ls -lt *Gplot2.txt | head -n 7 | tail -n 6 | awk '{ print $8 ; }' | head -n 1`
	PERCENTAGE=`ls -lt *Gplot.txt | head -n 7 | tail -n 6 | awk '{ print $8 ; }' | head -n 1`
	GLOBAL=`ls -lt *GR* | head -n 7 | tail -n 6 | awk '{ print $8 ; }' | head -n 1`
	LOCAL=`ls -lt *LR* | head -n 7 | tail -n 6 | awk '{ print $8 ; }' | head -n 1`
	P2P=`ls -lt *P2P* | head -n 7 | tail -n 6 | awk '{ print $8 ; }' | head -n 1`
	MODEL=`ls -lt *SI.txt | head -n 7 | tail -n 6 | awk '{ print $8 ; }' | head -n 1`

	echo $PERCENTAGE | xargs cat > "Infected_Population"
	echo $CURVE | xargs cat > Epidemic_Curve
	echo $GLOBAL| xargs cat > Global_Estimated_Rate
	echo $LOCAL | xargs cat > Local_Estimated_Rate
	echo $P2P | xargs cat >  P2P
	cd ../statistics
	FILE=`ls -lt | head -n 2 | tail -n 1 | awk '{ print $8 ; }'`
	cp -f $FILE ../plots/statsfile
	N=`ls -lt | head -n 2 | tail -n 1 | awk '{ print $8 ; }' | xargs cat | grep "Total population" | head -n 1 | awk '{ print $3; }'`
	p2=`ls -lt | head -n 2 | tail -n 1 | awk '{ print $8 ; }' | xargs cat | grep "P2P" | head -n 1 | awk '{ print $8; }'`
	R=`ls -lt | head -n 2 | tail -n 1 | awk '{ print $8 ; }' | xargs cat | grep "immune" | head -n 1 | awk '{ print $6; }'`
	I=`ls -lt | head -n 2 | tail -n 1 | awk '{ print $8 ; }' | xargs cat | grep "Initial" | head -n 1 | awk '{ print $4; }'`
	L=$(echo `wc -l ../../GraphTopology.txt | awk '{ print $1; }'`/2 | bc)

else
	echo "Working on  - Speeddaemon, Darwin or Cygwin "
        #rename the files in a proper style
	CURVE=`ls -lt *Gplot2.txt | head -n 7 | tail -n 6 | awk '{ print $9 ; }' | head -n 1`
	PERCENTAGE=`ls -lt *Gplot.txt | head -n 7 | tail -n 6 | awk '{ print $9 ; }' | head -n 1`
	GLOBAL=`ls -lt *GR* | head -n 7 | tail -n 6 | awk '{ print $9 ; }' | head -n 1`
	LOCAL=`ls -lt *LR* | head -n 7 | tail -n 6 | awk '{ print $9 ; }' | head -n 1`
	P2P=`ls -lt *P2P* | head -n 7 | tail -n 6 | awk '{ print $9 ; }' | head -n 1`
	MODEL=`ls -lt *SI.txt | head -n 7 | tail -n 6 | awk '{ print $9 ; }' | head -n 1`

	echo $PERCENTAGE | xargs cat > "Infected_Population"
	echo $CURVE | xargs cat > Epidemic_Curve
	echo $GLOBAL| xargs cat > Global_Estimated_Rate
	echo $LOCAL | xargs cat > Local_Estimated_Rate
	echo $P2P | xargs cat >  P2P
	cd ../statistics
	FILE=`ls -lt | head -n 2 | tail -n 1 | awk '{ print $9 ; }'`
	echo "File=$FILE"
	cp -f $FILE ../plots/statsfile
	N=`ls -lt | head -n 2 | tail -n 1 | awk '{ print $9 ; }' | xargs cat | grep "Total population" | head -n 1 | awk '{ print $3; }'`
	p2=`ls -lt | head -n 2 | tail -n 1 | awk '{ print $9 ; }' | xargs cat | grep "P2P" | head -n 1 | awk '{ print $8; }'`
	R=`ls -lt | head -n 2 | tail -n 1 | awk '{ print $9 ; }' | xargs cat | grep "immune" | head -n 1 | awk '{ print $6; }'`
	I=`ls -lt | head -n 2 | tail -n 1 | awk '{ print $9 ; }' | xargs cat | grep "Initial" | head -n 1 | awk '{ print $4; }'`
	L=$(echo `wc -l ../../GraphTopology.txt | awk '{ print $1; }'`/2 | bc)
fi

pwd
cd ../plots
YMAX=`cat Global_Estimated_Rate | awk '{ print $2 ; }' | sort -n | tail -n 1`
echo "YMAX=$YMAX"
YMIN=1

if awk "BEGIN {exit $YMAX > $YMIN}" ; then
	echo "OK"
	YMAX=1
fi
YMAX=`expr "($YMAX + 1)/1" | bc`
echo "YMAX=$YMAX"

echo "Graph size $N - Number of edges $L - Initial infected nodes $I - Initial immune nodes $R - P2P members $p2"
#Y2=$("$N * $YMAX" | bc)
Y2=`expr "$N * $YMAX" | bc`
echo "Y2 = $Y2"

#echo "set grid" > plot.plt
#echo "N=$N" >> plot.plt
echo "set title \"Initial infected nodes $I - Initial immune nodes $R - P2P members $p2 - Number of connections $L\"" > plot.plt
echo "set xrange[0:$1]" >> plot.plt
echo "set xlabel \"Time\"" >> plot.plt
echo "set y2label \"Total Population $N Nodes\"" >> plot.plt
echo "set ylabel \"Rate\"" >> plot.plt
#echo "set format y2 \"%.0f%%\";" >> plot.plt
#echo "set y2tics 1" >> plot.plt
echo "set y2tics border nomirror " >> plot.plt
#echo "show y2data" >> plot.plt
echo "set pointsize 1.5" >> plot.plt
#echo "set y2tics 0, 0.2" >> plot.plt
#echo "set ytics nomirror" >> plot.plt
echo "set yrange[0:$YMAX]" >> plot.plt
echo "set y2range[0:$Y2]" >> plot.plt
#echo 'plot "Percentage_of_Infected_Population * 100%" with linespoints, "Epidemic_Curve" with linespoints, \
#"Global_Estimated_Rate" with linespoints, "Local_Estimated_Rate" with linespoints, 1 title "Total Population", ($N-$p2)/$p2'  >> plot.plt
echo "plot \"Epidemic_Curve\" with linespoints title \"Epidemic Curve\", \
 \"Global_Estimated_Rate\" with linespoints title \"Global Estimated Rate\" lt -1 pt 6, \"Local_Estimated_Rate\" with linespoints title \"Local Estimated Rate\",\
 \"Infected_Population\" with linespoints title \"Infected Population\",  1 title \"Total Population\" with linespoints lt 1 pointsize 0.5, ($N.0-$p2.0)/$N.0 title \"Non P2P Members\" with linespoints lt 7 pointsize 0.5"  >> plot.plt
#echo "pause 5" >> plot.plt
echo "set terminal postscript eps enhanced color" >> plot.plt
echo "set output 'data.ps'" >> plot.plt
echo "replot" >> plot.plt
echo "q" >> plot.plt
gnuplot "plot.plt"

#mv ./plot.plt ../../other/plots/plot.plt

#echo  "set grid" > peer.plt

echo "=stacked;Security Level 1;Security Level 2;Security Level 3;Security Level 4;Security Level 5" > peer.plt
# green instead of gray since not planning on printing this
echo "colors=cyan,yellow,light_green,blue,red" >> peer.plt
#echo "=patterns" >> peer.plt
echo "=table" >> peer.plt
#echo "yformat=%g" >> peer.plt
echo "#max=1000" >> peer.plt
echo "=norotate" >> peer.plt
#echo "=sortbmarks" >> peer.plt Stupid error sorts the bar avoid it at any cost
echo "#legendx=7000" >> peer.plt
echo "#legendy=1800" >> peer.plt
echo "ylabel=Security Level of Participating Nodes" >> peer.plt
echo "xlabel=Time" >> peer.plt
echo "=noxlabels" >> peer.plt
# stretch it out in x direction
echo "extraops=set size 1.2,1" >> peer.plt
#lets keep the 50 first time slots to
head -n $2 ../../other/plots/P2P >> peer.plt
#cat ../../other/plots/P2P >> peer.plt
#bargraph  ./peer.plt > peer.ps
bargraph.pl -pdf ./peer.plt > peer.pdf
bargraph.pl -png ./peer.plt > peer.png
bargraph.pl  ./peer.plt > peer.ps


#mv ./peer.plt ../../other/plots/peer.plt
#mv ./peer.pdf ../../other/plots/peer.pdf
