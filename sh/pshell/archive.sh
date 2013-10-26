#Authors: Vasileios Vlachos, Liatsis Fotis
#Date: 24/10/2013

#Move the results of the simulation in proper directories.
#The format of the directory is:
#Date of month[0-31]
#Month [Jan-Dec]
#Year [20xx]
#Hour [00-23]
#Minutes [00-59]
#Second [00-59]

cd ../../other/plots/
TODAY=`date +%d%h%Y_%H:%M:%S`
mkdir ../com_virology/$TODAY

mv ./plot.plt ../com_virology/$TODAY
mv ./peer.plt ../com_virology/$TODAY
mv ./Infected_Population ../com_virology/$TODAY
mv ./Epidemic_Curve ../com_virology/$TODAY
mv ./Global_Estimated_Rate ../com_virology/$TODAY
mv ./Local_Estimated_Rate ../com_virology/$TODAY
mv ./P2P ../com_virology/$TODAY
#mv ./Unconstraint_Model ../com_virology/$TODAY
mv ./data.ps ../com_virology/$TODAY
mv ./peer.png ../com_virology/$TODAY
mv ./peer.pdf ../com_virology/$TODAY
mv ./peer.ps ../com_virology/$TODAY
mv ./statsfile ../com_virology/$TODAY
cp ../../bin/config.txt ../com_virology/$TODAY

cd ../../src/promis
head -n 35 Node.java > tempCode

cat tempCode | grep bufferSize > code
cat tempCode | grep incomingSize >> code
cat tempCode | grep minActiveNodesP >> code
cat tempCode | grep tHigh >> code
cat tempCode | grep tLow >> code

rm -f ./tempCode
mv ./code ../../other/com_virology/$TODAY

#cd -
#./clean.sh
