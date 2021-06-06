package com.example.myrxjava.codeWar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import io.reactivex.annotations.NonNull;

import static java.util.stream.Collectors.toList;

public class CodeWarChallenge {
    public static void main(String[] args) throws IOException {

       // timeConversion("06:40:03AM");
        //countLetters("the quick brown fox jump over a lazy dog");
        /*int A[] = {5, 7, 3, 9, 4, 9, 8, 3, 1};
        int max = findLargestNumber(A);
        System.out.println("Max item = " + max);*/

        /* String text = "dsfdfds";
        text.contains(text);

        int[] A = {5, 7, 3, 9, 4, 9, 8, 3, 1};
        largestUniqueNumber(A);
        ListNode fourth = new ListNode(1);
        ListNode third = new ListNode(3);
        ListNode second = new ListNode(9);
        ListNode first = new ListNode(3);
        ListNode head = new ListNode(1);


        head.next = first;
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = null;

       isPalindromeSmartest(head);

        String[] strs = {"10","0001","111001","1","0"};
        findMaxForm(strs,5,3);

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(0);
        list.add(0);
        jumpingOnClouds(list);
        repeatedCount("aba", 10);
        array2D_DS();
         //2,3,4,5,1
        leftRotationSmartest(new int[]{1, 2, 3, 4, 5}, 1);
        System.out.println();
        rightRotationSmartest(new int[]{1, 2, 3, 4, 5}, 1);
        List<Integer> q = new ArrayList<Integer>(){
            {
                add(1);
                add(2);
                add(5);
                add(3);
                add(7);
                add(8);
                add(6);
                add(4);
            }
        };
        minimumBribes(q);
         minimumSwapSmartest(new int[]{4, 3, 1, 2});

          List<List<Integer>> arr = new ArrayList<List<Integer>>() {
            {
                List<Integer> row1 = new ArrayList<>();
                row1.add(1);
                row1.add(5);
                row1.add(3);
                List<Integer> row2 = new ArrayList<>();
                row2.add(4);
                row2.add(8);
                row2.add(7);
                List<Integer> row3 = new ArrayList<>();
                row3.add(6);
                row3.add(9);
                row3.add(1);

                add(row1);
                add(row2);
                add(row3);
            }
        };

        arrayManipulation(10, arr);
        sherlockAndAnagram("akkkbabkaaa");
         Math.abs(3);
        List<Long> numbers = new ArrayList<>();
        numbers.add(1L);
        numbers.add(25L);
        numbers.add(50L);
        numbers.add(75L);
        numbers.add(2L);
        numbers.add(2L);
        numbers.add(4L);
        countTriplet(numbers,2);
         sort(numbers);

        parkingDilemma(new int[]{2, 10, 8, 17}, 3);
        */
        List<Integer> arr = new ArrayList<>();
        arr.add(9);
        arr.add(9);
        arr.add(10);
        arr.add(10);
        arr.add(11);
        arr.add(9);
        arr.add(9);
        arr.add(9);
        arr.add(7);
        arr.add(6);
        arr.add(3);

        ranking(arr, 3);


    }

    public static void timeConversion(String s) {
        // Write your code here
        String mode = s.substring(s.length() - 2);
        String[] arr = s.split(":");
        String fPart = arr[0];
        String rawDate = s.substring(2, s.length() - 2);
        String hourPart = fPart;
        String fullDate = "";
        ;
        if (mode.equalsIgnoreCase("pm")) {

            if (Integer.parseInt(hourPart) != 12) {
                int result = Integer.parseInt(hourPart) + 12;;
                hourPart = ""+ result;

            }

        } else {
            if (Integer.parseInt(hourPart) == 12) {
                hourPart = "00";
            }
        }
        fullDate = hourPart + "" + rawDate;
        System.out.println(fullDate);

    }


    public static void ranking(List<Integer> candles, int rank) {
        // Write your code here

        int[] arr = new int[candles.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = candles.get(i);
        }

        SortAlgorithm.sort(arr,0,arr.length-1,true);

        int playerCounter = 1;
        for (int i = 1,rankCounter=1; i < arr.length; i++) {
            //System.out.println(arr[i]);
            if (rankCounter <= rank) {
                playerCounter++;
            }else{
                break;
            }

            if (arr[i] != arr[i + 1]) {
                rankCounter = playerCounter;

            }


        }

        System.out.println(playerCounter);

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

        ranking(apples,10);

        bufferedReader.close();
    }


