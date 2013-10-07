/**
 * @author Vasileios Vlachos<br>
 * @author Vassiliki Vouzi<br>
 */

/*
Copyright (C) Vasileios Vlachos, Vassiliki Vouzi
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
with the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
of the Software, and to permit persons to whom the Software is furnished to do
so, subject to the following conditions:

    * Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimers.
    * Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimers in the documentation
and/or other materials provided with the distribution.
    * Neither the names of <Name of Development Group, Name of Institution>, nor
the names of its contributors may be used to endorse or promote products derived
from this Software without specific prior written permission.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE CONTRIBUTORS
OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS WITH THE SOFTWARE.
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


/**
 * This program uses the various graph classes (ERGraph, FixedGraph, FromFile, Homogenous, Scale-free) to implement
 * different types of graphs using an already existing text file named config or a program-user dialog through
 * Command-Prompt.
 */
public class BuildGraph extends Thread {

    /**
     * The number of vertices/nodes is stored .
     */
    protected static int numOfVertices = 0;

    /**
     * The classpath in which user files will be stored.
     */
    protected static String classpath = "../";

    /**
     * The graph degree which is used only for random graphs with fixed connectivity.
     */
    protected static int neighbors = 0;

    /**
     * The number of edges which is used only for custom random and scale-free graphs .
     */
    protected static int numOfEdges = 0;

    /**
     * The probability which is used for building random graphs Erdos Reny and Custom.
     */
    protected static int m = 0;

    protected static int numOfInitNodes = 0;

    protected static double prob = 0;

    /**
     * The graph topology model which will be simulated.
     */
    protected static String typeOfmodel = null;

    /**
     * The seed number that feeds the random pseudogenerator and allows the creation of reproducible graphs.
     */
    protected static int seed = 0;

    /**
     * The path and name of the pre-preapred graph text file.
     */
    protected static String ffpath = null;

    /**
     * The variable takes the value true when the config.txt file is used for inputs.<br> If the program does not find
     * the text file (config==false) opens a dialog through command prompt.
     */
    private static boolean config = false;

    /**
     * Specifies whether the user wants to post-process the Random graph with Fixed Connectivity (postPrcs==true) or not
     * (postPrcs==false).
     */
    protected static boolean postPrcs = false;

    /**
     * Specifies whether the user wants to post-process the graph (analyze=true) or not (analyze=false).
     */
    protected static boolean analyze = false;

    public static double version = 2.00;

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                numOfVertices = Integer.parseInt(args[0].substring(1, args[0].length()));
                System.out.println(numOfVertices);
                typeOfmodel = args[1].substring(1, args[1].length());
                System.out.println(typeOfmodel);
                if (typeOfmodel.equalsIgnoreCase("f")) {
                    neighbors = Integer.parseInt(args[2].substring(1, args[2].length()));
                    postPrcs = true;
                } else if (typeOfmodel.equalsIgnoreCase("e")) {
                    prob = Double.parseDouble(args[2].substring(1, args[2].length()));
                    seed = Integer.parseInt(args[3].substring(1, args[3].length()));
                } else if (typeOfmodel.equalsIgnoreCase("s")) {
                    seed = Integer.parseInt(args[2].substring(1, args[2].length()));
                } else if (typeOfmodel.equalsIgnoreCase("m")) {
                    numOfEdges = Integer.parseInt(args[2].substring(1, args[2].length()));
                    seed = Integer.parseInt(args[3].substring(1, args[3].length()));
                } else if (typeOfmodel.equalsIgnoreCase("c")) {
                    prob = Double.parseDouble(args[2].substring(1, args[2].length()));
                    numOfEdges = Integer.parseInt(args[3].substring(1, args[3].length()));
                    seed = Integer.parseInt(args[4].substring(1, args[4].length()));
                } else if (typeOfmodel.equalsIgnoreCase("a")) {
                    m = Integer.parseInt(args[2].substring(1, args[2].length()));
                    numOfInitNodes = Integer.parseInt(args[3].substring(1, args[3].length()));
                    seed = Integer.parseInt(args[4].substring(1, args[4].length()));
                } else if (typeOfmodel.equalsIgnoreCase("b")) {
                    ffpath = args[2].substring(1, args[2].length());
                } else if (typeOfmodel.equalsIgnoreCase("x")) {
                    neighbors = Integer.parseInt(args[2].substring(1, args[2].length()));
                    postPrcs = false;
                }

