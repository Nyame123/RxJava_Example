package com.example.myrxjava.ordinal.ArrayAndStrings;

public class CompressedStrings {

    public static void main(String[] args) {
        String s = "abcdefffffffffffffff";
        compressStringWithRepetition(s);
    }

    static boolean willCompressedBeSmaller(String s){
        int compressedCounter = 0;
        int charCounter = 1;
        char cur = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            if (cur == s.charAt(i)){
                charCounter++;
            }else{
                compressedCounter += String.valueOf(charCounter).length() + 1;
                if (s.length() <= compressedCounter){
                    return false;
                }
                charCounter = 1;
                cur = s.charAt(i);
            }

            if (i == s.length()-1){
                compressedCounter += String.valueOf(charCounter).length() + 1;
                if (s.length() <= compressedCounter){
                    return false;
                }
            }
        }

        return true;
    }

    static void compressStringWithRepetition(String s) {
        if (!willCompressedBeSmaller(s)) {
            System.out.println(s);
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        char cur = s.charAt(0);
        int countCur = 1;
        for (int i = 1; i < s.length(); i++) {
            if (cur == s.charAt(i)) {
                countCur++;
            } else {
                stringBuilder.append(cur).append(countCur);
                cur = s.charAt(i);
                countCur = 1;
            }

            if (i == s.length()-1){
                stringBuilder.append(cur).append(countCur);
            }
        }

        System.out.println(stringBuilder.toString());

    }
}
