package com.example.myrxjava.treeAndGraph.easy;

/**
 * Tree Traversals (Inorder, Preorder and Postorder)
 * Difficulty Level : Easy
 * Last Updated : 29 May, 2021
 * Unlike linear data structures (Array, Linked List, Queues, Stacks, etc)
 * which have only one logical way to traverse them, trees can be traversed in different ways.
 * Following are the generally used ways for traversing trees.
 * <p>
 * Depth First Traversals:
 * (a) Inorder (Left, Root, Right) : 4 2 5 1 3
 * (b) Preorder (Root, Left, Right) : 1 2 4 5 3
 * (c) Postorder (Left, Right, Root) : 4 5 2 3 1
 * Breadth First or Level Order Traversal : 1 2 3 4 5
 * Please see this post for Breadth First Traversal.
 * Inorder Traversal (Practice):
 * <p>
 * Algorithm Inorder(tree)
 * 1. Traverse the left subtree, i.e., call Inorder(left-subtree)
 * 2. Visit the root.
 * 3. Traverse the right subtree, i.e., call Inorder(right-subtree)
 * <p>
 * <p>
 * Preorder Traversal (Practice):
 * <p>
 * Algorithm Preorder(tree)
 * 1. Visit the root.
 * 2. Traverse the left subtree, i.e., call Preorder(left-subtree)
 * 3. Traverse the right subtree, i.e., call Preorder(right-subtree)
 * <p>
 * Postorder Traversal (Practice):
 * <p>
 * <p>
 * <p>
 * Algorithm Postorder(tree)
 * 1. Traverse the left subtree, i.e., call Postorder(left-subtree)
 * 2. Traverse the right subtree, i.e., call Postorder(right-subtree)
 * 3. Visit the root.
 * <p>
 * https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 **/

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}


public class DFSTree {

    // Root of Binary Tree
    Node root;

    DFSTree() {
        root = null;
    }

    public static void main(String[] args) {
        DFSTree tree = new DFSTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println(
                "Preorder traversal of binary tree is ");
        tree.printPreorder();

        System.out.println(
                "\nInorder traversal of binary tree is ");
        tree.printInorder();

        System.out.println(
                "\nPostorder traversal of binary tree is ");
        tree.printPostorder();
    }


    /**
     * Given a binary tree, print its nodes according to the
     * "bottom-up" postorder traversal.
     * Time Complexity = O(n)
     **/
    void printPostorder(Node node) {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.left);

        // then recur on right subtree
        printPostorder(node.right);

        // now deal with the node
        System.out.print(node.key + " ");
    }

    /**
     * Given a binary tree, print its nodes in inorder
     * Time Complexity = O(n)
     */
    void printInorder(Node node) {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.key + " ");

        /* now recur on right child */
        printInorder(node.right);
    }

    /**
     * Given a binary tree, print its nodes in preorder
     * Time Complexity = O(n)
     **/
    void printPreorder(Node node) {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.key + " ");

        /* then recur on left sutree */
        printPreorder(node.left);

        /* now recur on right subtree */
        printPreorder(node.right);
    }

    // Wrappers over above recursive functions
    void printPostorder() {
        printPostorder(root);
    }

    void printInorder() {
        printInorder(root);
    }

    void printPreorder() {
        printPreorder(root);
    }
}
