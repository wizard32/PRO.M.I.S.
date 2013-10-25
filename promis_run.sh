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
	echo "x:"
	echo "n: Exit"
	echo "-----------------------------------------"
	read -p  "Enter your selection: " answer
	echo "-----------------------------------------"
	
	if test "$answer" = "1" ; then
		
	elif test "$answer" = "2" ; then

	elif test "$answer" = "x" ; then

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

