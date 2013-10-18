#parse all files to create a script that finds the relative protection of peers 
#intitialsize clean temp files 
#taking in to account the p2p size
cd ../../other/$1
rm -f ./survivors.dat
rm -f ./temp
rm -f ./total.plt
rm -f ./total.ps
for i in `ls` 
	do if [  -d $i ] ; then  
		cd $i
		pwd
		P2PSIZE=`cat config.txt | tail -n 2 | head -n 1 | awk '{ print $1 ; }' | tr ';' ' '`
		SURVIVORS=`tail -n 1 P2P | awk '{ print $6 ; }'`
		echo "P2PSIZE=$P2PSIZE"
		echo "SURVIVORS=$SURVIVORS"
		#echo -e "$SURVIVORS\t$P2PSIZE" >> ../temp
		RATE=`expr "$SURVIVORS/$P2PSIZE" | bc`
		echo "RATE=$RATE"
		echo -e "$P2PSIZE\t$SURVIVORS" >> ../temp
		N=`cat statsfile | grep "Total population" | tail -n 1 | awk '{ print $3; }'`
		echo "<------------------------------------------------------------------------->"
		cd ..
	fi
done 
sort -g temp > survivors.dat
rm -f ./temp

echo "set title \"Total Population $N Nodes \"" > total.plt
echo "set xlabel \"P2P Members\"" >> total.plt
echo "set ylabel \"Survived Nodes\"" >> total.plt
echo "plot \"survivors.dat\" with linespoints lt -1 pt 6 title \"\"" >> total.plt
echo "pause -1" >> total.plt
echo "set terminal postscript" >> total.plt
echo "set output \"total.ps\"" >> total.plt
echo "replot" >> total.plt
echo "q" >> total.plt

