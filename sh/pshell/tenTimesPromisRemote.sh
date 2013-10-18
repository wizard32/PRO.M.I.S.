#/bin/sh
#This is the script to run a number of times the simulations. 

cd ../../bin
x=0
while [ $x -lt 10 ]
do
    	echo $x execution of the Promis simulator 
	nohup nice ./promis.sh > /dev/null  && cd ../sh/pshell/ && ./archive.sh && ./clean.sh &&  cd ../../bin/
	pwd
	x=`echo "$x + 1" | bc`
done
