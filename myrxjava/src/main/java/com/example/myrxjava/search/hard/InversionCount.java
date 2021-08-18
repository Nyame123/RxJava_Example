package com.example.myrxjava.search.hard;

import java.util.Arrays;

/**
 * Count Inversions in an array | Set 1 (Using Merge Sort)
 * Difficulty Level : Hard
 * Last Updated : 28 Jun, 2021
 * <p>
 * Inversion Count for an array indicates – how far (or close) the array is
 * from being sorted. If the array is already sorted, then the
 * inversion count is 0, but if the array is sorted in the reverse order, the inversion count is the maximum.
 * Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j
 * Example:
 * <p>
 * Input: arr[] = {8, 4, 2, 1}
 * Output: 6
 * <p>
 * Explanation: Given array has six inversions:
 * (8, 4), (4, 2), (8, 2), (8, 1), (4, 1), (2, 1).
 * <p>
 * <p>
 * Input: arr[] = {3, 1, 2}
 * Output: 2
 * <p>
 * Explanation: Given array has two inversions:
 * (3, 1), (3, 2)
 **/
public class InversionCount {

    static int arr[] = new int[]{1, 20, 6, 4, 5};

    // Driver method to test the above function
    public static void main(String[] args) {
        System.out.println("Number of inversions are "
                + mergeSortAndCount(arr,0,arr.length-1));
    }

    // Function to count the number of inversions
    // during the merge process
    private static int mergeAndCount(int[] arr, int l,
                                     int m, int r) {

        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }
        while (i < left.length)
            arr[k++] = left[i++];
        while (j < right.length)
            arr[k++] = right[j++];
        return swaps;
    }

    // Merge sort function
    //Time Complexity = O(nlog(n))
    //Space Complexity = O(n)
    private static int mergeSortAndCount(int[] arr, int l, int r) {

        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left subarray count
            // + right subarray count + merge count

            // Left subarray count
            count += mergeSortAndCount(arr, l, m);

            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }

        return count;
    }


    //Time Complexity = O(n^2)
    //Space Complexity = O(1)
    static int getInvCount(int n) {
        int inv_count = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (arr[i] > arr[j])
                    inv_count++;

        return inv_count;
    }


}
