#parse all files to create a script that finds the relative protection of peers 
#intitialsize clean temp files
cd ../../other/$1
rm -f ./survivors.dat
rm -f ./temp
rm -f ./active.plt
rm -f ./active.ps
rm -f ./out.tmp
for i in `ls` 
	do cd $i
	pwd
	ACTIVE=`cat code | tail -n 3 | head -n 1 | awk '{ print $5 ; }'`
	SURVIVORS=`tail -n 1 P2P | awk '{ print $6 ; }'`
	echo "ACTIVE=$ACTIVE"
	echo "SURVIVORS=$SURVIVORS"
	echo -e "$ACTIVE\t$SURVIVORS" >> ../temp
	N=`cat statsfile | grep "Total population" | tail -n 1 | awk '{ print $3; }'`
	echo "<------------------------------------------------------------------------->"
	cd ..
done 
sort -g temp > survivors.dat
tr -d 'f ;' <survivors.dat> out.tmp
rm -f ./temp
rm -f ./survivors.dat
cat out.tmp > survivors.dat

echo "set title \"Total Population $N Nodes \"" >active.plt
echo "set xlabel \"Minimum Active Nodes\"" >>active.plt
echo "set ylabel \"Survived Nodes\"" >>active.plt
echo "plot \"survivors.dat\" with linespoints lt -1 pt 6" >>active.plt
echo "pause -1" >>active.plt
echo "set terminal postscript" >>active.plt
echo "set output \"active.ps\"" >>active.plt
echo "replot" >>active.plt
echo "q" >>active.plt

