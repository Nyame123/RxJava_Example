package com.example.myrxjava.ordinal.ArrayAndStrings;

public class StringRotation {

    public static void main(String[] args) {
        String si = "waterbottle";
        String sy = "erbottlewat";
        if(isRotatedString(si,sy)){
            System.out.println("This is a rotation");
        }else{
            System.out.println("This is not a rotation");
        }
    }
    static boolean isRotatedString(String si, String sy){

        if (si.length() != sy.length()){
            return false;
        }

        StringBuilder x = new StringBuilder();
        StringBuilder y = new StringBuilder();
        for (int i = 0,j = 0; i < si.length(); i++) {

            if (si.charAt(i) == sy.charAt(j)){
                x.append(si.charAt(i));
                j++;
            }else{
                y.append(si.charAt(i));
            }
        }

        String latter = y.toString();
        for (int i = 0; i < latter.length(); i++) {
            x.append(latter.charAt(i));
        }

        return x.toString().equalsIgnoreCase(sy);
    }
}
