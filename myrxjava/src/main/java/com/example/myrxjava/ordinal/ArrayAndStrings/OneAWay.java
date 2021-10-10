package com.example.myrxjava.ordinal.ArrayAndStrings;

public class OneAWay {

    public static void main(String[] args) {
        String si = "apple", sy = "ale";
        if (isOneAwayStrictNess(si, sy)) {
            System.out.println("This is just One Way");
        } else {
            System.out.println("This is not just One Way");
        }
    }


    //This is used to check for edit in one place if
    // the strictness is put on the positions
    //Time complexity = O(n)
    static boolean isOneAwayStrictNess(String si, String sy) {
        if (si.length() == sy.length()) {
            return editReplace(si, sy);
        } else if (si.length() + 1 == sy.length()) {
            return editInsertOrRemove(si, sy);
        } else if (si.length() - 1 == sy.length()) {
            return editInsertOrRemove(sy, si);
        }

        return false;
    }

    private static boolean editInsertOrRemove(String si, String sy) {
        int index1 = 0, index2 = 0;
        while (index1 < si.length() && index2 < sy.length()) {
            if (si.charAt(index1) != sy.charAt(index2)) {
                if (index1 != index2)
                    return false;
            } else {
                //move the longest index by 1 only when the chars are equal
                index1++;
            }
            index2++;
        }
        return true;
    }

    private static boolean editReplace(String si, String sy) {

        boolean isFound = false;
        for (int i = 0; i < si.length(); i++) {

            if (si.charAt(i) != sy.charAt(i)) {

                if (isFound)
                    return false;
                isFound = true;
            }
        }

        return true;
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
    //Space Complexity = O(1) //space used = 26
    //This is used to check for edit in one place if
    // the strictness is not put on the positions
    static boolean isOneAwayNoStrictNess(String si, String sy) {

        int[] siArr = new int[26];
        int[] syArr = new int[26];


        //load the first string into mem 1
        for (int i = 0; i < si.length(); i++) {
            siArr[getNumeric(si.charAt(i))]++;
        }

        for (int i = 0; i < sy.length(); i++) {
            //reduce the count in mem 1 if it contains item in second string
            int item = getNumeric(sy.charAt(i));
            if (siArr[item] > 0) {
                siArr[item]--;
            } else {
                //load the mem 2 if first mem does not contain item in second string
                syArr[item]++;
            }

        }

        int countRight = 0, countLeft = 0;
        for (int i = 0; i < siArr.length; i++) {
            //return false if the left item count in mem 1 is more than 1
            if (Math.abs(siArr[i]) > 1) {
                return false;
            }
            //return false if the left item count in mem 2 is more than 1
            if (Math.abs(syArr[i]) > 1) {
                return false;
            }
            if (Math.abs(siArr[i]) > 0) {
                countLeft++;
            }
            if (Math.abs(syArr[i]) > 0) {
                countRight++;
            }
        }

        return countLeft <= 1 && countRight <= 1;
    }

}
