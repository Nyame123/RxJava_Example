package com.example.myrxjava.search.medium;
/**
 * Merge Sort
 * Difficulty Level : Medium
 * Last Updated : 01 Jul, 2021
 * Like QuickSort, Merge Sort is a Divide and Conquer algorithm.
 * It divides the input array into two halves, calls itself for the two halves,
 * and then merges the two sorted halves. The merge() function is used for merging two halves.
 * The merge(arr, l, m, r) is a key process that assumes that arr[l..m] and arr[m+1..r]
 * are sorted and merges the two sorted sub-arrays into one. See the following C implementation for details.
 *
 * MergeSort(arr[], l,  r)
 * If r > l
 *      1. Find the middle point to divide the array into two halves:
 *              middle m = l+ (r-l)/2
 *      2. Call mergeSort for first half:
 *              Call mergeSort(arr, l, m)
 *      3. Call mergeSort for second half:
 *              Call mergeSort(arr, m+1, r)
 *      4. Merge the two halves sorted in step 2 and 3:
 *              Call merge(arr, l, m, r)
 *
 *   https://www.geeksforgeeks.org/merge-sort/
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1,12,32,12,234,4,56,67,8,7,54,3,54,56,4,67,87,977,543};
        sort(arr,0,arr.length-1,false);
        // Arrays.sort(arr);

        for (int j = 0; j < arr.length; ++j)
            System.out.print(arr[j] + ",");
    }

    static void merge(int arr[], int l, int m, int r,boolean desc) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (desc){
                if (L[i] >= R[j]) {
                    arr[k] = L[i];
                    i++;
                }
                else {
                    arr[k] = R[j];
                    j++;
                }
            }else{
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                }
                else {
                    arr[k] = R[j];
                    j++;
                }
            }

            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public static void sort(int[] arr, int l, int r, boolean desc) {
        if (l < r) {
            // Find the middle point
            int m =l + (r-l)/2;

            // Sort first and second halves
            sort(arr, l, m,desc);
            sort(arr, m + 1, r,desc);

            // Merge the sorted halves
            merge(arr, l, m, r,desc);
        }
    }

    // Main function that sorts arr[l..r] using

    // merge()
    public static void sort(Double[] arr, int l, int r, boolean desc) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m, desc);
            sort(arr, m + 1, r, desc);

            // Merge the sorted halves
            merge(arr, l, m, r, desc);
        }
    }

    static void merge(Double arr[], int l, int m, int r,boolean desc) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        double L[] = new double[n1];
        double R[] = new double[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (desc){
                if (L[i] >= R[j]) {
                    arr[k] = L[i];
                    i++;
                }
                else {
                    arr[k] = R[j];
                    j++;
                }
            }else{
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                }
                else {
                    arr[k] = R[j];
                    j++;
                }
            }

            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
