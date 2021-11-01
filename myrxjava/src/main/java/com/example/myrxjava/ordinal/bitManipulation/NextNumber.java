package com.example.myrxjava.ordinal.bitManipulation;

public class NextNumber {

    public static void main(String[] args) {

        int num = 10;
        int nextSmallest = nextSmallerNumberArithmetic(num);
        int nextbiggest = nextBiggerNumberArithmetic(num);
        System.out.println("Smallest = "+nextSmallest);
        System.out.println("Biggest = "+nextbiggest);
    }

    //using Arithmetic Approach
    static int nextSmallerNumberArithmetic(int num){
        //1. count zeros and then ones
        int cZeros = 0, cOnes = 0;
        int temp = num;
        while ( (temp & 1) == 1){
            cOnes++;
            temp >>= 1;
        }

        //Error: cannot generate smaller ones with same number of bits
        if (temp == 0){
            return -1;
        }

        while ((temp != 0) && (temp & 1) == 0){
            cZeros++;
            temp >>= 1;
        }

        return num - (1 << cOnes) - (1 << cZeros - 1) + 1;
    }

    //using Arithmetic Approach
    static int nextBiggerNumberArithmetic(int num){
        //compute c0 and c1
        int c = num;
        int c0 = 0, c1 = 0;
        while (((c & 1) == 0) && (c != 0)){
            c0++;
            c >>= 1;
        }

        while ((c & 1) == 1){
            c1++;
            c >>= 1;
        }

        //Error when num = 11..1100...00, then there is no bigger number with same
        //number of 1s
        if (c0 + c1 == 31 || c0 + c1 == 0){
            return -1;
        }

        return num + (1 << c0) + (1 << (c1-1)) - 1;
    }

    static int nextSmallestNumber(int num) {

        //1. count zeros and then ones
        int cZeros = 0, cOnes = 0;
        int temp = num;
        while ( (temp & 1) == 1){
            cOnes++;
            temp >>= 1;
        }

        //Error: cannot generate smaller ones with same number of bits
        if (temp == 0){
            return -1;
        }

        while ((temp != 0) && (temp & 1) == 0){
            cZeros++;
            temp >>= 1;
        }

        //2. Get the position to turn off the bit
        int p = cOnes + cZeros;
        num &= -(1 << p+1);
        num |= ((1 << cOnes+1) - 1) << (cZeros-1);


        return num;
    }

    static int nextBiggerNumber(int num) {

        //1. Get the rightmost non-trailing zero position, p
        //2. Turn on the bit at position, p
        //3. Count the number of zeros (cZeros) and ones (cOnes) up to the p
        //4. Clear all the bits up to p
        //5. Turn on the bits from 0 through (cOnes - 1)

        //1. Get the rightmost non-trailing zero position, p
        //temp number
        int temp = num;
        boolean oneFound = false;
        int pCounter = 0;
        while (temp != 0){
            //if first one is found
            if ((temp & 1) == 1){
                oneFound = true;
            }
            if ((temp & 1) == 0 && oneFound){
                break;
            }

            pCounter++;
            //reduce the number by 2
            temp >>>= 1;
        }

        //3. Count the number of zeros (cZeros) and ones (cOnes) up to the p
        int cZero = 0, cOnes = 0;
        int p = pCounter;
        temp = num;
        while (p != 0){
            if ((temp & 1) == 0){
                cZero++;
            }
            if ((temp & 1) == 1){
                cOnes++;
            }
            p--;
            temp >>>= 1;
        }

        if (cOnes + cZero == 31 || cOnes + cZero == 0){
            return -1;
        }

        //2. Turn on the bit at position, p
        num |= (1 << pCounter);


        //4. Clear all the bits up to p
        int pMask = -(1 << pCounter);
        num &= pMask;


        //5. Turn on the bits from 0 through (cOnes - 1)
        int finalMask = (1 << cOnes-1) - 1;
        num |= finalMask;

        return num;
    }

    static int getNext(int num){
        //compute c0 and c1
        int c = num;
        int c0 = 0, c1 = 0;
        while (((c & 1) == 0) && (c != 0)){
            c0++;
            c >>= 1;
        }

        while ((c & 1) == 1){
            c1++;
            c >>= 1;
        }

        //Error when num = 11..1100...00, then there is no bigger number with same
        //number of 1s
        if (c0 + c1 == 31 || c0 + c1 == 0){
            return -1;
        }

        //position of the rightmost non-trailing zero
        int p = c0 + c1;
        num |= (1 << p); //flip the bit on
        num &= -(1 << p); //clear all bits to the right of p
        num |= ((1 << c1-1) - 1); //insert (C1-1) ones on the right
        return num;
    }
}
