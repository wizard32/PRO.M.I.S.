#move the results of the simulation in proper directories
#the format of the directory is
#date of month[0-31]
#month [Jan-Dec]
#hour [00-23]


cd ../../other/plots
TODAY=`date +%d%h%H%M`
mkdir ../com_virology/$TODAY

mv ./plot.plt ../com_virology/$TODAY
mv ./peer.plt ../com_virology/$TODAY
mv ./Infected_Population ../com_virology/$TODAY
mv ./Epidemic_Curve ../com_virology/$TODAY
mv ./Global_Estimated_Rate ../com_virology/$TODAY
mv ./Local_Estimated_Rate ../com_virology/$TODAY
mv ./P2P ../com_virology/$TODAY
mv ./Unconstraint_Model ../com_virology/$TODAY
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
