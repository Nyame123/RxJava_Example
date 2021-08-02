package com.example.myrxjava.ordinal.medium;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *Find the prime numbers which can written as sum of most consecutive primes
 * Difficulty Level : Medium
 * Last Updated : 28 Jun, 2021
 * Given an array of limits. For every limit, find the prime number which can be written as
 * the sum of the most consecutive primes smaller than or equal to limit.
 * The maximum possible value of a limit is 10^4.
 *
 * Example:
 *
 * Input  : arr[] = {10, 30}
 * Output : 5, 17
 * Explanation : There are two limit values 10 and 30.
 * Below limit 10, 5 is sum of two consecutive primes,
 * 2 and 3. 5 is the prime number which is sum of largest
 * chain of consecutive below limit 10.
 *
 * Below limit 30, 17 is sum of four consecutive primes.
 * 2 + 3 + 5 + 7 = 17
 *
 * https://www.geeksforgeeks.org/find-prime-number-can-written-sum-consecutive-primes/
 *
 **/
public class PrimeNumberSum {

    static int MAX = 10000;
    // Store prime number in vector
    static ArrayList<Object> primes = new ArrayList<Object>();

    public static void main(String[] args) {
        primeNumberConsecutiveSum();
    }

    static void primeNumberConsecutiveSum() {
        int []arr = { 10, 30, 40, 50, 1000 };
        int n = arr.length;

        LSCP(arr, n);
    }

    // Function find the prime number
// which can be written as the
// sum of the most consecutive primes
    static int LSCPUtil(int limit, long[] sum_prime) {

        // To store maximum length of
        // consecutive primes that can
        // sum to a limit
        int max_length = -1;

        // The prime number (or result)
        // that can be reprsented as
        // sum of maximum number of primes.
        int prime_number = -1;

        // Conisder all lengths of
        // consecutive primes below limit.
        for (int i = 0; (int) primes.get(i) <= limit; i++) {
            for (int j = 0; j < i; j++) {

                // If we cross the limit, then
                // break the loop
                if (sum_prime[i] - sum_prime[j] >
                        limit)
                    break;

                // sum_prime[i]-sum_prime[j] is
                // prime number or not
                long consSum = sum_prime[i] -
                        sum_prime[j];

                Object[] prime = primes.toArray();

                // Check if sum of current length
                // of consecutives is prime or not.
                if (Arrays.binarySearch(
                        prime, (int) consSum) >= 0) {

                    // Update the length and prime number
                    if (max_length < i - j + 1) {
                        max_length = i - j + 1;
                        prime_number = (int) consSum;
                    }
                }
            }
        }
        return prime_number;
    }

    // Returns the prime number that
// can written as sum of longest
// chain of consecutive primes.
    static void LSCP(int[] arr, int n) {
        sieveSundaram();

        long[] sum_prime = new long[primes.size() + 1];

        // Calculate sum of prime numbers
        // and store them in sum_prime
        // array. sum_prime[i] stores sum
        // of prime numbers from
        // primes[0] to primes[i-1]
        sum_prime[0] = 0;
        for (int i = 1; i <= primes.size(); i++)
            sum_prime[i] = (int) primes.get(i - 1) +
                    sum_prime[i - 1];

        // Process all queries one by one
        for (int i = 0; i < n; i++)
            System.out.print(LSCPUtil(
                    arr[i], sum_prime) + " ");
    }

    // Utility function for sieve of sundaram
    static void sieveSundaram() {

        // In general Sieve of Sundaram,
        // produces primes smaller than
        // (2*x + 2) for a number given
        // number x. Since we want primes
        // smaller than MAX, we reduce MAX
        // to half. This array is used to
        // separate numbers of the form
        // i+j+2ij from others where 1 <= i <= j
        boolean[] marked = new boolean[MAX / 2 + 1];

        // Main logic of Sundaram. Mark
        // all numbers which do not
        // generate prime number by
        // doing 2*i+1
        for (int i = 1; i <= (Math.sqrt(MAX) - 1) / 2; i++)
            for (int j = (i * (i + 1)) << 1; j <= MAX / 2; j = j + 2 * i + 1)
                marked[j] = true;

        // Since 2 is a prime number
        primes.add(2);

        // Print other primes. Remaining
        // primes are of the form 2*i + 1
        // such that marked[i] is false.
        for (int i = 1; i <= MAX / 2; i++)
            if (marked[i] == false)
                primes.add(2 * i + 1);
    }

}
