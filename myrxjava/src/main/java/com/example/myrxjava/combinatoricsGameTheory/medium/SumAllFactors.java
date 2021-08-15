package com.example.myrxjava.combinatoricsGameTheory.medium;
/**
* Sum of all the factors of a number
 * Difficulty Level : Medium
 * Given a number n, the task is to find the sum of all the factors.
 * Examples :
 *
 *
 * Input : n = 30
 * Output : 72
 * Dividers sum 1 + 2 + 3 + 5 + 6 +
 *              10 + 15 + 30 = 72
 *
 * Input :  n = 15
 * Output : 24
 * Dividers sum 1 + 3 + 5 + 15 = 24
 *
 *
**/
public class SumAllFactors {

    public static void main(String[] args) {
        int n = 12;
        System.out.println(sumofFactors(n));
    }
    // Function to calculate sum of all
    //divisors of a given number
    static int divSum(int n) {
        if(n == 1)
            return 1;
        // Final result of summation
        // of divisors
        int result = 0;

        // find all divisors which divides 'num'
        for (int i = 2; i <= Math.sqrt(n); i++) {
            // if 'i' is divisor of 'n'
            if (n % i == 0) {
                // if both divisors are same
                // then add it once else add
                // both
                if (i == (n / i))
                    result += i;
                else
                    result += (i + n / i);
            }
        }

        // Add 1 and n to result as above loop
        // considers proper divisors greater
        // than 1.
        return (result + n + 1);

    }

    // Returns sum of all factors of n.
    /**
     * An efficient solution is to use below formula.
     * Let p1, p2, … pk be prime factors of n. Let a1, a2, .. ak
     * be highest powers of p1, p2, .. pk respectively that
     * divide n, i.e., we can write n as n = (p1a1)*(p2a2)* … (pkak).
     *
     * Sum of divisors = (1 + p1 + p12 ... p1a1) *
     *                   (1 + p2 + p22 ... p2a2) *
     *                   .............................................
     *                   (1 + pk + pk2 ... pkak)
     **/
    static int sumofFactors(int n) {
        // Traversing through all prime factors.
        int res = 1;
        for (int i = 2; i <= Math.sqrt(n); i++) {


            int  curr_sum = 1;
            int curr_term = 1;

            while (n % i == 0) {

                // THE BELOW STATEMENT MAKES
                // IT BETTER THAN ABOVE METHOD
                // AS WE REDUCE VALUE OF n.
                n = n / i;

                curr_term *= i;
                curr_sum += curr_term;
            }

            res *= curr_sum;
        }

        // This condition is to handle
        // the case when n is a prime
        // number greater than 2
        if (n > 2)
            res *= (1 + n);

        return res;
    }



}
