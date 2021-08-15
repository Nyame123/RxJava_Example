package com.example.myrxjava.combinatoricsGameTheory.easy;

/**
 * Program to find GCD or HCF of two numbers
 * Difficulty Level : Easy
 * Last Updated : 22 Mar, 2021
 * GCD (Greatest Common Divisor) or HCF (Highest Common Factor)
 * of two numbers is the largest number that divides both of them.
 * <p>
 * For example GCD of 20 and 28 is 4 and GCD of 98 and 56 is 14.
 *
 * https://www.geeksforgeeks.org/c-program-find-gcd-hcf-two-numbers/
 **/
public class GCD {
    public static void main(String[] args) {
        int a = 56, b = 98;
        System.out.println("GCD of " + a + " and " + b + " is " + gcdMeth1(a, b));
        System.out.println("GCD of array is " + findGCD(new int[]{2, 4, 6, 8}));

        runExtendedEuclideanAlgo();
    }

    /**
     * METHOD 1
     * ===============================================================================
     * 1. Find the prime factors of all the numbers
     * 2. Find the intersection of the prime factors of the numbers (Common prime factors)
     * 3. Find the product of the common prime factors
     *
     * For you to find the prime factor, use the tutorial on getting the prime factors either by
     * seive method or normal brute force.
     * */


    /**
     * FINDING THE GCM OF ALL NUMBERS
     * =======================================================================================
     * 1. Find the GCD of the numbers in a iterative manner.
     *
     * Given an array of numbers, find GCD of the array elements. In a previous post we find GCD of two number.
     * Examples:
     *
     *
     * Input  : arr[] = {1, 2, 3}
     * Output : 1
     *
     * Input  : arr[] = {2, 4, 6, 8}
     * Output : 2
     *
     **/

    static int findGCD(int [] arr){
        int result = arr[0];
        int n = arr.length;
        for (int i = 1; i < n - 1; i++) {
            result = gcdMeth1(arr[i],result);

            if (result == 1){
                return 1;
            }
        }

        return result;
    }


    // Recursive function to return gcd of a and b
    static int gcdMeth1(int a, int b) {
        return (a % b == 0)? Math.abs(b): gcdMeth1(b, a % b);
    }


    static int gcdMeth2(int a, int b) {

        while (b != 0) {

            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

/**
 * Time Complexity: O(Log min(a, b))
 * Extended Euclidean Algorithm:
 * Extended Euclidean algorithm also finds integer coefficients x and y such that:
 *
 *   ax + by = gcd(a, b)
 * Examples:
 *
 * Input: a = 30, b = 20
 * Output: gcd = 10
 *         x = 1, y = -1
 * (Note that 30*1 + 20*(-1) = 10)
 *
 * Input: a = 35, b = 15
 * Output: gcd = 5
 *         x = 1, y = -2
 * (Note that 35*1 + 15*(-2) = 5)
 * The extended Euclidean algorithm updates results of gcd(a, b) using
 * the results calculated by recursive call gcd(b%a, a). Let values of x and y
 * calculated by the recursive call be x1 and y1. x and y are updated using the below expressions.
 *
 * x = y1 - ⌊b/a⌋ * x1
 * y = x1
 **/
    public static int gcdExtended(int a, int b, int x, int y) {
        // Base Case
        if (a == 0) {
            x = 0;
            y = 1;
            return b;
        }

        int x1=1, y1=1; // To store results of recursive call
        int gcd = gcdExtended(b%a, a, x1, y1);

        // Update x and y using results of recursive
        // call
        x = y1 - (b/a) * x1;
        y = x1;

        System.out.printf("gcd(%d,%d) = %d with x = %d, y = %d \n",a,b,gcd,x,y);
        return gcd;
    }

    static void runExtendedEuclideanAlgo(){
        int x=1, y=1;
        int a = 35, b = 15;
        int g = gcdExtended(a, b, x, y);

    }

    // Recursive function to return gcd of a and b
    static int gcd(int a, int b) {
        // Everything divides 0
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        // base case
        if (a == b)
            return a;

        // a is greater
        if (a > b)
            return gcd(a - b, b);
        return gcd(a, b - a);
    }
}
