package com.example.myrxjava.ordinal.easy;

public class PalindromicString {

    public static void main(String[] args) {
        isPalindromic("abbccbba");
    }

    static void isPalindromic(String s){
        int startIndex = 0;
        int endIndex = s.length()-1;
        while (startIndex < endIndex){
            if (s.charAt(startIndex++) != s.charAt(endIndex--)){
                System.out.printf("%s is not palindromic",s);
                return;
            }
        }
        System.out.printf("%s is palindromic",s);
    }
}
