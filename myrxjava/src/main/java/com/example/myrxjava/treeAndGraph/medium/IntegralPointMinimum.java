package com.example.myrxjava.treeAndGraph.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Find integral points with minimum distance from given set of integers using BFS
 * Difficulty Level : Medium
 * Last Updated : 20 Jul, 2021
 * Given an integer array A[] of length N and an integer K. The task is to find K
 * distinct integral points that are not present in the given array such that the sum
 * of their distances from the nearest point in A[] is minimized.
 * <p>
 * An integral point is defined as the point with both the coordinates as integers.
 * Also, in the x-axis, we don’t need to consider y-coordinate because y-coordinated of all the points is equal to zero.
 * <p>
 * Examples:
 * <p>
 * Input: A[] = { -1, 4, 6 }, K = 3
 * Output: -2, 0, 3
 * Explanation:
 * Nearest point for -2 in A[] is -1 -> distance = 1
 * Nearest point for 0 in A[] is -1 -> distance = 1
 * Nearest point for 3 in A[] is 4 -> distance = 1
 * Total distance = 1 + 1 + 1 = 3 which is minimum possible distance.
 * Other results are also possible with the same minimum distance.
 * Input: A[] = { 0, 1, 3, 4 }, K = 5
 * Output: -1, 2, 5, -2, 6
 * Explanation:
 * Nearest point for -1 in A[] is 0 -> distance = 1
 * Nearest point for 2 in A[] is 3 -> distance = 1
 * Nearest point for 5 in A[] is 4 -> distance = 1
 * Nearest point for -2 in A[] is 0 -> distance = 2
 * Nearest point for 6 in A[] is 4 -> distance = 2
 * Total distance = 2 + 1 + 1 + 1 + 2 = 7 which is minimum possible distance.
 *
 * https://www.geeksforgeeks.org/find-integral-points-with-minimum-distance-from-given-set-of-integers-using-bfs/?ref=rp
 **/
public class IntegralPointMinimum {

    public static void main(String[] args) {
        int A[] = new int[]{0, 1, 3, 4};
        int K = 5;

        findIntegralPoint(A, K);
    }

    static void findIntegralPoint(int[] arr, int k) {
        int n = arr.length;
        Queue<Integer> que = new LinkedList<>();
        Set<Integer> hash = new HashSet<>();
        for (int i = 0; i < n; i++) {
            que.add(arr[i]);
            hash.add(arr[i]);

        }

        int[] result = new int[k];
        while (!que.isEmpty()) {
            int x = que.remove();
            if (k == 0)
                break;
            int desc = x - 1;
            int inc = x + 1;
            if (!hash.contains(desc)) {
                que.add(desc);
                hash.add(desc);
                k--;
                result[k] = desc;
            }

            if (k == 0)
                break;

            if (!hash.contains(inc)) {
                que.add(inc);
                hash.add(inc);
                k--;
                result[k] = inc;
            }

        }

        for (int i = result.length - 1; i >= 0; i--) {
            System.out.print(result[i] + " ");
        }
    }

}