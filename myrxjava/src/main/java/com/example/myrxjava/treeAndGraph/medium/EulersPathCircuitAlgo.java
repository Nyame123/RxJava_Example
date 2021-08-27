package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.ordinal.graphs.Graph;
import com.example.myrxjava.ordinal.graphs.Vertex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Eulerian path and circuit for undirected graph
 * Difficulty Level : Medium
 * Last Updated : 25 Aug, 2021
 * Eulerian Path is a path in graph that visits every edge exactly once.
 * Eulerian Circuit is an Eulerian Path which starts and ends on the same vertex.
 **/
public class EulersPathCircuitAlgo {

    private int V;   // No. of vertices

    // Array  of lists for Adjacency List Representation
    private LinkedList<Integer> adj[];

    // Constructor
    EulersPathCircuitAlgo(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Driver method
    public static void main(String args[]) {
        // Let us create and test graphs shown in above figures
        EulersPathCircuitAlgo g1 = new EulersPathCircuitAlgo(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.test();

        EulersPathCircuitAlgo g2 = new EulersPathCircuitAlgo(5);
        g2.addEdge(1, 0);
        g2.addEdge(0, 2);
        g2.addEdge(2, 1);
        g2.addEdge(0, 3);
        g2.addEdge(3, 4);
        g2.addEdge(4, 0);
        g2.test();

        EulersPathCircuitAlgo g3 = new EulersPathCircuitAlgo(5);
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(1, 3);
        g3.test();

        // Let us create a graph with 3 vertices
        // connected in the form of cycle
        EulersPathCircuitAlgo g4 = new EulersPathCircuitAlgo(3);
        g4.addEdge(0, 1);
        g4.addEdge(1, 2);
        g4.addEdge(2, 0);
        g4.test();

        // Let us create a graph with all vertices
        // with zero degree
        EulersPathCircuitAlgo g5 = new EulersPathCircuitAlgo(3);
        g5.test();
    }

    //Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);// Add w to v's list.
        adj[w].add(v); //The graph is undirected
    }

    // A function used by DFS
    void DFSUtil(int v, boolean visited[]) {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // Method to check if all non-zero degree vertices are
    // connected. It mainly does DFS traversal starting from
    boolean isConnected() {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        int i;
        for (i = 0; i < V; i++)
            visited[i] = false;

        // Find a vertex with non-zero degree
        for (i = 0; i < V; i++)
            if (adj[i].size() != 0)
                break;

        // If there are no edges in the graph, return true
        if (i == V)
            return true;

        // Start DFS traversal from a vertex with non-zero degree
        DFSUtil(i, visited);

        // Check if all non-zero degree vertices are visited
        for (i = 0; i < V; i++)
            if (!visited[i] && adj[i].size() > 0)
                return false;

        return true;
    }

    /* The function returns one of the following values
       0 --> If grpah is not Eulerian
       1 --> If graph has an Euler path (Semi-Eulerian)
       2 --> If graph has an Euler Circuit (Eulerian)  */
    int isEulerian() {
        // Check if all non-zero degree vertices are connected
        if (!isConnected())
            return 0;

        // Count vertices with odd degree
        int odd = 0;
        for (int i = 0; i < V; i++)
            if (adj[i].size() % 2 != 0)
                odd++;

        // If count is more than 2, then graph is not Eulerian
        if (odd > 2)
            return 0;

        // If odd count is 2, then semi-eulerian.
        // If odd count is 0, then eulerian
        // Note that odd count can never be 1 for undirected graph
        return (odd == 2) ? 1 : 2;
    }

    // Function to run test cases
    void test() {
        int res = isEulerian();
        if (res == 0)
            System.out.println("graph is not Eulerian");
        else if (res == 1)
            System.out.println("graph has a Euler path");
        else
            System.out.println("graph has a Euler cycle");
    }
}

class EulerianPathAndCircuit<T> {

    public static void main(String args[]) {

        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addSingleVertex(1);
        graph.addSingleVertex(2);
        graph.addSingleVertex(3);
        graph.addEdge(4, 5);
        graph.addEdge(6, 4);
        graph.addEdge(5, 6);

        EulerianPathAndCircuit<Integer> eulerian = new EulerianPathAndCircuit<Integer>();
        Eulerian result = eulerian.isEulerian(graph);
        System.out.print(result);
    }

    private boolean isConnected(Graph<T> graph) {

        Vertex<T> startVertex = null;

        for (Vertex<T> vertex : graph.getAllVertex()) {
            if (vertex.getDegree() != 0) {
                startVertex = vertex;
                break;
            }
        }

        if (startVertex == null) {
            return true;
        }

        Map<Vertex<T>, Boolean> visited = new HashMap<Vertex<T>, Boolean>();
        DFS(startVertex, visited);

        for (Vertex<T> testVertex : graph.getAllVertex()) {
            if (testVertex.getDegree() != 0 && !visited.containsKey(testVertex)) {
                return false;
            }
        }
        return true;

    }

    private void DFS(Vertex<T> startVertex, Map<Vertex<T>, Boolean> visited) {
        visited.put(startVertex, true);
        for (Vertex<T> child : startVertex.getAdjacentVertexes()) {
            if (!visited.containsKey(child)) {
                DFS(child, visited);
            }
        }
    }

    public Eulerian isEulerian(Graph<T> graph) {

        if (!isConnected(graph)) {
            return Eulerian.NOT_EULERIAN;
        }

        int odd = 0;
        for (Vertex<T> vertex : graph.getAllVertex()) {
            if (vertex.getDegree() != 0 && vertex.getDegree() % 2 != 0) {
                odd++;
            }
        }

        if (odd > 2) {
            return Eulerian.NOT_EULERIAN;
        }

        return odd == 0 ? Eulerian.EULERIAN : Eulerian.SEMIEULERIAN;
    }


    public enum Eulerian {
        NOT_EULERIAN,
        EULERIAN,
        SEMIEULERIAN
    }
}
