#Authors: Vasileios Vlachos, Liatsis Fotis
#Date: 24/10/2013

echo "Creating required folders..."

#Make the required files in order to store the simulation outcomes in a prore way
[ -d ../../other ] || mkdir -m 777 ../../other
[ -d ../../results ] || mkdir -m 777 ../../results

#Verification and statistics are mostly for debug purposes
[ -d ../../other/com_virology ] || mkdir -m 777 ../../other/com_virology
[ -d ../../other/verification ] || mkdir -m 777 ../../other/verification
[ -d ../../other/statistics ] || mkdir -m 777 ../../other/statistics
[ -d ../../other/tempfiles ] || mkdir -m 777 ../../other/tempfiles

#The crusial information to draw the graphical plots of the simulators outcome
[ -d ../../other/plots ] || mkdir -m 777 ../../other/plots

#Creating a directory for benchmark purposes
[ -d ../../other/splots ] || mkdir -m 777 ../../other/splots

sleep 1 ; echo "\033[0;31mDone!\033[0m"
