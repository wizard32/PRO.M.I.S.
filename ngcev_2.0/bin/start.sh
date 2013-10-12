#!/bin/sh

clear
cd ../classes/

flag=c
while test "$flag" != "q" ; do
	
	echo "User: $USER"
	DATE=`date`
	echo "$DATE. \n"
	
	echo "######################################################"
	echo "# Network Graphs for Computer Epidemiologists (NGCE) #"
	echo "######################################################"

	echo "\n1: Build Graph"
	echo "2: Post-processing of the Graph (Analyze)"
	echo "3: Visualize the Graph structure"
	echo "4: Exit"
	echo "-----------------------------------------"
	read -p  "Enter your selection: " answer
	
	if test "$answer" = "1" ; then
		java -Xmx300M BuildGraph
	elif test "$answer" = "2" ; then
		java -Xmx300M GraphExplorer
	elif test "$answer" = "3" ; then
		java -Xmx300M PajekOut
	elif test "$answer" = "4" ; then
		echo "\nGood Bye..."
		break
	else
		echo "\nWrong Parameter Selected"
	fi
	echo "\n"
	echo "Do you want to continue? c(Continue) q(Quit)"
	read -p  "Enter your choise: " flag
	clear
	if test "$flag" = "q" ; then
		echo "\nGood Bye..."
	fi

done
