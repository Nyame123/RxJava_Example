package com.example.myrxjava.ordinal.bitManipulation;
/**
 * This insert a bit into another bit of 32 bit integer
 **/
public class BitInsertion {

    public static void main(String[] args) {
        int N = 1 << 10;
        int M = 19;
        int i = 2;
        int j = 6;

       int output = insertion(N,M,i,j);
       System.out.println(Integer.toBinaryString(output));
    }

    static int insertion(int n, int m, int i, int j) {
        //clear the bits in the position between i and j

        //create all ones bits using 8 bits
        int allOnes = ~0;

        //create left bits
        int left = allOnes << (j+1);

        //create right bits
        int right = ((1 << i) - 1);

        //NOW CREATE THE DESIRED MASK
        int mask = left | right;
        System.out.println(Integer.toBinaryString(m << i));
        System.out.println(Integer.toBinaryString(mask));


        //remove the bits between i and j in n
        int nCleared = n & mask;
        System.out.println(Integer.toBinaryString(nCleared));
        //move m into a correct position
        int bitMask = m << i;
        return nCleared | bitMask;

    }
}
