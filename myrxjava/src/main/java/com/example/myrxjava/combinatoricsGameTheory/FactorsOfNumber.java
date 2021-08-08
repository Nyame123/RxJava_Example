package com.example.myrxjava.combinatoricsGameTheory;
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
        printDivisors(100);;
    }
    //Time Complexity = O(n)
    // method to print the divisors
    static void printDivisors(int n) {
        for (int i=1;i<=n;i++)
            if (n%i==0)
                System.out.print(i+" ");
    }
}
