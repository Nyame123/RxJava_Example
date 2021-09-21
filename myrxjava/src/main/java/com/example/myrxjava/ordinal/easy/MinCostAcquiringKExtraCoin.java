package com.example.myrxjava.ordinal.easy;

import java.util.Arrays;

/**
 * Minimum cost for acquiring all coins with k extra coins allowed with every coin
 * Difficulty Level : Easy
 * Last Updated : 26 Apr, 2021
 * You are given a list of N coins of different denominations. You can pay an amount equivalent to any
 * 1 coin and can acquire that coin. In addition, once you have paid for a coin, we can choose at most K
 * more coins and can acquire those for free. The task is to find the minimum amount required to acquire
 * all the N coins for a given value of K.
 * <p>
 * Examples :
 * <p>
 * Input : coin[] = {100, 20, 50, 10, 2, 5},
 * k = 3
 * Output : 7
 * <p>
 * Input : coin[] = {1, 2, 5, 10, 20, 50},
 * k = 3
 * Output : 3
 * <p>
 * https://www.geeksforgeeks.org/minimum-cost-for-acquiring-all-coins-with-k-extra-coins-allowed-with-every-coin/
 **/
public class MinCostAcquiringKExtraCoin {

    public static void main(String[] args) {
        int[] coin = {8, 5, 3, 10, 2, 1, 15, 25};
        int n = coin.length;

        preprocess(coin, n);

        int k = 3;
        System.out.println(minCost(coin, n, k));

        k = 7;
        System.out.println(minCost1(coin, n, k));
    }

    /**
     * As per the question, we can see that at a cost of 1 coin, we can acquire at most K+1 coins. Therefore, in
     * order to acquire all the n coins, we will be choosing ceil(n/(k+1)) coins and the cost of choosing coins
     * will be minimum if we choose the smallest ceil(n/(k+1)) ( Greedy approach). The smallest ceil(n/(k+1)) coins
     * can be found by simply sorting all the N values in increasing order.
     * If we should check for time complexity (n log n) is for sorting element and (k) is for adding the total amount.
     * So, finally Time Complexity: O(n log n).
     * ==================================================================
     * function to calculate min cost
     * Time complexity = O(n log n)
     */
    static int minCost(int coin[], int n, int k) {

        // sort the coins value
        Arrays.sort(coin);

        // calculate no. of
        // coins needed
        int coins_needed = (int) Math.ceil(1.0 * n / (k + 1));

        // calculate sum of
        // all selected coins
        int ans = 0;

        for (int i = 0; i <= coins_needed - 1; i++)
            ans += coin[i];

        return ans;
    }


    // Converts coin[] to prefix sum array

    /**
     * How to handle multiple queries for a single predefined array?
     * In this case, if you are asked to find the above answer for many values of K,
     * you have to compute it fast and our time complexity got increased as per the number of queries for k.
     * For the purpose to serve, we can maintain a prefix sum array after sorting
     * all the N values and can answer queries easily and quickly.
     **/
    static void preprocess(int[] coin, int n) {

        // sort the coins value
        Arrays.sort(coin);

        // Maintain prefix sum array
        for (int i = 1; i <= n - 1; i++)
            coin[i] += coin[i - 1];
    }

    // Function to calculate min cost when we
    // can get k extra coins after paying
    // cost of one.
    static int minCost1(int[] coin, int n, int k) {

        // calculate no. of coins needed
        int coinsNeeded = (int) Math.ceil(1.0 * n / (k + 1));

        // return sum of from prefix array
        return coin[coinsNeeded - 1];
    }
}
