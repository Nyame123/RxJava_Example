package com.example.myrxjava.combinatoricsGameTheory.medium;

/**
 * Compute nCr % p | Set 1 (Introduction and Dynamic Programming Solution)
 * Difficulty Level : Medium
 * Last Updated : 13 Aug, 2021
 * Given three numbers n, r and p, compute value of nCr mod p.
 * Example:
 * <p>
 * Input:  n = 10, r = 2, p = 13
 * Output: 6
 * Explanation: 10C2 is 45 and 45 % 13 is 6.
 * <p>
 * https://www.geeksforgeeks.org/compute-ncr-p-set-1-introduction-and-dynamic-programming-solution/
 **/
public class ModularCombination {

    public static void main(String[] args) {
        //int n = 10, r = 2, p = 13;
        int n = 1000, r = 900, p = 13;
        System.out.println("Value of nCr % p is "
                + nCrModpLucas(n, r, p));
    }

    /**
     *  Lucas Theorem based function that returns nCr % p
     *  This function works like decimal to binary conversion
     *  recursive function. First we compute last digits of
     *  n and r in base p, then recur for remaining digits
     **/
    static int nCrModpLucas(int n, int r, int p) {
       // Base case
        if (r == 0)
            return 1;

       // Compute last digits of n and r in base p
        int ni = n % p;
        int ri = r % p;

        // Compute result for last digits computed above, and
        // for remaining digits. Multiply the two results and
        // compute the result of multiplication in modulo p.
        return (nCrModpLucas(n / p, r / p, p) * // Last digits of n and r
                nCrModp(ni, ri, p)) % p; // Remaining digits
    }


    static int nCrModp(int n, int r, int p) {
        r = Math.min(n - r, r);

        // The array C is going to store last
        // row of pascal triangle at the end.
        // And last entry of last row is nCr
        int C[] = new int[r + 1];

        C[0] = 1; // Top row of Pascal Triangle

        // One by constructs remaining rows of Pascal
        // Triangle from top to bottom
        for (int i = 1; i <= n; i++) {

            // Fill entries of current row using previous
            // row values
            for (int j = Math.min(i, r); j > 0; j--)

                // nCj = (n-1)Cj + (n-1)C(j-1);
                C[j] = (C[j] + C[j - 1]) % p;
        }
        return C[r];
    }

}
