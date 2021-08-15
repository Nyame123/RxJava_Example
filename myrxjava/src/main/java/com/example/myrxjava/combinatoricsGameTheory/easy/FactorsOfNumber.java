package com.example.myrxjava.combinatoricsGameTheory.easy;

import java.util.Vector;

import static java.lang.Math.sqrt;

/**
 *Find all factors of a natural number | Set 1
 * Difficulty Level : Easy
 * Last Updated : 06 Aug, 2021
 * Given a natural number n, print all distinct divisors of it.
 *
 * https://www.geeksforgeeks.org/find-divisors-natural-number-set-1/
 **/
public class FactorsOfNumber {

    public static void main(String args[]) {
        System.out.println("The divisors of 100 are: ");
        printDivisorsOrderedPairMeth1(100);

        System.out.printf("\nSmallest Kth factor = %d",kThSmallestFactor(4,3));
    }

    static int kThSmallestFactor(int n, int k) {
        if (n <= 0 && k <= 0) return -1;

        int divisorCount = 0;
        for (int i = 1; i * i < n; ++i) {
            if (n % i == 0) ++divisorCount;
            if (divisorCount == k) return i;
        }

        for (int i = (int) sqrt(n); i >= 1; --i) {
            if (n % i == 0) ++divisorCount;
            if (divisorCount == k) return n / i;
        }

        return -1;
    }

    // Function to print the divisors
    public static void printDivisorsOrderedPairMeth1(int n) {
        for(int i = 1; i * i < n; i++) {
            if (n % i == 0)
                System.out.print(i + " ");
        }

        for(int i = (int)Math.sqrt(n); i >= 1; i--) {
            if (n % i == 0)
                System.out.print(n / i + " ");
        }
    }

    //Time Complexity = O(n)
    // method to print the divisors
    static void printDivisors(int n) {
        for (int i=1;i<=n;i++)
            if (n%i==0)
                System.out.print(i+" ");
    }

    // method to print the divisors
    static void printDivisorsOrderedPairs(int n) {
        // Vector to store half of the divisors
        Vector<Integer> v = new Vector<>();
        for (int i = 1; i <= sqrt(n); i++) {
            if (n % i == 0) {

                // check if divisors are equal
                if (n / i == i)
                    System.out.printf("%d ", i);
                else {
                    System.out.printf("%d ", i);

                    // push the second divisor in the vector
                    v.add(n / i);
                }
            }
        }

        // The vector will be printed in reverse
        for (int i = v.size() - 1; i >= 0; i--)
            System.out.printf("%d ", v.get(i));
    }

    //Time Complexity = O(Sqrt(n))
    // method to print the divisors
    static void printDivisorsPairs(int n) {
        // Note that this loop runs till square root
        for (int i = 1; i<= sqrt(n); i++) {
            if (n%i==0) {
                // If divisors are equal, print only one
                if (n/i == i)
                    System.out.print(" "+ i);

                else // Otherwise print both
                    System.out.print(i+" " + n/i + " " );
            }
        }
    }
}
