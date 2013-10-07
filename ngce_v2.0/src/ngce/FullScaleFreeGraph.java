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
import java.util.Vector;

/**
 * This Class constructs  a full model BA (Barabasi and Albert) FullScale-Free graphs.
 */
public class FullScaleFreeGraph extends Graph {

    /**
     * In this variable is stored the total number of connencted edges of the FullScale-Free graph.
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
     * In this variable is stored the initial number of nodes of the FullScale-Free graph.
     */
    public int numOfInitialNodes;

    /**
     * In this variable is stored the initial number of local connections of each node of the FullScale-Free graph.
     */
    public int mEdges;


    /**
     * Creates a new instance of Custom Scale-Free by using as arguments the the maximum number of edges, the number of
     * vertices-nodes the graph will have and the seed.<br> The algorithm works as follows:<br> 1. If the number of
     * edges is less than the predefined preferred number, select randomly a vertex and connect it with probability
     * P(ki) = ki/Sum(jkj)	to the vertex i.<br> 2. Repeat step 1 <br> ======= Creates a new instance of Full Scale-Free by
     * using as arguments the number of vertices-nodes the graph will have, the seed the number oh initial nodes and the
     * initial number of local connections of each node.<br> The algorithm is constituted by two major parts. For the
     * first mo nodes:<br> 1. Create a pool L of all nodes and a pool K with mo initial nodes<br> 2. Remove randomly a
     * node l from the pool L and connect it to a randomly chosen node from the pool K <br> 3. Add node l to the pool
     * K<br> And for the rest of the nodes, we follow closely the BA's algorithm<br> 1. Remove randomly a node i from
     * the pool L <br> 2. Select randomly a vertex from the pool K and connect it with probability<br> * P(ki) =
     * ki/Sum(jkj)	to the vertex i<br> 3. Add i to the pool K.<br>
     */


