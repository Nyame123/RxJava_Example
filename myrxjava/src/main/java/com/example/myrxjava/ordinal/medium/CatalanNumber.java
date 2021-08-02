package com.example.myrxjava.ordinal.medium;

import java.math.BigInteger;

/**
 *
 * Program for nth Catalan Number
 * Difficulty Level : Medium
 *
 * Application of Catalan Number algorithm can be read from below link
 * https://www.geeksforgeeks.org/applications-of-catalan-numbers/
 * Catalan numbers are a sequence of natural numbers that occurs in many interesting counting problems like following.
 * <p>
 * Count the number of expressions containing n pairs of parentheses which are correctly matched. For n = 3,
 * possible expressions are ((())), ()(()), ()()(), (())(), (()()).
 * Count the number of possible Binary Search Trees with n keys (See this)
 * Count the number of full binary trees (A rooted binary tree is full if every vertex has either
 * two children or no children) with n+1 leaves.
 * Given a number n, return the number of ways you can draw n chords in a circle with 2 x n points such that no 2 chords intersect.
 * See this for more applications.
 * The first few Catalan numbers for n = 0, 1, 2, 3, … are 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, …
 *
 * https://www.geeksforgeeks.org/program-nth-catalan-number/?ref=lbp
 *
 **/
public class CatalanNumber {

    public static void main(String[] args) {

        catalanNumber(7);
    }


    static void catalanNumber1(int n) {
        BigInteger b = new BigInteger("1");
        BigInteger c = new BigInteger("1");
        //find n!
        for (int i = 1; i <= n + 1; i++) {

            b = b.multiply(BigInteger.valueOf(i));
            if (i == n) { //n!
                c = b;
            }
        }

        BigInteger d = new BigInteger("1");
        for (int i = 1; i <= 2 * n; i++) {
            d = d.multiply(BigInteger.valueOf(i));
        }

        //find 2n! / (n+1)! * n!
        BigInteger ans = d.divide(c.multiply(b));

        System.out.println("The catalan number of " + n + " = " + ans.toString());
    }

    static void catalanNumber(int n) {
        BigInteger b = new BigInteger("1");

        //find n!
        for (int i = 1; i <= n; i++) {
            b = b.multiply(BigInteger.valueOf(i));
        }

        //find n!*n!
        b = b.multiply(b);

        BigInteger d = new BigInteger("1");
        for (int i = 1; i <= 2 * n; i++) {
            d = d.multiply(BigInteger.valueOf(i));
        }

        //find 2n! / n! * n!
        BigInteger ans = d.divide(b).divide(BigInteger.valueOf(n + 1));

        System.out.println("The catalan number of " + n + " = " + ans.toString());
    }
}
