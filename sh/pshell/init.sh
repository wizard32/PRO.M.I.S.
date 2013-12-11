#Authors: Vasileios Vlachos, Liatsis Fotis
#Date: 24/10/2013

#Make the required folders in order to store the simulation outcomes in a prore way

#This script should be run only once. At the installation of the tool
echo "Creating Other Directory..." 
[ -d ../../other ] || mkdir -m 777 ../../other
echo "Creating Results Directory..."
[ -d ../../results ] || mkdir -m 777 ../../results

#Verification and statistics are mostly for debug purposes
echo "Creating Com_Virology Directory..."
[ -d ../../other/com_virology ] || mkdir -m 777 ../../other/com_virology
echo "Creating Verification Directory..."
[ -d ../../other/verification ] || mkdir -m 777 ../../other/verification
echo "Creating Statistics Directory..."
[ -d ../../other/statistics ] || mkdir -m 777 ../../other/statistics
echo "Creating Temp Directory..."
[ -d ../../other/tempfiles ] || mkdir -m 777 ../../other/tempfiles

#The crusial information to draw the graphical plots of the simulators outcome
echo "Creating Plot Directory..." 
[ -d ../../other/plots ] || mkdir -m 777 ../../other/plots

#Creating a directory for benchmark purposes
echo "Creating Splot directory..." 
[ -d ../../other/splots ] || mkdir -m 777 ../../other/splots
