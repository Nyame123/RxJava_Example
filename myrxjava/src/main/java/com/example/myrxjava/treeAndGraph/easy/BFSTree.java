package com.example.myrxjava.treeAndGraph.easy;

import java.util.ArrayList;
import java.util.HashMap;
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


//Level of vertices
class LevelBFS {

    //using adjacency List
    static ArrayList<Integer>[] graph;

    LevelBFS(int n) {
        graph = new ArrayList[n];
        //initialize the graph
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        int V = 8;

        LevelBFS levelBFS = new LevelBFS(V);

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(1, 5);
        addEdge(2, 5);
        addEdge(2, 6);
        addEdge(6, 7);
        boolean[] visited = new boolean[V];
        findLevel(0, visited);

    }

    static void addEdge(int u, int v) {
        graph[u].add(v);
        graph[v].add(u);
    }

    static void findLevel(int start, boolean[] visited) {
        HashMap<Integer, Integer> level = new HashMap<>();
        Queue<Integer> que = new LinkedList<Integer>();

        int count = 0;
        que.add(start);
        level.put(start, count);
        while (!que.isEmpty()) {
            int current = que.remove();
            visited[current] = true;
            for (int i = 0; i < graph[current].size(); i++) {
                count = level.get(current) + 1;
                int destination = graph[current].get(i);
                if (!visited[destination]) {
                    que.add(graph[current].get(i));
                    level.put(graph[current].get(i), count);
                }

            }

        }

        for (Integer k : level.keySet()) {
            System.out.print(k + " ---> " + level.get(k));
            System.out.println();
        }

    }

}

class BinaryTreeDistance {

    public static void main(String[] args) {
        int n = 7;
        Node root = new Node(5);
        root.left = new Node(4);
        root.right = new Node(6);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.left.left.left = new Node(1);
        root.left.left.right = new Node(2);

        distanceFromRoot(root, n);
    }

    static void distanceFromRoot(Node x, int size) {

        int[] distance = new int[size + 1];
        Queue<Node> que = new LinkedList<>();

        distance[x.data] = 0;
        que.add(x);

        while (!que.isEmpty()) {
            Node c = que.remove();
            int nextDis = distance[c.data] + 1;
            if (c.left != null) {
                distance[c.left.data] = nextDis;
                que.add(c.left);
            }

            if (c.right != null) {
                distance[c.right.data] = nextDis;
                que.add(c.right);
            }


        }

        for (int i = 1; i <= size; i++) {
            System.out.print(i + "--->" + distance[i]);
            System.out.println();
        }


    }

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int d) {
            data = d;
            left = null;
            right = null;
        }
    }


}


