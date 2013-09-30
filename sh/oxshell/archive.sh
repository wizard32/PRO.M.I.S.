#move the results of the simulation in proper directories
#the format of the directory is
#date of month[0-31]
#month [Jan-Dec]
#hour [00-23]


cd ../../other/plots
TODAY=`date +%d%h%H%M`

if [ -d ../$1  ]
then
	echo "Directory Exists -- Moving On"
else
	mkdir ../$1
fi


if [ -d ../$1/$TODAY ]
then
	echo "Directory Exists -- Please investigate further (do you use very small graphs?)"
else
	mkdir ../$1/$TODAY
fi


mv ./plot.plt ../$1/$TODAY
mv ./peer.plt ../$1/$TODAY
mv ./Infected_Population ../$1/$TODAY
mv ./Epidemic_Curve ../$1/$TODAY
mv ./Global_Estimated_Rate ../$1/$TODAY
mv ./Local_Estimated_Rate ../$1/$TODAY
mv ./P2P ../$1/$TODAY
#mv ./Unconstraint_Model ../$1/$TODAY
mv ./data.ps ../$1/$TODAY
mv ./peer.png ../$1/$TODAY
mv ./peer.pdf ../$1/$TODAY
mv ./peer.ps ../$1/$TODAY
mv ./statsfile ../$1/$TODAY
cp ../../bin/config.txt ../$1/$TODAY

cd ../../src/promis
head -n 35 Node.java > tempCode

cat tempCode | grep bufferSize > code
cat tempCode | grep incomingSize >> code
cat tempCode | grep minActiveNodesP >> code
cat tempCode | grep tHigh >> code
cat tempCode | grep tLow >> code

rm -f ./tempCode
mv ./code ../../other/$1/$TODAY
#cd -
#./clean.sh
