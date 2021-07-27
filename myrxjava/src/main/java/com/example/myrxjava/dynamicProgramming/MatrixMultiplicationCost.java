package com.example.myrxjava.dynamicProgramming;

import java.util.Arrays;

/**
 *Given a sequence of matrices, find the most efficient way to multiply these matrices together. The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.
 * We have many options to multiply a chain of matrices because matrix multiplication is associative. In other words, no matter how we parenthesize the product, the result will be the same. For example, if we had four matrices A, B, C, and D, we would have:
 *
 * (ABC)D = (AB)(CD) = A(BCD) = ....
 * However, the order in which we parenthesize the product affects the number of simple arithmetic operations needed to compute the product, or the efficiency. For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,
 *
 * (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
 * A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
 *
 * Reference
 *  http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
 **/
public class MatrixMultiplicationCost {

    public static void main(String[] args) {

        //int arr[] = {4,2,3,5,3};
        int arr[] = {2,3,6,4,5};
        int cost = findCost(arr);

        System.out.print("Most Efficient Operation to be done = "+cost);

        /*int arr[] = { 1, 2, 3, 4 };*/
        int n= arr.length;

        for (int[] row : dp)
            Arrays.fill(row, -1);

        System.out.println("Minimum number of multiplications is " + MatrixChainOrder(arr, n));
    }

    public static int findCost(int arr[]){
        int temp[][] = new int[arr.length][arr.length];
        int q = 0;
        for(int l=2; l < arr.length; l++){
            for(int i=0; i < arr.length - l; i++){
                int j = i + l;
                temp[i][j] = 1000000;
                for(int k=i+1; k < j; k++){
                    q = temp[i][k] + temp[k][j] + arr[i]*arr[k]*arr[j];
                    if(q < temp[i][j]){
                        temp[i][j] = q;
                    }
                }
            }
        }
        return temp[0][arr.length-1];
    }

    static int[][] dp = new int[100][100];

    // Function for matrix chain multiplication
    static int matrixChainMemoised(int[] p, int i, int j) {
        if (i == j) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            dp[i][j] = Math.min(
                    dp[i][j], matrixChainMemoised(p, i, k)
                            + matrixChainMemoised(p, k + 1, j)
                            + p[i - 1] * p[k] * p[j]);
        }
        return dp[i][j];
    }

    static int MatrixChainOrder(int[] p, int n) {
        int i = 1, j = n - 1;
        return matrixChainMemoised(p, i, j);
    }


}
