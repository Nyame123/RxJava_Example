package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.treeAndGraph.prep.TreeNode;

/**
 * In this work, we check if T2 root node is a subtree of
 * T1 root node.
 **/
public class SubsetChecking {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(5);
        root.setLeft(new TreeNode<>(4));
        root.setRight(new TreeNode<>(6));
        root.getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(7));
        root.getRight().setLeft(new TreeNode<>(5));
        root.getRight().setRight(new TreeNode<>(9));
        /**
         *             5
         *         /      \
         *        4        6
         *       / \      / \
         *      3   7    5   9
         * */

        TreeNode<Integer> root1 = new TreeNode<>(6);
        root1.setLeft(new TreeNode<>(4));
        root1.setRight(new TreeNode<>(9));

        if (isSubtreeOf(root, root1)) {
            System.out.println("Root one is a subtree of root");
        } else {
            System.out.println("Root one is not a subtree of root");
        }
    }

    static boolean isSubtreeOf(TreeNode<Integer> root, TreeNode<Integer> root1) {
        return usingNodeEqualization(root, root1);
    }

    //Time Complexity = O(n + m) where m is root1 size and n is root size;
    //Space Complexity = O(n + m) where m is root1 size and n is root size;
    static boolean usingPreOrderTraversal(TreeNode<Integer> root, TreeNode<Integer> root1) {
        StringBuilder s = new StringBuilder();
        StringBuilder s1 = new StringBuilder();
        getString(root, s);
        getString(root1, s1);

        return s.toString().contains(s1.toString());
    }

    static void getString(TreeNode<Integer> root1, StringBuilder s) {
        //base case
        if (root1 == null)
            return;
        s.append(root1.getData());
        getString(root1.getLeft(), s);
        getString(root1.getRight(), s);

    }

    //Time Complexity = O(n + km) where n is the number of nodes in main root and
    // k is the number of possible match between the main root and root1 and
    //m is the number of nodes in the root1

    //Space Complexity = O(log(n) + log(m))
    static boolean usingNodeEqualization(TreeNode<Integer> root, TreeNode<Integer> root1) {
        //base case
        //if the two nodes are null
        if (root == null && root1 == null)
            return true;

        //if only one of the two nodes are null
        if (root == null || root1 == null)
            return false;

        if (root.getData().equals(root1.getData())) {
            return matchNodes(root, root1);
        } else {
            return usingNodeEqualization(root.getLeft(), root1) || usingNodeEqualization(root.getRight(), root1);
        }
    }

    static boolean matchNodes(TreeNode<Integer> root, TreeNode<Integer> root1) {

        //base case
        if (root == null && root1 == null)
            return true;

        //if only one of the two nodes are null
        if (root == null || root1 == null)
            return false;

        //if the two node are identical then we match to see
        //if their children are the same
        if (root.getData().equals(root1.getData())) {
            //check the left and the right nodes of
            return matchNodes(root.getLeft(), root1.getLeft()) && matchNodes(root.getRight(), root1.getRight());
        }


        return false;
    }
}
