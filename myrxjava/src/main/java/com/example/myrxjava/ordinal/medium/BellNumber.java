package com.example.myrxjava.ordinal.medium;

/**
 *
 * Bell Numbers (Number of ways to Partition a Set)
 * Difficulty Level : Medium
 *
 * Bell number is an algorithm that helps to
 * find the number of ways to partition a given
 * sequence
 *
 * For Example,
 * 1
 * 1 2
 * 2 3 5
 * 5 7 10 15
 * 15 20 27 37 52
 *
 * https://www.geeksforgeeks.org/bell-numbers-number-of-ways-to-partition-a-set/?ref=lbp
 **/
public class BellNumber {

    public static void main(String[] args) {
        //for (int n=0; n<=5; n++)
            bellNumber(3);
    }

    static void bellNumber(int n){
        int[][] bell = new int[n+1][n+1];

        bell[0][0] = 1;
        for (int i = 1; i < n; i++) {
            bell[i][0] = bell[i-1][i-1];
            for (int j = 1; j <= i; j++) {
                bell[i][j] = bell[i-1][j-1] + bell[i][j-1];
                System.out.print(" "+bell[i][j]);
            }
            System.out.println("");
        }

        System.out.println("Maximum partition = "+bell[n-1][n-1]);
    }
}
