package com.example.myrxjava.treeAndGraph.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 0-1 BFS (Shortest Path in a Binary Weight Graph)
 * Difficulty Level : Medium
 * Given a graph where every edge has weight as either 0 or 1.
 * A source vertex is also given in the graph. Find the shortest path from source vertex to every other vertex.
 * Example:
 * <p>
 * <p>
 * Input : Source Vertex = 0 and below graph
 * <p>
 * <p>
 * Output : Shortest distances from given source
 * 0 0 1 1 2 1 2 1 2
 * <p>
 * Explanation :
 * Shortest distance from 0 to 0 is 0
 * Shortest distance from 0 to 1 is 0
 * Shortest distance from 0 to 2 is 1
 * ..................
 **/
public class ZeroOnesBFS {

    private static final int numVertex = 9;
    private ArrayList<Node>[] edges = new ArrayList[numVertex];

    public ZeroOnesBFS() {
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<Node>();
        }
    }

    public static void main(String[] args) {
        ZeroOnesBFS graph = new ZeroOnesBFS();
        graph.addEdge(0, 1, 0);
        graph.addEdge(0, 7, 1);
        graph.addEdge(1, 7, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 0);
        graph.addEdge(2, 5, 0);
        graph.addEdge(2, 8, 1);
        graph.addEdge(3, 4, 1);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 1);
        graph.addEdge(5, 6, 1);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 1);
        int src = 0;//source node
        graph.zeroOneBFS(src);
        return;
    }

    public void addEdge(int u, int v, int wt) {
        edges[u].add(new Node(v, wt));
        edges[v].add(new Node(u, wt));
    }


    //Time Complexity = O(V + E)
    public void zeroOneBFS(int src) {

        // initialize distances from given source
        int[] dist = new int[numVertex];
        for (int i = 0; i < numVertex; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // double ended queue to do BFS
        Deque<Integer> queue = new ArrayDeque<Integer>();
        dist[src] = 0;
        queue.addFirst(src);

        while (!queue.isEmpty()) {
            int v = queue.removeFirst();
            for (int i = 0; i < edges[v].size(); i++) {

                // checking for optimal distance
                if (dist[edges[v].get(i).to] >
                        dist[v] + edges[v].get(i).weight) {

                    // update the distance
                    dist[edges[v].get(i).to] =
                            dist[v] + edges[v].get(i).weight;

                    // put 0 weight edges to front and 1
                    // weight edges to back so that vertices
                    // are processed in increasing order of weight
                    if (edges[v].get(i).weight == 0) {
                        queue.addFirst(edges[v].get(i).to);
                    } else {
                        queue.addLast(edges[v].get(i).to);
                    }
                }
            }
        }

        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    private static class Node {
        int to; // the ending vertex
        int weight; // the weight of the edge

        public Node(int to, int wt) {
            this.to = to;
            this.weight = wt;
        }
    }
}
