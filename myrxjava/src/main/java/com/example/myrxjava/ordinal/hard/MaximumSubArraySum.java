package com.example.myrxjava.ordinal.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * Maximum subarray sum modulo m
 * Difficulty Level : Hard
 * <p>
 * Given an array of n elements and an integer m. The task is to find the maximum value of the
 * sum of its subarray modulo m i.e find the sum of each subarray mod m and print the
 * maximum value of this modulo operation.
 * Examples:
 * <p>
 * <p>
 * Input : arr[] = { 3, 3, 9, 9, 5 }
 * m = 7
 * Output : 6
 * All sub-arrays and their value:
 * { 9 } => 9%7 = 2
 * { 3 } => 3%7 = 3
 * { 5 } => 5%7 = 5
 * { 9, 5 } => 14%7 = 2
 * { 9, 9 } => 18%7 = 4
 * { 3, 9 } => 12%7 = 5
 * { 3, 3 } => 6%7 = 6
 * { 3, 9, 9 } => 21%7 = 0
 * { 3, 3, 9 } => 15%7 = 1
 * { 9, 9, 5 } => 23%7 = 2
 * { 3, 3, 9, 9 } => 24%7 = 3
 * { 3, 9, 9, 5 } => 26%7 = 5
 * { 3, 3, 9, 9, 5 } => 29%7 = 1
 * <p>
 * Input : arr[] = {10, 7, 18}
 * m = 13
 * Output : 12
 * The subarray {7, 18} has maximum sub-array
 * sum modulo 13.
 * <p>
 * https://www.geeksforgeeks.org/maximum-subarray-sum-modulo-m/
 **/
public class MaximumSubArraySum {

    public static void main(String[] args) {
        int arr[] = {3, 3, 9, 9, 5};
        int mod = 7;

       // maxSubArrSumMod(arr, mod);
        int max = maxSubarray(arr, arr.length, mod);
        System.out.print(max);
    }

    // Return the maximum sum subarray mod m.
    static int maxSubarray(int[] arr, int n, int m) {
        int prefix = 0;
        int maxim = 0;
        Set<Integer> S = new HashSet<>();
        S.add(0);

        // Traversing the array.
        for (int i = 0; i < n; i++) {

            // Finding prefix sum.
            prefix = (prefix + arr[i]) % m;

            // Finding maximum of prefix sum.
            maxim = Math.max(maxim, prefix);

            // Finding iterator poing to the first
            // element that is not less than value
            // "prefix + 1", i.e., greater than or
            // equal to this value.
            int it = 0;

            for (int j : S) {
                if (j >= prefix + 1)
                    it = j;
            }
            if (it != 0) {
                maxim = Math.max(maxim, prefix - it + m);
            }

            // adding prefix in the set.
            S.add(prefix);
        }
        return maxim;
    }

    //brute force solution
    //Time Complexity = O(n^2)
    //Space Complexity = O(1)
    static void maxSubArrSumMod(int[] arr, int mod) {

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                int modulo = sum % mod;
                max = Math.max(max,modulo );
            }
        }

        System.out.println(max);
    }

    //brute force solution
    //Time Complexity = O(n^2)
    //Space Complexity = O(1)
    static void maxSubArrSumModPrefixSum(int[] arr, int mod) {
/**
 *  arr[] = { 10, 7, 18 }
 * */
        int max = 0;
        System.out.println("Starting ==================");
        for (int i = 1; i <= arr.length; i++) {
            System.out.println("level "+i);
            for (int j = 0; j < arr.length - i + 1; j++) {
                int start = j;
                int end = j + i;
                int sum = 0;
                StringBuilder stringBuilder = new StringBuilder();
                while (start < end){
                    stringBuilder.append(arr[start]).append(",");
                    sum += arr[start];
                    start++;
                }
                System.out.printf("(%s),",stringBuilder.toString());
                max = Math.max(max,sum % mod);
            }
            System.out.println();
            // prefixSum[i] += arr[i];

        }

        System.out.println(max);
    }

}
