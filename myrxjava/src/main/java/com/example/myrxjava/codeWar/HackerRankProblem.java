package com.example.myrxjava.codeWar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

import io.reactivex.internal.util.LinkedArrayList;

import static java.util.stream.Collectors.toList;

public class HackerRankProblem {

    public static void main(String[] args) throws IOException {

        // readTestCases();

      /*   List<Integer> list = Arrays.asList(2, 6);
        List<Integer> b = Arrays.asList(24, 36);
        GCM(list, b);*/

//        parkingDilemma(4,new int[]{2, 10, 8, 17},3);

        // reachEndTime(3, new String[]{"..#...","#.#..#","#.#.##","..#.##", "......"}, 9);

        //divisibleSumPairs(new int[]{1, 3, 2, 6, 1, 2}, 3);

        //migratoryBirds(Arrays.asList(1, 4, 4, 4, 5, 3));
        //dayOfProgrammer(1800);

        System.out.printf("Fibo %d",fibonacciSeries(100,new HashMap<Integer, Long>()));
    }



    public static long fibonacciSeries(int n, HashMap<Integer,Long> memo){
        if (memo.containsKey(n)){
            return memo.get(n);
        }
        if (n <= 2){
            return 1;
        }

        long res = fibonacciSeries(n-1,memo) + fibonacciSeries(n-2,memo);
        memo.put(n,res);
        return res;
    }

    public static String dayOfProgrammer(int year) {
        // Write your code here
        int dayFallOn;
        int monthFallOn = 9;
        if(year >= 1700 && year <= 1917){ //Julian Calendar
            if(year % 4 == 0){//leap year
                dayFallOn =  12;
            }else{
                dayFallOn =  13;
            }
        }else if(year >= 1919){//Gregorian Calendar
            if((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)){//leap year
                dayFallOn =  12;
            }else{
                dayFallOn =  13;
            }
        }else{ //Transition calendar
            dayFallOn =  26;
        }

        String dateFormat = String.format("%02d.%02d.%02d",dayFallOn,monthFallOn,year);
        return dateFormat;
    }


    public static int migratoryBirds(List<Integer> arr) {
        // Write your code here
        int[] freq = new int[10];
        int max = Integer.MIN_VALUE;
        int item = 6;
        for (int i = 0; i < arr.size(); i++) {
            freq[arr.get(i)]++;
            if (freq[arr.get(i)] == max) {
                item = Math.min(arr.get(i), item);
            } else if (freq[arr.get(i)] > max) {
                max = freq[arr.get(i)];
                item = arr.get(i);
            }
        }

        return item;
    }

    public static void divisibleSumPairs(int[] ar, int k) {
        // Write your code here
        int counter = 0;
        for (int i = 0; i < ar.length; i++) {

            for (int j = i + 1; j < ar.length; j++) {
                if ((ar[i] + ar[j]) % k == 0) {
                    counter++;
                }
            }
        }

        System.out.println(counter);

    }

    public static void findDivisibleSumPairsSmartest(int[] arr, int k) {

        int a[] = new int[k];
        int count = 0;
        for (int a_i = 0; a_i < arr.length; a_i++) {
            int number = arr[a_i];
            number = number % k;
            int complement = number == 0 ? k : number;
            count += a[k - complement];
            a[number] += 1;
        }
        System.out.println(count);
    }

    private static void reachEndTime(int row, String[] grid, int maxTime) {

        int minAt = 0;
        int curIndex = 0;
        for (int i = 0; i < grid.length; ) {

            if (i == grid.length - 1 && curIndex == grid[i].length() - 1) {
                break;
            }


            if (minAt > maxTime) {
                break;
            }

            for (int j = 0; j < grid[i].length() - 1; ) {
                String segment = grid[i];
                Character left = null;
                Character right = null;
                Character top = null;
                Character bottom = null;
                if (j - 1 >= 0)
                    left = segment.charAt(j - 1);
                if (j + 1 < segment.length())
                    right = segment.charAt(j + 1);
                if (i - 1 >= 0)
                    top = grid[i - 1].charAt(j);
                if (i + 1 < grid.length)
                    bottom = grid[i + 1].charAt(j);


                if (bottom != null && bottom.equals('.')) {
                    minAt++;
                    i++;
                } else if (right != null && right.equals('.')) {
                    minAt++;
                    j++;
                } else if (left != null && left.equals('.')) {
                    minAt++;
                    j--;
                } else if (top != null && top.equals('.')) {
                    minAt++;
                    i--;
                } else {
                    minAt++;
                    break;
                }

                curIndex = j;

            }


        }

        if (minAt > maxTime) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

    private static void parkingDilemma(int n, int[] parking, int k) {

        SortAlgorithm.sort(parking, 0, parking.length - 1, false);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < parking.length; i++) {
            int lastIndex = i + (k - 1);
            if (lastIndex >= parking.length) {
                break;
            }
            int first = parking[i];
            int last = parking[lastIndex];
            int minLength = (last - first) + 1;

            if (minLength < min) {
                min = minLength;
            }
        }

        System.out.println(min);
    }

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        // Write your code here

