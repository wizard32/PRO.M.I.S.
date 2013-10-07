/**
 * @author Vasileios Vlachos<br>
 */

/*Copyright (C) 2004  Vasileios Vlachos

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

/**
 * This Class constructs  BA (Barabasi and Albert) Scale-Free graphs, working with a fixed number of nodes.
 */
public class ScaleFreeGraph extends Graph {

    /**
     * In this variable is stored the total number of connencted edges of the Scale-Free graph.
     */
    public int numOfConnectedEdges;

    /**
     * In this variable is stored the number of nodes that have edges.
     */
    public int numOfActivatedNodes;

    /**
     * In this variable is stored the seed number that feeds the random pseudogenerator and allows the creation of
     * reproducible graphs.
     */
    public int graphSeed;


    /**
     * Creates a new instance of Custom Scale-Free by using as arguments the the maximum number of edges, the number of
     * vertices-nodes the graph will have and the seed.<br> The algorithm works as follows:<br> 1. If the number of
     * edges is less than the predefined preferred number, select randomly a vertex and connect it with probability
     * P(ki) = ki/Ójkj	to the vertex i.<br> 2. Repeat step 1 <br>
     */


    public ScaleFreeGraph(int numOfVertices, int totalNumOfEdges, int seed) {
        super(numOfVertices);
        int adjVertex;
        double x = 0;
        double i = 0;
        double P = 0;
        double prandom = 0;
        int newNode;
        int[] numOfEdges = new int[numOfVertices];
        boolean connected = false;
        numOfActivatedNodes = 1;

        System.out.println("Number of Vertices: " + numOfVertices);
        System.out.println("Total Number of Edges: " + totalNumOfEdges);

        {

            Random random = new Random(seed);
            graphSeed = seed;

            x = random.nextDouble();

            while (numOfConnectedEdges < totalNumOfEdges) {
                if (connected) {

                    x = random.nextDouble();
                    connected = false;
                }

                //The node we want to connect to the graph
                //x = random.nextDouble();
                newNode = (int) (x * numOfVertices);
                i = random.nextDouble();
                adjVertex = (int) (i * numOfVertices);


                if (adjVertex >= numOfVertices) {
                    System.out.println("Critical Error: Target exit number of vertices");
                    System.exit(-1);
                } else if (newNode == adjVertex) {
                    System.out.println("Warning: Target and source  node are the same");
                } else if (numOfConnectedEdges == 0) {
                    super.addEdge(newNode, adjVertex);
                    if (super.isAdjacent(newNode, adjVertex)) {

                        numOfEdges[newNode] = numOfEdges[newNode] + 1;
                        numOfEdges[adjVertex] = numOfEdges[adjVertex] + 1;

                        numOfConnectedEdges += 2;
                        numOfActivatedNodes++;

                        System.out.print("INITIAL CONNECTION");
                        connected = true;
                    }
                }
                //We make a slight modification to the algorithm. Instead of using
                //total number of edges we limit ourselfes to the maximum number of possible
                // connections = numOfVertices
                if (super.countEdges(adjVertex) > 0) {
                    P = (float) (super.countEdges(adjVertex)) / (numOfConnectedEdges);
                    prandom = random.nextDouble();

                    {
                        System.out.println("*");
                    }
                    System.out.println("Connecting node " + newNode + " (" + super.countEdges(newNode) + " ) edges" + " with node " + adjVertex + " (" + super.countEdges(adjVertex) + " ) edges" + " with Probability P:= " + P); //+" where prandom is "+prandom
                    System.out.println("numOfConnectedEdges:= " + numOfConnectedEdges);

                    if ((newNode == adjVertex) || (adjVertex >= numOfVertices) || (P < prandom) || (connected == true)) {
                        continue;
                    } else if ((newNode == adjVertex) || (adjVertex >= numOfVertices)) {
                        System.out.println("Critical Error: Target and source  node are the same");
                        System.exit(-1);
                    } else if (super.isAdjacent(newNode, adjVertex) == true) {
                        System.out.println("Warning: Already connected nodes");
                        //System.exit(-1);
                        continue;
                    } else if ((P > prandom)) {
                        super.addEdge(newNode, adjVertex);
                        if (super.isAdjacent(newNode, adjVertex)) {
                            numOfEdges[newNode] = numOfEdges[newNode] + 1;
                            numOfEdges[adjVertex] = numOfEdges[adjVertex] + 1;

                            numOfConnectedEdges++;
                            numOfActivatedNodes++;
                            connected = true;
                            System.out.print(".");
                            System.out.println("PROPER CONNECTION");
                        }
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
        s = "#Nodes:= " + numOfVertices + "\n";
        graphTopology.write(s);
        s = "#ConnectedEdges:= " + numOfConnectedEdges + "\n";
        graphTopology.write(s);
        s = "#Class:= " + ScaleFreeGraph.class.getName() + "\n";
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
                        // fw.write(s);
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

}
