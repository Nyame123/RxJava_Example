package com.example.myrxjava.combinatoricsGameTheory.medium;

/**
 * Sieve of Eratosthenes
 * Difficulty Level : Medium
 * Last Updated : 31 Mar, 2021
 * <p>
 * Given a number n, print all primes smaller than or equal to n. It is also given that n is a small number.
 * <p>
 * Example:
 * <p>
 * Input : n =10
 * Output : 2 3 5 7
 * <p>
 * Input : n = 20
 * Output: 2 3 5 7 11 13 17 19
 * <p>
 * The sieve of Eratosthenes is one of the most efficient ways to find all
 * primes smaller than n when n is smaller than 10 million or so (Ref Wiki).
 * <p>
 * https://www.geeksforgeeks.org/sieve-of-eratosthenes/
 **/
public class SieveErastosthenes {

    public static void main(String[] args) {
        int n = 100;
        System.out.print(
                "Following are the prime numbers ");
        System.out.println("smaller than or equal to " + n);

        sieveOfEratosthenes(n);

    }

    //Time Complexity = O(n log( log(n)))
    static void sieveOfEratosthenes(int n) {
        // Create a boolean array
        // "prime[0..n]" and
        // initialize all entries
        // it as true. A value in
        // prime[i] will finally be
        // false if i is Not a
        // prime, else true.
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a
            // prime
            if (prime[p]) {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime[i])
                System.out.print(i + " ");
        }
    }


}
