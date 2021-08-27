package com.example.myrxjava.treeAndGraph.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Floyd Warshall Algorithm | DP-16
 * Difficulty Level : Medium
 * Last Updated : 19 Jul, 2021
 * <p>
 * The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem.
 * The problem is to find shortest distances between every pair of vertices in a given edge weighted directed Graph.
 * Example:
 * <p>
 * Input:
 * graph[][] = { {0,   5,  INF, 10},
 * {INF,  0,  3,  INF},
 * {INF, INF, 0,   1},
 * {INF, INF, INF, 0} }
 * which represents the following graph
 * 10
 * (0)------->(3)
 * |         /|\
 * 5 |          |
 * |          | 1
 * \|/         |
 * (1)------->(2)
 * 3
 * Note that the value of graph[i][j] is 0 if i is equal to j
 * And graph[i][j] is INF (infinite) if there is no edge from vertex i to j.
 * <p>
 * Output:
 * Shortest distance matrix
 * 0      5      8      9
 * INF      0      3      4
 * INF    INF      0      1
 * INF    INF    INF      0
 **/
public class FloydWarshallAllPairShortestPath {

    private static final int INF = 1000000;

    public static void main(String args[]) {
        int[][] graph = {
                {0, 3, 6, 15},
                {INF, 0, -2, INF},
                {INF, INF, 0, 2},
                {1, INF, INF, 0}
        };

        FloydWarshallAllPairShortestPath shortestPath = new FloydWarshallAllPairShortestPath();
        int[][] distance = shortestPath.allPairShortestPath(graph);
        System.out.println("Minimum Distance matrix");
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public int[][] allPairShortestPath(int[][] distanceMatrix) {

        int distance[][] = new int[distanceMatrix.length][distanceMatrix.length];
        int path[][] = new int[distanceMatrix.length][distanceMatrix.length];

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                distance[i][j] = distanceMatrix[i][j];
                if (distanceMatrix[i][j] != INF && i != j) {
                    path[i][j] = i;
                } else {
                    path[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < distanceMatrix.length; k++) {
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix.length; j++) {
                    if (distance[i][k] == INF || distance[k][j] == INF) {
                        continue;
                    }
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }

        //look for negative weight cycle in the graph
        //if values on diagonal of distance matrix is negative
        //then there is negative weight cycle in the graph.
        for (int i = 0; i < distance.length; i++) {
            if (distance[i][i] < 0) {
                throw new NegativeWeightCycleException();
            }
        }

        printPath(path, 3, 2);
        return distance;
    }

    public void printPath(int[][] path, int start, int end) {
        if (start < 0 || end < 0 || start >= path.length || end >= path.length) {
            throw new IllegalArgumentException();
        }

        System.out.println("Actual path - between " + start + " " + end);
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(end);
        while (true) {
            end = path[start][end];
            if (end == -1) {
                return;
            }
            stack.addFirst(end);
            if (end == start) {
                break;
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pollFirst() + " ");
        }

        System.out.println();
    }

    class NegativeWeightCycleException extends RuntimeException {

    }
}