    public static void birthdayCakeCandles(List<Integer> candles) {
        // Write your code here
        int max = 0;
        HashMap<String, Integer> track = new HashMap<>();
        for (int i = 0; i < candles.size(); i++) {
            if (candles.get(i) > max) {
                max = candles.get(i);
            }

            String key = candles.get(i) + "";
            if (track.get(key) == null) {
                track.put(key, 1);
            } else {
                int newValue = track.get(key) + 1;
                track.put(key, newValue);
            }

        }

        System.out.println(track.get("" + max));
    }


    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here
        long minSum = 0;
        long maxSum = 0;
        int n = arr.size();
        int temp = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }

            }
        }

        for (int i = 0, c = 0; i < 4; i++, c++) {
            minSum += arr.get(i);
            maxSum += arr.get(n - 1 - i);

        }

        System.out.print(minSum + " " + maxSum);
    }


    private static void printStar(int x, String ch) {
        if (x > 0) {
            System.out.print(ch);
            printStar(x - 1, ch);
        } else {
            System.out.println();
        }
    }

    public static void parkingDilemma(int[] cars, int k) {
        // write your code here
        Arrays.sort(cars);
        int len = cars.length;
        int min = cars[k - 1] - cars[0];
        for (int i = 1; i <= len - k; i++) {
            if (min > cars[k - 1 + i] - cars[i]) {
                min = cars[k - 1 + i] - cars[i];
            }
        }

        System.out.println(min + 1);
    }

    private static void sort(List<Integer> scores) {

        int[] arr = new int[scores.size()];
        int[] rank = new int[scores.size()];
        for (int i = 0; i < scores.size(); i++) {
            arr[i] = scores.get(i);
        }

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] < arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        }


        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();


        for (int i = 0; i < n; ++i) {
            rank[i] = i + 1;

        }

        for (int i = 0; i < n; ++i)
            System.out.print(rank[i] + " ");
        System.out.println();
    }

    /**
     * Count Triplet Count
     **/
    private static void countTriplet(List<Long> list, int ratio) {
        Map<Long, Long> leftMap = new HashMap<>();
        Map<Long, Long> rightMap = new HashMap<>();

        for (long item : list) {
            rightMap.put(item, rightMap.getOrDefault(item, 0L) + 1);
        }

        long count = 0;

        for (int i = 0; i < list.size(); i++) {
            long midTerm = list.get(i);
            long c1 = 0, c3 = 0;

            rightMap.put(midTerm, rightMap.getOrDefault(midTerm, 0L) - 1);

            if (leftMap.containsKey(midTerm / ratio) && midTerm % ratio == 0) {
                c1 = leftMap.get(midTerm / ratio);
            }
            if (rightMap.containsKey(midTerm * ratio)) {
                c3 = rightMap.get(midTerm * ratio);
            }

            leftMap.put(midTerm, leftMap.getOrDefault(midTerm, 0L) + 1);

            count += c1 * c3;
        }

        System.out.println("The triplet count => " + count);
    }

    /**
     * Checking if two words are anagram
     */
    private static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int[] lettermap = new int[26];
        for (int j = 0; j < a.length(); j++) {
            char ch = a.charAt(j);
            lettermap[ch - 'a']++;
            ch = b.charAt(j);
            lettermap[ch - 'a']--;
        }

        for (int j = 0; j < lettermap.length; j++) {
            if (lettermap[j] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compute the count of anagrams of a text
     */
    private static void sherlockAndAnagram(String text) {
        ArrayList<String> list = new ArrayList<>();
        int len = text.length();
        int counter = 0;
        for (int j = 0; j < len; j++) {
            for (int sub = 1; sub <= len - j; sub++) {
                String subPart = text.substring(j, j + sub);
                list.add(subPart);
            }
        }
        for (int j = 0; j < list.size(); j++) {
            String element = list.get(j);
            for (int k = j + 1; k < list.size(); k++) {
                if (isAnagram(list.get(k), element)) {
                    //System.out.println(list.get(k) + "->" + element);
                    counter++;
                }
            }
        }
        System.out.println(counter);

    }

    /**
     * Checking if the notes contains in the magazine
     */
    public static void checkMagazine(List<String> magazine, List<String> note) {
        // Write your code here

        HashMap<String, Integer> magazineMap = new HashMap<>();


        for (String word : magazine) {
            Integer i = magazineMap.get(word);

            if (i == null) {
                magazineMap.put(word, 1);
            } else {
                magazineMap.put(word, i + 1);
            }
        }

        for (String word : note) {
            Integer i = magazineMap.get(word);

            if (i == null || magazineMap.get(word) == 0) {
                System.out.println("No");
                return;
            } else {
                magazineMap.put(word, i - 1);
            }
        }

        System.out.println("Yes");
    }


    /**
     * Array manipulation
     **/
    private static void arrayManipulationSmartest(int n, List<List<Integer>> arr) {

        long[] initArray = new long[n + 1];
        for (int j = 0; j < arr.size(); j++) {
            List<Integer> row = arr.get(j);
            int a = row.get(0);
            int b = row.get(1);
            int k = row.get(2);

            initArray[a - 1] += k;
            initArray[b] -= k;

        }


        /* Find max value */
        long sum = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            sum += initArray[i];
            max = Math.max(max, sum);
        }


        System.out.println("Maximum manipulation > " + max);
    }

    private static void arrayManipulation(int n, List<List<Integer>> arr) {

        int[] initArray = new int[n];
        Arrays.fill(initArray, 0);
        int max = 0;
        for (int j = 0; j < arr.size(); j++) {
            for (int i = 0; i < initArray.length; i++) {
                List<Integer> row = arr.get(j);
                int a = row.get(0);
                int b = row.get(1);
                int k = row.get(2);

                int currentIndex = i + 1;
                if (currentIndex >= a && currentIndex <= b) {
                    initArray[i] += k;

                }

                max = Math.max(initArray[i], max);

            }
        }

        System.out.println("Maximum manipulation > " + max);
    }

    /**
     * Getting the minimum Index
     **/
    private static int minimumIndex(int from, int[] arr) {
        int max = Integer.MAX_VALUE;
        int j = from;
        while (j < arr.length) {
            if (arr[j] < max) {
                max = arr[j];
                from = j;
            }
            j++;
        }

        return from;
    }

    /**
     * Computing minimum swaps of an unordered array
     **/
    private static void minimumSwaps(int[] arr) {
        int i = 0;
        int count = 0;
        int temp;
        int n = arr.length;
        while (i < n) {

            int minIndex = minimumIndex(i, arr);

            if (arr[i] != i + 1) {
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                count++;
            }
            i++;


        }
        System.out.println("Minimum swaps > " + count);
    }

    /**
     * Computing minimum swaps of an unordered array
     **/
    private static void minimumSwapSmartest(int[] arr) {
        int i = 0;
        int count = 0;
        int temp;
        int n = arr.length;
        while (i < n) {
            if (arr[i] != i + 1) {
                temp = arr[i];
                arr[i] = arr[temp - 1];
                arr[temp - 1] = temp;
                count++;
            } else {
                i++;
            }
        }
        System.out.println("Minimum swaps > " + count);
    }

    /**
     * Computing minimum bribes
     **/
    private static void minimumBribes(List<Integer> q) {
        int bribeCount = 0;
        for (int i = 0; i < q.size(); i++) {
            int bribeCheck = q.get(i) - (i + 1);
            if (bribeCheck > 2) {
                System.out.println("Too chaotic");
                return;
            }

            int next = q.get(i) - 2;
            for (int j = Math.max(0, next); j < i; j++)
                if (q.get(j) > q.get(i)) {
                    bribeCount++;
                }

            /*if (bribeCheck > 0){
                //bribes.add(bribeCheck);
                bribeCount += bribeCheck;
            }*/
        }

        System.out.println(bribeCount);


    }


    /**
     * Computing Right Rotation on arrays
     **/
    private static void rightRotationSmartest(int[] arr, int shift) {
        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int newLoc = (i + shift) % arr.length;
            newArr[newLoc] = arr[i];
        }

        for (int i = 0; i < newArr.length; i++) {
            System.out.print(newArr[i] + ",");
        }
    }

    /**
     * Computing Left Rotation on arrays
     **/
    private static void leftRotationSmartest(int[] arr, int shift) {
        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int newLoc = (i + (arr.length - shift)) % arr.length;
            newArr[newLoc] = arr[i];
        }

        for (int i = 0; i < newArr.length; i++) {
            System.out.print(newArr[i] + ",");
        }
    }

    /**
     * Computing Left Rotation on arrays
     **/
    private static void leftRotation(int[] arr, int shift) {
        int[] toShift = new int[shift];
        int[] newArr = new int[arr.length];
        for (int i = 0; i < shift; i++) {
            toShift[i] = arr[i];
        }

        int j = 0;
        for (int i = shift; i < arr.length; i++, j++) {
            newArr[j] = arr[i];
        }

        for (int i = 0; i < toShift.length; i++, j++) {
            newArr[j] = toShift[i];
        }

        for (int i = 0; i < newArr.length; i++) {
            System.out.print(newArr[i] + ",");
        }
    }


    /**
     * Finding the sum of hourglass using the 2D Array DS
     **/
    private static void array2D_DS() {
        Scanner sc = new Scanner(System.in);
        int[][] a = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                a[i][j] = sc.nextInt();
            }

        }

        int sum = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int temp = a[i][j] + a[i][j + 1] + a[i][j + 2] + a[i + 1][j + 1] + a[i + 2][j] + a[i + 2][j + 1] + a[i + 2][j + 2];
                if (temp > sum) {
                    sum = temp;
                }
            }

        }

        System.out.println(sum);
    }

    /**
     * finding repeated strings
     */
    public static void repeatedCount(String str, int n) {
        int count = 0;
        int repeatedCount = n / str.length();
        int left = 0;
        if (repeatedCount != 0) {
            left = n % repeatedCount;
        }

        int len = Math.min(str.length(), n);

        for (int i = 0; i < len; i++) {
            if ('a' == str.charAt(i)) {
                count++;
            }
        }

        count = n > str.length() ? (repeatedCount * count) : count;
        for (int i = 0; i < left; i++) {
            if (str.charAt(i) == 'a') {
                count++;
            }
        }

        System.out.println("Total count of a in the string = " + count);
    }

    /**
     * finding number of jumpings on clouds
     */
    public static void jumpingOnClouds(List<Integer> c) {
        // Write your code here
        int n = c.size();
        int count = -1;
        for (int i = 0; i < n; i++, ++count) {
            if (i < n - 2 && c.get(i + 2) == 0) {
                i++;
            }
        }
        System.out.println("Min Jump = " + count);
    }

    /**
     * finding longest Valid Parenthesis
     */
    public static void longestValidParentheses(String s) {
        int n = s.length(), max = 0;
        int[] balanced = new int[n];
        Arrays.fill(balanced, -1);
        Stack<Integer> open = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') open.push(i);
            else if (!open.isEmpty()) {
                int start = open.pop();
                //Try to combine with immediately previous balanced set.
                start = (start - 1 > 0 && s.charAt(start - 1) == ')' && balanced[start - 1] != -1
                        ? balanced[start - 1] : start);
                max = Math.max(max, i - start + 1);
                balanced[i] = start;
            }
        }


        System.out.println("Max Balance Parenthesis => " + max);
    }


    /**
     * finding max zeros and ones subsets in a set
     */
    public static void findMaxForm(String[] strs, int m, int n) {
        int dp[][] = new int[m + 1][n + 1];

        for (String s : strs) {
            int count[] = new int[2];

            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - '0']++;
            }

            for (int i = m; i >= count[0]; i--) {
                for (int j = n; j >= count[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - count[0]][j - count[1]] + 1);
                }
            }
        }
        System.out.println("Max Form => " + dp[m][n]);
    }

    /**
     * finding if a linked list is palindrome
     */
    public static void isPalindromeSmartest(ListNode head) {
        Boolean isPalindrome = true;
        int counter = 0;
        ListNode node = head;
        while (node != null) {
            counter++;
            node = node.next;
        }
        int counter1 = counter;
        if (counter1 % 2 == 1) {
            counter--;
        }
        ListNode prev = null;
        node = head;
        counter = counter / 2;
        while (counter > 0) {
            ListNode nextNode = node.next;
            node.next = prev;
            prev = node;
            node = nextNode;
            counter--;
        }
        if (counter1 % 2 == 1) {
            node = node.next;
        }
        while (prev != null && node != null) {
            if (prev.val != node.val) {
                isPalindrome = false;
                break;
            }
            prev = prev.next;
            node = node.next;
        }

        System.out.println("Is Palindrome => " + isPalindrome.toString());
    }

    private static ListNode lastNode(ListNode head) {
        if (head.next == null) {
            return head;
        } else {
            head = head.next;
            lastNode(head);
        }

        return head;
    }

    /**
     * finding the unique largest number
     */
    public static void largestUniqueNumber(int[] A) {
        int[] res = new int[1001];
        int uniqueNum = -1;
        for (int i = 0; i < A.length; i++) {
            res[A[i]]++;
        }
        for (int i = 1000; i >= 0; i--) {
            if (res[i] == 1) {
                uniqueNum = i;
                break;
            }
        }
        System.out.println("Largest Unique Number => " + uniqueNum);
    }


    /**
     * finding the count of Vowel in smartest way
     */
    public static void getCountOfVowelSmartest(String str) {
        String replace = str.replaceAll("(?i)[^aeiou]", "");
        int count = replace.length();
        System.out.println("Count of Vowels =>" + count + " Text => " + replace);
    }

    /**
     * finding the count of Vowel in ordinary way
     */
    public static void getCountOfVowel(String str) {
        int vowelsCount = 0;
        // your code here
        String vowels = "aeiou";
        if (str != null && !str.isEmpty()) {
            String[] strAr = str.replace(" ", "").split("");
            for (String item : strAr) {
                if (vowels.contains(item)) {
                    vowelsCount++;
                }
            }
        }
        System.out.println("Count of Vowels =>" + vowelsCount);
    }

    /**
     * finding the duplicate count in ordinary way
     */
    public static void duplicateCount1(String text) {
        // Write your code here
        String[] newTextAr = text.replace(" ", "").split("");

        HashMap<String, Integer> uniqueHashMap = new HashMap<>();
        for (int i = 0; i < newTextAr.length; i++) {

            String lowerCase = newTextAr[i].toLowerCase();
            if (!uniqueHashMap.containsKey(lowerCase)) {

                uniqueHashMap.put(lowerCase, 1);
            } else {
                int count = uniqueHashMap.get(lowerCase) + 1;
                uniqueHashMap.put(lowerCase, count);
            }
        }

        //count letter and digits more than one or duplicates occurence
        int sumDup = 0;
        for (String key : uniqueHashMap.keySet()) {
            if (uniqueHashMap.get(key) > 1) {
                sumDup++;
            }
        }

        System.out.println("Duplicates Count =>" + sumDup);

    }

    /**
     * finding the duplicate count in smartest way
     */
    public static void duplicateCount(String text) {
        int ans = 0;
        text = text.toLowerCase();
        while (text.length() > 0) {
            String firstLetter = text.substring(0, 1);
            text = text.substring(1);
            if (text.contains(firstLetter)) ans++;
            text = text.replace(firstLetter, "");
        }
        System.out.println("Duplicates Count =>" + ans);
    }

    /**
     * finding the count of valley
     */
    public static void countingValley(int n, @NonNull String args) {

        char[] hike = args.toCharArray();

        int count = 0;
        int altitude = 0;

        for (char c : hike) {
            // Step up
            if (c == 'U') {
                if (altitude == -1) {
                    count++;
                }
                altitude++;
            }
            // Step down
            else {
                altitude--;
            }
        }

        System.out.println(count);
    }

    /**
     * finding the number of pairs in smartest way
     */
    public static int sockMerchantPair(int n, @NonNull List<Integer> ar) {
        // Write your code here

        int[] freq = new int[101];
        for (int i = 0; i < ar.size(); i++) {
            freq[ar.get(i)]++;
        }

        int pair = 0;
        for (int i = 0; i < freq.length; i++) {
            pair += freq[i] >> 1;
        }

        return pair;

    }


    private static int findLargestNumber(int[] A) {

        int largest = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > largest) {
                largest = A[i];
            }
        }

        return largest;
    }

    /**
     * finding the count of letters in a sentence
     */
    public static void countLetters(String sentence) {
        String newSentence = sentence.replace(" ", "").toLowerCase();
        String[] sentenceArray = newSentence.split("");

        //get various unique letters in the sentence
        HashMap<String, Integer> uniqueOccurrence = new HashMap<>();
        for (int i = 0; i < sentenceArray.length; i++) {
            if (!uniqueOccurrence.containsKey(sentenceArray[i])) {
                uniqueOccurrence.put(sentenceArray[i], 1);
            } else {
                Integer freg = uniqueOccurrence.get(sentenceArray[i]) + 1;
                uniqueOccurrence.put(sentenceArray[i], freg);
            }
        }

        for (String item : uniqueOccurrence.keySet()) {
            System.out.format("Letters: %s and Frequency: %s \n", item, uniqueOccurrence.get(item));
        }
    }

    /**
     * Maskifying the whole text except the last four;
     */
    public static String maskify(String str) {

        int len = str.length();
        if (len > 4) {
            int lastFourIndex = len - 4;
            String substring = str.substring(0, lastFourIndex);
            for (int i = 0; i < lastFourIndex; i++) {
                substring = substring.replace(str.charAt(i), '#');
            }
            String lastPart = str.substring(lastFourIndex);
            return substring + "" + lastPart;
        } else {
            return str;
        }


    }

    public static boolean isPhoneNumber(String textToUse, int lastFourIndex) {
        Pattern pattern = Pattern.compile("^([a-zA-Z]){" + lastFourIndex + "}$");
        Matcher m = pattern.matcher(textToUse);
        if (m.find()) {
            // ...then you can use group() methods.
            System.out.println(m.group(0)); // whole matched expression
            System.out.println(m.group(1)); // first expression from round brackets (Testing)
            System.out.println(m.group(2)); // second one (123)
            System.out.println(m.group(3)); // third one (Testing)
        }
        return m.matches();
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
