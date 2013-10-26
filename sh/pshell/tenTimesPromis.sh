#/bin/sh
#This script runs ten times the Promis Simulations.

mkdir -m 777 ../../results/$1

cd ../../bin

x=0
while [ $x -lt 2 ]
do
	clear
    echo $(($x+1)) execution of the Promis simulator ; sleep 1   
	./promis.sh && cd ../sh/pshell/ && ./dothemagic.sh 150 150 && ./archive.sh && ./clean.sh &&  cd ../../bin/
	x=`echo "$x + 1" | bc`
	sleep 5
done

#Moving Promis Simulation Files to the Result Directory 
mv ../other/* ../results/$1
#Make the required folders in order to store the simulation outcomes in a prore way
cd ../sh/pshell/
./createfolders.sh
