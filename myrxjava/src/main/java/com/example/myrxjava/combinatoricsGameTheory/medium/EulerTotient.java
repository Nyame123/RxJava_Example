package com.example.myrxjava.combinatoricsGameTheory.medium;

/**
 * Euler’s Totient Function
 * Difficulty Level : Medium
 * Last Updated : 01 Apr, 2021
 * Euler’s Totient function Φ (n) for an input n is the count of numbers in {1, 2, 3, …, n}
 * that are relatively prime to n, i.e., the numbers whose GCD (Greatest Common Divisor) with n is 1.
 * Examples :
 *
 * Φ(1) = 1
 * gcd(1, 1) is 1
 *
 * Φ(2) = 1
 * gcd(1, 2) is 1, but gcd(2, 2) is 2.
 *
 * Φ(3) = 2
 * gcd(1, 3) is 1 and gcd(2, 3) is 1
 *
 * Φ(4) = 2
 * gcd(1, 4) is 1 and gcd(3, 4) is 1
 *
 * Φ(5) = 4
 * gcd(1, 5) is 1, gcd(2, 5) is 1,
 * gcd(3, 5) is 1 and gcd(4, 5) is 1
 *
 * Φ(6) = 2
 * gcd(1, 6) is 1 and gcd(5, 6) is 1,
 *
 * https://www.geeksforgeeks.org/eulers-totient-function/
 **/
public class EulerTotient {

    public static void main(String[] args) {
        int n;

        for (n = 1; n <= 10; n++)
            System.out.println("phi(" + n + ") = " + eulerTotientFunction(n));
    }


    static int phiMeth2(int n) {
        // Initialize result as n
        float result = n;

        // Consider all prime factors of n and for
        // every prime factor p, multiply result
        // with (1 - 1/p)
        for (int p = 2; p * p <= n; ++p) {
            // Check if p is a prime factor.
            if (n % p == 0) {
                // If yes, then update n and result
                while (n % p == 0)
                    n /= p;
                result *= (1.0 - (1.0 / (float)p));
            }
        }

        // If n has a prime factor greater than sqrt(n)
        // (There can be at-most one such prime factor)
        if (n > 1)
            result *= (1.0 - (1.0 / (float)n));

        return (int)result;
    }

    // Function to return GCD of a and b
    static int gcd(int a, int b) {
        return (b % a == 0)? Math.abs(a): gcd(b % a, a);
    }

    // A simple method to evaluate
    // Euler Totient Function
    static int phi(int n) {
        int result = 1;
        for (int i = 2; i < n; i++)
            if (gcd(i, n) == 1)
                result++;
        return result;
    }

    // Computes and prints totient of all numbers
// smaller than or equal to n.
    static void computeTotient(int n) {

        // Create and initialize an array to store
        // phi or totient values
        long phi[] = new long[n + 1];
        for (int i = 1; i <= n; i++)
            phi[i] = i; // indicates not evaluated yet
        // and initializes for product
        // formula.

        // Compute other Phi values
        for (int p = 2; p <= n; p++) {

            // If phi[p] is not computed already,
            // then number p is prime
            if (phi[p] == p) {

                // Phi of a prime number p is
                // always equal to p-1.
                phi[p] = p - 1;

                // Update phi values of all
                // multiples of p
                for (int i = 2 * p; i <= n; i += p) {

                    // Add contribution of p to its
                    // multiple i by multiplying with
                    // (1 - 1/p)
                    phi[i] = (phi[i] / p) * (p - 1);
                }
            }
        }

        // Print precomputed phi values
        for (int i = 1; i <= n; i++)
            System.out.println("Totient of " + i +
                    " is " + phi[i]);
    }

    static long eulerTotientFunction(long n) {
        long result = 1;
        for(long i = 2; i * i <= n; i++) {
            long c = 0;
            if (n % i == 0) {
                while (n % i == 0) {
                    c++;
                    n /= i;
                }
            }
            if (c > 0) {
                long sm = (long)Math.pow(i, c - 1) * (i - 1);
                result *= sm;
            }
        }

        if (n > 1) {
            result *= (n - 1);
        }
        return result;
    }
}
