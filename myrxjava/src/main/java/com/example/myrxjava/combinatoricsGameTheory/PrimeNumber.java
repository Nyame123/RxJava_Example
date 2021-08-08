package com.example.myrxjava.combinatoricsGameTheory;

import java.util.ArrayList;
import java.util.List;

/**
 * Primality Test | Set 1 (Introduction and School Method)
 * Difficulty Level : Easy
 * Last Updated : 17 Mar, 2021
 * Given a positive integer, check if the number is prime or not. A prime is a natural number
 * greater than 1 that has no positive divisors other than 1 and itself. Examples of first few prime numbers are {2, 3, 5,
 * Examples :
 *
 *
 * Input:  n = 11
 * Output: true
 *
 * Input:  n = 15
 * Output: false
 *
 * Input:  n = 1
 * Output: false
 *
 * https://www.geeksforgeeks.org/primality-test-set-1-introduction-and-school-method/
 **/
public class PrimeNumber {

    public static void main(String[] args) {

        for (Integer x : primeNumbers(150)) {
            System.out.printf("%d ", x);
        }


        if(isPrimeMeth1(53))
            System.out.println(" true");
        else
            System.out.println(" false");
        if(isPrimeMeth1(61))
            System.out.println(" true");
        else
            System.out.println(" false");
    }

    public static List<Integer> primeNumbers(int n){
        List<Integer> result = new ArrayList<Integer>();
        result.add(2);
        boolean flag = false;
        for(int i=3; i < n; i+=2){
            for(int r : result){
                if(2*r > i){
                    break;
                }
                if(i % r == 0){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                result.add(i);
            }
            flag = false;
        }
        return result;
    }

    static boolean isPrimeMeth1(int n){
      if (n <= 3){
          return n > 1;
      }

      if (n % 2 == 0 || n % 3 == 0)
          return false;

      for (int i = 5; i*i <= n; i += 6){
          if (n % i == 0 || n % (i+2) == 0)
              return false;

      }

      return true;
    }

    //Time complexity of this solution is O(n)
    static boolean isPrimeMeth2(int n) {
        // Corner case
        if (n <= 1) return false;

        // Check from 2 to n-1
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;

        return true;
    }

    //Time complexity of this solution is O(n)
    static boolean isPrime(int n) {
        // Corner case
        if (n <= 1) return false;

        // Check from 2 to n-1
        for (int i = 2; i <= n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
