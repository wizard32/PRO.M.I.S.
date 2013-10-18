#parse all files to create a script that finds the relative protection of peers 
#intitialsize clean temp files
#taken in to account the Buffer buffer size
cd ../../other/$1
rm -f ./incoming.dat
rm -f ./temp
rm -f ./buffer.plt
rm -f ./buffer.ps

for i in `ls` 
	do if [  -d $i ] ; then 
		cd $i
		pwd
		BUFFERSIZE=`cat code | grep bufferSize | awk '{ print $5 ; }' | tr ';' ' '`
		SURVIVORS=`tail -n 1 P2P | awk '{ print $6 ; }'`
		echo "SURVIVORS=$SURVIVORS"
		echo "BUFFERSIZE=$BUFFERSIZE"
		echo -e "$BUFFERSIZE\t$SURVIVORS" >> ../temp
		N=`cat statsfile | grep "Total population" | tail -n 1 | awk '{ print $3; }'`
		echo "<------------------------------------------------------------------------->"
		cd ..
	fi
done 


sort -g temp > buffer.dat
rm -f ./temp

echo "set title \"Total Population $N Nodes \"" > buffer.plt
echo "set xlabel \"Number of past measurements \"" >> buffer.plt
echo "set ylabel \"Survived Nodes\"" >> buffer.plt
#echo "set yrange[0:500]" >> buffer.plt
echo "plot \"buffer.dat\" with linespoints lt -1 pt 6 title \"Past measurements - Survived nodes\"" >> buffer.plt
echo "pause -1" >> buffer.plt
echo "set terminal postscript" >> buffer.plt
echo "set output \"buffer.ps\"" >> buffer.plt
echo "replot" >> buffer.plt
echo "q" >> buffer.plt
