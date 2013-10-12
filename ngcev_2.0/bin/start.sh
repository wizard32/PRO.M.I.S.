#!/bin/sh

clear


cd ../classes/

m=c
while test "$m" != "q" ; do
	
	echo "User: $USER"
	DATE=`date`
	echo "$DATE. \n"
	
	echo "######################################################"
	echo "# Network Graphs for Computer Epidemiologists (NGCE) #"
	echo "######################################################"

	echo "\n1: Build Graph"
	echo "2: Post-processing of the Graph (Analyze)"
	echo "3: Visualize the Graph structure"
	echo "-----------------------------------------"
	read -p  "Enter your choise: " n
	
	if test "$n" = "1" ; then
		java -Xmx300M BuildGraph
	elif test "$n" = "2" ; then
		java -Xmx300M GraphExplorer
	elif test "$n" = "3" ; then
		java -Xmx300M PajekOut
	elif test "$m" = "q" ; then
		echo "\nGood Bye!"
	else
		echo "\nWrong Parameter Selected"
	fi
	echo "\n"
	echo "Do you want to continue? c(Continue) q(Quit)"
	read -p  "Enter your choise: " m
	clear

done
