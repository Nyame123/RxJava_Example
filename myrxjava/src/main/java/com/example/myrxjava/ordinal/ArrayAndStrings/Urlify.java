package com.example.myrxjava.ordinal.ArrayAndStrings;

public class Urlify {

    public static void main(String[] args) {
        String s = "Mr John Smith";
       System.out.println(urlify(s));

        String s1 = "Mr John Smith    ";
       System.out.println(urlify(s1.toCharArray(),13));


    }

    static String urlify(char[] str, int trueLength){
        //grab all the spaces in the char array
        int spaceCount = 0,i;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' '){
                spaceCount++;
            }
        }

        //multiply the spaceCount by 2 and add to the original length
        int index = trueLength + spaceCount*2;
        if (trueLength < str.length) str[trueLength] = '\0';
        for (i = trueLength-1; i >= 0; i--) {
            if (str[i] == ' '){
                str[--index] = '0';
                str[--index] = '2';
                str[--index] = '%';
                //index -= 3;
            }else {
                str[index-1] = str[i];
                index--;
            }
        }

        return new String(str);
    }

    static String urlify(String s){
        final String REPLACEMENT = "%20";
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ' '){
                stringBuilder.append(REPLACEMENT);
            }else{
                stringBuilder.append(s.charAt(i));
            }
        }

        return stringBuilder.toString();
    }
}
