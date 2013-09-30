#this is a mega script which finalizes almost everything after the simulation is TOTALY completed 
#intitialsize clean temp files

cd ../../other/$1
rm -f ./all.dat
rm -f ./temp
rm -f ./tempfile
rm -f ./survivors.dat

echo -e "SURVIVORS INCOMINGSIZE CLOUDSIZE\tBUFFERSIZE\t\tACTIVE\t\t\tN\t\tSUSCEPTIBLE\tLB" > ./temp
for i in `ls` 
	do if [  -d $i ] ; then 
		cd $i
		pwd
		INCOMINGSIZE=`cat code | grep incomingSize | awk '{ print $5 ; }' | tr ';' ' '`
		CLOUDSIZE=`cat config.txt | tail -n 2 | head -n 1 | awk '{ print $1 ; }'`
		BUFFERSIZE=`cat code | grep bufferSize | awk '{ print $5 ; }' | tr ';' ' '`
		#We take the rare case in which some of the survivors did not reach the highest security and therefore we add alla the intermediate 
		#levels
		SURVIVORS5=`tail -n 1 P2P | awk '{ print $6 ; }'`
		SURVIVORS4=`tail -n 1 P2P | awk '{ print $5 ; }'`
		SURVIVORS3=`tail -n 1 P2P | awk '{ print $4 ; }'`
		SURVIVORS2=`tail -n 1 P2P | awk '{ print $3 ; }'`
		SURVIVORS1=`tail -n 1 P2P | awk '{ print $2 ; }'`
		SURVIVORS=`expr $SURVIVORS5 + $SURVIVORS4 + $SURVIVORS2 + $SURVIVORS1`
		echo "L5=$SURVIVORS5"
		echo "L4=$SURVIVORS4"
		echo "L3=$SURVIVORS3"
		echo "L2=$SURVIVORS2"
		echo "L1=$SURVIVORS1"
		#ACTIVE=`cat code | tail -n 3 | head -n 1 | awk '{ print $5 ; }'` 
		ACTIVE=`cat code | tail -n 3 | head -n 1 | awk '{ print $5 ; }' | tr -d 'f;'`
		INFECTED=`cat statsfile | grep "infected" | tail -n 1 | awk '{ print $6; }'`
		N=`cat statsfile | grep "Total population" | tail -n 1 | awk '{ print $3; }'`
		
		#uninfected are all the nodes that for any case were not infected (immune, survived etc) and therefore the susceptible population
		SUSCEPTIBLE=`cat statsfile | grep "susceptible" | tail -n 1 | awk '{ print $6; }'`
		#LB stands for Lucky Bastards, which are the non members of the P2P/Cloud which remained uninfected
		LB=`expr $SUSCEPTIBLE - $SURVIVORS`
		
		echo "SURVIVORS=$SURVIVORS"
		echo "INCOMINGSIZE=$INCOMINGSIZE"
		echo "CLOUDSIZE=$CLOUDSIZE"
		echo "BUFFERSIZE=$BUFFERSIZE"
		echo "ACTIVE=$ACTIVE"
		echo "INFECTED=$INFECTED"
		echo "Total population=$N"
		echo "SUSCEPTIBLE=$SUSCEPTIBLE"
		echo "LuckyBastards=$LB"
		#echo -e "$SURVIVORS\t$INCOMINGSIZE\t\t$CLOUDSIZE\t\t$BUFFERSIZE\t\t\t$ACTIVE\t\t\t\t\t$N\t\t$SUSCEPTIBLE\t\t$LB" >> ../temp
		echo -e "$SURVIVORS\t$INCOMINGSIZE\t\t$CLOUDSIZE\t\t$BUFFERSIZE\t\t\t$ACTIVE\t\t\t$N\t\t$SUSCEPTIBLE\t\t$LB" >> ../temp
		#echo -e "$SURVIVORS\t$INCOMINGSIZE\t\t$P2PSIZE" >> ../temp
		#echo $INCOMINGSIZE
		cd ..
	fi
done 

cat temp>all.dat
sort -g temp > survivors.dat
sort -g temp > testfile		
echo -e "---------------------------------------------AVERAGE-MEAN--------------------------------------------------------------------------------" >> ./all.dat



less testfile | grep ^[0-9]  > numericfile	
echo "Cleaning File, Leaving only numbers - No text"

#find the exact number of lines of the file
LineNumb=$(wc -l numericfile | awk '{ print $1 ; }')
echo "Number of Lines=$LineNumb"

