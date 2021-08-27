package com.example.myrxjava.treeAndGraph.medium;

import java.util.Vector;

/**
 * Union-Find Algorithm | (Union By Rank and Find by Optimized Path Compression)
 * Difficulty Level : Medium
 * Last Updated : 28 Jul, 2021
 * Check whether a given graph contains a cycle or not.
 * <p>
 * https://www.geeksforgeeks.org/union-find-algorithm-union-rank-find-optimized-path-compression/
 **/
public class DisjointSetUnionByPathCompression {

    static int MAX_VERTEX = 101;

    // Arr to represent parent of index i
    static int[] parents = new int[MAX_VERTEX];

    // Size to represent the number of nodes
// in subgxrph rooted at index i
    static int[] size = new int[MAX_VERTEX];

    // set parent of every node to itself and
// size of node to one
    static void initialize(int n) {
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    // Each time we follow a path, find function
// compresses it further until the path length
// is greater than or equal to 1.
    static int find(int i) {
        // while we reach a node whose parent is
        // equal to itself
        while (parents[i] != i) {
            i = parents[i]; // Move to the new level
            parents[i] = parents[i]; // Skip one level

        }
        return i;
    }

    // A function that does union of two nodes x and y
// where xr is root node of x and yr is root node of y
    static void _union(int xr, int yr) {
        if (size[xr] < size[yr]) // Make yr parent of xr
        {
            parents[xr] = parents[yr];
            size[yr] += size[xr];
        } else // Make xr parent of yr
        {
            parents[yr] = parents[xr];
            size[xr] += size[yr];
        }
    }

    // The main function to check whether a given
// graph contains cycle or not
    static int isCycle(Vector<Integer> adj[], int V) {
        // Iterate through all edges of graph,
        // find nodes connecting them.
        // If root nodes of both are same,
        // then there is cycle in graph.
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int x = find(i); // find root of i

                // find root of adj[i][j]
                int y = find(adj[i].get(j));

                if (x == y)
                    return 1; // If same parent
                _union(x, y); // Make them connect
            }
        }
        return 0;
    }

    // Driver Code
    public static void main(String[] args) {
        int V = 3;

        // Initialize the values for arxry Arr and Size
        initialize(V);

    /* Let us create following gxrph
        0
        | \
        | \
        1-----2 */

        // Adjacency list for graph
        Vector<Integer>[] adj = new Vector[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Vector<Integer>();

        adj[0].add(1);
        adj[0].add(2);
        adj[1].add(2);

        // call is_cycle to check if it contains cycle
        if (isCycle(adj, V) == 1)
            System.out.print("Graph contains Cycle.\n");
        else
            System.out.print("Graph does not contain Cycle.\n");
    }
}
