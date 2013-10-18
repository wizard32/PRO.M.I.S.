#restore the results of the simulation to replot them 
#the format of the directory is 
#date of month[0-31]
#month [Jan-Dec]
#hour [00-23]
cd ../../../sh/pshell
./clean.sh
cd -

mv ./plot.plt ../../plots
mv ./peer.plt ../../plots
mv ./Infected_Population  ../../plots
mv ./Epidemic_Curve ../../plots
mv ./Global_Estimated_Rate ../../plots
mv ./Local_Estimated_Rate ../../plots
mv ./P2P ../../plots
mv ./Unconstraint_Model ../../plots
mv ./data.ps ../../plots
mv ./peer.pdf ../../plots
mv ./statsfile ../../plots
