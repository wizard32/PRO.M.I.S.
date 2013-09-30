#parse all files to create a script that finds the relative protection of peers
#intitialsize clean temp files
#taken in to account the incoming buffer size
cd ../../other/$1
rm -f ./incoming.dat
rm -f ./temp
rm -f ./incoming.plt
rm -f ./incoming.ps
rm -f ./out.tmp
rm -f ./all.dat


for i in `ls`
	do if [  -d $i ] ; then
		cd $i
		pwd
		INCOMINGSIZE=`cat code | grep incomingSize | awk '{ print $5 ; }' | tr ';' ' '`
		SURVIVORS=`tail -n 1 P2P | awk '{ print $6 ; }'`
		echo "SURVIVORS=$SURVIVORS"
		echo "INCOMINGSIZE=$INCOMINGSIZE"
		echo -e "$INCOMINGSIZE\t$SURVIVORS" >> ../temp
		N=`cat statsfile | grep "Total population" | tail -n 1 | awk '{ print $3; }'`
		echo "<------------------------------------------------------------------------->"
		cd ..
	fi
done


sort -g temp > incoming.dat
sort -n incoming.dat > survivors.dat 
rm -f ./temp

echo "set title \"Total Population $N Nodes \"" > incoming.plt
echo "set xlabel \"Promis P2P max queries per node\"" >> incoming.plt
echo "set ylabel \"Survived Nodes\"" >> incoming.plt
echo "set yrange[0:500]" >> incoming.plt
echo "plot \"incoming.dat\" with linespoints lt -1 pt 6 title \"\"" >> incoming.plt
echo "pause -1" >> incoming.plt
echo "set terminal postscript" >> incoming.plt
echo "set output \"incoming.ps\"" >> incoming.plt
echo "replot" >> incoming.plt
echo "q" >> incoming.plt


