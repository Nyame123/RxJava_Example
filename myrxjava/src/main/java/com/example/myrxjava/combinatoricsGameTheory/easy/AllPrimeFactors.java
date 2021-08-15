package com.example.myrxjava.combinatoricsGameTheory.easy;

import java.util.Vector;

/**
 * Efficient program to print all prime factors of a given number
 * Difficulty Level : Easy
 * Last Updated : 04 May, 2021
 * Given a number n, write an efficient function to print all prime factors of n.
 * For example, if the input number is 12, then output should be (2 2 3).
 * And if the input number is 315, then output should be (3 3 5 7).
 *
 * Following are the steps to find all prime factors.
 * 1) While n is divisible by 2, print 2 and divide n by 2.
 * 2) After step 1, n must be odd. Now start a loop from i = 3 to
 * square root of n. While i divides n, print i and divide n by i.
 * After i fails to divide n, increment i by 2 and continue.
 * 3) If n is a prime number and is greater than 2, then n will not
 * become 1 by above two steps. So print n if it is greater than 2.
 *
 * https://www.geeksforgeeks.org/print-all-prime-factors-of-a-given-number/
 *
 **/
public class AllPrimeFactors {

    public static void main(String[] args) {
        int n = 12246;
//        allPrimeFactor(n);
        runSieveAlgo();
    }

    static void runSieveAlgo(){
        // precalculating Smallest Prime Factor
        sieve();
        int x = 56;
        System.out.print("prime factorization for " + x + " : ");

        // calling getFactorization function
        Vector<Integer> p = getFactorization(x);

        for (int i=0; i<p.size(); i++)
            System.out.print(p.get(i) + " ");
        System.out.println();
    }

    static final int MAXN = 100001;

    // stores smallest prime factor for every number
    static int spf[] = new int[MAXN];

    // Calculating SPF (Smallest Prime Factor) for every
    // number till MAXN.
    // Time Complexity : O(nloglogn)
    static void sieve() {
        //spf[1] = 1;
       /* for (int i=2; i<MAXN; i++)

            // marking smallest prime factor for every
            // number to be itself.
            spf[i] = i;*/

        // separately marking spf for every even
        // number as 2
        for (int i=2; i<MAXN; i+=2)
            spf[i] = 2;

        for (int i=3; i*i<MAXN; i++) {
            // checking if i is prime
            if (spf[i] == 0) {
                // marking SPF for all numbers divisible by i
                for (int j=i*i; j<MAXN; j+=i)

                    // marking spf[j] if it is not
                    // previously marked
                    if (spf[j]==0)
                        spf[j] = i;
            }
        }
    }

    // A O(log n) function returning primefactorization
    // by dividing by smallest prime factor at every step
    static Vector<Integer> getFactorization(int x) {
        Vector<Integer> ret = new Vector<>();
        while (x != 1) {
            //mark previously unmarked primes
            if (spf[x] == 0)
                spf[x] = x;
            ret.add(spf[x]);
            x = x / spf[x];
        }
        return ret;
    }

    static void largestPrimeNumberOf(int n){

        int max = Integer.MIN_VALUE;
        while (n % 2 == 0){
            //System.out.print(2+" ");
            n /= 2;
            max = 2;
        }


        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i * i <= n; i+=2){
            while (n%i == 0){
                //System.out.print(i+" ");
                n /= i;

                max = Math.max(max,i);
            }
        }

        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n > 2)
            max = Math.max(max,n);

        System.out.print(max);
    }

    static void allPrimeFactor(int n){

        while (n % 2 == 0){
            System.out.print(2+" ");
            n /= 2;
        }


        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i * i <= n; i+=2){
            while (n%i == 0){
                System.out.print(i+" ");
                n /= i;
            }
        }

        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n > 2)
            System.out.print(n);
    }
}
