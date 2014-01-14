#/bin/sh
#This is the script to run a number of times the simulations.

mkdir -m 777 ../../results/$2

cd ../../bin

x=0
while [ $x -lt $1 ]
do
    clear
    echo $(($x+1)) execution of the Promis simulator ; sleep 1
	./promis.sh && cd ../sh/pshell/ && ./dothemagic.sh 170 170 && ./archive.sh && cd ../../bin/
	x=`echo "$x + 1" | bc`
done

#Copying Promis Simulation Files to the Results Directory 
cp -R ../other/* ../results/$2
chmod 777 -R ../results/*

#Removing Temporary Stored Results
cd ../sh/pshell/
./cleandata.sh ; sleep 2

