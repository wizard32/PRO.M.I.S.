/**
 * @author Vasileios Vlachos<br>
 */

/*
   Copyright (C) 2004  Vasileios Vlachos
   
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

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * This class implements a custom algorithm for the creation of random graphs with fixed connectivity.
 */
public class FixedGraph extends Graph {
    /**
     * Stores the maximum number of edges
     */
    public int connectivity;

    /**
     * In this variable is stored the seed number that feeds the random pseudogenerator and allows the creation of
     * reproducible graphs.
     */
    public int graphSeed;


    /**
     * Initialize Random Graph with integer arguments the number of vertices and the number of neighbours each node will
     * have.<br> The algorithm work as follows:<br> 1. Start with a graph with N nodes and no edges<br> 2. Connect each
     * pair of two nodes with probability Per.<br> 3. Pick randomly a vertex v and a vertex u from the pool p, if the
     * pool p has at least two elements, else exit<br> 4. Connect vertex v with vertex u and decrease their available
     * connectivity by one<br> 5. If vertex's v or vertex's u available connectivity is 0, remove it from the pool<br>
     * 6. Repeat step 3<br>
     */

    public FixedGraph(int numOfVertices, int b, int seed) {
        super(numOfVertices);
        int adjVertex;
        double x = 0;
        int[] numOfEdges = new int[numOfVertices];
        Vector<Integer> graphVector = new Vector<Integer>();
        connectivity = b;


        Random random = new Random(seed);
        graphSeed = seed;

        for (int v = 0; v < numOfVertices; v++) {
            graphVector.add(new Integer(v));
        }

        for (int i = 0; i < numOfVertices; i++) {

            while ((numOfEdges[i] < b) && (graphVector.isEmpty() == false)) {
                x = random.nextDouble();
                do {
                    adjVertex = (int) (x * numOfVertices);

                } while (adjVertex >= numOfVertices);


                if ((i == adjVertex) || (numOfEdges[adjVertex] >= b)) {
                    if (numOfEdges[adjVertex] >= b) {
                        for (int v = 0; v < graphVector.size(); v++) {

                            int compInt = ((Integer) graphVector.elementAt(v)).intValue();
                            if (adjVertex == compInt) {
                                graphVector.removeElementAt(v);
                            }
                            if (graphVector.isEmpty() == true) {
                                System.out.println("Graph Vector Empty");
                            }
                        }

                    }

                }
                if ((i != adjVertex) && (adjVertex < numOfVertices) && (numOfEdges[adjVertex] < b) && (super.isAdjacent(i, adjVertex) == false) && (numOfEdges[i] < b)) {
                    super.addEdge(i, adjVertex);
                    if (super.isAdjacent(i, adjVertex)) {
                        numOfEdges[i] = numOfEdges[i] + 1;
                        numOfEdges[adjVertex] = numOfEdges[adjVertex] + 1;
                    }
                    System.out.print("*");

                }

                if (numOfEdges[i] >= b) {
                    for (int v = 0; v < graphVector.size(); v++) {

                        int compInt = ((Integer) graphVector.elementAt(v)).intValue();
                        if (i == compInt) {
                            graphVector.removeElementAt(v);
                        }
                        if (graphVector.isEmpty() == true) {
                            System.out.println("Graph Vector Empty");
                        }
                    }
                    break;
                }
                if (graphVector.size() < b) {
                    //boolean deadlock = false;
                    System.out.println("Graph size= " + graphVector.size());

                    for (int v = 0; v < graphVector.size(); v++) {

                        int compInt = ((Integer) graphVector.elementAt(v)).intValue();
                        //System.out.println("i:= " + i + " and compInt:= " + compInt);
                        if (super.isAdjacent(i, compInt) || (i == compInt)) {
                            //System.out.println("" + i + " adjacent to " + compInt + " " + super.isAdjacent(i, compInt));
                            // deadlock = false;
                        } else {
                            if ((numOfEdges[compInt] < b) && (numOfEdges[i] < b) && (i != compInt)) {
                                super.addEdge(i, compInt);
                                numOfEdges[i] = numOfEdges[i] + 1;
                                numOfEdges[compInt] = numOfEdges[compInt] + 1;
                                if (numOfEdges[i] >= b) {
                                    for (int w = 0; w < graphVector.size(); w++) {

                                        int compW = ((Integer) graphVector.elementAt(w)).intValue();
                                        if (i == compW) {
                                            graphVector.removeElementAt(w);
                                        }
                                        if (graphVector.isEmpty() == true) {
                                            System.out.println("Graph Vector Empty");
                                        }
                                    }
                                    break;
                                }
                                if (numOfEdges[compInt] >= b) {
                                    for (int w = 0; w < graphVector.size(); w++) {

                                        int compW = ((Integer) graphVector.elementAt(w)).intValue();
                                        if (compInt == compW) {
                                            graphVector.removeElementAt(w);
                                        }
                                        if (graphVector.isEmpty() == true) {
                                            System.out.println("Graph Vector Empty");
                                        }
                                    }

                                }
                                // deadlock = true;
                            }
                        }
                    }
                    break;
                }
            }

            System.out.println();
        }

    }

