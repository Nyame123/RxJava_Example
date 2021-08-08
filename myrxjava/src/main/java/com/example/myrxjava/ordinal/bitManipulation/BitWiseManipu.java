package com.example.myrxjava.ordinal.bitManipulation;

public class BitWiseManipu {
    public static void main(String[] args) {

        toggle(8,6);
        //System.out.println(Integer.bitCount(6));
    }


    //toggle at a pos using XOR
    static void toggle(int n,int pos){
        int leftShift = 1 << pos;
        n ^= leftShift;
        System.out.printf("toggle bit at pos %d = %d",pos,n);
    }

    //unset a bit in nth pos of a num
    static void isSet(int n, int pos){
        int leftShift = (1 << pos);

       int res = n & leftShift;
        System.out.printf("is bit set at pos %d = %d",pos,res);
    }

    //unset a bit in nth pos of a num
    static void unSet(int n, int pos){
        int leftShift = ~(1 << pos);

        n &= leftShift;
        System.out.printf("set bit at pos %d = %d",pos,n);
    }

    //set a bit in nth pos of a num
    static void set(int n, int pos){
        int leftShift = 1 << pos;
        n |= leftShift;
        System.out.printf("set bit at pos %d = %d",pos,n);
    }

    //Time complexity is O(logn)
    static void countSetBit1(int n){
        int count = 0;
        while (n != 0){
            count += n & 1;
            n >>= 1;
        }
     System.out.println(count);
    }

  //Time complexity is O(logn)
    static void countSetBit2(int n) {
        int count = 0;
        while (n != 0) {
           n &= (n-1);
           count++;
        }

        System.out.println(count);
    }
}
