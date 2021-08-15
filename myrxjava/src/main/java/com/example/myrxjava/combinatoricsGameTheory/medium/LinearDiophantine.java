package com.example.myrxjava.combinatoricsGameTheory.medium;

/**
 * Linear Diophantine Equations
 * Difficulty Level : Medium
 * Last Updated : 31 Mar, 2021
 * A Diophantine equation is a polynomial equation, usually in two or more unknowns, such that only the
 * integral solutions are required. An Integral solution is a solution such that all the unknown variables take only integer values.
 * Given three integers a, b, c representing a linear equation of the form : ax + by = c.
 * Determine if the equation has a solution such that x and y are both integral values.
 * Examples :
 *
 *
 * Input : a = 3, b = 6, c = 9
 * Output: Possible
 * Explanation : The Equation turns out to be,
 * 3x + 6y = 9 one integral solution would be
 * x = 1 , y = 1
 *
 * Input : a = 3, b = 6, c = 8
 * Output : Not Possible
 * Explanation : o integral values of x and y
 * exists that can satisfy the equation 3x + 6y = 8
 *
 * Input : a = 2, b = 5, c = 1
 * Output : Possible
 * Explanation : Various integral solutions
 * possible are, (-2,1) , (3,-1) etc.
 *
 * https://www.geeksforgeeks.org/linear-diophantine-equations/
 **/
public class LinearDiophantine {

    public static void main(String[] args) {
        // First example
        int a = 3, b = 6, c = 9;
        if(isPossible(a, b, c))
            System.out.println( "Possible" );
        else
            System.out.println( "Not Possible");

        // Second example
        a = 3; b = 6; c = 8;
        if(isPossible(a, b, c))
            System.out.println( "Possible") ;
        else
            System.out.println( "Not Possible");

        // Third example
        a = 2; b = 5; c = 1;
        if(isPossible(a, b, c))
            System.out.println( "Possible" );
        else
            System.out.println( "Not Possible");
    }
    // Utility function to find the GCD
    // of two numbers
    static int gcd(int a, int b) {
        return (a % b == 0) ?
                Math.abs(b) : gcd(b,a%b);
    }

    // This function checks if integral
    // solutions are possible
    static boolean isPossible(int a, int b, int c) {
        return (c % gcd(a, b) == 0);
    }
}
