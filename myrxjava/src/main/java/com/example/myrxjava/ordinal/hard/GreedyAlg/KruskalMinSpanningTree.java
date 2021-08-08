package com.example.myrxjava.ordinal.hard.GreedyAlg;

import java.util.Arrays;

/**
 * Kruskal’s Minimum Spanning Tree Algorithm | Greedy Algo-2
 * Difficulty Level : Hard
 * Last Updated : 07 Jul, 2021
 * What is Minimum Spanning Tree?
 * Given a connected and undirected graph, a spanning tree of that graph is a subgraph that is a
 * tree and connects all the vertices together. A single graph can have many different spanning trees.
 * A minimum spanning tree (MST) or minimum weight spanning tree for a weighted, connected, undirected
 * graph is a spanning tree with a weight less than or equal to the weight of every other spanning tree.
 * The weight of a spanning tree is the sum of weights given to each edge of the spanning tree.
 * How many edges does a minimum spanning tree has?
 * A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.
 *
 * Below are the steps for finding MST using Kruskal’s algorithm
 *
 * 1. Sort all the edges in non-decreasing order of their weight.
 * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it.
 * 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
 *
 * Step #2 uses the Union-Find algorithm to detect cycles. So we recommend reading the following post as a prerequisite.
 **/
public class KruskalMinSpanningTree {

    // Driver Code
    public static void main(String[] args) {

        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        int V = 4; // Number of vertices in graph
        int E = 5; // Number of edges in graph
        KruskalMinSpanningTree graph = new KruskalMinSpanningTree(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;

        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;

        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        // Function call
        graph.KruskalMST();
    }

    // A class to represent a graph edge
    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        // Comparator function used for
        // sorting edgesbased on their weight
        public int compareTo(Edge compareEdge)
        {
            return this.weight - compareEdge.weight;
        }
    }

    // A class to represent a subset for
    // union-find
    class Subset {
        int parent, rank;
    };

    int V, E; // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges

    // Creates a graph with V vertices and E edges
    KruskalMinSpanningTree(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    // A utility function to find set of an
    // element i (uses path compression technique)
    int find(Subset subsets[], int i) {
        // find root and make root as parent of i
        // (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    // A function that does union of two sets
    // of x and y (uses union by rank)
    void Union(Subset subsets[], int xroot, int yroot) {
       /* int xroot = find(subsets, x);
        int yroot = find(subsets, y);*/


        // Attach smaller rank tree under root
        // of high rank tree (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

            // If ranks are same, then make one as
            // root and increment its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }


    /**
     * The main function to construct MST using Kruskal's algorithm
     * Time Complexity: O(ElogE) or O(ElogV). Sorting of edges takes O(ELogE) time. After sorting,
     * we iterate through all edges and apply the find-union algorithm. The find and union operations
     * can take at most O(LogV) time. So overall complexity is O(ELogE + ELogV) time. The value of E
     * can be at most O(V2), so O(LogV) is O(LogE) the same. Therefore, the overall time complexity is O(ElogE) or O(ElogV)
     **/
    void KruskalMST() {
        // This will store the resultant MST
        Edge result[] = new Edge[V];

        // An index variable, used for result[]
        int e = 0;

        // An index variable, used for sorted edges
        int i = 0;
        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        // Step 1:  Sort all the edges in non-decreasing
        // order of their weight.  If we are not allowed to
        // change the given graph, we can create a copy of
        // array of edges
        Arrays.sort(edge);

        // Allocate memory for creating V ssubsets
        Subset subsets[] = new Subset[V];
        for (i = 0; i < V; ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

       /* // Create V subsets with single elements
        for (int v = 0; v < V; ++v) {

        }*/

        i = 0; // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (e < V - 1) {
            // Step 2: Pick the smallest edge. And increment
            // the index for next iteration
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // If including this edge does't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }

        // print the contents of result[] to display
        // the built MST
        System.out.println("Following are the edges in "
                + "the constructed MST");
        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -- "
                    + result[i].dest
                    + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree "
                + minimumCost);
    }
}