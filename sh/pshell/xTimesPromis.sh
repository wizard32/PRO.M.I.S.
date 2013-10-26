#/bin/sh
#This is the script to run a number of times the simulations.

mkdir -m 777 ../../results/$2

cd ../../bin

x=0
while [ $x -lt $1 ]
do
    clear
    echo $(($x+1)) execution of the Promis simulator ; sleep 1
	./promis.sh && cd ../sh/pshell/ && ./dothemagic.sh 100 100 && ./archive.sh && cd ../../bin/
	x=`echo "$x + 1" | bc`
	sleep 3
done

#Moving Promis Simulation Files to the Result Directory 
mv ../other/* ../results/$2
chmod 777 -R ../results/*
#Make the required folders in order to store the simulation outcomes in a prore way
cd ../sh/pshell/
./createfolders.sh
