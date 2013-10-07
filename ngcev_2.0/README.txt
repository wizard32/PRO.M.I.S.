OVERVIEW
Network Graphs for Computer Epidemiologists (NGCE) is a tool for the design of
complex graphs.  A detailed explanation of implementation issues among with
theoretical aspects of this work is available at:
http://istlab.dmst.aueb.gr/~vbill/ngce.html

INSTALLATION
To install, simply download and unzip ngce.zip 

COMPILATION 
To build  NGCE simply issue "make" in the ./NGCE directory. To remove previously
built files use "make clean" 

EXECUTION
To execute NGCE two  options are available
1: Command line:
Go to ./NGCE/bin/ directory  and run ./ngce

However in order to use the command line version, a text file named config.txt
should be stored  in the ./bin/ directory

By editing this text file the user can built graph models with the desired
parameters.

Examples of  all the available graph models are given below:

********************************************************************************
*************
EXAMPLES:
--------

-HOMOGENEOUS GRAPH:

../	-> path
10456	-> number of vertices
h	-> graph type

********************************************************************************
*************
-RANDOM WITH FIXED CONNECTIVITY:

../        -> path
345	   -> number of vertices
f	   -> graph type
23	   -> neighbors
100        -> seed

********************************************************************************
*************
-RANDOM WITH FIXED CONNECTIVITY WITHOUT CHECKING:

../        -> path
345        -> number of vertices
x          -> graph type
23         -> neighbors
100        -> seed

********************************************************************************
*************
-ERDOS-RENY RANDOM GRAPH:

../	-> path
3000	-> number of vertices
e	-> graph type
0.0075	-> probability
100	-> seed

********************************************************************************
*************
-SCALE-FREE GRAPH (Barabasi&Albert):

../	-> path
10000	-> number of vertices
s	-> graph type
100	-> seed

********************************************************************************
*************
-CUSTOM SCALE-FREE GRAPH:

../	-> path
10000	-> number of vertices
m	-> graph type
20000	-> maximum number of edges

********************************************************************************
*************
-CUSTOM RANDOM GRAPH:

../	-> path
3000	-> number of vertices
c	-> graph type
0.0075	-> probability
2000	-> maximum number of edges
100	-> seed

********************************************************************************
*************
-PRE-PREPARED GRAPH:

../		 -> path
10456		 -> number of vertices
b		 -> graph type
../goo/graph.txt -> directory AND name of the already builded graph in text
format.


2. Graphical User Interface: 
A graphical version of the NGCE tool is available. Goto ./NGCE/bin directory and
execute ./visual_ngce 
All options are selectable via the graphical interface

DIRECTORIES STRUCTURE
A short explanation of the contents of each directory:
./bin -> The execution scripts and the configuration file config.txt
./classes-> The produced java classes
./Plots -> The generated  probability distribution functions plots in
Encapsulated Postscript (eps) Format. Each plot contains in its title all the
required information in order to have the analyzed graph rebuild 
./other -> Various temp files
./src -> The source of NGCE's java classes

REQUIREMENTS
In order to build  graphs, NGCE requires only a Java Runtime Environment (JRE)
installed in the system. To build the program, however, the Java Soft
Development Kit  (Java SDK) is needed. To extract the graph properties, to draw
the distribution plots and check the compliance with user defined parameters (in
the case of the Random Graph with Fixed Connectivity model), bash should be also
installed, with coreutils as bc. Finally to visualize the generated graphs, the
Pajek tool is a  requisite, which in turn depends on the Wine program in order
to be executed under the Linux operating system. 
Software:  
JRE-> Executes NGCE to generate graphs
Java SDK-> Builds NGCE
Bash-> Extracts graph properties
Gnuplot-> Plots the  probability distribution function
Pajek-> Visualize the generated graph
Wine-> Executes Pajek under the Linux Operating System
 
Hardware:
Any java-friendly architecture. To build small to  medium sized graphs any
machine capable to support  java should be sufficient. To construct larger
graphs (thousands of nodes) a modest computer is needed. 
Recommend hardware: ATHLON XP 2000+, 512MB RAM


KNOWN ISSUES:
The program works fine for small to medium graphs. To generate large graphs
(many thousand nodes) please add the -Xmx[available memory]M to the ngce and
visual_ngce files in order to increase the pre-allocated memory for the Java
Virtual Machine, otherwise you may experience the OutOfMemory exception. 

ERRATA:
In Random Graph with Fixed Connectivity the Gnuplot shows incorrectly the seed of the first itteration.

DEVELOPMENT
Vasileios Vlachos
Vassiliki Vouzi 

CONTACT
We would like very much to hear your opinion about NGCE. Furthermore we would
appreciate it if you let us know how you used NGCE. Please contact us at vbill
at aueb.gr    
