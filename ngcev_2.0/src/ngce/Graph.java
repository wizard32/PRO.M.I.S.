/**
 * @author Liwu Li<br>
 */

/*
 From: Java Structures and Programming, by Liwu Li
 Copyright (C) 1998 Liwu Li
 All rights reserved
 
*/

/**
 * Class Graph for undirected, unweighted graphs. Vertex indexes are 0,1,...,vertexNumber-1. Edges are represented in a
 * 2-dimensional boolean array.
 */

public
class Graph {


    /**
     * Creates a new instance of Graph by using as argument the number of vertices.
     */
    public
    Graph(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        vertices = new Node[vertexNumber];
        adjacencyMatrix = new boolean[vertexNumber][vertexNumber];

        for (int i = 0; i < vertexNumber; i++) {
            Node vertex = new Node(i);
            vertices[i] = vertex;
        }
    }

    /**
     * Creates a new instance of Graph by using as argument a metrix of Nodes.
     */
    public
    Graph(Node[] vertices) {
        this(vertices.length);
        for (int i = 0; i < vertexNumber; i++) {
            this.vertices[i] = vertices[i];
        }
    }

    /**
     * Returns the Node with the specified index
     */
    public
    Node getVertex(int index) {
        return vertices[index];
    }

    /**
     * Sets a node at a specified position of the Nodes matrix
     */
    public
    void setVertex(Node vertex, int index) {
        vertices[index] = vertex;
    }

    /**
     * Checks adjacency between vertices by using as arguments Node objects.
     */
    public
    boolean isAdjacent(Node vertex1, Node vertex2) {
        int index1 = indexOf(vertex1), index2 = indexOf(vertex2);
        return isAdjacent(index1, index2);
    }

    /**
     * Checks adjacency between veritces by using as arguments the indices of the Node objects in the Node matrix.
     */
    public
    boolean isAdjacent(int index1, int index2) {
        if (index1 < 0 || index1 >= vertexNumber || index2 < 0 || index2 >= vertexNumber) {
            return false;
        }
        return adjacencyMatrix[index1][index2];
    }

    /**
     * Adds an edge between two vertices by using as arguments the Node objects
     */
    public
    void addEdge(Node vertex1, Node vertex2) {
        int index1 = indexOf(vertex1), index2 = indexOf(vertex2);
        addEdge(index1, index2);
    }

    /**
     * Adds an edge between two vertices by using as arguments the indices of the Node objects in the Node matrix.
     */
    public
    void addEdge(int index1, int index2) {
        if (index1 < 0 || index1 >= vertexNumber || index2 < 0 || index2 >= vertexNumber) {
            return;
        }
        adjacencyMatrix[index1][index2] = adjacencyMatrix[index2][index1] = true;

        Node node1 = this.getVertex(index1);
        Node node2 = this.getVertex(index2);

        if (index1 < 0 || index1 >= vertexNumber || index2 < 0 || index2 >= vertexNumber || (isAdjacent(index1, index2) == false)) {
            System.out.println("Error");
        } else {
            node1.addEdge();
            node2.addEdge();
        }


    }

    /**
     * Removes an existing edge between two vetricesby using as arguments the Node objects
     */
    public
    void removeEdge(Node vertex1, Node vertex2) {
        int index1 = indexOf(vertex1), index2 = indexOf(vertex2);
        removeEdge(index1, index2);
    }

    /**
     * Removes an existing edge between two vetrices by using as arguments the indices of the Node objects in the Node
     * matrix.
     */
    public
    void removeEdge(int index1, int index2) {
        if (index1 < 0 || index1 >= vertexNumber || index2 < 0 || index2 >= vertexNumber) {
            return;
        }
        adjacencyMatrix[index1][index2] = adjacencyMatrix[index2][index1] = false;
    }

    /**
     * Returns the index of the requested vertex.
     */
    protected
    int indexOf(Node vertex) {
        for (int i = 0; i < vertexNumber; i++) {
            if (vertex == vertices[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Counts the number of the edges of the specific node
     */
    public
    int countEdges(int index) {

        Node n = this.getVertex(index);
        int numEdges = n.getNumOfEdges();
        return (numEdges);
    }


    /**
     * The matrix which describes the connectivity of the graph.
     */
    protected
    boolean[][] adjacencyMatrix;

    /**
     * The matrix of vertices of the graph
     */
    protected
    Node[] vertices;

    /**
     * The total number of vertices of the graph.
     */
    protected
    int vertexNumber;
}
