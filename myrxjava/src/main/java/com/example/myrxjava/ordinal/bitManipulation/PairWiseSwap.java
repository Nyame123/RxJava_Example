package com.example.myrxjava.ordinal.bitManipulation;

/**
 * Swapping even bits and odd bits, such that bit0 and bit1 are swapped
 * bit2 and bit3 are also swapped in that order
 **/
public class PairWiseSwap {

    public static void main(String[] args) {
        int num = 10;
        int result = swappOddEvenBits(num);
        System.out.println(result);
    }

    static int swappOddEvenBits(int num){
        //1. we can use a 0xaaaaaaaa and shift it to right by 1 place to shift odd bits
        //2. we can use a 0x55555555 and shift it to right by 1 place to shift even bits
        return (((num & 0xaaaaaaaa) >>> 1) | (((num & 0x55555555) << 1)));
    }
}
