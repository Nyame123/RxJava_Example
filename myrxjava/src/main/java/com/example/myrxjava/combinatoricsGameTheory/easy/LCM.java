package com.example.myrxjava.combinatoricsGameTheory.easy;

import java.util.Vector;

/**
 * Program to find LCM of two numbers
 * Difficulty Level : Easy
 * Last Updated : 28 Jun, 2021
 * <p>
 * <p>
 * LCM (Least Common Multiple) of two numbers is the smallest number which can be divided by both numbers.
 * <p>
 * For example, LCM of 15 and 20 is 60, and LCM of 5 and 7 is 35.
 * <p>
 * A simple solution is to find all prime factors of both numbers, then find union of all factors
 * present in both numbers. Finally, return the product of elements in union.
 * <p>
 * An efficient solution is based on the below formula for LCM of two numbers ‘a’ and ‘b’.
 * <p>
 * <p>
 * RELATIONSHIP BETWEEN LCM AND GCD
 * ==========================================================
 * a x b = LCM(a, b) * GCD (a, b)
 * <p>
 * LCM(a, b) = (a x b) / GCD(a, b)
 **/
public class LCM {

    public static void main(String[] args) {
        int a = 15, b = 20;
        System.out.println("LCM of " + a +
                " and " + b +
                " is " + lcm(a, b));

        System.out.println("LCM of array " + lcmArrayOf(new int[]{2, 7, 3, 9, 4}));

    }

    /**
     * FINDING THE LCM OF NUMBERS IN AN ARRAY
     * =====================================================
     * Initialize ans = arr[0].
     * Iterate over all the elements of the array i.e. from i = 1 to i = n-1
     * At the ith iteration ans = LCM(arr[0], arr[1], …….., arr[i-1]).
     * This can be done easily as LCM(arr[0], arr[1], …., arr[i]) = LCM(ans, arr[i]).
     * Thus at i’th iteration we just have to do ans = LCM(ans, arr[i]) = ans x arr[i] / gcd(ans, arr[i])
     **/

    static int lcmOfArray(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = (result * arr[i]) / gcd(result, arr[i]);
        }

        return result;
    }

    public static long lcmOfArrayElements(int[] elementArray) {
        long lcmOfArrayElements = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < elementArray.length; i++) {

                // lcmOfArrayElements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcmOfArrayElements.

                if (elementArray[i] == 0) {
                    return 0;
                } else if (elementArray[i] < 0) {
                    elementArray[i] = elementArray[i] * (-1);
                }
                if (elementArray[i] == 1) {
                    counter++;
                }

                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (elementArray[i] % divisor == 0) {
                    divisible = true;
                    elementArray[i] = elementArray[i] / divisor;
                }
            }

            // If divisor able to completely divide any number
            // from array multiply with lcmOfArrayElements
            // and store into lcmOfArrayElements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcmOfArrayElements = lcmOfArrayElements * divisor;
            } else {
                divisor++;
            }

            // Check if all element_array is 1 indicate
            // we found all factors and terminate while loop.
            if (counter == elementArray.length) {
                return lcmOfArrayElements;
            }
        }
    }


    // Returns LCM of arr[0..n-1]

    /**
     * Initialize result = 1
     * 1. Find a common factors of two or more array elements.
     * 2. Multiply the result by common factor and divide all the array elements by this common factor.
     * 3. Repeat steps 2 and 3 while there is a common factor of two or more elements.
     * 4. Multiply the result by reduced (or divided) array elements.
     **/
    static long lcmArrayOf(int arr[]) {
        // Find the maximum value in arr[]
        int maxNum = 0;
        int n = arr.length;

        // Initialize result
        long res = 1;

        // Find all factors that are present in
        // two or more array elements.
        // Current factor.


        int allOnes = 0;
        int x = 2;
        while (allOnes < n) {
            // To store indexes of all array
            // elements that are divisible by x.
            Vector<Integer> indexes = new Vector<>();
            for (int j = 0; j < n; j++) {
                // Reduce all array elements divisible
                // by x.
                if (arr[j] % x == 0) {
                    arr[j] /= x;
                    indexes.add(j);
                    if (arr[j] == 1) {
                        allOnes++;
                    }
                }
            }

            // If there are 2 or more array elements
            // that are divisible by x.
            if (indexes.size() >= 1) {
                res *= x;
            } else {
                x++;
            }

        }

        return res;
    }


    // Recursive method to return gcd of a and b
    static int gcd(int a, int b) {
        return (a % b == 0) ? Math.abs(b) : gcd(b, a % b);
    }


    // method to return LCM of two numbers
    static int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}
