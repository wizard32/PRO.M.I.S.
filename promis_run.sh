#!/bin/sh

/*
	@creator Liatsis Fotis
	liatsisfotis at Gmail dot com
*/

clear
cd sh/pshell/

re='^[0-9]+$'

flag=Y
while test "$flag" != "q" ; do

	echo "User: $USER"
	DATE=`date`	
	echo "$DATE. \n"

	echo "+------------------------------------------------------+"
	echo "|\033[0;33m PRO.M.I.S. - PRoactive Malware Identification System \033[0m|"
	echo "+------------------------------------------------------+"

	echo "\n1: Run PRO.M.I.S. Simulation"
	echo "2: Export PROM.I.S. Results"
	echo "3: Show GraphTopology Information"
	echo "4: Show Simulation Results"
	echo "5: Remove Simulation Results"
	echo "6: Create Required Folders"
	echo "7: Edit config file"
	echo "8: Create Network Graphs (NGCE Tool)"
	echo "9: ReadMe File"
	echo "0:\033[1;31m Exit\033[0m"
	echo "------------------------"
	read -p  "Enter your selection: " answer
	echo "------------------------\n"
	
	#Run PRO.M.I.S. Simulation Option
	if test "$answer" = "1" ; then
		read -p "Give Number of PRO.M.I.S. Simulation Time: " number
		if [ ! -n "$number" ] ; then
			echo "\nYou didn't enter any number"
			sleep 2 ; clear ; continue
		fi
		read -p "Give Folder Name of PRO.M.I.S. Simulation Results: " name
		if [ ! -n "$name" ] ; then
			echo "\nYou didn't enter any name"
			sleep 2 ; clear ; continue
		fi
		./xTimesPromis.sh $number $name
		clear ; continue
	
	#Export PROM.I.S. Results Option
	elif test "$answer" = "2" ; then
		echo "Not Yet..!"
	
	#Show GraphTopology Information Option
	elif test "$answer" = "3" ; then
		if [ -f ../../GraphTopology.txt ] ; then
			clear
			echo "GraphTopology Specifications"
			echo "---------------------------------\n"
			INFO=`cat ../../GraphTopology.txt  | head -5 | awk '{ print $0; }'`
			echo "$INFO"
			SIZE=$(ls -lah ../../GraphTopology.txt | awk '{ print $5}')
			echo "#Size:= $SIZE"
		else
			echo "GraphTopology Specifications\n----------------------------------\n\nGraphTopology file does not exists"
			sleep 2 ; clear ; continue
		fi 
		
	#Show Simulation Results Option
	elif test "$answer" = "4" ; then
		echo "\033[1mPRO.M.I.S. Simulation Results:\n\033[0m"
		echo "$(ls -lah ../../results/ | awk 'NR>3' | awk '{ print $9 }')"
		#echo "$CONTENT"
	
	#Remove Simulation Results Option			
	elif test "$answer" = "5" ; then
		echo "Do you want to delete all simulation results?"
		echo "Note: This option will permanently delete all simulation data. File(s) cannot be retrieved."
		read -p "Y(Yes), N(No) : " delete
		
		if test "$delete" = "Y" ; then
			./clean.sh
			sleep 2 ; clear ; continue
		elif [ ! "$delete" = "N" ] ; then
			echo "\nWrong answer..."
			sleep 2 ; clear ; continue
		fi
		
	#Create Required Folders Option
	elif test "$answer" = "6" ; then
		./createfolders.sh
		sleep 2 ; clear ;continue
	
	#Configure config file Option
	elif test "$answer" = "7" ; then
		gedit ../../bin/config.txt
		echo "config.txt file Updated"
		sleep 2 ; clear ; continue
	
	#Create Network Graphs (NGCE Tool)
	elif test "$answer" = "8" ; then
		cd ../../ngcev_2.0/
		make clean folders all docs 
		cd bin/; ./ngce_run.sh
		cd ../../sh/pshell/
		
	#ReadMe File Option
	elif test "$answer" = "9" ; then
		less ../../README
		pwd
	#Exit Option
	elif test "$answer" = "0" ; then
		echo "\033[0;34m\nGood Bye...\033[0m"
		break
	else
		echo "\nWrong Parameter Selected"
	fi
	
	echo "\n"
	read -p "Do you want to continue [Y/n]? " flag
	
	if test "$flag" = "n" ; then
		echo "\033[0;34m\nGood Bye...\033[0m"
		break
	elif [ ! "$flag" = "Y" ] ; then
			echo "\nWrong answer..."
			sleep 2 ; clear ; continue
	fi
	clear
done

