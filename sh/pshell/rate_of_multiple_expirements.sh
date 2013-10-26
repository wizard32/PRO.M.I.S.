#Find the proper directory
#First argument is number of nodes, second is graph type

if [ -d ../../results/$1  ]
then
	cd ../../results/$1
else
	echo 'Directory Not Found'
	exit
fi

#add all files to a single one
#sort -n ./* >testfile

#find the exact number of lines of the file
Number_Of_Lines=$(wc -l survivors.dat | awk '{ print $1 ; }')
echo "Number of Lines=$Number_Of_Lines"

#parse the last line and get the maximum number of timeslots in any of the available files
#maxNumber=$(tail -n 1 numericfile | awk '{ print $1 ; }')
#echo "maxNumber=$maxNumber"

#works better but should be verified if the array index starts from 0 or 1--latest change from 1 to 0
#revert to 1 because of the head command
x=1
i=0
times=0
totalSum=0


dos2unix survivors.dat
less survivors.dat  > tempFile
dos2unix tempFile
rm -f finalRate

Exp="$(head -n 1 tempFile | awk '{ print $1 ; }')"
echo "Exp= $Exp"
sleep 3

while [ $x -le $Number_Of_Lines ]
do
    NewExp="$(head -n $x tempFile | tail -n 1  | awk '{ print $1 ; }')"
    Res="$(head -n $x tempFile | tail -n 1  | awk '{ print $2 ; }')"
    echo "NewExp= $NewExp <***> Exp= $Exp <***> Res= $Res"
    equal=$(perl -e "print $Exp - $NewExp")
    equal=$(echo $equal | tr -d ".")
    echo "equal: $equal"
    if [ $equal -eq 0 ] ;then
    	totalSum=`expr "$Res+$totalSum"| bc`
    	times=`expr "$times + 1" | bc`
    	echo "Current Total Sum=$totalSum <-------------------> Number of Measurements:$times"
	meanPoint=`expr "$totalSum / $times" | bc -l `
    else
	echo " meanPoint= $meanPoint"
    	echo "$Exp	$meanPoint" >> finalRate
    	echo "New expirement found ^^^^^^ Initializing"
    	Exp=$NewExp
    	times=1
    	totalSum=$Res
	meanPoint=$totalSum
    fi 
    #echo " meanPoint= $meanPoint"
    #echo "$x	$meanPoint" >> finalRate
    echo "******************************************************************"
    x=`expr "$x + 1" | bc`
done

echo "$Exp	$meanPoint" >> finalRate
echo "Finalizing ^^^^^^ EOL"


rm -f testfile
rm -f tempFile

