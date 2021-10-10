package com.example.myrxjava.ordinal.ArrayAndStrings;

import java.util.HashMap;
/***
 * In this solution we assumed the scenarios are not case sensitive
 * and whitespace do not count
 **/
public class TwoStringPermutation {

    public static void main(String[] args) {
        String si = "abbc", sy = "caab";
        if (isPermutationOf(si, sy)) {
            System.out.println("They are permutation of other");
        } else {
            System.out.println("They are not permutation of other");
        }
    }

    //Time Complexity = O(n)
    //Space Complexity = 0(1)
    static boolean isPermutationUsingBits(String si, String sy) {

        //primal test to check if the two strings are of the same length
        if (si.toLowerCase().trim().length() != sy.toLowerCase().trim().length())
            return false;

        int checkeri = 0, checkery = 0;

        //rolling the si into bits holder
        for (char c : si.toLowerCase().trim().toCharArray()) {
            int val = c - 'a';
            checkeri += val;
        }

        //rolling the sy into bits holder
        for (char c : sy.toLowerCase().trim().toCharArray()) {
            int val = c - 'a';
            checkery += val;
        }

        return  (checkery == checkeri);

    }

    //Time Complexity = O(n)
    //Space Complexity = 0(n)
    static boolean isPermutationOf(String si, String sy) {

        //primal test to check if the two strings are of the same length
        if (si.toLowerCase().trim().length() != sy.toLowerCase().trim().length())
            return false;

        HashMap<Character, Integer> charTable = new HashMap<>();

        //roll the first string in a hash table for lookups
        for (Character c : si.toLowerCase().trim().toCharArray()) {
            charTable.putIfAbsent(c, 0);
            charTable.put(c, charTable.get(c) + 1);
        }

        //lookup the hashtable from above using second string and
        //at the end, there should be nothing in the hashtable
        for (Character c : sy.toLowerCase().trim().toCharArray()) {
            if (!charTable.containsKey(c))
                return false;
            charTable.put(c,charTable.get(c)-1);
            if (charTable.get(c) == 0)
                charTable.remove(c);
        }

        return true;

    }
}
