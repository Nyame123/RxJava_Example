package com.example.myrxjava.treeAndGraph.medium;
/**
 * Disjoint Set (Or Union-Find) | Set 1 (Detect Cycle in an Undirected Graph)
 * Difficulty Level : Medium
 * Last Updated : 05 Jul, 2021
 * A disjoint-set data structure is a data structure that keeps track of a set of elements partitioned
 * into a number of disjoint (non-overlapping) subsets. A union-find algorithm is an algorithm that performs
 * two useful operations on such a data structure:
 *
 * Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.
 *
 * Union: Join two subsets into a single subset.
 *
 * In this post, we will discuss the application of Disjoint Set Data Structure. The application is to check whether
 * a given graph contains a cycle or not.
 *
 * Union-Find Algorithm can be used to check whether an undirected graph contains cycle or not. Note that we have
 * discussed an algorithm to detect cycle. This is another method based on Union-Find. This method assumes that
 * the graph doesn’t contain any self-loops.
 *
 *
 *
 * We can keep track of the subsets in a 1D array, let’s call it parent[].
 **/
public class DisjointSetGraphAlgo {

    // Driver Method
    public static void main (String[] args) {
        /* Let us create the following graph
        0
        | \
        |  \
        1---2 */
        int V = 3, E = 3;
        DisjointSetGraphAlgo graph = new DisjointSetGraphAlgo(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;

        // add edge 0-2
        graph.edge[2].src = 2;
        graph.edge[2].dest = 0;

        if (graph.isCycle(graph)==1)
            System.out.println( "graph contains cycle" );
        else
            System.out.println( "graph doesn't contain cycle" );
    }

    int V, E;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // /collection of all edges

    class Edge {
        int src, dest;
    }

    // Creates a graph with V vertices and E edges
    DisjointSetGraphAlgo(int v,int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }

    // A utility function to find the subset of an element i
    int find(int parent[], int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    // A utility function to do union of two subsets
    void Union(int parent[], int x, int y) {
        parent[x] = y;
    }


    // The main function to check whether a given graph
    // contains cycle or not
    int isCycle(DisjointSetGraphAlgo graph) {
        // Allocate memory for creating V subsets
        int[] parent = new int[graph.V];

        // Initialize all subsets as single element sets
        for (int i=0; i<graph.V; ++i)
            parent[i]=-1;

        // Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.
        for (int i = 0; i < graph.E; ++i) {
            int x = graph.find(parent, graph.edge[i].src);
            int y = graph.find(parent, graph.edge[i].dest);

            if (x == y)
                return 1;

            graph.Union(parent, x, y);
        }
        return 0;
    }


}
