package com.example.myrxjava.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class MinimumCoinChanging {

    public static void main(String[] args) {

        //findMinimumCoinChanging(new int[]{3,5,6,8},9);
        MinimumCoinChanging coinChanging = new MinimumCoinChanging();
        coinChanging.minimumCoinBottomUp(10,new int[]{3,5,6,8});
        System.out.println(coinChanging.minimumCoinTopDown(10,new int[]{3,5,6,8},new HashMap<>()));
    }

    private static void findMinimumCoinChanging(int[] arr, int total){

        int[][] coinChanging = new int[arr.length][total+1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j <= total; j++) {

                if (arr[i] > j) {
                    if (i == 0){
                        coinChanging[i][j] = 21474836;
                    }else{
                        coinChanging[i][j] = coinChanging[i-1][j];
                    }

                }else if (arr[i] == j){
                    coinChanging[i][j] = 1;
                }else{
                    if (i == 0){
                        int left = coinChanging[i][j - arr[i]];
                        coinChanging[i][j] = 1 + left;
                    }else {
                        int left = coinChanging[i][j - arr[i]];
                        coinChanging[i][j] = Math.min(coinChanging[i - 1][j], 1 + left);
                    }
                }

            }
        }

        System.out.println("Min number of coins = "+ coinChanging[arr.length-1][total]);
    }

    private int min(int a,int b, int c){
        int l = Math.min(a, b);
        return Math.min(l, c);
    }

    private int minRecursive(int total,int[] coins,int index,Map<String,Integer> map){
        String key = String.format("%s,%s",total,index);
        if (map.containsKey(key)){
            return map.get(key);
        }
        if (total == 0 || index == 0){
            return 0;
        }


        int unAccessible = 21474836;
        int val = 0;
        if (coins[index] > total){
             int rowMin = min(minRecursive(total,coins,index-1,map),
                     minRecursive(total-1,coins,index,map),
                     minRecursive(total-1,coins,index-1,map));
             int actualVal = minRecursive(total-coins[index],coins,index,map) + 1;
            val = Math.min(rowMin,actualVal);
        }else if (coins[index] == total){
            val = 1;
        }else{
            val = Math.min(unAccessible,minRecursive(total,coins,index-1,map));
        }

        map.put(key,val);
        return val;
    }

    /**
     * Top down dynamic programing. Using map to store intermediate results.
     * Returns Integer.MAX_VALUE if total cannot be formed with given coins
     */
    public int minimumCoinTopDown(int total, int coins[], Map<Integer, Integer> map) {

        //if total is 0 then there is nothing to do. return 0.
        if ( total == 0 ) {
            return 0;
        }

        //if map contains the result means we calculated it before. Lets return that value.
        if ( map.containsKey(total) ) {
            return map.get(total);
        }

        //iterate through all coins and see which one gives best result.
        int min = Integer.MAX_VALUE;
        for ( int i=0; i < coins.length; i++ ) {
            //if value of coin is greater than total we are looking for just continue.
            if( coins[i] > total ) {
                continue;
            }
            //recurse with total - coins[i] as new total
            int val = minimumCoinTopDown(total - coins[i], coins, map);

            //if val we get from picking coins[i] as first coin for current total is less
            // than value found so far make it minimum.
            if( val < min ) {
                min = val;
            }
        }

        //if min is MAX_VAL dont change it. Just result it as is. Otherwise add 1 to it.
        min =  (min == Integer.MAX_VALUE ? min : min + 1);

        //memoize the minimum for current total.
        map.put(total, min);
        return min;
    }

    /**
     * Bottom up way of solving this problem.
     * Keep input sorted. Otherwise temp[j-arr[i]) + 1 can become Integer.Max_value + 1 which
     * can be very low negative number
     * Returns Integer.MAX_VALUE - 1 if solution is not possible.
     */
    public int minimumCoinBottomUp(int total, int coins[]){
        int T[] = new int[total + 1];
        int R[] = new int[total + 1];
        T[0] = 0;
        for(int i=1; i <= total; i++){
            T[i] = Integer.MAX_VALUE-1;
            R[i] = -1;
        }
        for(int j=0; j < coins.length; j++){
            for(int i=1; i <= total; i++){
                if(i >= coins[j]){
                    if (T[i - coins[j]] + 1 < T[i]) {
                        T[i] = 1 + T[i - coins[j]];
                        R[i] = j;
                    }
                }
            }
        }
        printCoinCombination(R, coins);
        return T[total];
    }

    private void printCoinCombination(int R[], int coins[]) {
        if (R[R.length - 1] == -1) {
            System.out.print("No solution is possible");
            return;
        }
        int start = R.length - 1;
        System.out.print("Coins used to form total ");
        while ( start != 0 ) {
            int j = R[start];
            System.out.print(coins[j] + " ");
            start = start - coins[j];
        }
        System.out.print("\n");
    }
}
