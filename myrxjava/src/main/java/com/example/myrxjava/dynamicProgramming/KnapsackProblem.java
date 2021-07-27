package com.example.myrxjava.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Admin
 * This algorithm calculates the max values of item to pick to
 * fit into a bag of capacity W
 *
 *  References -
 *  * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 *  * https://en.wikipedia.org/wiki/Knapsack_problem
 **/
public class KnapsackProblem {

    public static void main(String[] args) {
        /*int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};*/
        int val[] = {1,4,5,7};
        int wt[] = {1,3,4,5};
        int weight = 7;
      //  knapsackTabulation(wt,val,weight);

        KnapsackProblem knapsackProblem = new KnapsackProblem();

        int r1 = knapsackProblem.topDownRecursive(val, wt, weight);
        System.out.println("Maximized values to picked Using recursion is "+ r1);
    }

    /**
     * Key for memoization
     */
    class Index {
        int remainingWeight;
        int remainingItems;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Index index = (Index) o;

            if (remainingWeight != index.remainingWeight) return false;
            return remainingItems == index.remainingItems;

        }

        @Override
        public int hashCode() {
            int result = remainingWeight;
            result = 31 * result + remainingItems;
            return result;
        }
    }

    /**
     * Solves 0/1 knapsack in top down DP
     */
    public int topDownRecursive(int values[], int weights[], int W) {
        //map of key(remainingWeight, remainingCount) to maximumValue they can get.
        Map<Index, Integer> map = new HashMap<>();
        return topDownRecursiveUtil(values, weights, W, 0, map);
    }

    public int topDownRecursiveUtil(int values[], int weights[], int remainingWeight, int currentItem, Map<Index, Integer> map) {
        //if currentItem exceeds total item count or remainingWeight is less than 0 then
        //just return with 0;
        if(currentItem >= weights.length || remainingWeight <= 0) {
            return 0;
        }

        //fom a key based on remainingWeight and remainingCount
        Index key = new Index();
        key.remainingItems = weights.length - currentItem -1;
        key.remainingWeight = remainingWeight;

        //see if key exists in map. If so then return the maximumValue for key stored in map.
        if(map.containsKey(key)) {
            return map.get(key);
        }
        int maxValue;
        //if weight of item is more than remainingWeight then try next item by skipping current item
        if(remainingWeight < weights[currentItem]) {
            maxValue = topDownRecursiveUtil(values, weights, remainingWeight, currentItem + 1, map);
        } else {
            //try to get maximumValue of either by picking the currentItem or not picking currentItem
            maxValue = Math.max(values[currentItem] + topDownRecursiveUtil(values, weights,
                    remainingWeight - weights[currentItem], currentItem + 1, map),
                    topDownRecursiveUtil(values, weights, remainingWeight, currentItem + 1, map));
        }
        //memoize the key with maxValue found to avoid recalculation
        map.put(key, maxValue);
        return maxValue;

    }


    public static void knapsackTabulation(int[] wts,int[] vals, int W){

        int[][] K = new int[wts.length+1][W+1];
        for (int i = 1; i < K.length; i++){
            for (int j = 1; j < K[i].length; j++){

                if (wts[i-1] > j){
                    K[i][j] = K[i-1][j];
                }else{
                    K[i][j] = Math.max(vals[i-1] + K[i-1][j - wts[i-1]],K[i-1][j]);
                }
            }
        }

        System.out.println("Maximized values to picked is "+ K[vals.length][W]);
    }
}
