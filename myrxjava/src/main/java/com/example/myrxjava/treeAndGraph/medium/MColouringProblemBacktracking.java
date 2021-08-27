package com.example.myrxjava.treeAndGraph.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * m Coloring Problem | Backtracking-5
 * Difficulty Level : Medium
 * Last Updated : 20 Jul, 2021
 * Given an undirected graph and a number m, determine if the graph can be coloured
 * with at most m colours such that no two adjacent vertices of the graph are colored
 * with the same color. Here coloring of a graph means the assignment of colors to all vertices.
 * <p>
 * Input-Output format:
 * <p>
 * Input:
 * <p>
 * A 2D array graph[V][V] where V is the number of vertices in graph and graph[V][V] is an
 * adjacency matrix representation of the graph. A value graph[i][j] is 1 if there is a direct
 * edge from i to j, otherwise graph[i][j] is 0.
 * An integer m is the maximum number of colors that can be used.
 * Output:
 * An array color[V] that should have numbers from 1 to m. color[i] should represent
 * the color assigned to the ith vertex. The code should also return false if the graph cannot be colored with m colors.
 * <p>
 * Example:
 * <p>
 * <p>
 * <p>
 * Input:
 * graph = {0, 1, 1, 1},
 * {1, 0, 1, 0},
 * {1, 1, 0, 1},
 * {1, 0, 1, 0}
 * Output:
 * Solution Exists:
 * Following are the assigned colors
 * 1  2  3  2
 * Explanation: By coloring the vertices
 * with following colors, adjacent
 * vertices does not have same colors
 * <p>
 * Input:
 * graph = {1, 1, 1, 1},
 * {1, 1, 1, 1},
 * {1, 1, 1, 1},
 * {1, 1, 1, 1}
 * Output: Solution does not exist.
 * Explanation: No solution exits.
 * <p>
 * https://www.geeksforgeeks.org/m-coloring-problem-backtracking-5/
 **/
public class MColouringProblemBacktracking {

    // Number of vertices in the graph
    static int V = 4;

    /* A utility function to print solution */
    static void printSolution(int[] color) {
        System.out.println("Solution Exists:" +
                " Following are the assigned colors ");
        for (int i = 0; i < V; i++)
            System.out.print("  " + color[i]);
        System.out.println();
    }

    // check if the colored
    // graph is safe or not
    static boolean isSafe(boolean[][] graph, int[] color) {
        // check for every edge
        for (int i = 0; i < V; i++)
            for (int j = i + 1; j < V; j++)
                if (graph[i][j] && color[j] == color[i])
                    return false;
        return true;
    }

    /**
     * This function solves the m Coloring
     * problem using recursion. It returns
     * false if the m colours cannot be assigned,
     * otherwise, return true and prints
     * assignments of colours to all vertices.
     * Please note that there may be more than
     * one solutions, this function prints one
     * of the feasible solutions.
     * <p>
     * Time Complexity: O(m^V).
     * Space Complexity: O(V).
     **/
    static boolean graphColoring(boolean[][] graph, int m,
                                 int i, int[] color) {
        // if current index reached end
        if (i == V) {

            // if coloring is safe
            if (isSafe(graph, color)) {

                // Print the solution
                printSolution(color);
                return true;
            }
            return false;
        }

        // Assign each color from 1 to m
        for (int j = 1; j <= m; j++) {
            color[i] = j;

            // Recur of the rest vertices
            if (graphColoring(graph, m, i + 1, color))
                return true;
            color[i] = 0;
        }
        return false;
    }

    // Driver code
    public static void main(String[] args) {

    /* Create following graph and
        test whether it is 3 colorable
        (3)---(2)
        | / |
        | / |
        | / |
        (0)---(1)
        */
        boolean[][] graph = {
                {false, true, true, true},
                {true, false, true, false},
                {true, true, false, true},
                {true, false, true, false},
        };
        int m = 3; // Number of colors

        // Initialize all color values as 0.
        // This initialization is needed
        // correct functioning of isSafe()
        int[] color = new int[V];
        for (int i = 0; i < V; i++)
            color[i] = 0;
        if (!graphColoring(graph, m, 0, color))
            System.out.println("Solution does not exist");
    }
}

//Time Complexity: O(m^V).
//Space Complexity: O(V).
class mColoringProbBacktracking {
    final int V = 4;
    int color[];

