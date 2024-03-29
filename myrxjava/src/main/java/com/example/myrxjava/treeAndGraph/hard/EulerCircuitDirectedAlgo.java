package com.example.myrxjava.treeAndGraph.hard;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Euler Circuit in a Directed Graph
 * Difficulty Level : Hard
 * Last Updated : 28 Jul, 2021
 * Eulerian Path is a path in graph that visits every edge exactly once.
 * Eulerian Circuit is an Eulerian Path which starts and ends on the same vertex.
 * <p>
 * A graph is said to be eulerian if it has a eulerian cycle.
 * We have discussed eulerian circuit for an undirected graph. In this post, the same is discussed for a directed graph.
 * <p>
 * For example, the following graph has eulerian cycle as {1, 0, 3, 4, 0, 2, 1}
 * <p>
 * https://www.geeksforgeeks.org/euler-circuit-directed-graph/
 **/
public class EulerCircuitDirectedAlgo {

    private int V;   // No. of vertices
    private LinkedList<Integer> adj[];//Adjacency List
    private int in[];            //maintaining in degree

    //Constructor
    EulerCircuitDirectedAlgo(int v) {
        V = v;
        adj = new LinkedList[v];
        in = new int[V];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
            in[i] = 0;
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        EulerCircuitDirectedAlgo g = new EulerCircuitDirectedAlgo(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0);

        if (g.isEulerianCycle())
            System.out.println("Given directed graph is eulerian ");
        else
            System.out.println("Given directed graph is NOT eulerian ");
    }

    //Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
        in[w]++;
    }

    // A recursive function to print DFS starting from v
    void DFSUtil(int v, Boolean visited[]) {
        // Mark the current node as visited
        visited[v] = true;

        int n;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // Function that returns reverse (or transpose) of this graph
    EulerCircuitDirectedAlgo getTranspose() {
        EulerCircuitDirectedAlgo g = new EulerCircuitDirectedAlgo(V);
        for (int v = 0; v < V; v++) {
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext()) {
                g.adj[i.next()].add(v);
                (g.in[v])++;
            }
        }
        return g;
    }

    // The main function that returns true if graph is strongly
    // connected
    Boolean isSC() {
        // Step 1: Mark all the vertices as not visited (For
        // first DFS)
        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Step 2: Do DFS traversal starting from the first vertex.
        DFSUtil(0, visited);

        // If DFS traversal doesn't visit all vertices, then return false.
        for (int i = 0; i < V; i++)
            if (!visited[i])
                return false;

        // Step 3: Create a reversed graph
        EulerCircuitDirectedAlgo gr = getTranspose();

        // Step 4: Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Step 5: Do DFS for reversed graph starting from first vertex.
        // Starting Vertex must be same starting point of first DFS
        gr.DFSUtil(0, visited);

        // If all vertices are not visited in second DFS, then
        // return false
        for (int i = 0; i < V; i++)
            if (!visited[i])
                return false;

        return true;
    }

    /* This function returns true if the directed graph has a eulerian
       cycle, otherwise returns false  */
    Boolean isEulerianCycle() {
        // Check if all non-zero degree vertices are connected
        if (!isSC())
            return false;

        // Check if in degree and out degree of every vertex is same
        for (int i = 0; i < V; i++)
            if (adj[i].size() != in[i])
                return false;

        return true;
    }
}