                analyze = true;
                buildingGraph();
            } catch (Exception e) {
                printGuide();
            }
        } else if (args.length > 4) {
            printGuide();
        } else if (args.length <= 0) {
            fileParsing();
        }
    }

    /**
     * Parses config.txt to build the graph if no input is entered
     */

    protected static void fileParsing() {
        try {
            FileReader f = null;
            BufferedReader br = null;
            StringTokenizer st;
            String s;
            int lineCounter = 0;

            f = new FileReader("../bin/config.txt");
            br = new BufferedReader(f);

            while ((s = br.readLine()) != null) {
                ++lineCounter;
                st = new StringTokenizer(s);
                if (lineCounter == 1) {
                    BuildGraph.classpath = st.nextToken();
                    while (st.hasMoreTokens()) {
                        BuildGraph.classpath += st.nextToken();
                    }
                } else if (lineCounter == 2) {
                    setNumOfVertices(Integer.parseInt(st.nextToken()));
                } else if (lineCounter == 3) {
                    BuildGraph.typeOfmodel = st.nextToken();
                } else if ((lineCounter == 4) && ((typeOfmodel.equals("f")) || (typeOfmodel.equals("x")))) //||/*(typeOfmodel.equals("s"))||*/(typeOfmodel.equals("m")))))
                {
                    neighbors = Integer.parseInt(st.nextToken());
                } else if ((lineCounter == 4) && ((typeOfmodel.equals("e")) || (typeOfmodel.equals("c")))) {
                    prob = Double.parseDouble(st.nextToken());
                } else if ((lineCounter == 4) && (typeOfmodel.equals("s"))) {
                    seed = Integer.parseInt(st.nextToken());
                } else if ((lineCounter == 4) && (typeOfmodel.equals("m"))) {
                    numOfEdges = Integer.parseInt(st.nextToken());
                } else if ((lineCounter == 4) && (typeOfmodel.equals("b"))) {
                    BuildGraph.ffpath = st.nextToken();
                } else if ((lineCounter == 4) && (typeOfmodel.equals("a"))) {
                    m = Integer.parseInt(st.nextToken());
                }

                if ((lineCounter == 5) && (typeOfmodel.equals("e"))) {
                    seed = Integer.parseInt(st.nextToken());
                } else if ((lineCounter == 5) && (typeOfmodel.equals("c"))) {
                    numOfEdges = Integer.parseInt(st.nextToken());
                } else if ((lineCounter == 5) && (typeOfmodel.equals("m"))) {
                    seed = Integer.parseInt(st.nextToken());
                } else if ((lineCounter == 5) && ((typeOfmodel.equals("f")) || (typeOfmodel.equals("x")))) {
                    seed = Integer.parseInt(st.nextToken());
                } else if ((lineCounter == 5) && (typeOfmodel.equals("b"))) {
                    seed = Integer.parseInt(st.nextToken());
                } else if ((lineCounter == 5) && (typeOfmodel.equals("a"))) {
                    numOfInitNodes = Integer.parseInt(st.nextToken());
                }

                if ((lineCounter == 6) && ((typeOfmodel.equals("c")) || (typeOfmodel.equals("a")))) {
                    seed = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("File Parsed");
            config = true;

            f.close();
            br.close();

            postPrcs = true;
            analyze = true;

            buildingGraph();
            System.out.println("Program terminated normaly");
        } catch (FileNotFoundException err) {
            printGuide();
        } catch (IOException e) {
            System.err.println("I/O Exception" + e);
        }
    }


    /**
     * Builds the graph topology the user requested.
     */
    protected static void buildingGraph() {
        try {
            if (BuildGraph.typeOfmodel.equalsIgnoreCase("H")) {

                Homogenous homoGraph = new Homogenous(BuildGraph.getNumOfVertices());
                //ONLY FOR DEBUG:
                // System.out.println("***Checking Adjacency Among Vertices***");
                // homoGraph.check(BuildGraph.getNumOfVertices());
                homoGraph.printTopology(BuildGraph.getNumOfVertices(), BuildGraph.classpath);

            } else if (BuildGraph.typeOfmodel.equalsIgnoreCase("F")) {
                if (neighbors == 0) {
                    System.out.println("Please enter number of neighbors: ");
                    String tmp = SimpleIO.readLine();
                    neighbors = Integer.parseInt(tmp);
                }
                System.out.println("Generating new Fixed Graph - Please wait");
                FixedGraph fixedGraph = new FixedGraph(BuildGraph.getNumOfVertices(), neighbors, seed);
                fixedGraph.printTopology(BuildGraph.getNumOfVertices(), BuildGraph.classpath);
            } else if (BuildGraph.typeOfmodel.equalsIgnoreCase("X")) {
                if (neighbors == 0) {
                    System.out.println("Please enter number of neighbors: ");
                    String tmp = SimpleIO.readLine();
                    neighbors = Integer.parseInt(tmp);
                }
                System.out.println("Generating new Fixed Graph - Please wait");
                FixedGraph fixedGraph = new FixedGraph(BuildGraph.getNumOfVertices(), neighbors, seed);
                fixedGraph.printTopology(BuildGraph.getNumOfVertices(), BuildGraph.classpath);

            } else if (BuildGraph.typeOfmodel.equalsIgnoreCase("S")) {
                numOfEdges = BuildGraph.getNumOfVertices();
                System.out.println("Generating new ScaleFree Graph - Please wait");
                ScaleFreeGraph scaleFreeGraph = new ScaleFreeGraph(BuildGraph.getNumOfVertices(), numOfEdges, seed);
                scaleFreeGraph.printTopology(BuildGraph.getNumOfVertices(), BuildGraph.classpath);

            } else if (BuildGraph.typeOfmodel.equalsIgnoreCase("A")) {
                numOfEdges = BuildGraph.getNumOfVertices();
                System.out.println("Generating new FullScaleFree Graph - Please wait");
                FullScaleFreeGraph fullScaleFreeGraph = new FullScaleFreeGraph(BuildGraph.getNumOfVertices(), m, numOfInitNodes, seed);
                fullScaleFreeGraph.printTopology(BuildGraph.getNumOfVertices(), BuildGraph.classpath);

            } else if (BuildGraph.typeOfmodel.equalsIgnoreCase("M")) {
                if (numOfEdges == 0) {
                    System.out.println("Setting Default");
                    numOfEdges = BuildGraph.getNumOfVertices();
                }
                System.out.println("Generating new Mixed ScaleFree Graph - Please wait");
                ScaleFreeGraph scaleFreeGraph = new ScaleFreeGraph(BuildGraph.getNumOfVertices(), numOfEdges, seed);
                scaleFreeGraph.printTopology(BuildGraph.getNumOfVertices(), BuildGraph.classpath);

            } else if (BuildGraph.typeOfmodel.equalsIgnoreCase("E")) {
                if ((numOfEdges == 0) && (prob == 0)) {
                    System.err.println("Missing Argument-Exiting");
                }
                System.out.println("Generating new ER Graph - Please wait");
                ERGraph erGraph = new ERGraph(BuildGraph.getNumOfVertices(), prob, seed);
                erGraph.printTopology(BuildGraph.getNumOfVertices(), BuildGraph.classpath);
            } else if (BuildGraph.typeOfmodel.equalsIgnoreCase("C")) {
                if ((numOfEdges == 0) || (prob == 0)) {
                    System.err.println("Missing Argument-Exiting");

                }
                System.out.println("Generating new ER Graph - Please wait");
                ERGraph erGraph = new ERGraph(BuildGraph.getNumOfVertices(), prob, numOfEdges, seed);
                erGraph.printTopology(BuildGraph.getNumOfVertices(), BuildGraph.classpath);
            } else if (BuildGraph.typeOfmodel.equalsIgnoreCase("b")) {
                if ((ffpath == null) || (ffpath.length() == 0)) {
                    System.err.println("Missing Argument-Exiting");

                }

                try {
                    Runtime rt = Runtime.getRuntime();
                    rt.exec("cp " + ffpath + " " + classpath + "GraphTopology.txt");
                } catch (Exception ie) {
                    System.err.println("Problem " + ie);
                }

                System.out.println("Generating new From File Graph - Please wait");

            } else {
                System.out.println("\nYOUR ENTRY WAS INVALID!");
                System.out.println("**THIS TYPE OF MODEL DOES NOT EXIST**\n" + "**PLEASE TRY AGAIN**\n");
                if (config == false) {
                    BuildGraph.printGuide();
                }
            }


        } catch (IOException e) {
            System.out.println("Error\t" + e);
        }
    }

    /**
     * Sets the number of vertices of the graph we generate.
     */
    protected static void setNumOfVertices(int inf) {
        BuildGraph.numOfVertices = inf;
    }

    /**
     * Returns the number of vertices of the graph we generate.
     */
    protected static int getNumOfVertices() {
        return (BuildGraph.numOfVertices);
    }

    /**
     * Prints on Screen the User Guide.
     */
    protected static void printGuide() {
        try {
            FileReader f = null;
            BufferedReader br = null;
            StringTokenizer st;
            String s;
            f = new FileReader("../guide.txt");
            br = new BufferedReader(f);

            while ((s = br.readLine()) != null) {
                st = new StringTokenizer(s);
                while (st.hasMoreTokens()) {
                    System.out.print(st.nextToken() + " ");
                }
                System.out.println("");
            }

        } catch (Exception e) {
            System.out.println("NO USER GUIDE FOUND.Please see README.txt for further information.");
        }
    }
}