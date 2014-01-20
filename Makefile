all:
	#Createting The Proper Class Files
	javac -d ./classes  src/promis/*.java
	chmod 777 -R ./*
clean:
	#Cleaning All Simulation Data
	rm -f ./classes/*.class
