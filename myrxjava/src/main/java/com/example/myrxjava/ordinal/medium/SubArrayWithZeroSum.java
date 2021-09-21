package com.example.myrxjava.ordinal.medium;

import java.util.HashMap;

/***
 * Find if there is a subarray with 0 sum
 * Difficulty Level : Medium
 * Last Updated : 08 Feb, 2021
 * Given an array of positive and negative numbers, find if there is a subarray (of size at-least one) with 0 sum.
 *
 * Examples :
 *
 * Input: {4, 2, -3, 1, 6}
 * Output: true
 * Explanation:
 * There is a subarray with zero sum from index 1 to 3.
 *
 * Input: {4, 2, 0, 1, 6}
 * Output: true
 * Explanation:
 * There is a subarray with zero sum from index 2 to 2.
 *
 * Input: {-3, 2, 3, 1, 6}
 * Output: false
 **/
public class SubArrayWithZeroSum {

    public static void main(String[] args) {
        int arr[] = { 4, 2, -3, 1, 6};
        if (subArrayExists(arr))
            System.out.println(
                    "Found a subarray with 0 sum");
        else
            System.out.println("No Such Sub Array Exists!");
    }

    //Time Complexity = O(n^2)
    static boolean subArrayExists(int[] a){
        for (int i = 0; i < a.length; i++){
            int sum = a[i];
            for (int j = i+1; j < a.length; j++) {
                if (sum == 0){
                    return true;
                }
                sum += a[j];
            }
        }

        return false;
    }

    //Time Complexity = O(n)
    static boolean subArrayWithZero(int[] a){
        HashMap<Integer,Integer> prefixSum = new HashMap<Integer,Integer>();
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            sum += a[i];

            if(a[i] == 0){
                return true;
            }

            if(sum == 0){
                return true;
            }

            if(prefixSum.containsKey(sum)){
                int index = prefixSum.get(sum);
                return true;
            }

            prefixSum.put(sum,i);

        }

        return false;
    }

}
