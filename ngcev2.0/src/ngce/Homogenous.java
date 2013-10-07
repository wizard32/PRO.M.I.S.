/**
 * @author Vasileios Vlachos<br>
 * @author Vassiliki Vouzi<br>
 */

/*
    Copyright (C) 2004 Vasileios Vlachos, Vassiliki Vouzi
    
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


import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This Class creates Homogenous graphs and provides a checking method in order to check whether our graph topology is
 * correct or not.
 */
public
class Homogenous extends Graph {


    /**
     * Initialize Homogenous graph with argument the number Of Vertices.<br> The algorithm works as follows:<br> 1.
     * Start with N vertices and no edges<br> 2. Connect each vertex with all of the other vertices<br>
     */
    public
    Homogenous(int numOfVertices) {
        super(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            for (int j = 0; j < numOfVertices; j++) {
                if (i == j) {
                    continue;
                } else {
                    super.addEdge(i, j);
                }
            }
        }

    }

    /**
     * Check adjacency among veritces and print results either on screen or on file
     */
    public
    void check(int numOfVertices) throws IOException {

        try {
            System.out.println("***WOULD YOU LIKE TO PRINT RESULTS ON SCREEN ?***");
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
                System.out.println("****Please give me the name of the file ****");
                String userInput = SimpleIO.readLine();//READING THE NAME OF THE FILE
                FileOutputStream f = new FileOutputStream(userInput);//SAVING THE NAME OF THE FILE
                DataOutputStream df = new DataOutputStream(f);
                for (int i = 0; i < numOfVertices; i++) {
                    for (int j = 0; j < numOfVertices; j++) {
                        s = null;
                        if (i == j) {
                            continue;
                        } else {
                            s = "[" + i + "," + j + "]=" + super.isAdjacent(i, j);
                        }
                        s = s + '\n';
                        df.writeChars(s);
                    }
                }
                df.close();
                f.close();

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
    public
    void printTopology(int numOfVertices, String topologyFilePath) throws IOException {


        try {
            String s;
            //FileWriter graphToplogy = null;


            topologyFilePath += "GraphTopology.txt";
            FileWriter graphTopology = new FileWriter(topologyFilePath);

            s = "#Nodes:= " + numOfVertices + "\n";
            graphTopology.write(s);
            s = "#Class:= " + Homogenous.class.getName() + "\n";
            graphTopology.write(s);
            s = "#Version:= " + BuildGraph.version + "\n";
            graphTopology.write(s);
            graphTopology.close();

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

        } catch (IOException e) {
            System.out.println("I/O Exception " + e);
        }
    }
}