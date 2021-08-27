package com.example.myrxjava.treeAndGraph.easy;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Depth First Search or DFS for a Graph
 * Difficulty Level : Easy
 * Last Updated : 22 Jul, 2021
 * <p>
 * Depth First Traversal (or Search) for a graph is similar
 * to Depth First Traversal of a tree. The only catch here is,
 * unlike trees, graphs may contain cycles, a node may be visited twice.
 * To avoid processing a node more than once, use a boolean visited array.
 * <p>
 * Example:
 * <p>
 * Input: n = 4, e = 6
 * 0 -> 1, 0 -> 2, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 3
 * Output: DFS from vertex 1 : 1 2 0 3
 * <p>
 * https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
 **/
public class GraphDFS {

    private int V; // No. of vertices
    // Array  of lists for
    // Adjacency List Representation
    private LinkedList<Integer> adj[];

    // Constructor
    @SuppressWarnings("unchecked")
    GraphDFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    public static void main(String[] args) {
        GraphDFS g = new GraphDFS(6);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.addEdge(4, 5);

        System.out.println(
                "Following is Depth First Traversal");

        g.DFS();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w); // Add w to v's list.
    }

    // A function used by DFS
    void DFSUtil(int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // The function to do DFS traversal. It uses recursive
    // DFSUtil()
    void DFS() {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];

        // Call the recursive helper function to print DFS
        // traversal starting from all vertices one by one
        for (int i = 0; i < V; ++i)
            if (!visited[i])
                DFSUtil(i, visited);
    }


}
