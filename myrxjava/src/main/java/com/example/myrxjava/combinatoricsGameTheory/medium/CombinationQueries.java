package com.example.myrxjava.combinatoricsGameTheory.medium;
/**
 * Queries of nCr%p in O(1) time complexity
 * Difficulty Level : Medium
 * Last Updated : 12 Aug, 2021
 * Given Q queries and P where P is a prime number,
 * each query has two numbers N and R and the task is to calculate nCr mod p.
 * Constraints:
 *
 * N <= 106
 * R <= 106
 * p is a prime number
 * Examples:
 *
 * Input:
 * Q = 2 p = 1000000007
 * 1st query: N = 15, R = 4
 * 2nd query: N = 20, R = 3
 * Output:
 * 1st query: 1365
 * 2nd query: 1140
 * 15!/(4!*(15-4)!)%1000000007 = 1365
 * 20!/(20!*(20-3)!)%1000000007 = 1140
 *
 * https://www.geeksforgeeks.org/queries-of-ncrp-in-o1-time-complexity/
 **/
public class CombinationQueries {

    static int N = 1000001;

    // Array to store inverse of 1 to N
    static long[] factorialNumInverse = new long[N + 1];

    // Array to precompute inverse of 1! to N!
    static long[] naturalNumInverse = new long[N + 1];

    // Array to store factorial of first N numbers
    static long[] fact = new long[N + 1];

    // Driver code
    public static void main (String[] args) {

        // Calling functions to precompute the
        // required arrays which will be required
        // to answer every query in O(1)
        int p = 1000000007;
        InverseofNumber(p);
        InverseofFactorial(p);
        factorial(p);

        // 1st query
        int n = 15;
        int R = 4;
        System.out.println(Binomial(n, R, p));

        // 2nd query
        n = 20;
        R = 3;
        System.out.println(Binomial(n, R, p));
    }

    // Function to precompute inverse of numbers
    public static void InverseofNumber(int p) {
        naturalNumInverse[0] = naturalNumInverse[1] = 1;

        for(int i = 2; i <= N; i++)
            naturalNumInverse[i] = naturalNumInverse[p % i] *
                    (long)(p - p / i) % p;
    }

    // Function to precompute inverse of factorials
    public static void InverseofFactorial(int p) {
        factorialNumInverse[0] = factorialNumInverse[1] = 1;

        // Precompute inverse of natural numbers
        for(int i = 2; i <= N; i++)
            factorialNumInverse[i] = (naturalNumInverse[i] *
                    factorialNumInverse[i - 1]) % p;
    }

    // Function to calculate factorial of 1 to N
    public static void factorial(int p) {
        fact[0] = 1;

        // Precompute factorials
        for(int i = 1; i <= N; i++)
        {
            fact[i] = (fact[i - 1] * (long)i) % p;
        }
    }

    // Function to return nCr % p in O(1) time
    public static long Binomial(int N, int R, int p) {

        // n C r = n!*inverse(r!)*inverse((n-r)!)
        long ans = ((fact[N] * factorialNumInverse[R]) %
                p * factorialNumInverse[N - R]) % p;

        return ans;
    }


}
