package com.example.myrxjava.treeAndGraph.expert;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Dial’s Algorithm (Optimized Dijkstra for small range weights)
 * Difficulty Level : Expert
 * Last Updated : 02 Nov, 2020
 * Dijkstra’s shortest path algorithm runs in O(Elog V) time when
 * implemented with adjacency list representation (See C implementation and STL based C++ implementations for details).
 *
 * Can we optimize Dijkstra’s shortest path algorithm to work better than O(E log V)
 * if maximum weight is small (or range of edge weights is small)?
 * For example, in the above diagram, maximum weight is 14. Many a times the range
 * of weights on edges in is in small range (i.e. all edge weight can be mapped to
 * 0, 1, 2.. w where w is a small number). In that case, Dijkstra’s algorithm can be
 * modified by using different data structure, buckets, which is called dial implementation
 * of dijkstra’s algorithm. time complexity is O(E + WV) where W is maximum weight on any
 * edge of graph, so we can see that, if W is small then this implementation runs much faster
 * than traditional algorithm. Following are important observations.
 *
 * Maximum distance between any two node can be at max w(V – 1) (w is maximum edge weight
 * and we can have at max V-1 edges between two vertices).
 * In Dijkstra algorithm, distances are finalized in non-decreasing, i.e., distance of the
 * closer (to given source) vertices is finalized before the distant vertices.
 * Algorithm
 *
 * Below is complete algorithm:
 * Maintains some buckets, numbered 0, 1, 2,…,wV.
 * Bucket k contains all temporarily labeled nodes with distance equal to k.
 * Nodes in each bucket are represented by list of vertices.
 * Buckets 0, 1, 2,..wV are checked sequentially until the first non-empty bucket is found.
 * Each node contained in the first non-empty bucket has the minimum distance label by definition.
 * One by one, these nodes with minimum distance label are permanently labeled and deleted from
 * the bucket during the scanning process.
 * Thus operations involving vertex include:
 * Checking if a bucket is empty
 * Adding a vertex to a bucket
 * Deleting a vertex from a bucket.
 * The position of a temporarily labeled vertex in the buckets is updated accordingly
 * when the distance label of a vertex changes.
 * Process repeated until all vertices are permanently labeled (or distances of all
 * vertices are finalized).
 *
 * https://www.geeksforgeeks.org/dials-algorithm-optimized-dijkstra-for-small-range-weights/
 **/
public class DialsShortestPath {


    private final ArrayList<Node>[] graph;

    public static void main(String[] args) {

        DialsShortestPath dialsShortestPath = new DialsShortestPath(9);
        dialsShortestPath.addEdge(0,1,4);
        dialsShortestPath.addEdge(0,7,8);
        dialsShortestPath.addEdge(1,2,8);
        dialsShortestPath.addEdge(1,7,11);
        dialsShortestPath.addEdge(2,3,7);
        dialsShortestPath.addEdge(2,8,2);
        dialsShortestPath.addEdge(2,5,4);
        dialsShortestPath.addEdge(3,4,9);
        dialsShortestPath.addEdge(3, 5, 14);
        dialsShortestPath.addEdge(4, 5, 10);
        dialsShortestPath.addEdge(5, 6, 2);
        dialsShortestPath.addEdge(6, 7, 1);
        dialsShortestPath.addEdge(6, 8, 6);
        dialsShortestPath.addEdge(7, 8, 7);

        dialsShortestPath.shortestPath(0,14);
    }

    DialsShortestPath(int totalVertices){
        graph = new ArrayList[totalVertices];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private void addEdge(int u,int v, int weight){
        graph[u].add(new Node(v,weight));
        graph[v].add(new Node(u,weight));
    }

    //Time complexity = O(E + WV)
    private void shortestPath(int src, int w){
        //create the distance storage for the vertices from the source vertex
        int[] distance = new int[graph.length];

        //create the bucket of vertices of equal weight
        Deque<Integer>[] bucket = new ArrayDeque[w * graph.length];
        //fiil the distance initially with INFINITY value
        Arrays.fill(distance, Integer.MAX_VALUE);
        //Arrays.fill(bucket, new ArrayDeque<>());
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayDeque<>();
        }

        //Initialize the source vertex with zero distance
        distance[src] = 0;

        bucket[0].addLast(src);

        int bucketIndex = 0;
        while(true){

            while(bucketIndex < bucket.length && bucket[bucketIndex].isEmpty()){
                bucketIndex++;
            }

            if (bucketIndex == bucket.length)
                break;

            //Relax the neighbours of the current edges
            int current = bucket[bucketIndex].pollFirst();
            for (int k = 0; k < graph[current].size(); k++) {

                Node destination = graph[current].get(k);
                if (distance[destination.u] > distance[current] + destination.w){
                    //remove from the old weight slot in the bucket and put it into current weight slot
                    if (distance[destination.u] != Integer.MAX_VALUE){
                        bucket[distance[destination.u]].remove(destination.u);
                    }
                    distance[destination.u] = distance[current] + destination.w;
                    bucket[distance[destination.u]].addLast(destination.u);
                }
            }

        }

        // Print shortest distances stored in dist[]
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i] + " ");
        }


    }


    private static class Node{
        int u;
        int w;

        public Node(int u, int w) {
            this.u = u;
            this.w = w;
        }
    }
}
