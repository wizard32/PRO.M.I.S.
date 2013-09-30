#init.sh: Create the proper directories
#Author: Vasileios Vlachos vbill@aueb.gr
#Date: 17/11/2005
#clean all simulation data
#in a prore way
#
#This script should be run only once if you want to remove all stored results
#from previous simulations  

#Verification and statistics are mostly for debug purposes
echo "Cleaning verification directory"
rm -f ../../other/verification/./*
echo "Cleaning statistics directory"
rm -f ../../other/statistics/./*
echo "Cleaning temp directory"
rm -f ../../other/tempfiles/./*

#The crusial information to draw the graphical plots of the simulators outcome
echo "Cleaning plot directory" 
rm -f ../../other/plots/./*

echo "Cleaning splot directory" 
rm -f ../../other/splots/./*