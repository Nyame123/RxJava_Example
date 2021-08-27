package com.example.myrxjava.treeAndGraph.easy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * Level of Each node in a Tree from source node (using BFS)
 * Difficulty Level : Easy
 * Last Updated : 21 Jun, 2021
 * Given a tree with v vertices, find the level of each node in a tree from the source node.
 * <p>
 * https://www.geeksforgeeks.org/level-node-tree-source-node-using-bfs/
 **/
public class BFSTree {

    // Driver Code
    public static void main(String args[]) {
        // adjacency graph for tree
        int V = 8;
        Vector<Vector<Integer>> graph = new Vector<Vector<Integer>>();

        for (int i = 0; i < V + 1; i++)
            graph.add(new Vector<Integer>());

        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(1).add(4);
        graph.get(1).add(5);
        graph.get(2).add(5);
        graph.get(2).add(6);
        graph.get(6).add(7);

        // call levels function with source as 0
        printLevels(graph, V, 0);
    }
    // function to determine level of each node starting
// from x using BFS
    static void printLevels(Vector<Vector<Integer>> graph, int V, int x) {
        // array to store level of each node
        int level[] = new int[V];
        boolean marked[] = new boolean[V];

        // create a queue
        Queue<Integer> que = new LinkedList<Integer>();

        // enqueue element x
        que.add(x);

        // initialize level of source node to 0
        level[x] = 0;

        // marked it as visited
        marked[x] = true;

        // do until queue is empty
        while (que.size() > 0) {

            // dequeue element
            x = que.remove();

            // traverse neighbors of node x
            for (int i = 0; i < graph.get(x).size(); i++) {
                // b is neighbor of node x
                int b = graph.get(x).get(i);

                // if b is not marked already
                if (!marked[b]) {

                    // enqueue b in queue
                    que.add(b);

                    // level of b is level of x + 1
                    level[b] = level[x] + 1;

                    // mark b
                    marked[b] = true;
                }
            }
        }

        // display all nodes and their levels
        System.out.println("Nodes"
                + " "
                + "Level");
        for (int i = 0; i < V; i++)
            System.out.println(" " + i + " --> " + level[i]);
    }


}
