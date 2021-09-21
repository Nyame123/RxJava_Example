package com.example.myrxjava.treeAndGraph.hard;

import java.util.Arrays;

/**
 * Segment Tree | Set 2 (Range Minimum Query)
 * Difficulty Level : Hard
 * Last Updated : 06 Jul, 2021
 * We have introduced segment tree with a simple example in the previous post.
 * In this post, Range Minimum Query problem is discussed as another example where Segment Tree
 * can be used. The following is the problem statement:
 * We have an array arr[0 . . . n-1]. We should be able to efficiently find the minimum value
 * from index qs (query start) to qe (query end) where 0 <= qs <= qe <= n-1.
 * <p>
 * https://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
 **/
public class MinInRangeSegmentTree {

    int[] segArr;

    public static void main(String[] args) {
        int[] arr = {-111, 3, 2, 7, 9, 11};
        int n = arr.length;
        MinInRangeSegmentTree minInRangeSegmentTree = new MinInRangeSegmentTree();
        minInRangeSegmentTree.buildSegmentTree(arr);
        int qs = 3;  // Starting index of query range
        int qe = 5;  // Ending index of query range

        // Print minimum value in arr[qs..qe]
        System.out.println("Minimum of values in range [" + qs + ", "
                + qe + "] is = " + minInRangeSegmentTree.findMinInRange(qs, qe,n));
    }

    void buildSegmentTree(int[] arr){
        int n = arr.length;
        //know the length of memory needed for allocation

        int size = getSizeWithPowerOf2(n);
        segArr = new int[size];

        //Arrays.fill(segArr,Integer.MAX_VALUE);

       int overallMin = contructSegmentTree(arr,0,0,n-1);
       System.out.println("Overall min = "+ overallMin);

    }

    //Find the minimum within range
    int findMinInRange(int l, int r, int n){
        //edges cases testing
        if (l < 0 || r > n-1 || l > r){
            System.out.println("Invalid range given");
            return Integer.MIN_VALUE;
        }

        return findMinInRangeUtil(l,r,0,n-1,0);
    }

    int findMinInRangeUtil(int l, int r, int s, int e,int pos){

        //if query within range
        if (l <= s && e <= r)
            return segArr[pos];

         if (l > e || r < s)
            return Integer.MAX_VALUE;

        int mid = s + (e-s)/2;
        int leftMin = findMinInRangeUtil(l,r,s,mid,2*pos+1);
        int rightMin = findMinInRangeUtil(l,r,mid+1,e,2*pos+2);
        int min = Math.min(leftMin,rightMin);
        return min;

    }

    //generate the segment Tree
    private int contructSegmentTree(int[] arr,int pos,int l,int r) {

        if (l == r){
            segArr[pos] = arr[l];
            return arr[l];
        }
        int mid = l + (r-l) / 2;
        int leftMin = contructSegmentTree(arr,2*pos+1,l,mid);
        int rightMin = contructSegmentTree(arr,2*pos+2,mid+1,r);

        int min = Math.min(leftMin,rightMin);
        segArr[pos] = min;

        return min;
    }

    //checking for power of 2
    boolean isPowerOf2(int n){
        while (n%2 == 0){
            n /= 2;
            if (n == 1)
                return true;
        }

        return false;
    }

    //find next power of 2 of n
    int nextPowerOf(int n){
        int power = 1;
        while (power <= n){
            power *= 2;
        }

        return power;
    }

    //checking if n is a power of 2
    int getSizeWithPowerOf2(int n){
        int size = 2*n - 1;
        if (!isPowerOf2(n)){
            size = 2* nextPowerOf(n) - 1;
        }

        return size;
    }

    //using the height of the tree to know memory needed
    int getSizeWithLog(int n){
        int x = (int) Math.ceil(Math.log((n)/Math.log(2)));
        return (int) (2*Math.pow(2,x) - 1);
    }
}
