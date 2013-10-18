#find the proper directory
#first argument is number of nodes, second is graph type
if [ -d ../../other/plots  ]
then  	
	cd ../../other/plots/ 
else
	echo 'Directory Not Found'
fi

#add all files to a single one
sort -n ./* >testfile			

#leave only the numerical simulation data		
less testfile | grep ^[0-9]  > numericfile	

#find the exact number of lines of the file
wc -l numericfile | awk '{ print $1 ; }'	

#parse the last line and get the maximum number of timeslots in any of the available files
maxNumber=$(tail -n 1 numericfile | awk '{ print $1 ; }') 
echo "maxNumber=$maxNumber"

#works better but should be verified if the array index starts from 0 or 1--latest change from 1 to 0
x=0    
i=0
line=0
numOFLines=0
temp=0

#the final mean value for each time slot
meanPoint=0							
tlines=0

#in case a file has already finished, fill the remaining time slots with 1
finishedPoints=0

#is the number of the maximum measurements in any time slot 						
ultimumSingle=0						
	
while [ $x -le $maxNumber ]
do
    #echo $x;
    maxSingle=$(less numericfile | grep ^$x[^0-9] | wc -l)	#for each timeslot find the number of available data
    less numericfile | grep ^$x[^0-9] > tempFile
    numOFLines=$(less tempFile | wc -l )
    echo "num of lines = $numOFLines"
    echo "maxSingle is $maxSingle"
    if [  $maxSingle -gt $ultimumSingle ] ;then
	ultimumSingle=$maxSingle
    	echo "ultimumSingle= $ultimumSingle"	
    fi   
    echo `less tempFile | wc -l`
    echo '**************************************************************************************'
    
    #inner loop - works on each time slot granularity
    while [ $i -lt $maxSingle ]
    do
    	i=`expr "$i + 1" | bc`
    	t_array[$x]=$(head -n $i tempFile | awk '{ print $2 ; }')
	temp=`expr "$(head -n $i tempFile | tail -n 1  | awk '{ print $2 ; }')+$temp"| bc`
    	echo "time slot $x has ${t_array[$x]} points"
    	#echo "maxSingle is $maxSingle"
    done
    
    i=0
    if [ $maxSingle -ne 0 ] ; then
    	if [ $maxSingle  -lt $ultimumSingle ] ; then
		finisgedPoints=`expr "$ultimumSingle - $maxSingle"| bc`
	    	temp=`expr "$temp + $finisgedPoints"| bc`	
    		meanPoint=`expr "$temp / $ultimumSingle" | bc -l ` 
	else
    		meanPoint=`expr "$temp / $ultimumSingle" | bc -l `
	fi	
    fi
    echo "temp= $temp"
    echo " meanPoint= $meanPoint"
    echo "$x	$meanPoint" >> finalRate
    echo '**************************************************************************************'
    #initialize everything for the outer loop
    temp=0 							
    meanPoint=0
    finisgedPoints=0
    x=`expr "$x + 1" | bc`
done


z=0
sum=0

#Clear temporary files
rm -f testfile 
rm -f tempFile
rm -f  numericfile 
