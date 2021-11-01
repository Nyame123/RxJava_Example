package com.example.myrxjava.ordinal.bitManipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the longest subsequence where exactly one 0 bit can be flipped to 1s
 **/
public class FlipBitWin {

    public static void main(String[] args) {

        int result = findLongestSubsequenceAfterPreprocessing(30000);
        System.out.println(result);
    }

    //Time complexity = O(b) where b is the number of bits
    static int findLongestSubsequence(int num){

        //if all ones
        if (~num == 0){
            return Integer.BYTES * 8;
        }
        int maxLongestSub = 1;
        int previousSum = 0;
        int currentSum = 0;
        int i = num;
        for (; i != 0;) {

            if ((i & 1) == 1) {//if it is a 1 bit
                currentSum++;

            }else if ((i & 1) == 0){ //if it is 0 bit
                //check the next bit if not same as the searchedItem
                previousSum = ((i & 2) == 0)? 0 : currentSum+1;
                currentSum = 0;
            }

            i >>>= 1;
            maxLongestSub = Math.max(maxLongestSub,currentSum + previousSum);
        }

        return maxLongestSub;
    }


    //Time complexity = O(b) where b is the number of bits
    //Space complexity = O(b) where b is the number of bits
    static int findLongestSubsequenceAfterPreprocessing(int num){

        if (num == -1){ //all ones in two's complement
            return Integer.BYTES * 8;
        }
        List<Integer> sequences = getAllTotalCounts(num);
        int leftSequence = 0;
        int rightSequence = 0;
        int maxLongestSubsequence = 0;
        int totalSum = 0;
        for (int i = 0; i < sequences.size(); i += 2){
            int zeroSequence = sequences.get(i);
            leftSequence = (i - 1 < 0)? 0 : sequences.get(i-1);
            rightSequence = (i + 1 >= sequences.size())? 0 : sequences.get(i+1);
            if (zeroSequence == 1){
                //add left and right subsequence of ones and one zero
                totalSum = leftSequence + rightSequence + 1;
            }else if (zeroSequence > 1){
                //get the longest left or right and add one zero
                totalSum = Math.max(leftSequence,rightSequence) + 1;
            }else if (zeroSequence == 0){
                totalSum = Math.max(leftSequence,rightSequence);
            }

            maxLongestSubsequence = Math.max(maxLongestSubsequence,totalSum);
        }

        return maxLongestSubsequence;
    }

    static List<Integer> getAllTotalCounts(int num) {
        List<Integer> counts = new ArrayList<>();

        int counter = 0;
        int initNum = 0;
        for (int i = 0; i < Integer.BYTES*8; i++){
            if ((num & 1) != initNum){
                //save the counter
                counts.add(counter);
                counter = 0;
                initNum = (num & 1);
            }

            counter++;
            num >>>= 1;
        }

        counts.add(counter);

        return counts;
    }
}
