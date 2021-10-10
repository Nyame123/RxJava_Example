package com.example.myrxjava.combinatoricsGameTheory.hard;

public class Permutation {

    public static void main(String[] args) {
        Permutation per = new Permutation();
        //per.permutation("abis");
        per.permutationRec("abc","");
    }
    void permutation(String str) {
         permutation(str, "");
    }

    void permutation(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring ( i + 1);
                permutation(rem, prefix + str.charAt( i));
            }
        }
    }

    void permutationRec(String str,String sb){

        if (str.length() == 0){
            System.out.println(sb);
        }else {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                String s = str.substring(0, i) + str.substring(i + 1);
                permutationRec(s, sb+(c));
            }
        }
    }
}

class SquareRoot{

    public static void main(String[] args) {
        squareRootIterative(81);
    }

    static void squareRootWithRec(int n){
       System.out.println(squareRootWithRec(n,1,n));
    }

    static int squareRootWithRec(int n, int min, int max){
        if (max < min){
            return -1; //Not perfect square
        }

        int guess = (min+max) / 2;
        if (guess*guess == n){
            return guess;
        }else if (guess*guess < n){ //too low
           return squareRootWithRec(n,min+1,max);
        }else{  //too high
            return squareRootWithRec(n,min,max-1);
        }

    }

    static void squareRootIterative(int n){
        int sum = 0;
        for (int i = 0; i*i <= n; i++){
            if (i*i == n){

                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }
}
