#init.sh: Create the proper directories
#Author: Vasileios Vlachos vbill@aueb.gr
#Date: 17/02/2005
#make the required files in order to store the simulation outcomes
#in a prore way
#
#This script should be run only once. At the installation of the tool 

#Verification and statistics are mostly for debug purposes
echo "Creating Verification Directory"
mkdir ../../other/verification
echo "Creating Statistics Directory"
mkdir ../../other/statistics
echo "Creating Temp Directory"
mkdir ../../other/tempfiles

#The crusial information to draw the graphical plots of the simulators outcome
echo "Creating Plot Directory" 
mkdir ../../other/plots

#Creating a directory for benchmark purposes
echo "Creating Splot Directory" 
mkdir ../../other/splots
