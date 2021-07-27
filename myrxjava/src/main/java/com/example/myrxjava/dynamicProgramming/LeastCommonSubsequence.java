package com.example.myrxjava.dynamicProgramming;

/**
 * Finding the length of a least common subsequence of two string
 * For example, abcdef and acbcf.
 * We will have a least common subsequence of abcf of length 4
 *
 * if (str1[i] == str2[i]){
 *     T[i][j] = T[i-1][j-1] + 1;
 * }else{
 *      T[i][j] = Math.max(T[i-1][j] , T[i][j-1];
 *
 *      References
 *      http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 * }
*/
public class LeastCommonSubsequence {

    public static void main(String[] args) {

        String str1 = "longest";
        String str2 = "stone";
        //leastCommonSubsequence(str1,str2);
        int val = leastCommonSubsequenceDynamic(str1,str2,0,0);
        System.out.println("Length of least Common Subsequence = "+ val);
    }

    public static int leastCommonSubsequenceDynamic(String str1,String str2,int len1,int len2){
        if (len1 == str1.length() || len2 == str2.length()){
            return 0;
        }

        if (str1.charAt(len1) == str2.charAt(len2)){
            return 1 + leastCommonSubsequenceDynamic(str1,str2,len1+1,len2+1);
        }else{
            return Math.max(leastCommonSubsequenceDynamic(str1,str2,len1+1,len2),
                    leastCommonSubsequenceDynamic(str1,str2,len1,len2+1));
        }

    }

    public static void leastCommonSubsequence(String str1, String str2){

        int max = 0;
        int[][] T = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i < T.length; i++){
            for (int j = 1; j < T[i].length; j++){
                if (str1.charAt(i-1) == str2.charAt(j-1)){
                    T[i][j] = T[i-1][j-1] + 1;
                }else{
                    T[i][j] = Math.max(T[i-1][j],T[i][j-1]);
                }

                max = Math.max(max,T[i][j]);
            }
        }

        System.out.println("Length of least Common Subsequence = "+ T[str1.length()][str2.length()]);
    }
}
