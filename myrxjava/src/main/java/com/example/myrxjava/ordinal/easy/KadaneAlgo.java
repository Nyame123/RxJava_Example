package com.example.myrxjava.ordinal.easy;

/**
 * Largest Sum Contiguous Subarray
 * Difficulty Level : Medium
 * Last Updated : 27 May, 2021
 * Write an efficient program to find the sum of contiguous subarray within
 * a one-dimensional array of numbers that has the largest sum.
 *
 * Kadane’s Algorithm:
 *
 * Initialize:
 *     max_so_far = INT_MIN
 *     max_ending_here = 0
 *
 * Loop for each element of the array
 *   (a) max_ending_here = max_ending_here + a[i]
 *   (b) if(max_so_far < max_ending_here)
 *             max_so_far = max_ending_here
 *   (c) if(max_ending_here < 0)
 *             max_ending_here = 0
 * return max_so_far
 *
 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 **/
public class KadaneAlgo {

    public static void main (String[] args) {
        int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum contiguous sum is ");
        maxSubArraySumWithRange(a);
        //minSubArrayForEachIndex(new int[]{3,-1,-2});
        minSubArrayForEachIndex(a);
    }

    static void minSubArrayForEachIndex(int[] arr){
        int n = arr.length;
        int[] outPut = new int[n];
        outPut[n-1] = arr[n-1];
        int minSoFar = arr[n-1];
        int curMin = arr[n-1];

        for (int i = n-2; i >= 0; i--){
            curMin += arr[i];
            if (curMin > arr[i]){
                curMin = arr[i];
            }

            if (curMin < minSoFar){
                minSoFar = curMin;
            }

            outPut[i] = minSoFar;
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("Min at Index %d = %d \n",i,outPut[i]);
        }
    }

    //Giving out Indices
    static void maxSubArraySumWithRange(int a[]) {
        int maxSoFar = Integer.MIN_VALUE,
                maxEndingHere = 0,start = 0,
                end = 0, s = 0;
        int size = a.length;
        for (int i = 0; i < size; i++){
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
            currMax = Math.max(a[i], currMax+a[i]);
            maxSoFar = Math.max(maxSoFar, currMax);
        }
        return maxSoFar;
    }

    static int maxSubArraySum(int a[]){
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
}
