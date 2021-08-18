package com.example.myrxjava.search.easy;
/**
 * Linear Search
 * Difficulty Level : Basic
 * Last Updated : 28 Jun, 2021
 * Problem: Given an array arr[] of n elements, write a function to search a given element x in arr[].
 *
 * Examples :
 *
 * Input : arr[] = {10, 20, 80, 30, 60, 50,
 *                      110, 100, 130, 170}
 *           x = 110;
 * Output : 6
 * Element x is present at index 6
 *
 * Input : arr[] = {10, 20, 80, 30, 60, 50,
 *                      110, 100, 130, 170}
 *            x = 175;
 * Output : -1
 * Element x is not present in arr[].
 * Recommended: Please solve it on PRACTICE first, before moving on to the solution.
 *
 * A simple approach is to do a linear search, i.e
 *
 * Start from the leftmost element of arr[] and one by one compare x with each element of arr[]
 * If x matches with an element, return the index.
 * If x doesn’t match with any of elements, return -1.
 *
 *
 * https://www.geeksforgeeks.org/linear-search/
 **/
public class LinearSearch {

    public static void main(String[] args) {
        int arr[] = { 2, 3, 4, 10, 40 };
        int x = 10;

        // Function call
        /*int result = search(arr, x);
        if (result == -1)
            System.out.print(
                    "Element is not present in array");
        else
            System.out.print("Element is present at index "
                    + result);*/

        searchMeth1(arr,x);
    }


    //Time Complexity = O(n)
    public static int search(int arr[], int x) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == x)
                return i;
        }
        return -1;
    }


    //Time Complexity = O(n/2)
    public static void searchMeth1(int arr[], int searchElement) {
        int left = 0;
        int length = arr.length;
        int right = length - 1;
        int position = -1;

        // run loop from 0 to right
        for (left = 0; left <= right;) {

            // if search element is found with left variable
            if (arr[left] == searchElement) {
                position = left;
                System.out.println(
                        "Element found in Array at "
                                + (position + 1) + " Position with "
                                + (left + 1) + " Attempt");
                break;
            }

            // if search_element is found with right variable
            if (arr[right] == searchElement) {
                position = right;
                System.out.println(
                        "Element found in Array at "
                                + (position + 1) + " Position with "
                                + (length - right) + " Attempt");
                break;
            }

            left++;
            right--;
        }

        // if element not found
        if (position == -1)
            System.out.println("Not found in Array with "
                    + left + " Attempt");
    }

}
