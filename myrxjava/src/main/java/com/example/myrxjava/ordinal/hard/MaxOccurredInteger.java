package com.example.myrxjava.ordinal.hard;
/**
 * Maximum occurred integer in n ranges
 * Difficulty Level : Hard
 * Last Updated : 08 Jun, 2021
 * Given n ranges of the form L and R, the task is to find the maximum occurred integer in all the ranges.
 * If more than one such integer exists, print the smallest one.
 * 0 <= Li, Ri < 1000000.
 * Examples :
 *
 *
 * Input : L1 = 1 R1 = 15
 *         L2 = 4 R2 = 8
 *         L3 = 3 R3 = 5
 *         L3 = 1 R3 = 4
 * Output : 4
 *
 * Input : L1 = 1 R1 = 15
 *         L2 = 5 R2 = 8
 *         L3 = 9 R3 = 12
 *         L4 = 13 R4 = 20
 *         L5 = 21 R5 = 30
 * Output : 5
 * Numbers having maximum occurrence i.e 2  are 5, 6,
 * 7, 8, 9, 10, 11, 12, 13, 14, 15. The smallest number
 * among all are 5.
 *
 * https://www.geeksforgeeks.org/maximum-occurred-integer-n-ranges/
 **/
public class MaxOccurredInteger {
    public static void main(String[] args) {
        int[] L = { 1, 4, 9, 13, 21 };
        int[] R = { 15, 8, 12, 20, 30 };
        int n = L.length;
        System.out.println(maxOccInteger(L, R, n));
    }
    static int MAX = 1000000;

    // Return the maximum occurred element in all ranges.
    // Time complexity = O(n + MAX)
    static int maxOccInteger(int[] L, int[] R, int n){


            // Initialising all element of array to 0.
            int[] arr = new int[MAX];

            // Adding +1 at Li index and
            // substracting 1 at Ri index.
            int maxi=-1;
            for (int i = 0; i < n; i++) {
                arr[L[i]] += 1;
                arr[R[i] + 1] -= 1;
                if(R[i]>maxi){
                    maxi=R[i];
                }
            }

            // Finding prefix sum and index
            // having maximum prefix sum.
            int msum = arr[0];
            int ind = 0;
            for (int i = 1; i < maxi+1; i++) {
                arr[i] += arr[i - 1];
                if (msum < arr[i]) {
                    msum = arr[i];
                    ind = i;
                }
            }

            return ind;

    }
}
