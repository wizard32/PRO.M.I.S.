#!/bin/sh

clear
cd sh/pshell/

flag=c
while test "$flag" != "q" ; do

	echo "User: $USER"
	DATE=`date`	
	echo "$DATE. \n"

	echo "+------------------------------------------------------+"
	echo "| PRO.M.I.S. - PRoactive Malware Identification System |"
	echo "+------------------------------------------------------+"

	echo "\n1: Run PRO.M.I.S. Simulation"
	echo "2: Configure config file"
	echo "3: Create Required Folders"
	echo "4: Remove Previous Simulation Results"
	echo "5: Show GraphTopology Information"
	echo "x:"
	echo "n: Exit"
	echo "-----------------------------------------"
	read -p  "Enter your selection: " answer
	echo "-----------------------------------------"
	
	if test "$answer" = "1" ; then
		./xTimesPromis.sh 1 fotis
	elif test "$answer" = "2" ; then
		nano ../../bin/config.txt
	elif test "$answer" = "3" ; then
		./init.sh
	elif test "$answer" = "4" ; then
		./cleandata.sh
	#elif test "$answer" = "x" ; then
	elif test "$answer" = "5" ; then
		clear
		echo "GraphTopology Specifications"
		echo "-----------------------------------------\n"
		INFO=`cat ../../GraphTopology.txt  | head -5 | awk '{ print $0; }'`
		echo "$INFO"
		SIZE=$(ls -lah ../../GraphTopology.txt | awk '{ print $5}')
		echo "#Size:= $SIZE" 
	elif test "$answer" = "n" ; then
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

