package com.example.myrxjava.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array containing only positive integers
 * We are to check if the array can be partitioned into two subset with the
 * same sum
 **/
public class PartitionEqualSubset {

    public static void main(String[] args) {
        int[] arr = {5,1,11,5,8,2};
        if (canPartition(arr)){
            System.out.println("Can be partitioned");
        }else{
            System.out.println("Cannot be partitioned");
        }
    }

    static boolean canPartition(int[] arr) {
        //grab the total sum of the elements in the array
        int total = 0;

        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }

        int target = total/2;
        //if the total is odd then we cannot have equal partition
        if (total % 2 != 0){
            return false;
        }

        //return hasSubsetPartition(arr,target);
        //we can use a dynamic programming to look for subset that gives
        //sum equal to total / 2;
        Map<String,Boolean> memo = new HashMap<>();
        return canPartition(arr,target,0,memo);
    }

    static boolean canPartition(int[] arr, int sum, int target, int index,Map<String,Boolean> memo){

        String current = index + "" + sum;
        if (memo.containsKey(current))
            return memo.get(current);

        if (sum*2 == target){
            return true;
        }

        if (sum > target/2 || index >= arr.length){
            return false;
        }

        boolean result = canPartition(arr,sum,target,index + 1,memo) || canPartition(arr,
                sum+arr[index],target,index+1,memo);
        memo.put(current,result);
        return result;
    }

    static boolean canPartition(int[] arr, int target, int i,Map<String,Boolean> memo) {

        String current = i + "" + target;
        if (memo.containsKey(current))
            return memo.get(current);
        //base cases
        if (target == 0)
            return true;

        if (target < 0)
            return false;

        for (int j = i+1; j < arr.length; j++) {
            if (canPartition(arr,target-arr[j],j,memo)){
                memo.put(current,true);
                return true;
            }
        }

        memo.put(current,false);
        return false;


    }

    private static boolean hasSubsetPartition(int[] arr, int target) {

        //concept is to all subset and check if there is a subset
        //with total sum == total/2
        for (int i = 0; i < 1 << arr.length; i++) {
            int pos = arr.length-1;
            int bitMask = i;
            int sum = target;
            while (bitMask != 0){
                if ((bitMask & 1) == 1){
                    sum -= arr[pos];
                }
                pos--;
                bitMask >>= 1;
            }

            if (sum == 0){
                return true;
            }
        }

        return false;
    }
}
