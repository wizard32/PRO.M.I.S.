/*//////////////////////////////////////////////////
**  Class Graph for undirected, unweighted graphs.     **
**  Vertex indexes are 0,1,...,vertexNumber-1.            **
**  Edges are represented in a 2-dimensional              **
**  boolean array.                                                   **
//////////////////////////////////////////////////*/

public class Graph {


    public Graph() {
    }

    public Graph(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        vertices = new Node[vertexNumber];
        adjacencyMatrix = new boolean[vertexNumber][vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            Node vertex = new Node(i);
            vertices[i] = vertex;
        }
    }

    public Graph(Node[] vertices) {
        this(vertices.length);
        for (int i = 0; i < vertexNumber; i++)
            this.vertices[i] = vertices[i];
    }

    public Node getVertex(int index) {
        return vertices[index];
    }

    public void setVertex(Node vertex, int index) {
        vertices[index] = vertex;
    }

    public boolean isAdjacent(Node vertex1, Node vertex2) {
        int index1 = indexOf(vertex1), index2 = indexOf(vertex2);
        return isAdjacent(index1, index2);
    }

    public boolean isAdjacent(int index1, int index2) {
        if (index1 < 0 || index1 >= vertexNumber
            || index2 < 0 || index2 >= vertexNumber)
            return false;
        return adjacencyMatrix[index1][index2];
    }

    public void addEdge(Node vertex1, Node vertex2) {
        int index1 = indexOf(vertex1), index2 = indexOf(vertex2);
        addEdge(index1, index2);
    }

    public void addEdge(int index1, int index2) {
        if (index1 < 0 || index1 >= vertexNumber
            || index2 < 0 || index2 >= vertexNumber)
            return;
        adjacencyMatrix[index1][index2] =
        adjacencyMatrix[index2][index1] = true;
    }

    public void removeEdge(Node vertex1, Node vertex2) {
        int index1 = indexOf(vertex1), index2 = indexOf(vertex2);
        removeEdge(index1, index2);
    }

    public void removeEdge(int index1, int index2) {
        if (index1 < 0 || index1 >= vertexNumber
            || index2 < 0 || index2 >= vertexNumber)
            return;
        adjacencyMatrix[index1][index2] =
        adjacencyMatrix[index2][index1] = false;
    }

    protected int indexOf(Node vertex) {
        for (int i = 0; i < vertexNumber; i++)
            if (vertex == vertices[i]) return i;
        return -1;
    }

    protected boolean[][] adjacencyMatrix;
    protected Node[] vertices;
    protected int vertexNumber;
}
