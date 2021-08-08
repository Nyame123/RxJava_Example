package com.example.myrxjava.ordinal.easy.greedy;
/**
 * Window Sliding Technique
 * Difficulty Level : Easy
 * Last Updated : 10 Jul, 2021
 * This technique shows how a nested for loop in some problems can be
 * converted to a single for loop to reduce the time complexity.
 * Let’s start with a problem for illustration where we can apply this technique –
 *
 * Given an array of integers of size ‘n’.
 * Our aim is to calculate the maximum sum of ‘k’
 * consecutive elements in the array.
 *
 * Input  : arr[] = {100, 200, 300, 400}
 *          k = 2
 * Output : 700
 *
 * Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
 *          k = 4
 * Output : 39
 * We get maximum sum by adding subarray {4, 2, 10, 23}
 * of size 4.
 *
 * Input  : arr[] = {2, 3}
 *          k = 3
 * Output : Invalid
 * There is no subarray of size 3 as size of whole
 * array is 2.
 *
 * Window Sliding Technique
 *
 * The technique can be best understood with the window pane in bus, consider a window of length n
 * and the pane which is fixed in it of length k. Consider, initially the pane is at extreme
 * left i.e., at 0 units from the left. Now, co-relate the window with array arr[] of size n
 * and pane with current_sum of size k elements. Now, if we apply force on the window such
 * that it moves a unit distance ahead. The pane will cover next k consecutive elements.
 * Consider an array arr[] = {5, 2, -1, 0, 3} and value of k = 3 and n = 5
 * Applying sliding window technique :
 *
 * We compute the sum of first k elements out of n terms using a linear loop and store the sum in variable window_sum.
 * Then we will graze linearly over the array till it reaches the end and simultaneously keep track of maximum sum.
 * To get the current sum of block of k elements just subtract the first element from the
 * previous block and add the last element of the current block .
 **/
public class WindowSlicingTechn {

    public static void main(String[] args) {
        int arr[] = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
        //int arr[] = { 5,2,-1,0,3 };
        int k = 4;
        int n = arr.length;
        System.out.println(maxSum(arr, n, k));
    }

    // Returns maximum sum in
    // a subarray of size k.
    //Time Complexity is O(n)
    static int maxSum(int arr[], int n, int k) {
        // n must be greater
        if (n < k) {
            System.out.println("Invalid");
            return -1;
        }

        // Compute sum of first window of size k
        int max_sum = 0;
        for (int i = 0; i < k; i++)
            max_sum += arr[i];

        // Compute sums of remaining windows by
        // removing first element of previous
        // window and adding last element of
        // current window.
        int window_sum = max_sum;
       /*
       Another method
       for (int i = 1; i < n-k; i++) {
            window_sum += arr[i+k];
            window_sum -= arr[i - 1];
            max_sum = Math.max(max_sum, window_sum);
        }*/

        for (int i = k; i < n; i++) {
            window_sum += arr[i] - arr[i - k];
            max_sum = Math.max(max_sum, window_sum);
        }

        return max_sum;
    }
}
