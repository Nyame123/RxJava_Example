package com.example.myrxjava.dynamicProgramming;

public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        String str = "GEEKSFORGEEKS";
        //int res = calculateRecursive(str.toCharArray(),0,str.length());
       int res = dynamicRecursive(str.toCharArray(),0,str.length()-1);
       //int res = calculate1(str.toCharArray());
       //int res1 = longestPalindromicLen(str);
       System.out.print(res);
    }

    public static int calculate1(char []str){
        int T[][] = new int[str.length][str.length];
        for(int i=0; i < str.length; i++){
            T[i][i] = 1;
        }
        for(int l = 2; l <= str.length; l++){
            for(int i = 0; i < str.length-l + 1; i++){
                int j = i + l - 1;
                if(l == 2 && str[i] == str[j]){
                    T[i][j] = 2;
                }else if(str[i] == str[j]){
                    T[i][j] = T[i + 1][j-1] + 2;
                }else{
                    T[i][j] = Math.max(T[i + 1][j], T[i][j - 1]);
                }
            }
        }
        return T[0][str.length-1];
    }

    static int dynamicRecursive(char[] str,int start,int end){
        if (start == end) return 1;

        if (str[start] == str[end] && start+1 == end){
            return 2;
        }else if (str[start] == str[end]){
            return 2 + dynamicRecursive(str,start+1,end-1);
        }else{
            return  Math.max(dynamicRecursive(str,start,end-1),dynamicRecursive(str,start+1,end));
        }
    }

    public static int calculateRecursive(char str[],int start,int len){
        if(len == 1){
            return 1;
        }
        if(len ==0){
            return 0;
        }
        int end = start+len-1;
        if(str[start] == str[end]){
            return 2 + calculateRecursive(str,start+1,len-2);
        }else{
            return Math.max(calculateRecursive(str, start+1, len-1), calculateRecursive(str, start, len-2));
        }
    }

    static int longestPalindromicLen(String s){

        int[][] T = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            T[i][i] = 1;
        }

        for (int l = 2; l <= T.length; l++) {
            for (int j = 0; j < T.length-l+1; j++) {
                int k = j + l - 1;

                //if (k >= s.length()) break;

                boolean b = s.charAt(j) == s.charAt(k);
                if (l == 2 && b){
                    T[j][k] = 2;
                }else if (b){
                    T[j][k] = 2 + T[j+1][k-1];
                }else{
                    T[j][k] = Math.max(T[j][k-1],T[j+1][k]);
                }
            }
        }

        return T[0][s.length()-1];
    }
}
