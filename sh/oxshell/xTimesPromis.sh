#/bin/sh
#This is the script to run a number of times the simulations.
#Argument1: number of itterations
#Argument2: target directory

#trying to fix the silly problem with java in SuSE
HOST=`hostname`
if `test  $HOST = "lab"`
then
  echo "Working in office - Fix the damn SuSE problem"
  alias javac='/opt/jdk1.7.0/bin/javac'
  alias java='/opt/jdk1.7.0/bin/java'
fi
java -version

cd ../../bin
#Use the number of simulation cycles to draw the plot correctly 
timeslots=`cat config.txt | grep iteration |  awk '{ print $1 ; }'`
echo "timeslots=$timeslots"
sleep 5



x=0
while [ $x -lt $1 ]
do
    	echo $x execution of the Promis simulator
	./promis.sh && cd ../sh/oxshell/ && ./dothemagic.sh $timeslots $timeslots && ./archive.sh $2 && ./clean.sh &&  cd ../../bin/
	pwd
	x=`echo "$x + 1" | bc`
	sleep 15
done
