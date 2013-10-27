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
	echo "2: Show GraphTopology Information"
	echo "3: Create Required Folders"
	echo "4: Remove Previous Simulation Results"
	echo "5: Configure config file"
	echo "x:"
	echo "6: Exit"
	echo "-------------------------"
	read -p  "Enter your selection: " answer
	echo "-------------------------"
	
	if test "$answer" = "1" ; then
		echo "Give Number of PRO.M.I.S. Simulation Time"
		read -p  "Enter Number: " number
		if [ ! -n "$number" ] ; then
			echo "You didn't enter any number"
			sleep 2 ; clear
			continue
		fi
		echo "Give Folder Name of PRO.M.I.S. Simulation Results"
		read -p  "Enter Number: " name
		if [ ! -n "$name" ] ; then
			echo "You didn't enter any name"
			sleep 2 ; clear
			continue
		fi
		./xTimesPromis.sh $number $name
	elif test "$answer" = "5" ; then
		nano ../../bin/config.txt
	elif test "$answer" = "3" ; then
		./init.sh
	elif test "$answer" = "4" ; then
		echo "\nAre you sure you want to delete all previous results? y(Yes) n(No)"
		read -p  "Enter your choise: " delete
		if test "$delete" = "y" ; then
			./cleandata.sh
		fi
	#elif test "$answer" = "x" ; then
	elif test "$answer" = "2" ; then
		clear
		echo "GraphTopology Specifications"
		echo "-----------------------------------------\n"
		INFO=`cat ../../GraphTopology.txt  | head -5 | awk '{ print $0; }'`
		echo "$INFO"
		SIZE=$(ls -lah ../../GraphTopology.txt | awk '{ print $5}')
		echo "#Size:= $SIZE" 
	elif test "$answer" = "6" ; then
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

