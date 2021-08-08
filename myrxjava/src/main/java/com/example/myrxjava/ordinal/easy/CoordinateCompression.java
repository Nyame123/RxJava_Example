package com.example.myrxjava.ordinal.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Convert an array to reduced form | Set 1 (Simple and Hashing)
 * Difficulty Level : Easy
 * Last Updated : 07 Jul, 2021
 * Given an array with n distinct elements, convert the given array to a form where all elements are
 * in the range from 0 to n-1. The order of elements is the same, i.e., 0 is placed in the place of
 * the smallest element, 1 is placed for the second smallest element, … n-1 is placed for the largest element.
 *
 * Input:  arr[] = {10, 40, 20}
 * Output: arr[] = {0, 2, 1}
 *
 * Input:  arr[] = {5, 10, 40, 30, 20}
 * Output: arr[] = {0, 1, 4, 3, 2}
 * The expected time complexity is O(n Log n).
 *
 * ====================================================
 * STEPS
 * Method 1 (Simple)
 * A simple solution is to first find the minimum element, replace it with 0, consider the remaining array and find the minimum in the remaining array and replace it with 1, and so on. The time complexity of this solution is O(n2)
 *
 * Method 2 (Efficient)
 * The idea is to use hashing and sorting. Below are the steps.
 * 1) Create a temp array and copy the contents of the given array to temp[]. This takes O(n) time.
 * 2) Sort temp[] in ascending order. This takes O(n Log n) time.
 * 3) Create an empty hash table. This takes O(1) time.
 * 4) Traverse temp[] from left to right and store mapping of numbers and their values (in converted array) in the hash table. This takes O(n) time on average.
 * 5) Traverse given array and change elements to their positions using a hash table. This takes O(n) time on average.
 *
 *
 * The overall time complexity of this solution is O(n Log n).
 *
 *
 * https://www.geeksforgeeks.org/convert-an-array-to-reduced-form-set-1-simple-and-hashing/
 *
 **/
public class CoordinateCompression {

    // Driver code
    public static void main(String[] args) {

        int arr[] = {10, 20, 15, 12, 11, 50};
        int n = arr.length;

        System.out.println("Given Array is ");
        printArr(arr, n);

        convert(arr , n);

        System.out.println("\n\nConverted Array is ");
        printArr(arr, n);

    }
    public static void convert(int arr[], int n) {
        // Create a temp array and copy contents
        // of arr[] to temp
        int temp[] = arr.clone();

        // Sort temp array
        Arrays.sort(temp);

        // Create a hash table.
        HashMap<Integer, Integer> umap = new HashMap<>();

        // One by one insert elements of sorted
        // temp[] and assign them values from 0
        // to n-1
        int val = 0;
        for (int i = 0; i < n; i++)
            umap.put(temp[i], val++);

        // Convert array by taking positions from
        // umap
        for (int i = 0; i < n; i++)
            arr[i] = umap.get(arr[i]);
    }

    public static void printArr(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }


}
