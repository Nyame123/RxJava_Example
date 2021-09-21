package com.example.myrxjava.ordinal.easy;

public class NumberCheck {

    public static void main(String[] args) {
       /* boolean isPerfect = isPerfectNumber(6);
        if (isPerfect){
            System.out.println("The number is perfect");
        }else{
            System.out.println("The number is not perfect");
        }

        boolean isPowerful = isPowerfulNumber(10);
        if (isPowerful) {
            System.out.println("The number is powerful");
        } else {
            System.out.println("The number is not powerful");
        }*/

        //missingNumber(new int[]{2,3,1,5},5);
        increasingArray(new int[]{3,2,5,1,7});
        //longestSubsequence("ATTTTTTCGGGA");
        permutationWithAdjacentMorethanOne(6);
    }

    static void permutationWithAdjacentMorethanOne(int n){
        if(n == 1){
            System.out.println("Solution = "+1);
            return;
        }

        if(n == 2 || n == 3){
            System.out.println("No Solution");
            return;
        }

        System.out.print("Solution = ");
        if(n % 2 == 0){
            for(int i = 2; i < n; i += 2){
                System.out.print(i+" ");
            }

            for(int i = 1; i < n; i += 2){
                System.out.print(i+" ");
            }

        }else{
            for(int i = n-1; i > 0; i -= 2){
                System.out.print(i+" ");
            }

            for(int i = n; i > 0; i -= 2){
                System.out.print(i+" ");
            }
        }
    }

    static void increasingArray(int[] arr){
        int ans = 0;
        int max = 0;
        for(int it: arr){
            max = Math.max(it,max);
            ans += max - it;
        }

        System.out.println("Min number of moves required "+ ans);
    }


    static void missingNumber(int[] arr, int n){
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        int missingNum = n*(n+1)/2 - sum;
        System.out.println(missingNum);
    }

    static void longestSubsequence(String s){
        int ans = 0;
        int count = 0;
        char start = 'A';
        for(char d: s.toCharArray()){
            if(start == d){
                count++;
                ans = Math.max(ans,count);
            }else{
                start = d;
                count = 1;
            }
        }

        System.out.println("Longest subsequence "+ ans);

    }


    static boolean isPowerfulNumber(int n){

        int power = 0;
        while(n % 2 == 0){
            n /= 2;
            power++;
        }

        if(power == 1)
            return false;


        for(int i = 3; i < Math.sqrt(n); i+= 2){
            power = 0;
            while(n % i == 0){
                n /= i;
                power++;
            }

            if(power == 1){
                return false;
            }

        }

        return true;
    }

    static boolean isPerfectNumber(int n){

        int sum = 1;
        for(int i = 2; i <= Math.sqrt(n); i++){

            if(n % i == 0){
                if(i*i != n){
                    sum += i + n/i;
                }else{
                    sum += i;
                }
            }
        }

        if(sum == n && n != 1){
            return true;
        }

        return false;
    }
}
