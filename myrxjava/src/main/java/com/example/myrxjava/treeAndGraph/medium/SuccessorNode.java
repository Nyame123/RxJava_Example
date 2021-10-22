package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.treeAndGraph.prep.TreeNode;

/**
 * Generating the successor nodes of a tree
 **/
public class SuccessorNode {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(20);
        root.setParent(null);
        root.setLeft(new TreeNode<>(10));
        root.getLeft().setParent(root);
        root.setRight(new TreeNode<>(30));
        root.getRight().setParent(root);
        root.getLeft().setLeft(new TreeNode<>(5));
        root.getLeft().getLeft().setParent(root.getLeft());
        root.getLeft().setRight(new TreeNode<>(15));
        root.getLeft().getRight().setParent(root.getLeft());
        root.getLeft().getRight().setRight(new TreeNode<>(17));
        root.getLeft().getRight().getRight().setParent(root.getLeft().getRight());
        root.getLeft().getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().getLeft().getLeft().setParent(root.getLeft().getLeft());
        root.getLeft().getLeft().setRight(new TreeNode<>(7));
        root.getLeft().getLeft().getRight().setParent(root.getLeft().getLeft());

        TreeNode<Integer> result = findNextSuccessor(root.getLeft().getLeft().getRight());
        if (result == null){
            System.out.println("No solution");
        }else {
            System.out.println(result.getData());
        }
    }

    static TreeNode<Integer> findNextSuccessor(TreeNode<Integer> root){
        if (root == null){
            return null;
        }
        //check if the right subtree is not null, then traverse and get the leftmost
        if (root.getRight() != null){
           return traverseLeftMost(root.getRight());
        }else{
            //right sub tree is null then we have to
            //check the current node parent up the tree until
            //the parent happens to be the left of the current node
            //TreeNode<Integer> current = root;
            TreeNode<Integer> parent = root.getParent();
            //the immediate parent is traversed already
            if (parent == null){
                return null;
            }

            parent = parent.getParent();
            while (parent != null){
                 if (parent.getParent() != null && parent.getData() < parent.getParent().getData()){
                     break;
                 }
                parent = parent.getParent();
            }
            return parent;
        }
    }

    static TreeNode<Integer> traverseLeftMost(TreeNode<Integer> right) {
        //base case
        if (right == null){
            return null;
        }

        traverseLeftMost(right.getLeft());
        return right;
    }
}
