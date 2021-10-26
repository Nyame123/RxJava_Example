package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.treeAndGraph.prep.TreeNode;

/**
 * This checks if a binary tree is a valid binary
 * search tree such that the root is greater than or equal to the left and
 * the root is less than or equal to the right
 **/
public class ValidBST {

    public static void main(String[] args) {
       /* TreeNode<Integer> root = new TreeNode<>(5);
        root.setLeft(new TreeNode<>(4));
        root.setRight(new TreeNode<>(6));
        root.getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(7));
        root.getRight().setLeft(new TreeNode<>(5));
        root.getRight().setRight(new TreeNode<>(9));*/

        TreeNode<Integer> root = new TreeNode<>(20);
        root.setLeft(new TreeNode<>(10));
        root.setRight(new TreeNode<>(30));
        root.getLeft().setLeft(new TreeNode<>(5));
        root.getLeft().setRight(new TreeNode<>(15));
        root.getLeft().getRight().setRight(new TreeNode<>(17));
        root.getLeft().getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().getLeft().setRight(new TreeNode<>(7));


        checkValidBinaryTree(root);
    }

    static void checkValidBinaryTree(TreeNode<Integer> root) {
        ValidResult result = validBinaryTree(root, NodeDirection.LEFT);
        //isValidBinaryTreeUsingMaxMin(root,null,null);
        if (result.result) {
            System.out.println("This binary tree is a valid binary search tree");
        } else {
            System.out.println("This binary tree is not a valid binary search tree");
        }
    }

    static boolean isValidBinaryTreeUsingMaxMin(TreeNode<Integer> root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        if (min != null && (root.getData() <= min) || max != null && (root.getData() >= max)) {
            return false;
        }

        if (!isValidBinaryTreeUsingMaxMin(root.getLeft(), min, root.getData()) ||
                !isValidBinaryTreeUsingMaxMin(root.getRight(), root.getData(), max)) {
            return false;
        }

        return true;
    }

    //Time Complexity = O(logn)
    //Space Complexity = O(n)
    static ValidResult validBinaryTree(TreeNode<Integer> root, NodeDirection dir) {
        //base case
        if (root == null) {
            ValidResult result = new ValidResult();
            result.result = true;
            result.value = Integer.MIN_VALUE;
            return result;
        }

        ValidResult leftRes = validBinaryTree(root.getLeft(), NodeDirection.LEFT);

        ValidResult rightRes = validBinaryTree(root.getRight(), NodeDirection.RIGHT);

        boolean isValid = false;
        int value;
        if (leftRes.value != Integer.MIN_VALUE && rightRes.value != Integer.MIN_VALUE) {
            value = dir == NodeDirection.LEFT ? Math.max(leftRes.value, rightRes.value) : Math.min(leftRes.value, rightRes.value);
            isValid = leftRes.value <= root.getData() && root.getData() <= rightRes.value;
        } else if (leftRes.value == Integer.MIN_VALUE && rightRes.value == Integer.MIN_VALUE) {
            value = root.getData();
            isValid = true;
        } else if (leftRes.value == Integer.MIN_VALUE) {
            value = rightRes.value;
            isValid = root.getData() <= rightRes.value;
        } else {
            value = leftRes.value;
            isValid = leftRes.value < root.getData();
        }

        ValidResult result = new ValidResult();
        result.value = value;
        result.result = isValid;
        return result;

    }

    enum NodeDirection {
        RIGHT,
        LEFT
    }

    static class ValidResult {
        boolean result;
        Integer value;
    }
}
