#/bin/sh
#This is the script to run a number of times the simulations.

cd ../../bin
x=0
while [ $x -lt $1 ]
do
    	echo $x execution of the Promis simulator
	./promis.sh && cd ../sh/pshell/ && ./dothemagic.sh 100 100 && ./archive.sh && ./clean.sh &&  cd ../../bin/
	pwd
	x=`echo "$x + 1" | bc`
done
