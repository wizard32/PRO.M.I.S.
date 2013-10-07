/**
 * @author Vasileios Vlachos<br>
 * @author Vassiliki Vouzi<br>
 */

/*Copyright (C) 2004  Vasileios Vlachos, Vassiliki Vouzi

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

/**
 * This class creates Vertices and stores their state(healthy,infected suscptible) in a boolean array(state),in order to
 * keep track of their state during the simulation.
 */
public class Node {

    /**
     * Stores the number of edges of the Node object.
     */
    protected int numOfEdges = 0;

    /**
     * Stores the maximum number of edges of the Node object
     */
    protected int connectivity;

    /**
     * Constructor of the node. Each node is by default initialized as healthy and susceptible
     */
    Node(int index) {
        int name = index;
        numOfEdges = 0;

    }

    /**
     * Constructor of the node. Each node is by default initialized as susceptible and not immune (removed)
     */
    Node(String vertex) {
        String name = vertex;
        numOfEdges = 0;

    }

    /**
     * Returns the number of edges of a node.
     */
    protected int getNumOfEdges() {
        return numOfEdges;
    }


    /**
     * Increases the number of edges of this node by one.
     */
    protected void addEdge() {
        numOfEdges++;
    }

    /**
     * Set the maximum allowed number of edges for this node.
     */
    protected void setConnectivity(int maxEdges) {
        connectivity = maxEdges;
    }

    /**
     * Get the maximum allowed number of edges for this node.
     */
    protected int getConnectivity() {
        return (connectivity);
    }

}