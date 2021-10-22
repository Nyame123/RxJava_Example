package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.treeAndGraph.prep.TreeNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Listing of all nodes at a every level
 **/
public class DepthList {

    public static void main(String[] args) {

        TreeNode<Integer> root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(3));
        root.getLeft().setLeft(new TreeNode<>(4));
        root.getLeft().setRight(new TreeNode<>(5));
        root.getRight().setLeft(new TreeNode<>(6));
        root.getRight().setRight(new TreeNode<>(7));

        listDepthDFS(root);
    }

    static void listDepthDFS(TreeNode<Integer> root) {
        Map<Integer, LinkedList<Integer>> levels = new HashMap<>();

        levelList(levels, root, 0);
        for (Integer key : levels.keySet()) {
            System.out.println(levels.get(key));
        }
    }

    static void levelListBFS(Map<Integer, LinkedList<Integer>> levelMap, TreeNode<Integer> root, int level) {
        Queue<TreeNode<Integer>> queue = new ArrayDeque<>();
        root.level = level;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<Integer> src = queue.poll();
            levelMap.putIfAbsent(src.level, new LinkedList<>());
            LinkedList<Integer> nodeLevel = levelMap.get(src.level);

            nodeLevel.add(src.getData());
            TreeNode<Integer> leftNode = src.getLeft();
            if (leftNode != null){
                leftNode.level = src.level + 1;
                queue.add(leftNode);
            }

            TreeNode<Integer> rightNode = src.getRight();
            if (rightNode != null){
                rightNode.level = src.level + 1;
                queue.add(rightNode);
            }

        }
    }

    static void levelList(Map<Integer, LinkedList<Integer>> levelMap, TreeNode<Integer> root, int level) {
        //base case
        if (root == null) {
            return;
        }

        //create a level
        levelMap.putIfAbsent(level, new LinkedList<>());

        levelList(levelMap, root.getLeft(), level + 1);
        levelList(levelMap, root.getRight(), level + 1);

        LinkedList<Integer> node = levelMap.get(level);
        node.add(root.getData());

    }
}