    public FullScaleFreeGraph(int numOfVertices, int m, int initialNodes, int seed) {
        super(numOfVertices);
        double x = 0, y = 0;
        //double i = 0;
        double P = 0;
        int adjVertex = 0, newNode = 0, initNodes = 0, nodeIndex1 = 0, nodeIndex2 = 0, localConnections = 0, faults = 0;
        int[] numOfEdges = new int[numOfVertices];
        numOfActivatedNodes = 1;
        Vector<Integer> graphVector = new Vector<Integer>();
        Vector<Integer> deployVector = new Vector<Integer>();
        numOfInitialNodes = initialNodes;
        mEdges = m;
        int misses = 0;

        System.out.println("Number of Vertices: " + numOfVertices);
        System.out.println("Initial nodes: " + initialNodes);

        Random random = new Random(seed);
        graphSeed = seed;


        //Add everything (all nodes) to a new vector [graphVector].
        for (int v = 0; v < numOfVertices; v++) {
            graphVector.add(new Integer(v));
        }

        //Get the initial nodes and perform some initial connection
        //deployVector should be empty at the bigging
        while (initNodes < initialNodes)
                //while (deployVector.size()<=initialNodes)
        {
            //Get randomly a node from the available
            x = random.nextDouble();
            if (graphVector.isEmpty() == false) {
                nodeIndex1 = (int) (x * (graphVector.size() - 1));
                adjVertex = ((Integer) graphVector.elementAt(nodeIndex1)).intValue();

                y = random.nextDouble();

                if (initNodes == 0) {
                    //Get  another one node from the available.
                    //Rember this is the very first connecting
                    nodeIndex2 = (int) (y * (graphVector.size() - 1));
                    newNode = ((Integer) graphVector.elementAt(nodeIndex2)).intValue();
                } else {
                    if (deployVector.isEmpty() == false) {
                        nodeIndex2 = (int) (y * (deployVector.size() - 1));
                        newNode = ((Integer) deployVector.elementAt(nodeIndex2)).intValue();
                    }
                }
            }
            //Check to avoid if everything is ok
            if ((newNode == adjVertex) || (adjVertex >= numOfVertices) || (super.isAdjacent(newNode, adjVertex))) {
                continue;
            } else {
                super.addEdge(newNode, adjVertex);
                if (super.isAdjacent(newNode, adjVertex)) {
                    numOfEdges[newNode] = numOfEdges[newNode] + 1;
                    numOfEdges[adjVertex] = numOfEdges[adjVertex] + 1;

                    numOfConnectedEdges++;
                    numOfActivatedNodes++;

                    System.out.println("Connecting node " + newNode + " (" + super.countEdges(newNode) + " ) edges" + " with node " + adjVertex + " (" + super.countEdges(newNode) + " ) edges");
                    System.out.println("Number Of Connected Nodes:= " + initNodes);
                    System.out.println("----------------------------------------------------------------------------------------------------------------");

                    //Connect the first two nodes
                    if (initNodes == 0) {

                        System.out.println("CONNECTING THE VERY FIRST NODES");
                        System.out.println("graphVector size:= " + graphVector.size());

                        Integer tInteger = new Integer(adjVertex);
                        graphVector.removeElement(tInteger);

                        Integer tInteger2 = new Integer(nodeIndex2);
                        graphVector.removeElement(tInteger2);
                        System.out.println("graphVector size:= " + graphVector.size());

                        deployVector.addElement(new Integer(adjVertex));
                        deployVector.addElement(new Integer(newNode));

                        initNodes += 2;
                    } else {
                        initNodes++;
                        graphVector.removeElementAt(nodeIndex1);
                        deployVector.addElement(new Integer(adjVertex));
                        System.out.println("graphVector size:= " + graphVector.size());
                    }

                    System.out.print(".");
                }
            }

        }

        //After connecting the first initial mo nodes go for the rest
        //while (initNodes<=numOfVertices)
        while (graphVector.isEmpty() == false) {
            x = random.nextDouble();
            nodeIndex1 = (int) (x * (graphVector.size() - 1));
            adjVertex = ((Integer) graphVector.elementAt(nodeIndex1)).intValue();
            if (graphVector.size() == 1) {
                System.out.println("Warning:Graph Vector is empty");
            }
            //for (int i=0;i<m;i++)
            localConnections = 0;
            misses = 0;

            //Connect the selected node to m other nodes
            while (localConnections < m) {
                y = random.nextDouble();
                if (deployVector.isEmpty() == false) {
                    nodeIndex2 = (int) (y * (deployVector.size() - 1));
                    newNode = ((Integer) deployVector.elementAt(nodeIndex2)).intValue();

                    if (super.countEdges(newNode) == 0) continue;

                    P = (float) (super.countEdges(newNode)) / (numOfConnectedEdges);

                    System.out.println("Connecting node " + newNode + " (" + super.countEdges(newNode) + " ) edges" + " with node " + adjVertex + " (" + super.countEdges(newNode) + " ) edges" + " with Probability P:= " + P);
                    System.out.println("Number Of Connected Nodes:= " + initNodes);
                    System.out.println("m:= " + m + " Local Connections:= " + localConnections);
                    System.out.println("----------------------------------------------------------------------------------------------------------------");
                    if ((newNode == adjVertex) || (adjVertex >= numOfVertices)) {
                        System.out.println("Critical Error: Target and source node are the same or target node out of Vector limits");
                        System.exit(-1);
                    } else if (super.isAdjacent(newNode, adjVertex) == true) {
                        System.out.println("Warning: Already connected nodes");
                        //System.exit(-1);
                        misses++;
                        if (misses == (2 * deployVector.size())) {
                            System.out.println("Braking out");
                            break;
                        }
                    } else if ((P > random.nextDouble())) {
                        super.addEdge(newNode, adjVertex);
                        if (super.isAdjacent(newNode, adjVertex)) {
                            numOfEdges[newNode] = numOfEdges[newNode] + 1;
                            numOfEdges[adjVertex] = numOfEdges[adjVertex] + 1;

                            numOfConnectedEdges++;
                            numOfActivatedNodes++;

                            localConnections++;
                            //initNodes++;

                            //graphVector.removeElementAt(nodeIndex1);
                            // deployVector.addElement(new Integer(adjVertex));
                        }
                        System.out.print(".");
                        System.out.println("PROPER CONNECTION");
                    }
                }
            }

            if (localConnections < m) {
                System.out.println("ALARM-ALARM");
                faults++;
            }
            initNodes++;
            graphVector.removeElementAt(nodeIndex1);
            System.out.println("Removing NODE" + nodeIndex1 + " which is " + adjVertex + " nodeIndex2:= " + nodeIndex2);
            deployVector.addElement(new Integer(adjVertex));
            System.out.println("graphVector size:= " + graphVector.size());
        }
        System.out.println("Faults:= " + faults);
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
        s = "#Nodes:=" + " " + numOfVertices + "\n";
        graphTopology.write(s);
        //s = "#Initial Nodes:= " + numOfInitialNodes + "\n";
        s = "#Initial:= " + numOfInitialNodes + "\n";
        graphTopology.write(s);
        s = "#m:= " + mEdges + "\n";
        graphTopology.write(s);
        s = "#Class:= " + FullScaleFreeGraph.class.getName() + "\n";
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

}
