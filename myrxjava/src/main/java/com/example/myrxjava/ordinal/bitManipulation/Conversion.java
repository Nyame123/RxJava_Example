package com.example.myrxjava.ordinal.bitManipulation;

/**
 * This is to determin the number of bits to change to turn
 * one number to be same as other number, given two integers
 **/
public class Conversion {

    public static void main(String[] args) {

        int first = 29;
        int second = 15;
        int result = bitSwapRequired(first,second);
        System.out.println(result);
    }

    //counting the number of ones in the bits
    static int bitSwapRequired(int n1, int n2){
        int counter = 0;
        for (int c = n1 ^ n2; c != 0; c = c & (c-1)){
            counter++;
        }
        return counter;
    }

    static int numberOfBitForConversion(int n1, int n2) {

        int temp1 = n1;
        int temp2 = n2;
        int counter = 0;
        while (temp1 != 0 || temp2 != 0) {
            int i = (temp1 & 1);
            int j = (temp2 & 1);
            counter +=  i ^ j;
            temp1 >>= 1;
            temp2 >>= 1;
        }

        return counter;
    }

    static int numberOfBitForConversion1(int n1, int n2) {

        int counter = 0;
        for (int c = n1 ^ n2; c != 0; c >>= 1){
            counter += c & 1;
        }

        return counter;
    }
}