    /**
     * Check adjacency among veritces and print results either on screen or on file
     */
    public void check(int numOfVertices) throws IOException {

        try {
            System.out.println("***WOULD YOU LIKE TO PRINT RESULTS ON SCREEN OR IN A FILE?***");
            System.out.println("**You should type S->for screen and F->for file**");
            String dcs = SimpleIO.readLine();
            if (dcs.equalsIgnoreCase("S")) {

                for (int i = 0; i < numOfVertices; i++) {
                    for (int j = 0; j < numOfVertices; j++) {
                        if (i == j) {
                            continue;
                        } else {
                            System.out.println("[" + i + "," + j + "]=" + super.isAdjacent(i, j));
                        }
                    }
                }
            } else if (dcs.equalsIgnoreCase("F")) {
                String s;
                FileWriter fw;
                System.out.println("****Please give me the name of the file ****");
                String userInput = SimpleIO.readLine();//READING THE NAME OF THE FILE
                fw = new FileWriter(userInput);
                for (int i = 0; i < numOfVertices; i++) {
                    for (int j = 0; j < numOfVertices; j++) {
                        s = null;
                        if (i == j) {
                            continue;
                        } else {
                            s = "[" + i + "," + j + "]=" + super.isAdjacent(i, j);
                        }
                        s = s + '\n';
                        fw.write(s);
                    }
                }
                fw.close();
            } else {
                System.out.println("Your entry was invalid!");
            }

        } catch (IOException e) {
            System.out.println("I/O Exception " + e);
        }
    }


    /**
     * Prints Graph Topology on a text file and is stored in the classpath defined by the topologyFilePath argument.<br>
     * The integer argument numOfVertices is the number of vertices/nodes of the graph.
     */


    public void printTopology(int numOfVertices, String topologyFilePath) throws IOException {
        String s;
        //FileWriter graphToplogy = null;
        topologyFilePath += "GraphTopology.txt";
        FileWriter graphTopology = new FileWriter(topologyFilePath);

        //s = "#Nodes:= " + " " + numOfVertices + "\n";
        s = "#Nodes:= " + numOfVertices + "\n";
        graphTopology.write(s);
        s = "#Class:= " + FixedGraph.class.getName() + "\n";
        graphTopology.write(s);
        s = "#Connectivity:= " + connectivity + "\n";
        graphTopology.write(s);
        s = "#Seed:= " + graphSeed + "\n";
        graphTopology.write(s);
        s = "#Version:= " + BuildGraph.version + "\n";
        graphTopology.write(s);
        graphTopology.close();

        try {
            for (int i = 0; i < numOfVertices; i++) {
                graphTopology = new FileWriter(topologyFilePath, true);
                for (int j = 0; j < numOfVertices; j++) {
                    s = null;
                    if ((i == j) || (super.isAdjacent(i, j) == false)) {
                        continue;
                    } else {
                        s = "" + i + "\t" + j + "";
                        s = s + '\n';
                        graphTopology.write(s);

                    }
                }
                graphTopology.close();
            }
            graphTopology.close();
            System.out.println("Terminating Normally");
            System.out.println("Post Check");
            System.out.println();

            int numOfEdges;
            for (int i = 0; i < numOfVertices; i++) {

                numOfEdges = super.countEdges(i);
                System.out.print("Node " + i + " has " + numOfEdges + " Edges ");
                for (int j = 0; j < numOfEdges; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("I/O Exception " + e);
        }

    } //End of method

} //End of class
