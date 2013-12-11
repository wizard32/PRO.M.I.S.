#!/bin/sh
#Authors: Vasileios Vlachos, Liatsis Fotis
#Date: 24/10/2013

#Create the proper directories runnings once the init.sh file

#This script should be run only once if you want to remove all stored results
#from previous simulations  

echo "\nCleaning Temporary Directories\n---------------------------------"

#PRO.M.I.S. Results
echo "- Com_Virology"
rm -rf ../../other/com_virology/*

#Verification and statistics are mostly for debug purposes
echo "- Verification"
rm -rf ../../other/verification/./*
echo "- Statistics"
rm -rf ../../other/statistics/./*
echo "- Temp"
rm -rf ../../other/tempfiles/./*

#The crusial information to draw the graphical plots of the simulators outcome
echo "- Plot" 
rm -rf ../../other/plots/./*

echo "- Splot" 
rm -rf ../../other/splots/./*
