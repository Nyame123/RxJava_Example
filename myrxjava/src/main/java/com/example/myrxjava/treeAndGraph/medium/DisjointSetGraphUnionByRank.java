package com.example.myrxjava.treeAndGraph.medium;

/**
 * Union-Find Algorithm | Set 2 (Union By Rank and Path Compression)
 * Difficulty Level : Medium
 * Last Updated : 01 Jul, 2021
 *
 * The idea is to always attach smaller depth tree under the root of the deeper tree.
 * This technique is called union by rank. The term rank is preferred instead of height because if path
 * compression technique (we have discussed it below) is used, then rank is not always equal to height.
 * Also, size (in place of height) of trees can also be used as rank. Using size as rank also yields
 * worst case time complexity as O(Logn)
 *
 * Let us see the above example with union by rank
 * Initially, all elements are single element subsets.
 * 0 1 2 3
 *
 * Do Union(0, 1)
 *    1   2   3
 *   /
 *  0
 *
 * Do Union(1, 2)
 *    1    3
 *  /  \
 * 0    2
 *
 * Do Union(2, 3)
 *     1
 *  /  |  \
 * 0   2   3
 *
 **/
public class DisjointSetGraphUnionByRank {


    int V, E;
    Edge[] edge;

    DisjointSetGraphUnionByRank(int nV, int nE) {
        V = nV;
        E = nE;
        edge = new Edge[E];
        for (int i = 0; i < E; i++) {
            edge[i] = new Edge();
        }
    }

    // Driver Code
    public static void main(String[] args) {
        /* Let us create the following graph
            0
            | \
            |   \
            1-----2 */

        int V = 3, E = 3;
        DisjointSetGraphUnionByRank graph = new DisjointSetGraphUnionByRank(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;

        // add edge 0-2
        graph.edge[2].src = 2;
        graph.edge[2].dest = 0;

        if (graph.isCycle(graph) == 1)
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");
    }

    // class to represent edge
    class Edge {
        int src, dest;
    }

    // class to represent Subset
    class Subset {
        int parent;
        int rank;
    }

    // A utility function to find
    // set of an element i (uses
    // path compression technique)
    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // A function that does union
    // of two sets of x and y
    // (uses union by rank)
    void Union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[yroot].rank < subsets[xroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[xroot].parent = yroot;
            subsets[yroot].rank++;
        }
    }

    // The main function to check whether
    // a given graph contains cycle or not
    int isCycle(DisjointSetGraphUnionByRank graph) {
        int V = graph.V;
        int E = graph.E;

        Subset[] subsets = new Subset[V];
        for (int v = 0; v < V; v++) {

            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        for (int e = 0; e < E; e++) {
            int x = find(subsets, graph.edge[e].src);
            int y = find(subsets, graph.edge[e].dest);
            if (x == y)
                return 1;
            Union(subsets, x, y);
        }
        return 0;
    }


}