        int counter = GCM(a, b);
        return counter;

    }


    private static int GCM(List<Integer> a, List<Integer> b) {
        int factor = getLCMSmartest(a);
        int counter = 0;
        int min = Integer.MAX_VALUE;
        for (int i = factor; i <= min; i += factor) {
            int factorCounter = 0;
            for (int j = 0; j < b.size(); j++) {
                min = (min > b.get(j)) ? b.get(j) : min;
                boolean isFactor = isCommonMultiple(b.get(j), i);
                if (isFactor) {
                    factorCounter++;
                }
            }

            if (factorCounter == b.size()) {
                counter++;
            }

        }

        System.out.println(counter);

        return counter;
    }

    private static boolean isCommonMultiple(int cm, int factor) {
        if (cm % factor == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static int getLCMSmartest(List<Integer> a) {
        int[] primeFactors = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        int lcm = 1;
        for (int j = 0; j < primeFactors.length; ) {
            int atLeastOne = 0;
            int allOnes = 1;
            for (int i = 0; i < a.size(); i++) {
                allOnes *= a.get(i);

                if (a.get(i) % primeFactors[j] == 0) {
                    a.set(i, a.get(i) / primeFactors[j]);
                    atLeastOne++;
                }

            }

            if (allOnes == 1) {
                break;
            }

            if (atLeastOne > 0) {
                lcm *= primeFactors[j];
            } else {
                j++;
            }
        }

        System.out.println(lcm);

        return lcm;
    }

    private static int getLCM(List<Integer> a) {
        int[] primeFactors = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        int lcm = a.get(a.size() - 1);
        for (int i = a.size() - 1; i > 0; i--) {
            if (lcm % a.get(i - 1) != 0) {
                lcm = lcm * a.get(i - 1);
            }
        }

        Set<Integer> factors = new HashSet<>();
        int lcmDup = lcm;
        for (int i = 0; i < primeFactors.length; ) {

            if (primeFactors[i] <= lcmDup) {

                if (lcmDup % primeFactors[i] == 0) {
                    factors.add(primeFactors[i]);
                    System.out.println(primeFactors[i]);
                    lcmDup = lcmDup / primeFactors[i];
                } else {
                    i++;
                }
            } else {
                break;
            }
        }

        lcm = 1;
        for (Integer integer : factors) {
            lcm *= integer;
        }

        System.out.println(lcm);

        return lcm;
    }


    private static void readTestCases() throws IOException {
        File desktop = new File(System.getProperty("user.home"), "/Desktop/apple_orange_testcase.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(desktop)));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int s = Integer.parseInt(firstMultipleInput[0]);

        int t = Integer.parseInt(firstMultipleInput[1]);

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int a = Integer.parseInt(secondMultipleInput[0]);

        int b = Integer.parseInt(secondMultipleInput[1]);

        String[] thirdMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(thirdMultipleInput[0]);

        int n = Integer.parseInt(thirdMultipleInput[1]);

        List<Integer> apples = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> oranges = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());
        /*int[] arr =  Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .mapToInt(Integer::parseInt).toArray();*/

        countApplesAndOranges(s, t, a, b, apples, oranges);

        bufferedReader.close();
    }

    public static void countApplesAndOranges(int s, int t, int a, int b, List<Integer> apples, List<Integer> oranges) {
        // Write your code here
        int appleCount = 0;
        int orangeCount = 0;
        for (Integer apple : apples) {
            int appleDis = (apple + a);
            if (appleDis >= s && appleDis <= t) {
                appleCount++;
            }
        }

        for (Integer orange : oranges) {
            int orangeDis = (orange + b);
            if (orangeDis >= s && orangeDis <= t) {
                orangeCount++;
            }
        }

        System.out.println(appleCount);
        System.out.println(orangeCount);

    }


}
