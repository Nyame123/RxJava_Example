package com.example.myrxjava.codeWar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class HackerRankProblem {

    public static void main(String[] args) throws IOException {

        runMain();

    }

    private static void runMain() throws IOException {
        // readTestCases();

      /*   List<Integer> list = Arrays.asList(2, 6);
        List<Integer> b = Arrays.asList(24, 36);
        GCM(list, b);*/

//        parkingDilemma(4,new int[]{2, 10, 8, 17},3);

        // reachEndTime(3, new String[]{"..#...","#.#..#","#.#.##","..#.##", "......"}, 9);

        //divisibleSumPairs(new int[]{1, 3, 2, 6, 1, 2}, 3);

        //migratoryBirds(Arrays.asList(1, 4, 4, 4, 5, 3));
        //dayOfProgrammer(1800);


        //System.out.printf("Fibo %d",fibonacciSeries(100,new HashMap<Integer, Long>()));

        //runCountTriplets();

        runTreeConstructor();
    }

    static void runTreeConstructor(){
        treeConstructor(new String[] {"(10,20)", "(20,50)"});
    }

    public static String treeConstructor(String[] strArr) {
        // code goes here
        HashMap<String,List<String>> treeNodes = new HashMap<>();
        for(String s: strArr){
            String[] newStr = s.replaceAll("[()]","").split(",");
            String parent = newStr[1];
            String child = newStr[0];


            treeNodes.putIfAbsent(parent,new ArrayList<String>());
            treeNodes.get(parent).add(child);

            if(treeNodes.get(parent).size() > 2){
                return "false";
            }

        }

        return "true";
    }


    private static void runFreqQuery() throws IOException {
        /*List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1,5));
        list.add(Arrays.asList(1,6));
        list.add(Arrays.asList(3,2));
        list.add(Arrays.asList(1,10));
        list.add(Arrays.asList(1,10));
        list.add(Arrays.asList(1,6));
        list.add(Arrays.asList(2,5));
        list.add(Arrays.asList(3,2));
        List<Integer> result = freqQuery(list);*/
        freqQueryReader();
    }

    static void freqQueryReader() throws IOException {
        File desktop = new File(System.getProperty("user.home"), "/Desktop/freqTestCases.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(desktop)));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);


        bufferedReader.close();
    }

    static List<Integer> freqQuery (BufferedReader bufferedReader, int q)throws IOException {

        HashMap<Integer, Integer> valuesToCounts = new HashMap<>();
        HashMap<Integer, Set<Integer>> countsToValues = new HashMap<>();
        ArrayList<Integer> results = new ArrayList<>();
        int size = q;

        for (int i = 0; i < q; i++) {
            String[] query = bufferedReader.readLine().split(" ");
            int operation = Integer.parseInt(query[0]);
            int number = Integer.parseInt(query[1]);

            int oldCount = valuesToCounts.getOrDefault(number, 0);
            int newCount;

            if (operation == 1) {
                newCount = oldCount + 1;
                changeKeys(valuesToCounts, countsToValues, number, oldCount, newCount);
            }

            if (operation == 2) {
                newCount = (oldCount > 1) ? oldCount - 1 : 0;
                changeKeys(valuesToCounts, countsToValues, number, oldCount, newCount);
            }

            if (operation == 3) {
                if (number > size) results.add(0);
                else {
                    results.add((number == 0 || countsToValues.getOrDefault(number, Collections.emptySet()).size() > 0) ? 1 : 0);
                }
            }
        }

        return results;
    }

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> arr = new ArrayList<>();
        HashMap<Integer,Integer> freq = new HashMap<>();
        HashMap<Integer,Set<Integer>> freqKey = new HashMap<>();
        int size = queries.size();



        for (List<Integer> list: queries){
            Integer op = list.get(0);
            Integer item = list.get(1);
            int oldCount = freq.getOrDefault(item,0);
            int newCount;
            if (op == 1){//add
                newCount = oldCount + 1;
                changeKeys(freq, freqKey, item, oldCount, newCount);

            }else if (op == 2){//delete

                newCount = (oldCount > 1) ? oldCount - 1 : 0;
                changeKeys(freq, freqKey, item, oldCount, newCount);

            }else{//check or assert existence of freq
                if (freqKey.containsKey(item) && !freqKey.get(item).isEmpty()){
                    arr.add(1);
                }else {
                    arr.add(0);
                }
            }
        }

        return arr;

    }

    private static void changeKeys(HashMap<Integer, Integer> freq, HashMap<Integer, Set<Integer>> freqKey, Integer item, int oldCount, int newCount) {
        freq.put(item, newCount);

        if (freqKey.containsKey(oldCount)) {
            freqKey.get(oldCount).remove(item);
        }
        freqKey.putIfAbsent(newCount, new HashSet<>());
        freqKey.get(newCount).add(item);
    }


    private static void runTwoString() {
        String s = twoStrings("writetoyourparents", "fghmqzldbc");
    }

    static void runCountTriplets() {
        List<Long> arr = new ArrayList<>();
        arr.add(1L);
        arr.add(3L);
        arr.add(9L);
        arr.add(9L);
        arr.add(27L);
        /*arr.add(81L);*/
        Long count = countTripletsWorking(arr, 3);
        System.out.println(count);
    }

    static long countTripletsWorking(List<Long> arr, long r) {

        Map<Long, Long> rightMap = getOccurenceMap(arr);
        Map<Long, Long> leftMap = new HashMap<>();
        long numberOfGeometricPairs = 0;

        for (long val : arr) {
            long countLeft = 0;
            long countRight = 0;
            long lhs = 0;
            long rhs = val * r;
            if (val % r == 0) {
                lhs = val / r;
            }
            Long occurence = rightMap.get(val);
            rightMap.put(val, occurence - 1L);

            if (rightMap.containsKey(rhs)) {
                countRight = rightMap.get(rhs);
            }
            if (leftMap.containsKey(lhs)) {
                countLeft = leftMap.get(lhs);
            }
            numberOfGeometricPairs += countLeft * countRight;
            insertIntoMap(leftMap, val);
        }
        return numberOfGeometricPairs;
    }

    private static Map<Long, Long> getOccurenceMap(List<Long> test) {
        Map<Long, Long> occurenceMap = new HashMap<>();
        for (long val : test) {
            insertIntoMap(occurenceMap, val);
        }
        return occurenceMap;
    }

    private static void insertIntoMap(Map<Long, Long> occurenceMap, Long val) {
        if (!occurenceMap.containsKey(val)) {
            occurenceMap.put(val, 1L);
        } else {
            Long occurence = occurenceMap.get(val);
            occurenceMap.put(val, occurence + 1L);
        }
    }

    static long countTriplets(List<Long> arr, long r) {
        int size = arr.size();
        long count = 0;
        for (int i = 0; i < size; i++) {
            int[] indices = new int[3];
            indices[0] = i;
            int indexCount = 1;
            int occurrence = 0;
            for (int j = 1; j < 3; j++) {

                //check for the multiple times occurrence for a number
                int k = i;
                int index = arr.subList(k, arr.size()).indexOf(arr.get(indices[j - 1]) * r);
                while (index != -1) {
                    occurrence++;
                    index = arr.subList(k, arr.size()).indexOf(arr.get(indices[j - 1]) * r);
                    k++;
                }

                if (index != -1) {
                    indices[j] = index;

                    indexCount += occurrence;
                } else { //if there is no geometric progressive value at this index, then it does not obey the rule.
                    break;
                }


                if (indexCount == 3) {
                    count++;
                    break;
                }

            }
        }

        return count;
    }

    public static String twoStringsSmartest(String s1, String s2) {
        for (char key : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
            if (s1.indexOf(key) != -1 && s2.indexOf(key) != -1) {
                return "YES";
            }
        }
        return "NO";
    }

    public static String twoStrings(String s1, String s2) {
        // Write your code here
        //int[] alphabelts = new int[26];
        HashMap<Character, Boolean> alph = new HashMap<>();
        HashMap<Character, Boolean> alph1 = new HashMap<>();


        //Arrays.fill(alphabelts, -1);
        int len1 = s1.length();
        int len2 = s2.length();
        //int totalLen = len1 + len2;
        int len = Math.max(len1, len2);
        int count = 0;
        for (int i = 0; i < len; i++) {

            if (i < s1.length() && i < s2.length()) {
                char index1 = s1.charAt(i);
                char index2 = s2.charAt(i);
                if (index1 == index2) { //there is a character
                    count++;
                    break;
                }
            }

            if (i < s1.length()) { //edge cases
                char index1 = s1.charAt(i);
                alph.put(index1, true);


                //alphabelts[index1 - 'a']++;

            }

            if (i < s2.length()) { //edge cases
                char index2 = s2.charAt(i);
                alph1.put(index2, true);

                //alphabelts[index2 - 'a']--;
            }

        }

        if (count == 0) { // count only when count = 0;

            for (char key : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {

                if (alph.get(key) != null && alph1.get(key) != null
                        && alph.get(key) && alph1.get(key)) {
                    count++;
                    break;
                }
            }

        }

        return (count > 0) ? "YES" : "NO";

    }

    public static int formingMagicSquare(List<List<Integer>> s) {
        // Write your code here
        int magicMatrix3Combinations[][][] = {
                new int[][]{new int[]{4, 3, 8}, new int[]{9, 5, 1}, new int[]{2, 7, 6}},
                new int[][]{new int[]{2, 9, 4}, new int[]{7, 5, 3}, new int[]{6, 1, 8}},
                new int[][]{new int[]{6, 7, 2}, new int[]{1, 5, 9}, new int[]{8, 3, 4}},
                new int[][]{new int[]{8, 1, 6}, new int[]{3, 5, 7}, new int[]{4, 9, 2}},
                new int[][]{new int[]{8, 3, 4}, new int[]{1, 5, 9}, new int[]{6, 7, 2}},
                new int[][]{new int[]{4, 9, 2}, new int[]{3, 5, 7}, new int[]{8, 1, 6}},
                new int[][]{new int[]{2, 7, 6}, new int[]{9, 5, 1}, new int[]{4, 3, 8}},
                new int[][]{new int[]{6, 1, 8}, new int[]{7, 5, 3}, new int[]{2, 9, 4}}
        };

        int minimalCost = Integer.MAX_VALUE;
        for (int k = 0; k < magicMatrix3Combinations.length; k++) {
            int[][] set = magicMatrix3Combinations[k];
            int totalCost = 0;
            for (int l = 0; l < set.length; l++) {
                int[] row = set[l];
                for (int j = 0; j < row.length; j++) {
                    int item = s.get(l).get(j);
                    if (item != row[j]) {
                        totalCost += Math.abs(item - row[j]);
                    }
                }
            }

            minimalCost = Math.min(minimalCost, totalCost);
        }


        return minimalCost;
    }


    public static long fibonacciSeries(int n, HashMap<Integer, Long> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n <= 2) {
            return 1;
        }

        long res = fibonacciSeries(n - 1, memo) + fibonacciSeries(n - 2, memo);
        memo.put(n, res);
        return res;
    }

    public static String dayOfProgrammer(int year) {
        // Write your code here
        int dayFallOn;
        int monthFallOn = 9;
        if (year >= 1700 && year <= 1917) { //Julian Calendar
            if (year % 4 == 0) {//leap year
                dayFallOn = 12;
            } else {
                dayFallOn = 13;
            }
        } else if (year >= 1919) {//Gregorian Calendar
            if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {//leap year
                dayFallOn = 12;
            } else {
                dayFallOn = 13;
            }
        } else { //Transition calendar
            dayFallOn = 26;
        }

        String dateFormat = String.format("%02d.%02d.%02d", dayFallOn, monthFallOn, year);
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
