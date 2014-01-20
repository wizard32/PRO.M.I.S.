#!/bin/sh

/*
	@creator Liatsis Fotis
	liatsisfotis at Gmail dot com
*/

clear
cd ../classes/

flag=Y
while test "$flag" != "q" ; do
	
	echo "User: $USER"
	DATE=`date`
	echo "$DATE. \n"
	
	echo "######################################################"
	echo "#\033[0;34m Network Graphs for Computer Epidemiologists (NGCE) \033[0m#"
	echo "######################################################"

	echo "\n1: Build Graph"
	echo "2: Post-processing of the Graph (Analyze)"
	echo "3: Visualize the Graph structure"
	echo "4: Edit config file"
	echo "5: ReadMe File"
	echo "0: \033[1;31mExit\033[0m"
	echo "------------------------"
	read -p  "Enter your selection: " answer
	echo "------------------------\n"
	
	#Build Graph Option
	if test "$answer" = "1" ; then
		java -Xmx300M BuildGraph
	
	#Post-processing of the Graph (Analyze) Option
	elif test "$answer" = "2" ; then
		java -Xmx300M GraphExplorer
		cd ../other/
		gnuplot "plotGraph.plt"
		cd ../classes/
		
	#Visualize the Graph structure Option
	elif test "$answer" = "3" ; then
		java -Xmx300M PajekOut
	
	#Edit config file Option
	elif test "$answer" = "4" ; then
		gedit ../bin/config.txt
		echo "config.txt file Updated"
		sleep 2 ; clear ; continue
			
	#ReadMe File Option
	elif test "$answer" = "5" ; then
		less ../README.txt
	
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
