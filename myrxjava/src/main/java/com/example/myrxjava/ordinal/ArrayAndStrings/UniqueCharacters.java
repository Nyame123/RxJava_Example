package com.example.myrxjava.ordinal.ArrayAndStrings;

import java.util.Arrays;
import java.util.HashMap;

public class UniqueCharacters {

    public static void main(String[] args) {
        String string = "asdfghjklzxcvbnmpoi uytrewq";
        if (isUniqueCharsUsingBit(string)){
            System.out.println("Unique string");
        }else {
            System.out.println("Not unique string");
        }
    }

    static boolean isUniqueCharsUsingBit(String s){
        if (nonUniqueChars(s))
            return false;

        int checker = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            int value = ch - 'a';
            int bitMask = 1 << value;
            if ((checker & (bitMask)) > 0){
                return false;
            }
            checker |= (bitMask);
        }

        return true;
    }

    static boolean nonUniqueChars(String s){
        return s.length() > 128; //ASCII character set is 128 length;
    }

    static boolean isUniqueCharsNoHashMap(String s){
        if (nonUniqueChars(s)){
            return false;
        }
        char[] characters = s.toLowerCase().trim().toCharArray();
        Arrays.sort(characters);
        char prev = characters[0];
        for (int i = 1; i < characters.length; i++){
            if (characters[i] == prev){
                return false;
            }else{
                prev = characters[i];
            }
        }
        return true;
    }

    //checking if a string is unique using Hashmap
    static boolean isUniqueCharsUsingHashmap(String s){
        if (nonUniqueChars(s)){
            return false;
        }
        HashMap<Character,Integer> charTables = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (charTables.containsKey(character)){
                return false;
            }
            charTables.putIfAbsent(character,1);

        }

        return true;
    }

}
