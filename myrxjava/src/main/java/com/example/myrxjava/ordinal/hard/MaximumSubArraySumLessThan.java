package com.example.myrxjava.ordinal.hard;

/**
 * Maximum subarray size, such that all subarrays of that size have sum less than k
 * Difficulty Level : Hard
 * Last Updated : 28 Jun, 2021
 * Given an array of n positive integers and a positive integer k, the task is to find the maximum subarray size such that all subarrays of that size have the sum of elements less than or equals to k.
 *
 * Examples :
 *
 * Input :  arr[] = {1, 2, 3, 4} and k = 8.
 * Output : 2
 * Sum of subarrays of size 1: 1, 2, 3, 4.
 * Sum of subarrays of size 2: 3, 5, 7.
 * Sum of subarrays of size 3: 6, 9.
 * Sum of subarrays of size 4: 10.
 * So, maximum subarray size such that all subarrays of that size have the sum of elements less than 8 is 2.
 *
 * Input :  arr[] = {1, 2, 10, 4} and k = 8.
 * Output : -1
 * There is an array element with value greater than k, so subarray sum cannot be less than k.
 *
 * Input :  arr[] = {1, 2, 10, 4} and K = 14
 * Output : 2
 *
 * https://www.geeksforgeeks.org/maximum-subarray-size-subarrays-size-sum-less-k/
 **/
public class MaximumSubArraySumLessThan {

    public static void main(String[] args) {
        int arr[] = {1, 2, 10, 4};
//        int arr[] = { 10,2,1,4};
        int n = arr.length;
        int k = 14;
        System.out.println(maxSize(arr, n, k));
//        meth1(arr,k);
        func(arr,k,n);
    }

    //brute force solution
    //Time Complexity = O(n^2)
    //Space Complexity = O(1)
    static void meth1(int[] arr, int k) {

        int max = 0; int maxVal = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                /*if (sum < k && sum > maxVal){
                    maxVal = sum;
                    len = (j - i) + 1;
                }*/

                if (sum < k){
                    if (sum > maxVal){
                        maxVal = sum;
                        int len = j - i+1;
                        max = Math.min(max,len);
                    }

                }

            }
        }

        System.out.println(max);
    }

    // Function to find the
    // largest size subarray
    //Time Complexity = O(n)
    public static void func(int arr[], int k, int n) {

        // Variable declaration
        int ans = n;
        int sum = 0;
        int start = 0;

        // Loop till N
        for(int end = 0; end < n; end++) {

            // Sliding window from left
            sum += arr[end];

            while (sum > k) {

                // Sliding window from right
                sum -= arr[start];
                start++;

                // Storing sub-array size - 1
                // for which sum was greater than k
                ans = Math.min(ans, end - start + 1);

                // Sum will be 0 if start>end
                // because all elements are positive
                // start>end only when arr[end]>k i.e,
                // there is an array element with
                // value greater than k, so sub-array
                // sum cannot be less than k.
                if (sum == 0)
                    break;
            }

            if (sum == 0) {
                ans = -1;
                break;
            }
        }

        // Print the answer
        System.out.println(ans);
    }

    // Search for the maximum length
    // of required subarray.
    //time complexity = O(nlog n)
    static int bsearch(int prefixsum[], int n, int k) {
        // Initialize result
        int ans = -1;

        // Do Binary Search for largest
        // subarray size
        int left = 1, right = n;

        while(left <= right) {
            int mid = (left + right) >>> 1;

            // Check for all subarrays after mid
            int i;
            for (i = mid; i <= n; i++) {

                // Checking if all the subarrays
                // of a size is less than k.
                if (prefixsum[i] - prefixsum[i - mid] > k)
                    break;
            }

            // All subarrays of size mid have
            // sum less than or equal to k
            if (i == n + 1) {
                left = mid + 1;
                ans = mid;
            }

            // We found a subrray of size mid
            // with sum greater than k
            else
                right = mid - 1;
        }

        return ans;
    }

    // Return the maximum subarray size, such
    // that all subarray of that size have
    // sum less than K.
    static int maxSize(int arr[], int n, int k) {

        // Initialize prefix sum array as 0.
        int prefixsum[] = new int[n + 1];

        // Finding prefix sum of the array.
        for (int i = 0; i < n; i++)
            prefixsum[i + 1] = prefixsum[i] + arr[i];

        return bsearch(prefixsum, n, k);
    }
}
