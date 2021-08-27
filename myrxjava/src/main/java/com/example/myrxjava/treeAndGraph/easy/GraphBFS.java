package com.example.myrxjava.treeAndGraph.easy;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Breadth First Search or BFS for a Graph
 * Difficulty Level : Easy
 * Last Updated : 07 Aug, 2021
 * <p>
 * Breadth First Traversal (or Search) for a graph is similar to Breadth First Traversal
 * of a tree (See method 2 of this post). The only catch here is, unlike trees, graphs may
 * contain cycles, so we may come to the same node again. To avoid processing a node more
 * than once, we use a boolean visited array. For simplicity, it is assumed that all
 * vertices are reachable from the starting vertex.
 * <p>
 * For example, in the following graph, we start traversal from vertex 2.
 * When we come to vertex 0, we look for all adjacent vertices of it.
 * 2 is also an adjacent vertex of 0. If we don’t mark visited vertices,
 * then 2 will be processed again and it will become a non-terminating process.
 * A Breadth First Traversal of the following graph is 2, 0, 3, 1.
 * <p>
 * https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
 **/
public class GraphBFS {

    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists

    // Constructor
    GraphBFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Driver method to
    public static void main(String args[]) {
        GraphBFS g = new GraphBFS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal " +
                "(starting from vertex 2)");

        g.BFS(2);
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // prints BFS traversal from a given source s
    //Time Complexity: O(V+E)
    void BFS(int s) {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s + " ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}
