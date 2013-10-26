#Parse all files to create a script that finds the relative protection of peers 
#Intitialsize clean temp files

cd ../../results/$1
rm -f ./*.dat
rm -f ./temp
rm -f ./threshold.plt
rm -f ./threshold.ps
rm -f ./out.tmp
rm -f ./finalRate

for i in `ls` 
	do if [  -d $i ] ; then
		cd $i
		pwd
		THRES=`cat code | tail -n 2 | head -n 1 | awk '{ print $5 ; }'`
		SURVIVORS=`tail -n 1 P2P | awk '{ print $6 ; }'`
		echo "THRESHOLD=$THRES"
		echo "SURVIVORS=$SURVIVORS"
		echo -e "$THRES\t$SURVIVORS" >> ../temp
		N=`cat statsfile | grep "Total population" | tail -n 1 | awk '{ print $3; }'`
		echo "<------------------------------------------------------------------------->"
		cd ..
	fi
done 
sort -g temp > survivors.dat
tr -d 'f ;' <survivors.dat> out.tmp
rm -f ./temp
rm -f ./survivors.dat
cat out.tmp > survivors.dat

echo "set title \"Total Population $N Nodes \"" > threshold.plt
echo "set xlabel \"Activation Threshold\"" >> threshold.plt
echo "set ylabel \"Survived Nodes\"" >> threshold.plt
echo "plot \"survivors.dat\" with linespoints lt -1 pt 6 title \"\"" >> threshold.plt
echo "pause -1" >> threshold.plt
echo "set terminal postscript" >> threshold.plt
echo "set output \"threshold.ps\"" >> threshold.plt
echo "replot" >> threshold.plt
echo "q" >> threshold.plt

gnuplot "threshold.plt"
