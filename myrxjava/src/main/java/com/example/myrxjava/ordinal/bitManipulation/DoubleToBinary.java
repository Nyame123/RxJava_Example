package com.example.myrxjava.ordinal.bitManipulation;

import com.example.myrxjava.combinatoricsGameTheory.hard.Permutation;

/**
 * Convert double number to a binary representation
 **/
public class DoubleToBinary {

    public static void main(String[] args) {
        double num = 0.0001;
        String binaryRep = printBinary(num);
        System.out.println(binaryRep);
    }

    private static String printBinaryFractional(double num) {
        //number should be within 0 and 1
        if (num <= 0 || num >= 1){
            return "ERROR";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".");
        double fraction = 0.5;
        while (num > 0){
            //restricting limit to 32 characters
            if (stringBuilder.length() > 32) {
                return "ERROR";
            }
            if (num > fraction){
                stringBuilder.append(1);
                num -= fraction;
            }else{
                stringBuilder.append(0);
            }
            fraction /= 2;
        }

        return stringBuilder.toString();
    }

    private static String printBinary(double num) {
        //number should be within 0 and 1
        if (num <= 0 || num >= 1){
            return "ERROR";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".");
        while (num > 0){
            //restricting limit to 32 characters
            if (stringBuilder.length() > 32) {
                return "ERROR";
            }
            double r = num * 2;
            if (r > 1){
                stringBuilder.append(1);
                num = r - 1;
            }else{
                stringBuilder.append(0);
                num = r;
            }
        }

        return stringBuilder.toString();
    }
}
