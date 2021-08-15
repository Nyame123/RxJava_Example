package com.example.myrxjava.combinatoricsGameTheory.medium;

/**
 *Least prime factor of numbers till n
 * Difficulty Level : Medium
 * Last Updated : 19 Mar, 2021
 * Given a number n, print least prime factors of all numbers from
 * 1 to n. The least prime factor of an integer n is the smallest prime number
 * that divides the number. The least prime factor of all even numbers is 2.
 * A prime number is its own least prime factor (as well as its own greatest prime factor).
 * Note: We need to print 1 for 1.
 * Example :
 *
 * Input : 6
 * Output : Least Prime factor of 1: 1
 *          Least Prime factor of 2: 2
 *          Least Prime factor of 3: 3
 *          Least Prime factor of 4: 2
 *          Least Prime factor of 5: 5
 *          Least Prime factor of 6: 2
 *
 * We can use a variation of sieve of Eratosthenes to solve the above problem.
 *
 * Create a list of consecutive integers from 2 through n: (2, 3, 4, …, n).
 * Initially, let i equal 2, the smallest prime number.
 * Enumerate the multiples of i by counting to n from 2i in increments of i, and
 * mark them as having least prime factor as i (if not already marked). Also mark i as
 * least prime factor of i (i itself is a prime number).
 * Find the first number greater than i in the list that is not marked. If there was
 * no such number, stop. Otherwise, let i now equal this new number (which is the next prime), and repeat from step 3.
 *
 * https://www.geeksforgeeks.org/least-prime-factor-of-numbers-till-n/
 **/
public class LeastPrimeFactors {

    public static void main (String[] args) {
        int n = 10;
        leastPrimeFactor(n);
    }

    //Time Complexity: O(nlog(n))
    //Auxiliary Space: O(n)
    public static void leastPrimeFactor(int n){

        // Create a vector to store least primes.
        // Initialize all entries as 0.
        int[] least_prime = new int[n+1];

        // We need to print 1 for 1.
        least_prime[1] = 1;

       for (int i = 2; i <= n; i++) {

            // least_prime[i] == 0
            // means it i is prime
            if (least_prime[i] == 0) {

                // marking the prime number
                // as its own lpf
                //least_prime[i] = i;

                // mark it as a divisor for all its
                // multiples if not already marked
                for (int j = i; j <= n; j += i)
                    if (least_prime[j] == 0)
                        least_prime[j] = i;
            }
        }

        // print least prime factor of
        // of numbers till n
        for (int i = 1; i <= n; i++)
            System.out.println("Least Prime factor of " +
                    + i + ": " + least_prime[i]);
    }

}
