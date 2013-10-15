
folders:
	#Creating The Proper Directories
	mkdir classes/ doc/ other/ Plots/
	chmod 777 -R ./*
all:
	#Createting The Proper Class Files
	javac -d ./classes  src/ngce/*.java
docs:
	#Createting The Proper Doc Files
	javadoc -d ./doc ./src/ngce/*.java
clean:
	#Cleaning All Simulation Data
	rm -f ./classes/*.class 
	rm -rf ./doc/*
	rm -f ./other/*
	rm -f ./Plots/*
