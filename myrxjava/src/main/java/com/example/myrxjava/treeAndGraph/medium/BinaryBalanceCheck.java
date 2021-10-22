package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.treeAndGraph.prep.TreeNode;

/***
 * A check to see if a binary tree is balance. A balanced binary tee is a
 * tree with the height of the left subtree no more than the height of the
 * right subtree by one.
 **/
public class BinaryBalanceCheck {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        //root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(3));
        //root.getLeft().setLeft(new TreeNode<>(4));
        //root.getLeft().setRight(new TreeNode<>(5));
        root.getRight().setLeft(new TreeNode<>(6));
        root.getRight().setRight(new TreeNode<>(7));

        isBalancedTree(root);
    }

    static void isBalancedTree(TreeNode<Integer> root){
        if(root == null){
            System.out.println("This is balanced");
        }else{
            int heightRoot = getHeight(root);
            if (heightRoot == Integer.MIN_VALUE){
                System.out.println("This is not balanced");
            }else{
                System.out.println("This is balanced");
            }
        }
    }

    static int getHeight(TreeNode<Integer> root){
        //base case
        if (root == null){
            return -1;
        }
        //check if the left subtree is beyond the balanced variant
        int heightLeft = getHeight(root.getLeft());
        if (heightLeft == Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }


        //check if the left subtree is beyond the balanced variant
        int heightRight = getHeight(root.getRight());
        if (heightRight == Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }

        //compare the height of the left and right subtree
        if (Math.abs(heightRight -  heightLeft) > 1){
            return Integer.MIN_VALUE;
        }else{
            return Math.max(heightRight,heightLeft) + 1;
        }
    }
}
