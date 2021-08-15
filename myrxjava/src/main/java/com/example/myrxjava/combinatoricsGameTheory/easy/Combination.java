package com.example.myrxjava.combinatoricsGameTheory.easy;

/**
 * Program to calculate value of nCr
 * Difficulty Level : Easy
 * Last Updated : 22 Mar, 2021
 * Following are common definition of Binomial Coefficients.
 * <p>
 * <p>
 * A binomial coefficient C(n, k) can be defined as the coefficient of X^k in the expansion of (1 + X)^n.
 * A binomial coefficient C(n, k) also gives the number of ways, disregarding order,
 * that k objects can be chosen from among n objects; more formally,
 * the number of k-element subsets (or k-combinations) of an n-element set.
 * Given two numbers n and r, find value of nCr
 * Examples :
 * <p>
 * <p>
 * Input :  n = 5, r = 2
 * Output : 10
 * The value of 5C2 is 10
 * <p>
 * Input : n = 3, r = 1
 * Output : 3
 * <p>
 * <p>
 * https://www.geeksforgeeks.org/program-calculate-value-ncr/
 **/
public class Combination {

    public static void main(String[] args) {
        int n = 15, r = 2;
        //System.out.println(nCr(n, r));
        printNcR(n, r);
    }


    static int nCr(int n, int r) {
        return fact(n) / (fact(r) *
                fact(n - r));
    }

    // Returns factorial of n
    static int fact(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

    /**
     *
     * Time Complexity: O( R Log N)
     * Auxiliary Space: O(1)
     * Approach: A simple code can be created with the following knowledge that :
     * <p>
     * C(n, r) = [n * (n-1) * .... * (n-r+1)] / [r * (r-1) * .... * 1]
     * However, for big values of n, r the products may overflow,
     * hence during each iteration we divide the current variables holding value of products by their gcd.
     * <p>
     * Below is the required implementation:
     **/
    static void printNcR(int n, int r) {

        // p holds the value of n*(n-1)*(n-2)...,
        // k holds the value of r*(r-1)...
        long p = 1, k = 1;

        // C(n, r) == C(n, n-r),
        // choosing the smaller value
        if (n - r < r) {
            r = n - r;
        }

        if (r != 0) {
            while (r > 0) {
                p *= n;
                k *= r;

                // gcd of p, k
                long m = gcd(p, k);

                // dividing by gcd, to simplify
                // product division by their gcd
                // saves from the overflow
                p /= m;
                k /= m;

                n--;
                r--;
            }

            // k should be simplified to 1
            // as C(n, r) is a natural number
            // (denominator should be 1 ) .
        } else {
            p = 1;
        }

        // if our approach is correct p = ans and k =1
        System.out.println(p);
    }

    static long gcd(long n1, long n2) {
        /*long gcd = 1;

        for (int i = 1; i <= n1 && i <= n2; ++i) {
            // Checks if i is factor of both integers
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;*/
        return (n1 % n2 == 0)? Math.abs(n2) : gcd(n2,n1%n2);
    }
}
