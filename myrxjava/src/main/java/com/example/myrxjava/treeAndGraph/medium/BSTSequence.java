package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.treeAndGraph.prep.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Generating all possible sequence that can make up
 * a binary search tree
 **/
public class BSTSequence {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(5);
        root.setLeft(new TreeNode<>(4));
        root.setRight(new TreeNode<>(6));
        root.getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(7));
        /* root.getRight().setLeft(new TreeNode<>(5));
        root.getRight().setRight(new TreeNode<>(9));*/

        List<LinkedList<Integer>> allResults = allSequence(root);
        for (LinkedList<Integer> list : allResults) {
            System.out.println(list);
        }

    }

    static List<LinkedList<Integer>> allSequence(TreeNode<Integer> root) {
        List<LinkedList<Integer>> results = new ArrayList<>();
        //base cases
        if (root == null) {
            results.add(new LinkedList<>());
            return results;
        }

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.addFirst(root.getData());

        //first sequence
        List<LinkedList<Integer>> firstSequence = allSequence(root.getLeft());
        //second sequence
        List<LinkedList<Integer>> secondSequence = allSequence(root.getRight());

        //start weaving the first and the second linkedList with the prefix
        for (LinkedList<Integer> first : firstSequence) {

            for (LinkedList<Integer> second : secondSequence) {

                List<LinkedList<Integer>> weavedResult = new ArrayList<>();
                weaving(first, second, prefix, weavedResult);
                results.addAll(weavedResult);
            }
        }

        return results;
    }

    static void weaving(LinkedList<Integer> first, LinkedList<Integer> second,
                        LinkedList<Integer> prefix, List<LinkedList<Integer>> results) {

        //base case
        if (first.isEmpty() || second.isEmpty()) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        //take the first element from the first linkedList
        //use it and put it back later after usage
        Integer firstData = first.removeFirst();
        prefix.addLast(firstData);
        weaving(first, second, prefix, results);
        prefix.removeLast();
        first.addFirst(firstData);

        //take the first element of the second linkedList
        //use it and put it back later after usage
        Integer secondData = second.removeFirst();
        prefix.addLast(secondData);
        weaving(first, second, prefix, results);
        prefix.removeLast();
        second.addFirst(secondData);

    }
}
