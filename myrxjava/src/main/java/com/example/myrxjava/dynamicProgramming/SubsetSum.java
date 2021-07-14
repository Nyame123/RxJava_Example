package com.example.myrxjava.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Admin
 * Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up
 * to given total. Another variation is given an array is it possible to split it up into 2 equal
 * sum partitions. Partition need not be equal sized. Just equal sum.
 * <p>
 * Time complexity - O(input.size * total_sum)
 * Space complexity - O(input.size*total_sum)
 */
public class SubsetSum {

    public static void main(String[] args) {

        //subsetSumTabulation(new int[]{2,3,7,8,10},11);
        runRecursiveSubsetSum();
    }

    private static void runRecursiveSubsetSum(){
        Map<Integer,Boolean> memo = new HashMap<>();
        boolean result = subsetSumRecursion(new int[]{2,3,7,8,10},11,memo);

        System.out.println("Possibility of subset Sum  = " + result);
    }

    private static boolean subsetSumRecursion(int[] arr, int sum, Map<Integer,Boolean> memo) {
        if (memo.containsKey(sum)) return memo.get(sum);
        if (sum == 0) return true;
        if (sum < 0) return false;


        for (int i = 0; i < arr.length; i++){
            int remainder = sum - arr[i];
            if (subsetSumRecursion(arr,remainder,memo)){
                memo.put(remainder,true);
                return true;
            }
        }

        memo.put(sum,false);
        return false;
    }

    private static void subsetSumTabulation(int[] arr, int sum) {
        boolean[][] T = new boolean[arr.length + 1][sum + 1];
        for (int i = 0; i <= arr.length; i++) {
            T[i][0] = true;
        }

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[i].length; j++) {
                if (j < arr[i - 1]) {
                    T[i][j] = T[i - 1][j];
                } else {
                    T[i][j] = T[i - 1][j] || T[i - 1][j - arr[i - 1]];
                }
            }
        }

        System.out.println("Possibility of subset Sum  = " + T[arr.length][sum]);
    }

    public boolean partition(int arr[]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        boolean[][] T = new boolean[arr.length + 1][sum + 1];

        for (int i = 0; i <= arr.length; i++) {
            T[i][0] = true;
        }

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - arr[i - 1] >= 0) {
                    T[i][j] = T[i - 1][j - arr[i - 1]] || T[i - 1][j];
                } else {
                    T[i][j] = T[i-1][j];
                }
            }
        }
        return T[arr.length][sum];
    }
}
