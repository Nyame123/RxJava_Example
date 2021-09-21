package com.example.myrxjava.ordinal.medium;

import java.util.ArrayList;
import java.util.Arrays;

public class ConsecutivePrimeSum {
    static int MAX = 10000;

    // Store prime number in vector
    static ArrayList<Object> primes = new ArrayList<Object>();

    // Utility function for sieve of sundaram
    static void sieveSundaram() {

        // In general Sieve of Sundaram,
        // produces primes smaller than
        // (2*x + 2) for a number given
        // number x. Since we want primes
        // smaller than MAX, we reduce MAX
        // to half. This array is used to
        // separate numbers of the form
        // i+j+2ij from others where 1 <= i <= j
        boolean[] marked = new boolean[MAX / 2 + 1];
        Arrays.fill(marked, false);

        // Main logic of Sundaram. Mark
        // all numbers which do not
        // generate prime number by
        // doing 2*i+1
        for (int i = 1; i <= (Math.sqrt(MAX) - 1) / 2; i++)
            for (int j = (i * (i + 1)) << 1; j <= MAX / 2; j = j + 2 * i + 1)
                marked[j] = true;

        // Since 2 is a prime number
        primes.add(2);

        // Print other primes. Remaining
        // primes are of the form 2*i + 1
        // such that marked[i] is false.
        for (int i = 1; i <= MAX / 2; i++)
            if (marked[i] == false)
                primes.add(2 * i + 1);
    }

    // Function find the prime number
// which can be written as the
// sum of the most consecutive primes
    static int LSCPUtil(int limit, long[] sum_prime) {

        // To store maximum length of
        // consecutive primes that can
        // sum to a limit
        int max_length = -1;

        // The prime number (or result)
        // that can be reprsented as
        // sum of maximum number of primes.
        int prime_number = -1;

        // Consider all lengths of
        // consecutive primes below limit.
        for (int i = 0; (int) primes.get(i) <= limit; i++) {
            for (int j = 0; j < i; j++) {

                // If we cross the limit, then
                // break the loop
                if (sum_prime[i] - sum_prime[j] > limit)
                    break;

                // sum_prime[i]-sum_prime[j] is
                // prime number or not
                long consSum = sum_prime[i] - sum_prime[j];

                Object[] prime = primes.toArray();

                // Check if sum of current length
                // of consecutives is prime or not.
                if (Arrays.binarySearch(prime, (int) consSum) >= 0) {

                    // Update the length and prime number
                    if (max_length < i - j + 1) {
                        max_length = i - j + 1;
                        prime_number = (int) consSum;
                    }
                }
            }
        }
        return prime_number;
    }

    // Returns the prime number that
// can written as sum of longest
// chain of consecutive primes.
    static void LSCP(int[] arr, int n) {
        sieveSundaram();

        long[] sum_prime = new long[primes.size() + 1];

        // Calculate sum of prime numbers
        // and store them in sum_prime
        // array. sum_prime[i] stores sum
        // of prime numbers from
        // primes[0] to primes[i-1]
        sum_prime[0] = 0;
        for (int i = 1; i <= primes.size(); i++)
            sum_prime[i] = (int) primes.get(i - 1) +
                    sum_prime[i - 1];

        // Process all queries one by one
        for (int i = 0; i < n; i++)
            System.out.print(LSCPUtil(
                    arr[i], sum_prime) + " ");
    }

    // Driver code
    public static void main(String[] arg) {
        int[] arr = {10, 30, 40, 50, 1000};
        int n = arr.length;

        LSCP(arr, n);
    }
}

class primeConsecutiveSum {

    private static boolean[] primes;
    private static int[] actualPrime;

    static int[] prefixSumSieveOfErasthosthene(int max) {
        max += 5;
        primes = new boolean[max];
        Arrays.fill(primes, true);
        for (int i = 2; i < Math.sqrt(max); i++) {
            if (primes[i]) {
                for (int j = i * i; j < max; j += i) {
                    primes[j] = false;

                }
            }
        }

        actualPrime = new int[max];
        //retrieve all the prime numbers from the given primes
        for (int i = 2, j = 0; i < max; i++) {
            if (primes[i]) {
                actualPrime[j++] = i;
            }
        }

        return prefixSum(actualPrime);

    }

    //do prefix sum
    static int[] prefixSum(int[] primes) {
        int n = primes.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int j = 1, i = 0; i < n; i++) {
            int sum = prefixSum[j - 1] + primes[i];
            prefixSum[j++] = sum;
        }

        return prefixSum;

    }


    static void LCPS(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] conSum = prefixSumSieveOfErasthosthene(max);


        System.out.println("new primes sum");
        for (int i = 0; i < arr.length; i++) {
            int primeBefore = arr[i];
            int maxLength = -1;
            int primeNumber = -1;
            for (int j = 0; actualPrime[j] <= primeBefore && actualPrime[j] != 0; j++) {
                for (int k = 0; k < j; k++) {

                    int sumDiff = conSum[j] - conSum[k];
                    if (sumDiff >= primeBefore) {
                        break;
                    }

                    boolean isPrime = true;
                    for (int l = 2; l * l <= sumDiff; l++) {
                        if (sumDiff % l == 0) {
                            isPrime = false;
                            break;
                        }
                    }

                    if (isPrime && maxLength < j - k + 1) {
                        maxLength = Math.max(maxLength, j - k + 1);
                        primeNumber = sumDiff;
                    }

                }
            }

            System.out.print(primeNumber + " ");


        }


    }

    static void usingSieveSundaram(int max) {
        boolean[] primes = new boolean[max];
        for (int i = 1; i <= Math.sqrt(max)/2; i++) {
            for (int j = (i * (i + 1)) << 1; j <= max/2; j += 2 * i + 1) {
                primes[j] = true;
            }

        }

        System.out.print(2 + " ");
        for (int i = 1; i < max >> 1; i++) {
            if (!primes[i]) {
                int prime = 2 * i + 1;
                System.out.print(prime + " ");
            }
        }

    }


    public static void main(String[] args) {

        int[] arr = {10, 30, 40, 50, 1000, 2000};
        //LCPS(arr);
        usingSieveSundaram(100);
    }


}