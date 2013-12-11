#Parse all files to create a script that finds the relative protection of peers 
#Intitialsize clean temp files
#Taken in to account the incoming buffer size

cd ../../results/$1/com_virology/
rm -f ./all.dat
rm -f ./temp

echo -e "SURV INCOMING P2PSIZE ACTIVE BUFFER  N" > ./temp
for i in `ls` 
	do if [  -d $i ] ; then 
		cd $i
		pwd
		INCOMINGSIZE=`cat code | grep incomingSize | awk '{ print $5 ; }' | tr ';' ' '`
		P2PSIZE=`cat config.txt | tail -n 2 | head -n 1 | awk '{ print $1 ; }'`
		BUFFERSIZE=`cat code | grep bufferSize | awk '{ print $5 ; }' | tr ';' ' '`
		SURVIVORS=`tail -n 1 P2P | awk '{ print $6 ; }'`
		ACTIVE=`cat code | tail -n 3 | head -n 1 | awk '{ print $5 ; }'`
		N=`cat statsfile | grep "Total population" | tail -n 1 | awk '{ print $3; }'`
		echo "SURVIVORS= $SURVIVORS"
		echo "INCOMINGSIZE= $INCOMINGSIZE"
		echo "P2PSIZE= $P2PSIZE"
		echo "BUFFERSIZE= $BUFFERSIZE"
		echo "ACTIVE= $ACTIVE"
		echo "Total population= $N"
		echo -e "$SURVIVORS\t$INCOMINGSIZE\t\t$P2PSIZE\t\t$BUFFERSIZE\t\t\t$ACTIVE\t\t\t\t\t$N" >> ../temp
		#echo -e "$SURVIVORS\t$INCOMINGSIZE\t\t$P2PSIZE" >> ../temp
		#echo $INCOMINGSIZE
		echo "<------------------------------------------------------------------------->"
		cd ..
	fi
done 

#cat temp>all.dat
sort -g temp > survivors.dat
tr -d 'f;' <survivors.dat> out.tmp
rm -f ./temp
rm -f ./survivors.dat
cat out.tmp > all.dat
#sort -g temp >all.dat
#rm -f ./temp




