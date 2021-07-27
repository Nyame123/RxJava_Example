package com.example.myrxjava.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

/**
 * A binomial coefficient C(n, k) also gives the number of ways, disregarding order, that k objects
 * can be chosen from among n objects more formally, the number of k-element subsets (or k-combinations) of a n-element set.
 * https://www.geeksforgeeks.org/binomial-coefficient-dp-9/?ref=lbp
 *
 * Write a function that takes two parameters n and k and returns the value of Binomial Coefficient C(n, k).
 * For example, your function should return 6 for n = 4 and k = 2, and it should return 10 for n = 5 and k = 2.
 *
 * Formular
 *  C(n, k) = C(n-1, k-1) + C(n-1, k)
 *    C(n, 0) = C(n, n) = 1
 * */
public class BinomialCoefficient {
    public static void main(String[] args) {

        int n = 5;
        int k = 2;
        //int res = binomialCoefficient(n,k,new HashMap<>());
        int res = binomialCoeff(n,k);
        System.out.printf("Binomial Coefficient C(%d,%d) = %d ",n,k,res);
    }

    static int binomialCoeff(int n, int k) {
        int C[][] = new int[n + 1][k + 1];
        int i, j;

        // Calculate  value of Binomial
        // Coefficient in bottom up manner
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= min(i, k); j++) {
                // Base Cases
                if (j == 0 || j == i)
                    C[i][j] = 1;

                    // Calculate value using
                    // previously stored values
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }

        return C[n][k];
    }

    static int binomialCoefficient(int n, int k, Map<String,Integer> map){
        String key = String.format("%d,%d",n,k);
        if (map.containsKey(key)) return map.get(key);
        if (k == 0 || k == n) return 1;

        if (k > n ) return 0;

        int res = binomialCoefficient(n-1,k,map) + binomialCoefficient(n-1,k-1,map);
        map.put(key,res);
        return res;
    }
}
