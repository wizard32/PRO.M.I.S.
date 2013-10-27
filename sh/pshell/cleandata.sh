#Authors: Vasileios Vlachos, Liatsis Fotis
#Date: 24/10/2013

#Create the proper directories runnings once the init.sh file

#This script should be run only once if you want to remove all stored results
#from previous simulations  

#Verification and statistics are mostly for debug purposes
echo "Cleaning Verification Directory.."
rm -rf ../../other/verification/./*
echo "Cleaning Statistics Directory.."
rm -rf ../../other/statistics/./*
echo "Cleaning Temp Directory.."
rm -rf ../../other/tempfiles/./*

#The crusial information to draw the graphical plots of the simulators outcome
echo "Cleaning Plot Directory" 
rm -rf ../../other/plots/./*

echo "Cleaning Splot Directory.." 
rm -rf ../../other/splots/./*

#PRO.M.I.S. Results
echo "Cleaning PRO.M.I.S. Results.."
rm -rf ../../other/com_virology/*