#let's try to find the average for each columnn
i=1

col1=0
totalCol1=0

col2=0
totalCol2=0

col3=0
totalCol3=0

col4=0
totalCol4=0

col5=0
totalCol5=0

col6=0
totalCol6=0

col7=0
totalCol7=0

col8=0
totalCol8=0

#We have all the required data and now we will start to find the averages because that will be important 
while [ $i -le $LineNumb ]
do
	col1=`cat numericFile | head -n $i | tail -n 1 | awk '{ print $1 ; }'`
	totalCol1=`expr "$totalCol1 + $col1" | bc`
	#debug
	#echo "Col1-$col1 - TotalCol1=$totalCol1"
	
	col2=`cat numericFile | head -n $i | tail -n 1 | awk '{ print $2 ; }'`
	totalCol2=`expr "$totalCol2 + $col2" | bc`
	#debug
	#echo "Col2-$col2 - TotalCol2=$totalCol2"
	
	col3=`cat numericFile | head -n $i | tail -n 1 | awk '{ print $3 ; }'`
	totalCol3=`expr "$totalCol3 + $col3" | bc`
	#debug
	#echo "Col3-$col3 - TotalCol3=$totalCol3"
	
	col4=`cat numericFile | head -n $i | tail -n 1 | awk '{ print $4 ; }'`
	totalCol4=`expr "$totalCol4 + $col4" | bc`
	#debug
	#echo "Col4-$col4 - TotalCol4=$totalCol4"
	
	col5=`cat numericFile | head -n $i | tail -n 1 | awk '{ print $5 ; }'`
	totalCol5=`expr "$totalCol5 + $col5" | bc`
	#debug
	#echo "Col5-$col5 - TotalCol5=$totalCol5"
	
	col6=`cat numericFile | head -n $i | tail -n 1 | awk '{ print $6 ; }'`
	totalCol6=`expr "$totalCol6 + $col6" | bc`
	#debug
	#echo "Col6-$col6 - TotalCol6=$totalCol6"
	
	col7=`cat numericFile | head -n $i | tail -n 1 | awk '{ print $7 ; }'`
	totalCol7=`expr "$totalCol7 + $col7" | bc`
	#debug
	#echo "Col7-$col7 - TotalCol7=$totalCol7"
	
	col8=`cat numericFile | head -n $i | tail -n 1 | awk '{ print $8 ; }'`
	totalCol8=`expr "$totalCol8 + $col8" | bc`
	#debug
	#echo "Col8-$col8 - TotalCol8=$totalCol8"
	
	i=`expr "$i + 1" | bc`
	echo "Loop index i=$i"
done

meanTotalCol1=`expr "scale=3; $totalCol1 / $LineNumb" | bc -l`
echo "MeanTotaCol1=$meanTotalCol1"

meanTotalCol2=`expr "scale=3; $totalCol2 / $LineNumb" | bc -l`
echo "MeanTotaCol2=$meanTotalCol2"

meanTotalCol3=`expr "scale=3; $totalCol3 / $LineNumb" | bc -l`
echo "MeanTotaCol3=$meanTotalCol3"

meanTotalCol4=`expr "scale=3; $totalCol4 / $LineNumb" | bc -l`
echo "MeanTotaCol4=$meanTotalCol4"

meanTotalCol5=`expr "scale=3; $totalCol5 / $LineNumb" | bc -l`
echo "MeanTotaCol5=$meanTotalCol5"

meanTotalCol6=`expr "scale=3; $totalCol6 / $LineNumb" | bc -l`
echo "MeanTotaCol6=$meanTotalCol6"

meanTotalCol7=`expr "scale=3; $totalCol7 / $LineNumb" | bc -l`
echo "MeanTotaCol7=$meanTotalCol7"

meanTotalCol8=`expr "scale=3; $totalCol8 / $LineNumb" | bc -l`
echo "MeanTotaCol8=$meanTotalCol8"

echo -e "$meanTotalCol1\t$meanTotalCol2\t\t$meanTotalCol3\t\t$meanTotalCol4\t\t\t$meanTotalCol5\t\t\t$meanTotalCol6\t\t$meanTotalCol7\t\t$meanTotalCol8" >> all.dat


tr -d 'f;' <survivors.dat> out.tmp
rm -f ./temp
rm -f ./survivors.dat
#cat out.tmp > all.dat
#sort -g temp >all.dat
#rm -f ./temp




