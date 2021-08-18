package com.example.myrxjava.search.easy;

/**
 * Unbounded Binary Search Example (Find the point where a
 * monotonically increasing function becomes positive first time)
 * Difficulty Level : Easy
 * Last Updated : 10 May, 2021
 * Given a function ‘int f(unsigned int x)’ which takes a
 * non-negative integer ‘x’ as input and returns an integer as output.
 * The function is monotonically increasing with respect to the value of x, i.e.,
 * the value of f(x+1) is greater than f(x) for every input x.
 * Find the value ‘n’ where f() becomes positive for the first time.
 * Since f() is monotonically increasing, values of f(n+1), f(n+2),… must be positive and values of f(n-2), f(n-3), …
 * must be negative.
 * Find n in O(logn) time, you may assume that f(x) can be evaluated in O(1) time for any input x.
 *
 * https://www.geeksforgeeks.org/find-the-point-where-a-function-becomes-negative/
 **/
public class UnboundedBinarySearch {

    public static void main(String[] args) {
        System.out.print ("The value n where f() "+
                "becomes positive first is "+
                findFirstPositive());
    }


    public static int equationFunc(int x) {
        return (x*x - 10*x - 20);
    }

    // Returns the value x where above
    // function f() becomes positive
    // first time.
    public static int findFirstPositive() {
        // When first value itself is positive
        if (equationFunc(0) > 0)
            return 0;

        // Find 'high' for binary search
        // by repeated doubling
        int i = 1;
        while (equationFunc(i) <= 0)
            i = i * 2;

        // Call binary search
        return binarySearch(i / 2, i);
    }

    // Searches first positive value of
    // f(i) where low <= i <= high
    public static int binarySearch(int low, int high) {
        if (high >= low) {
            /* mid = (low + high)/2 */
            int mid = low + (high - low)/2;

            // If f(mid) is greater than 0 and
            // one of the following two
            // conditions is true:
            // a) mid is equal to low
            // b) f(mid-1) is negative
            if (equationFunc(mid) > 0 && (mid == low || equationFunc(mid-1) <= 0))
                return mid;

            // If f(mid) is smaller than or equal to 0
            if (equationFunc(mid) <= 0)
                return binarySearch((mid + 1), high);
            else // f(mid) > 0
                return binarySearch(low, (mid -1));
        }

        /* Return -1 if there is no positive
        value in given range */
        return -1;
    }
}
