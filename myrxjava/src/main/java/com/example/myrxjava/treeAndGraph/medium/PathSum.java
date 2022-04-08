package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.treeAndGraph.prep.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An algorithm to count the number of paths that sum up to a target sum
 * The path can start or end at any downward nodes
 **/
public class PathSum {

    public static void main(String[] args) {
      /*  TreeNode<Integer> root = new TreeNode<>(5);
        root.setLeft(new TreeNode<>(4));
        root.setRight(new TreeNode<>(8));
        root.getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(7));
        root.getRight().setLeft(new TreeNode<>(6));
        root.getRight().setRight(new TreeNode<>(9));*/

        /**
         *            5
         *          /   \
         *        4      8
         *       / \    / \
         *      3   7  6   9
         *
         *
         * */
        TreeNode<Integer> root = new TreeNode<>(10);
        root.setLeft(new TreeNode<>(5));
        root.setRight(new TreeNode<>(-3));
        root.getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(2));
        root.getLeft().getRight().setRight(new TreeNode<>(1));
        root.getLeft().getLeft().setRight(new TreeNode<>(-2));
        root.getLeft().getLeft().setLeft(new TreeNode<>(3));
        root.getRight().setRight(new TreeNode<>(11));


        //SumResult result = countOfPathSum(root,8);
        int result = countPathSum(root, 8,new HashMap<>());
        System.out.println(result);
    }

    static int countPathSum(TreeNode<Integer> root, int target, Map<Integer, Integer> memo) {
        return countPathSum(root, target, 0, memo);
    }

    //Time complexity = O(n)
    //Space complexity = O(n)
    static int countPathSum(TreeNode<Integer> root, int target, int currentSum, Map<Integer, Integer> memo) {
        //base case
        if (root == null)
            return 0;

        int finalCount = 0;

        currentSum += root.getData();
        if (currentSum == target)//sum up to the current node starting from the root
            finalCount++;

        finalCount += memo.getOrDefault(currentSum - target, 0);

        //increment path count
        incrementMemo(memo, currentSum, 1);

        finalCount += countPathSum(root.getLeft(), target, currentSum, memo);
        finalCount += countPathSum(root.getRight(), target, currentSum, memo);

        //decrement path count
        incrementMemo(memo, currentSum, -1);

        return finalCount;
    }

    static void incrementMemo(Map<Integer, Integer> memo, int currentSum, int item) {

        int newCount = memo.getOrDefault(currentSum, 0) + item;

        if (newCount == 0) { //to conserve space
            memo.remove(currentSum);
        } else {
            memo.put(currentSum, newCount);
        }
    }

    //using visiting each node and check the path sum from there
    //Time complexity = O(nlog(n)) for balanced tree and O(n^2) for unbalanced tree
    static int countPathSum(TreeNode<Integer> root, int target) {
        //base case
        if (root == null)
            return 0;

        int rootCount = countPathSumFromNode(root, target, 0);

        int leftCount = countPathSum(root.getLeft(), target);
        int rightCount = countPathSum(root.getRight(), target);

        return rootCount + leftCount + rightCount;
    }

    private static int countPathSumFromNode(TreeNode<Integer> root, int target, int currentSum) {
        if (root == null)
            return 0;

        int finalSum = 0;
        currentSum += root.getData();
        if (currentSum == target)
            finalSum++;
        finalSum += countPathSumFromNode(root.getRight(), target, currentSum);
        finalSum += countPathSumFromNode(root.getLeft(), target, currentSum);

        return finalSum;
    }

    //Time complexity = O(D) where n = number of nodes and D = depth of root
    //Space Complexity = O(n)
    static SumResult countOfPathSum(TreeNode<Integer> root, int sum) {
        //base case
        if (root == null) {
            SumResult result = new SumResult();
            result.count = 0;
            return result;
        }


        SumResult left = countOfPathSum(root.getLeft(), sum);
        SumResult right = countOfPathSum(root.getRight(), sum);


        for (int i = 0; i < left.result.size(); i++) {
            left.result.set(i, left.result.get(i) + root.getData());
            if (left.result.get(i) == sum) {
                left.count += 1;
            } /*else if (left.result.get(i) > sum) {
                left.result.remove(i);
            }*/
        }

        for (int i = 0; i < right.result.size(); i++) {
            right.result.set(i, right.result.get(i) + root.getData());
            if (right.result.get(i) == sum) {
                right.count += 1;
            } /*else if (right.result.get(i) > sum) {
                right.result.remove(i);
            }*/
        }

        if (root.getData() == sum) {
            left.count += 1;
        }

        left.result.addAll(right.result);
        left.result.add(root.getData());
        left.count += right.count;
        return left;
    }

    static class SumResult {
        List<Integer> result = new ArrayList<>();
        int count;
    }
}
