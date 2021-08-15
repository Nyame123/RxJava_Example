package com.example.myrxjava.combinatoricsGameTheory.hard;

/**
 * Modular multiplicative inverse
 * Difficulty Level : Hard
 * Last Updated : 22 Jun, 2021
 * Given two integers ‘a’ and ‘m’, find modular multiplicative inverse of ‘a’ under modulo ‘m’.
 * The modular multiplicative inverse is an integer ‘x’ such that.
 *
 * a x ≅ 1 (mod m)
 * The value of x should be in { 1, 2, … m-1}, i.e., in the range of integer modulo m.
 * ( Note that x cannot be 0 as a*0 mod m will never be 1 )
 * The multiplicative inverse of (a modulo m) exists if and only if a and m are relatively prime (i.e., if gcd(a, m) = 1).
 *
 * Examples:
 *
 * Input:  a = 3, m = 11
 * Output: 4
 * Since (4*3) mod 11 = 1, 4 is modulo inverse of 3(under 11).
 * One might think, 15 also as a valid output as [(15*3) mod 11]
 * is also 1, but 15 is not in ring {1, 2, ... 10}, so not
 * valid.
 *
 * Input:  a = 10, m = 17
 * Output: 12
 * Since (10*12) mod 17 = 1, 12 is modulo inverse of 10(under 17).
 *
 * https://www.geeksforgeeks.org/multiplicative-inverse-under-modulo-m/
 **/
public class ModularMultiplicativeInverse {

    public static void main(String[] args) {
        int a = 3, m = 11;

        // Function call
//        System.out.println(modInverseMeth1(a, m));
        modInverseMeth2(a,m);
    }


    /**
     * Method 1 (Naive)
     * A Naive method is to try all numbers from 1 to m. For every number x, check if (a*x)%m is 1.
     * Time Complexity = O(m)
     * Below is the implementation of this method.
     **/
    static int modInverse(int n, int m){

        for (int i = 1; i < m; i++) {
            if (((n % m) * (i % m)) % m == 1){
                return i;
            }
        }

        return 1;
    }

    /**
     * Method 2 (Works when m and a are coprime)
     * The idea is to use Extended Euclidean algorithms that takes two integers ‘a’ and ‘b’,
     * finds their gcd and also find ‘x’ and ‘y’ such that
     *
     * ax + by = gcd(a, b)
     * To find multiplicative inverse of ‘a’ under ‘m’, we put b = m in above formula.
     * Since we know that a and m are relatively prime, we can put value of gcd as 1.
     *
     * ax + my = 1
     * If we take modulo m on both sides, we get
     *
     * ax + my ≅ 1 (mod m)
     * We can remove the second term on left side as ‘my (mod m)’ would always be 0 for an integer y.
     *
     * ax  ≅ 1 (mod m)
     **/
    static int modInverseMeth1(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            // q is quotient
            int q = a / m;

            int t = m;

            // m is remainder now, process
            // same as Euclid's algo
            m = a % m;
            a = t;
            t = y;

            // Update x and y
            y = x - q * y;
            x = t;
        }

        // Make x positive
        if (x < 0)
            x += m0;

        return x;
    }

    /**
     * Time Complexity: O(Log m)
     *
     * Method 3 (Works when m is prime)
     * If we know m is prime, then we can also use Fermats’s little theorem to find the inverse.
     *
     * a^m-1 ≅ 1 (mod m)
     * If we multiply both sides with a-1, we get
     *
     * a^-1 ≅ a^m-2 (mod m)
     **/

    static void modInverseMeth2(int a, int m) {
        int g = gcd(a, m);
        if (g != 1)
            System.out.println("Inverse doesn't exist");
        else {
            // If a and m are relatively prime, then modulo
            // inverse is a^(m-2) mode m
            System.out.println(
                    "Modular multiplicative inverse is "
                            + power(a, m - 2, m));
        }
    }

    static int gcd(int a, int b) {
        return (a % b == 0)? Math.abs(b): gcd(b, a % b);
    }

    static int power(int x, int y, int m) {
        if (y == 0)
            return 1;
        int p = power(x, y / 2, m) % m;
        p = (int)((p * (long)p) % m);
        if (y % 2 == 0)
            return p;
        else
            return (int)((x * (long)p) % m);

    }


}
