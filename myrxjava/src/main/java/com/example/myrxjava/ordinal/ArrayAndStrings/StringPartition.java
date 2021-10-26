package com.example.myrxjava.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * A Lowercase letters is given and we are to partition the string such
 * that each letter appear in at most one part and return a list of the integer
 * representing the size of the partition
 **/
public class StringPartition {

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> partitionSize = partitionLabels(s);
        System.out.println(partitionSize);
    }

    static List<Integer> partitionLabels(String s) {
        List<Integer> partions = new ArrayList<>();
        //grab the last index of each of the characters
        int[] lastIndexes = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndexes[s.charAt(i) - 'a'] = i;
        }

        int i = 0,start = 0; int end = 0;
        while (i < s.length()){
             end = Math.max(end,lastIndexes[s.charAt(i) - 'a']);

             if (i == end){
                 partions.add(end-start+1);
                 start = i+1;
             }

             i++;
        }

        return partions;
    }
}
