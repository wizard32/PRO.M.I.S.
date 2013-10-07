/*
 * ER.java
 *
 * Created on April 21, 2004, 11:04 AM
 */
/**
 * @author Vasileios Vlachos<br>
 */

/*
    Copyright (C) 2004 Vasileios Vlachos
    
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
import java.text.NumberFormat;
import java.util.Random;

/**
 * This class implements the most widely adopted random graph algorithm, in particular the Erdos-Reny algorithm<br>
 */

public class ERGraph extends Graph {

    /**
     * The total number of connencted edges of the random graph.
     */
    private int numOfConnectedEdges;

    /**
     * The number of nodes that have edges.
     */
    private int numOfActivatedNodes;

    /**
     * The probability Per which connects each pair of nodes of the graph.
     */
    private double probability = 0;

    /**
     * The seed number that feeds the random pseudogenerator and allows the creation of reproducible graphs.
     */
    private int graphSeed;


    /**
     * Creates a new instance of Custom Random by using as arguments the the maximum number of edges, the probability
     * and the number of vertices-nodes the graph will have.<br> The algorithm works as follows:<br> 1. Start with a
     * graph with N nodes and no edges<br> 2. If the number of the connected edges is below the predefined number of
     * connected edges then pick randomly a vertex v and a vertex u and connect them with probability Per ,else exit<br>
     * 3. Increase the number of connected edges and repeat step 1<br>
     */

    public ERGraph(int numOfVertices, double prob, int totalNumOfEdges, int seed) {
        super(numOfVertices);
        int adjVertex;
        double u = 0;
        double v = 0;
        double rnumb = 0;
        int newNode;
        int[] numOfEdges = new int[numOfVertices];
        numOfActivatedNodes = 1;


        System.out.println("Number of Vertices: " + numOfVertices);
        probability = prob;
        System.out.println("Total Number of Edges: " + totalNumOfEdges);
        if (totalNumOfEdges > Math.pow(numOfVertices, 2)) {
            System.out.println("Critical Error: Number of Edges-> " + totalNumOfEdges + " > numOfVertices^2-> " + Math.pow(numOfVertices, 2) + " : impossible");
            System.out.println("Programm Terminated Abnormaly");
            System.exit(-1);
        }
        if (totalNumOfEdges == Math.sqrt(numOfVertices)) {
            System.out.println("Warning: Number of Edges = numOfVertices^2.  You will end with a Homogenous Graph ");
        }

        Random random = new Random(seed);
        graphSeed = seed;


        while (numOfConnectedEdges < totalNumOfEdges) {

            //The node we want to connect to the graph
            u = random.nextDouble();
            newNode = (int) (u * numOfVertices);

            v = random.nextDouble();
            adjVertex = (int) (v * numOfVertices);

            rnumb = random.nextDouble();

            {
                System.out.println("*");
            }
            System.out.println("newNode:= " + newNode);
            System.out.println("adjVertex:= " + adjVertex);
            System.out.println("super.countEdges(newNode):= " + super.countEdges(newNode));
            System.out.println("super.countEdges(adjVertex):= " + super.countEdges(adjVertex));
            System.out.println("numOfConnectedEdges:= " + numOfConnectedEdges);

            if (((newNode == adjVertex) || (adjVertex >= numOfVertices)) && (rnumb > prob)) {
                continue;
            } else {
                super.addEdge(newNode, adjVertex);
                if (super.isAdjacent(newNode, adjVertex)) {
                    numOfEdges[newNode] = numOfEdges[newNode] + 1;
                    numOfEdges[adjVertex] = numOfEdges[adjVertex] + 1;

                    numOfConnectedEdges++;
                    numOfActivatedNodes++;
                    System.out.print(".");
                    System.out.println("PROPER CONNECTION");
                }
            }

        }
    }

    //}


    /**
     * Creates a new instance of ER  by using as arguments the number of vertices/nodes and the probability the graph
     * should have.<br> The algorithm work as follows:<br> 1. Start with a graph with N nodes and no edges<br> 2.
     * Connect each pair of two nodes with probability Per.<br>
     */

    public ERGraph(int numOfVertices, double prob, int seed) {
        super(numOfVertices);
        int adjVertex;
        double Per = 0;
        int newNode;
        int[] numOfEdges = new int[numOfVertices];
        numOfActivatedNodes = 1;

        System.out.println("Number of Vertices: " + numOfVertices);
        probability = prob;


        Random random = new Random(seed);
        graphSeed = seed;

        for (newNode = 0; newNode < numOfVertices; newNode++) {
            for (adjVertex = 0; adjVertex < numOfVertices; adjVertex++) {

                //The node we want to connect to the graph
                Per = random.nextDouble();

                {
                    System.out.println("*");
                }
                System.out.println("Connecting node " + newNode + " (" + super.countEdges(newNode) + " ) edges" + " with node " + adjVertex + " (" + super.countEdges(newNode) + " ) edges" + " with Probability P:= " + Per);
                System.out.println("numOfConnectedEdges:= " + numOfConnectedEdges);
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                /*
                System.out.println("newNode:= " + newNode);
                System.out.println("adjVertex:= " + adjVertex);
                System.out.println("super.countEdges(newNode):= " + super.countEdges(newNode));
                System.out.println("super.countEdges(adjVertex):= " + super.countEdges(adjVertex));
                System.out.println("Per:=" + Per);
                System.out.println("numOfConnectedEdges:= " + numOfConnectedEdges);
                System.out.println("");*/
                if ((newNode == adjVertex) || (adjVertex >= numOfVertices)) {
                    continue;
                } else if (prob >= Per) {
                    super.addEdge(newNode, adjVertex);
                    if (super.isAdjacent(newNode, adjVertex)) {
                        numOfEdges[newNode] = numOfEdges[newNode] + 1;
                        numOfEdges[adjVertex] = numOfEdges[adjVertex] + 1;

                        numOfConnectedEdges++;
                        numOfActivatedNodes++;
                        System.out.print(".");
                        System.out.println("PROPER CONNECTION");
                    }
                }

            }
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
                String userInput = SimpleIO.readLine();
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
        topologyFilePath += "GraphTopology.txt";
        FileWriter graphTopology = new FileWriter(topologyFilePath);

        //s = "#Nodes:= " + " " + numOfVertices + "\n";
        s = "#Nodes:= " + numOfVertices + "\n";
        graphTopology.write(s);

        s = "#Class:= " + ERGraph.class.getName() + "\n";
        graphTopology.write(s);
        s = "#Connected Edges:= " + numOfConnectedEdges + "\n";
        graphTopology.write(s);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(5);
        nf.setMinimumFractionDigits(5);
        s = "#Probability:= " + nf.format(probability) + "\n";
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

    }
}
