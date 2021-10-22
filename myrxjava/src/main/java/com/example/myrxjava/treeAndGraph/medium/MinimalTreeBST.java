package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.treeAndGraph.prep.TreeNode;
/**
 * Generating a binary search tree from array of numbers
 **/
public class MinimalTreeBST {

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6};
        minimalHeight(arr);
    }

    static void minimalHeight(int[] arr) {

        TreeNode<Integer> rootNode = recurseBSTWithMinHeight(arr, 0, arr.length-1);
        System.out.println(rootNode.getData());
    }

    static TreeNode<Integer> recurseBSTWithMinHeight(int[] arr, int start, int end) {
        //base case
        if (start > end){
            return null;
        }
        //get the middle point
        int midPoint = start + (end - start) / 2;
        TreeNode<Integer> node = new TreeNode<>(arr[midPoint]);
        node.setLeft(recurseBSTWithMinHeight(arr,start,midPoint-1));
        node.setRight(recurseBSTWithMinHeight(arr,midPoint+1,end));
        return node;
    }
}