    // driver program to test above function
    public static void main(String args[]) {
        mColoringProbBacktracking Coloring
                = new mColoringProbBacktracking();
        /* Create following graph and
           test whether it is
           3 colorable
          (3)---(2)
           |   / |
           |  /  |
           | /   |
          (0)---(1)
        */
        int graph[][] = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
        };
        int m = 3; // Number of colors
        Coloring.graphColoring(graph, m);
    }

    /* A utility function to check
       if the current color assignment
       is safe for vertex v */
    boolean isSafe(
            int v, int graph[][], int color[],
            int c) {
        for (int i = 0; i < V; i++)
            if (graph[v][i] == 1 && c == color[i])
                return false;
        return true;
    }

    /* A recursive utility function
       to solve m coloring  problem */
    boolean graphColoringUtil(
            int graph[][], int m,
            int color[], int v) {
        /* base case: If all vertices are
           assigned a color then return true */
        if (v == V)
            return true;

        /* Consider this vertex v and try
           different colors */
        for (int c = 1; c <= m; c++) {
            /* Check if assignment of color c to v
               is fine*/
            if (isSafe(v, graph, color, c)) {
                color[v] = c;

                /* recur to assign colors to rest
                   of the vertices */
                if (graphColoringUtil(graph, m, color, v + 1))
                    return true;

                /* If assigning color c doesn't lead
                   to a solution then remove it */
                color[v] = 0;
            }
        }

        /* If no color can be assigned to
           this vertex then return false */
        return false;
    }

    /* This function solves the m Coloring problem using
       Backtracking. It mainly uses graphColoringUtil()
       to solve the problem. It returns false if the m
       colors cannot be assigned, otherwise return true
       and  prints assignments of colors to all vertices.
       Please note that there  may be more than one
       solutions, this function prints one of the
       feasible solutions.*/
    boolean graphColoring(int graph[][], int m) {
        // Initialize all color values as 0. This
        // initialization is needed correct
        // functioning of isSafe()
        color = new int[V];
        for (int i = 0; i < V; i++)
            color[i] = 0;

        // Call graphColoringUtil() for vertex 0
        if (!graphColoringUtil(
                graph, m, color, 0)) {
            System.out.println(
                    "Solution does not exist");
            return false;
        }

        // Print the solution
        printSolution(color);
        return true;
    }

    /* A utility function to print solution */
    void printSolution(int color[]) {
        System.out.println(
                "Solution Exists: Following"
                        + " are the assigned colors");
        for (int i = 0; i < V; i++)
            System.out.print(" " + color[i] + " ");
        System.out.println();
    }
}

class Node {

    // A node class which stores the color and the edges
    // connected to the node
    int color = 1;
    Set<Integer> edges = new HashSet<Integer>();
}

//Time Complexity: O(V + E).
//Space Complexity: O(V)
class MColouringProbDFS {

    static int canPaint(ArrayList<Node> nodes, int n, int m) {

        // Create a visited array of n
        // nodes, initialized to zero
        ArrayList<Integer> visited = new ArrayList<Integer>();
        for (int i = 0; i < n + 1; i++) {
            visited.add(0);
        }

        // maxColors used till now are 1 as
        // all nodes are painted color 1
        int maxColors = 1;

        // Do a full BFS traversal from
        // all unvisited starting points
        for (int sv = 1; sv <= n; sv++) {
            if (visited.get(sv) > 0) {
                continue;
            }

            // If the starting point is unvisited,
            // mark it visited and push it in queue
            visited.set(sv, 1);
            Queue<Integer> q = new LinkedList<>();
            q.add(sv);

            // BFS Travel starts here
            while (q.size() != 0) {
                int top = q.peek();
                q.remove();

                // Checking all adjacent nodes
                // to "top" edge in our queue
                for (int it : nodes.get(top).edges) {

                    // IMPORTANT: If the color of the
                    // adjacent node is same, increase it by 1
                    if (nodes.get(top).color == nodes.get(it).color) {
                        nodes.get(it).color += 1;
                    }

                    // If number of colors used shoots m, return
                    // 0
                    maxColors = Math.max(maxColors,
                            Math.max(nodes.get(top).color,
                                    nodes.get(it).color));
                    if (maxColors > m)
                        return 0;

                    // If the adjacent node is not visited,
                    // mark it visited and push it in queue
                    if (visited.get(it) == 0) {
                        visited.set(it, 1);
                        q.remove(it);
                    }
                }

            }
        }
        return 1;
    }

    // Driver code
    public static void main(String[] args) {
        int n = 4;
        int[][] graph = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}
        };
        int m = 3; // Number of colors

        // Create a vector of n+1
        // nodes of type "node"
        // The zeroth position is just
        // dummy (1 to n to be used)
        ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            nodes.add(new Node());
        }

        // Add edges to each node as per given input
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] > 0) {
                    // Connect the undirected graph
                    nodes.get(i).edges.add(i);
                    nodes.get(j).edges.add(j);
                }
            }
        }

        // Display final answer
        System.out.println(canPaint(nodes, n, m));
    }
}