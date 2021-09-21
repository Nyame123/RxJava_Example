package com.example.myrxjava.ordinal.easy;
/**
 *  Equilibrium index of an array
 * Difficulty Level : Easy
 * Last Updated : 27 May, 2021
 * Equilibrium index of an array is an index such that the sum of elements at
 * lower indexes is equal to the sum of elements at higher indexes. For example, in an array A:
 *
 * Example :
 *
 * Input: A[] = {-7, 1, 5, 2, -4, 3, 0}
 * Output: 3
 * 3 is an equilibrium index, because:
 * A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
 *
 * Input: A[] = {1, 2, 3}
 * Output: -1
 **/
public class Equilibrium {

    public static void main(String[] args) {
       int A[] = {-7, 1, 5, 2, -4, 3, 0};
        equilibriumIndex(A);
    }

    static void equilibriumIndex(int[] arr){
        //find the total
        int total = 0;
        for(int i = 0; i < arr.length; i++){
            total += arr[i];
        }

        int left = 0;
        for(int i = 0; i < arr.length; i++){
            left += arr[i];
            if(total == left){
                System.out.println("Equilibrium Index = "+ (i));
                break;
            }

            total -= arr[i];

        }
    }


    static void equilibriumIndex1(int[] a){
        //building the prefixeSUm
        int n = a.length;
        int[] front = new int[n];
        front[0] = a[0];
        for(int i = 1; i < a.length; i++){
            front[i] = front[i-1] + a[i];
        }


        int[] back = new int[n];
        back[n-1] = a[n-1];

        for(int i = n-2; i >= 0; i--){
            back[i] = back[i+1] + a[i];
        }

        for(int i = 0; i < n; i++){
            if(front[i] == back[i]){
                System.out.print("Equilibrium Index = "+ i);
                break;
            }
        }
    }
}
