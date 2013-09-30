#/bin/sh
#This is the script to run a number of times the simulations. 

cd ../../bin
x=0
while [ $x -lt $1 ]
do
    	echo $x execution of the Piranha simulator 
	./promis.sh
	pwd
	x=`echo "$x + 1" | bc`
done
