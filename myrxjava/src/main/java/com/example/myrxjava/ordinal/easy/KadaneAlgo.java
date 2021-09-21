package com.example.myrxjava.ordinal.easy;

/**
 * Largest Sum Contiguous Subarray
 * Difficulty Level : Medium
 * Last Updated : 27 May, 2021
 * Write an efficient program to find the sum of contiguous subarray within
 * a one-dimensional array of numbers that has the largest sum.
 * <p>
 * Kadane’s Algorithm:
 * <p>
 * Initialize:
 * max_so_far = INT_MIN
 * max_ending_here = 0
 * <p>
 * Loop for each element of the array
 * (a) max_ending_here = max_ending_here + a[i]
 * (b) if(max_so_far < max_ending_here)
 * max_so_far = max_ending_here
 * (c) if(max_ending_here < 0)
 * max_ending_here = 0
 * return max_so_far
 * <p>
 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 **/
public class KadaneAlgo {

    public static void main(String[] args) {
        int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum contiguous sum is ");
        maxSubArraySumWithRange(a);
       /* int sum = largestSumContigousSubArray(a);
        System.out.println("Maximum contiguous sum is "+sum);*/
        //minSubArrayForEachIndex(new int[]{3,-1,-2});
        minAtEachIndex(a);
    }

    static void minSubArrayForEachIndex(int[] arr) {
        int n = arr.length;
        int[] outPut = new int[n];
        outPut[n - 1] = arr[n - 1];
        int minSoFar = arr[n - 1];
        int curMin = arr[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            curMin += arr[i];
            if (curMin > arr[i]) {
                curMin = arr[i];
            }

            if (curMin < minSoFar) {
                minSoFar = curMin;
            }

            outPut[i] = minSoFar;
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("Min at Index %d = %d \n", i, outPut[i]);
        }
    }

    static void minAtEachIndex(int[] a) {
        int n = a.length;
        int minSoFar = a[n-1];
        int minHere = a[n-1];
        System.out.printf("Min At Index %d = %d\n", n - 1, minHere);
        for (int i = n - 2; i >= 0; i--) {

            minHere += a[i];
            if (a[i] < minHere) {
                minHere = a[i];
            }

            if (minHere < minSoFar) {
                minSoFar = minHere;
            }

            System.out.printf("Min At Index %d = %d\n", i, minSoFar);

        }

    }

    //Giving out Indices
    static void maxSubArraySumWithRange(int a[]) {
        int maxSoFar = Integer.MIN_VALUE,
                maxEndingHere = 0, start = 0,
                end = 0, s = 0;
        int size = a.length;
        for (int i = 0; i < size; i++) {
            maxEndingHere += a[i];

            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
                start = s;
                end = i;
            }

            if (maxEndingHere < 0) {
                maxEndingHere = 0;
                s = i + 1;
            }
        }
        System.out.println("Maximum contiguous sum is "
                + maxSoFar);
        System.out.println("Starting index " + start);
        System.out.println("Ending index " + end);
    }


    //Using DP
    static int maxSubArraySum(int a[], int size) {
        int maxSoFar = a[0];

        int currMax = a[0];

        for (int i = 1; i < size; i++) {
            currMax = Math.max(a[i], currMax + a[i]);
            maxSoFar = Math.max(maxSoFar, currMax);
        }
        return maxSoFar;
    }

    static int maxSubArraySum(int a[]) {
        int size = a.length;
        int maxSoFar = Integer.MIN_VALUE, maxEndingHere = 0;

        for (int i = 0; i < size; i++) {
            maxEndingHere = maxEndingHere + a[i];
            if (maxSoFar < maxEndingHere)
                maxSoFar = maxEndingHere;
            if (maxEndingHere < 0)
                maxEndingHere = 0;
        }
        return maxSoFar;
    }

    //using Kadane Algorithm
    static int largestSumContigousSubArray(int[] arr) {

        int maxSum = Integer.MIN_VALUE;
        int maxEndHere = 0;
        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {

            maxEndHere += arr[i];
            if (maxSum < maxEndHere) {
                end = i;
                maxSum = maxEndHere;
            }
            maxSum = Math.max(maxSum, maxEndHere);
            if (maxEndHere < 0) {
                maxEndHere = 0;
                start = i + 1;
            }
        }

        System.out.println("Start at " + start);
        System.out.println("End at " + end);
        return maxSum;

    }
}
