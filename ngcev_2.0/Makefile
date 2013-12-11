all:
	#Createting The Proper Class Files
	javac -d ./classes  src/ngce/*.java
	chmod 777 -R ./*
docs:
	#Createting The Proper Doc Files
	javadoc -d ./doc ./src/ngce/*.java
	chmod 777 -R ./*
folders:
	#Creating The Proper Directories
	[ -d ./classes ] || mkdir -m 777 classes/
	[ -d ./doc ] || mkdir -m 777 doc/
	[ -d ./other ] || mkdir -m 777 other/
	[ -d ./Plots ] || mkdir -m 777 Plots/
clean:
	#Cleaning All Simulation Data
	rm -f ./classes/*.class 
	rm -rf ./doc/*
	rm -f ./other/*
	rm -f ./Plots/*
	rm -f *PGraph*.net
	rm -f *GraphTopology*.txt
