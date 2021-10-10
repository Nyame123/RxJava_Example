package com.example.myrxjava.ordinal.ArrayAndStrings;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;

public class PalindromePermutation {

    public static void main(String[] args) {

        String si = "abbccddeefa";
        if (isPalindromePermUsingBit(si)) {
            System.out.println("This is a palindrome permutation");
        } else {
            System.out.println("This is not a palindrome permutation");
        }
    }

    //Using a bit manipulation for this task
    static boolean isPalindromePermUsingBit(String s) {
        //check if the string length is odd
        if (s.length() % 2 == 0)
            return false;

        int toggled = 0;
        for(int i = 0; i < s.length(); i++){
            int val = getNumeric(s.charAt(i));
            toggled = toggleWithXOR(toggled,val);
        }

        //substract 1 from the toggled value and 'and' the result with toggled value
        int result = toggled - 1;
        toggled &= result;

        return (toggled == 0);

    }

    //toggle using 'XOR' when 0
    private static int toggleWithXOR(int toggled,int val) {
        //toggle the char at ith pos
        int bitMask = 1 << val;
        toggled ^= bitMask;

        return toggled;
    }


    //toggle using 'OR' when 0
    //and using 'AND' then '~' when otherwise
    private static int toggle(int toggled,int val) {
        //toggle the char at ith pos
        int bitMask = 1 << val;
        if ((bitMask & toggled) == 0){
            toggled |= bitMask;
        }else{
            toggled &= ~bitMask;
        }

        return toggled;
    }


    //This is linear for both space and time complexity and it prevents the
    //second linear loop for lookup
    static boolean isPalindromePerm(String s) {
        //check if the string length is odd
        if (s.length() % 2 == 0)
            return false;
        int[] table = new int[Character.getNumericValue('z') -
                Character.getNumericValue('a') + 1];

        int oldCount = 0;
        for (char c : s.toCharArray()) {
            int cChar = getNumeric(c);
            if (cChar != -1) {
                table[cChar]++;

                if (table[cChar] % 2 == 0) {
                    oldCount--;
                } else {
                    oldCount++;
                }
            }
        }

        return oldCount == 1;
    }

    private static int getNumeric(char c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int cVal = Character.getNumericValue(c);
        if (a <= cVal && cVal <= z)
            return cVal - a;
        else
            return -1;
    }

    //Time Complexity = O(n)
    //Space Complexity = O(n)
    static boolean palindromePermutation(String s) {
        //check if the string length is odd
        if (s.length() % 2 == 0)
            return false;
        HashMap<Character, Integer> hashMap = getCharacterIntegerHashMap(s);
        //let's check if only one character is old and the rest even
        return isPalindromPerm(hashMap);
    }

    private static boolean isPalindromPerm(HashMap<Character, Integer> hashMap) {
        int oldCount = 0;
        Iterator<Character> keysIterator = hashMap.keySet().iterator();
        while (keysIterator.hasNext()) {
            int numCount = hashMap.get(keysIterator.next());
            if (numCount % 2 == 1) {
                oldCount++;
            }
        }

        return (oldCount == 1);
    }

    @NotNull
    private static HashMap<Character, Integer> getCharacterIntegerHashMap(String s) {
        //roll the char into hashtable
        s = s.toLowerCase().trim();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') {  //ignoring whitespace
                hashMap.putIfAbsent(c, 0);
                hashMap.put(c, hashMap.get(c) + 1);
            }
        }
        return hashMap;
    }
}
