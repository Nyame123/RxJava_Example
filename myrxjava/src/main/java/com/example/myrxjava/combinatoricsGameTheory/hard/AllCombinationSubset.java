package com.example.myrxjava.combinatoricsGameTheory.hard;

import java.util.Arrays;

/**
 *  Print all possible combinations of r elements in a given array of size n
 * Difficulty Level : Hard
 * Last Updated : 11 Jun, 2021
 *
 * Given an array of size n, generate and print all possible combinations of r
 * elements in array. For example, if input array is {1, 2, 3, 4} and r is 2,
 * then output should be {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4} and {3, 4}.
 * Following are two methods to do this.
 * Method 1 (Fix Elements and Recur)
 * We create a temporary array ‘data[]’ which stores all outputs one by one.
 * The idea is to start from first index (index = 0) in data[], one by one fix elements
 * at this index and recur for remaining indexes. Let the input array be {1, 2, 3, 4, 5}
 * and r be 3. We first fix 1 at index 0 in data[], then recur for remaining indexes, then
 * we fix 2 at index 0 and recur. Finally, we fix 3 and recur for remaining indexes.
 * When number of elements in data[] becomes equal to r (size of a combination), we print data[].
 * Following diagram shows recursion tree for same input.
 *
 * https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 **/
public class AllCombinationSubset {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5};
        int r = 3;
        int n = arr.length;
        printCombination(arr, n, r);
    }


    /**
     * Method 1 (Fix Elements and Recur)
     * ==============================================================
     * arr[]  ---> Input Array
     * data[] ---> Temporary array to store current combination
     * start & end ---> Starting and Ending indexes in arr[]
     * index  ---> Current index in data[]
     * r ---> Size of a combination to be printed
     **/
    static void combinationUtil(int arr[], int data[], int start, int end, int index, int r) {
        // Current combination is ready to be printed, print it
        if (index == r) {
            for (int j=0; j<r; j++)
                System.out.print(data[j]+" ");
            System.out.println("");
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i=start; i<=end && end-i+1 >= r-index; i++) {
            if (i != end && arr[i] == arr[i+1]) //checks for duplicates and end cases
                continue;
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r);
        }
    }

    /**
     * METHOD 2 (Include and Exclude every element)
     * =================================================================
     * arr[]  ---> Input Array
     *     data[] ---> Temporary array to store current combination
     *     start & end ---> Staring and Ending indexes in arr[]
     *     index  ---> Current index in data[]
     *     r ---> Size of a combination to be printed
     **/
    static void combinationUtil(int arr[], int n, int r, int index, int data[], int i) {
        // Current combination is ready to be printed, print it
        if (index == r) {
            for (int j=0; j<r; j++)
                System.out.print(data[j]+" ");
            System.out.println("");
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= n)
            return;

        // current is included, put next at next location
        data[index] = arr[i];
        combinationUtil(arr, n, r, index+1, data, i+1);

        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        combinationUtil(arr, n, r, index, data, i+1);
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static void printCombination(int arr[], int n, int r) {
        // A temporary array to store all combination one by one
        int data[]=new int[r];

        //In case there is duplicates
        Arrays.sort(arr);
        // Print all combination using temporary array 'data[]'
//        combinationUtil(arr, data, 0, n-1, 0, r);
        combinationUtil(arr, n, r,0,data, 0);
    }
}
