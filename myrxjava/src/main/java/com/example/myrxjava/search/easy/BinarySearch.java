package com.example.myrxjava.search.easy;

/**
 * Binary Search
 * Difficulty Level : Easy
 * Last Updated : 28 Jun, 2021
 * Given a sorted array arr[] of n elements, write a function to search a given element x in arr[].
 * A simple approach is to do a linear search. The time complexity of the above algorithm is O(n).
 * Another approach to perform the same task is using Binary Search.
 * Binary Search: Search a sorted array by repeatedly dividing the search interval in half.
 * Begin with an interval covering the whole array. If the value of the search key is less than
 * the item in the middle of the interval, narrow the interval to the lower half. Otherwise,
 * narrow it to the upper half. Repeatedly check until the value is found or the interval is empty.
 * <p>
 * https://www.geeksforgeeks.org/binary-search/
 **/
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {2, 3, 4, 10, 40};
        int n = arr.length;
        int x = 10;
        int result = binarySearch(arr, 0, n - 1, x);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at index " + result);
    }

    static int binarySearch(int arr[], int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == x)
                return m;

            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }


    // Returns index of x if it is present in arr[l..
    // r], else return -1
    //Time Complexity = O(log(n))
    static int binarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }
}
