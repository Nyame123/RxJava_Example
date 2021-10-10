package com.example.myrxjava.treeAndGraph.hard;

import java.util.List;
import java.util.Stack;

/**
 * Segment Tree | Set 1 (Sum of given range)
 * Difficulty Level : Hard
 * Last Updated : 20 Aug, 2021
 * Let us consider the following problem to understand Segment Trees.
 * We have an array arr[0 . . . n-1]. We should be able to
 * 1 Find the sum of elements from index l to r where 0 <= l <= r <= n-1
 * 2 Change value of a specified element of the array to a new value x. We need to do arr[i] = x where 0 <= i <= n-1.
 * <p>
 * https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 */
public class SegmentTreeMaxSum {

    private Node[] segmentArr;

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int n = arr.length;

        SegmentTreeMaxSum segmentTree = new SegmentTreeMaxSum();
        segmentTree.buildSegmentTree(arr);
        int sum = segmentTree.getSum(segmentTree.segmentArr, 0, n - 1, 1, 3, 0);
        System.out.println("Sum = " + sum);

        segmentTree.updateSegTree(arr,segmentTree.segmentArr,10,1, 0, n-1);
        int sumUpdated = segmentTree.getSum(segmentTree.segmentArr, 0, n - 1, 1, 3, 0);
        System.out.println("Sum after Update = " + sumUpdated);

    }

    boolean isPowerOfTwo(int n) {
        while (n % 2 == 0) {
            n /= 2;

            if (n == 1) {
                return true;
            }
        }

        return false;
    }

    boolean isPowerOfTwoBitManip(int n){
        return (n != 0) && ((n & (n-1)) == 0);
    }

    int smallestNextPower(int n) {
        int power = 1;
        while (power <= n) {
            power *= 2;
        }

        return power;
    }

    Node fillSegmentTree(int[] arr, Node[] segArr, int l, int r, int i) {
        if (l >= r) {
            Node baseNode = new Node(l, r, arr[l]);
            baseNode.parentIndex = i;
            segArr[i] = baseNode;
            //heapify(segArr);
            return baseNode;
        }

        int mid = l + (r - l) / 2;

        Node leftNode = fillSegmentTree(arr, segArr, l, mid, 2 * i + 1);
        Node rightNode = fillSegmentTree(arr, segArr, mid + 1, r, 2 * i + 2);
        int totalValue = leftNode.total + rightNode.total;
        Node node = new Node(l, r, totalValue);

        node.leftNode = leftNode;
        node.rightNode = rightNode;
        node.parentIndex = i;

        segArr[i] = node;
        //heapify(segArr);
        return node;
    }

    void updateSegTree(int[] arr,Node[] segArr, int x, int i, int start, int end){
        if (i < 0 || i > end){
            System.out.println("Invalid update");
            return;
        }

        int diff = x - arr[i];
        arr[i] = x;
        updateValue(segArr,diff,i,start,end);
    }

    void updateValue(Node[] segArr, int x, int i, int start, int end) {
        if (i < start || i > end)
            return;

        segArr[i].total += x;

        if (start != end) {
            int mid = start + (end - start) / 2;
            updateValue(segArr, x, i * 2 + 1, start, mid);
            updateValue(segArr, x, i * 2 + 2, mid + 1, end);
        }
    }

    int getSum(Node[] segArr, int start, int end, int l, int r, int i) {

        if (l <= start && end <= r)
            return segArr[i].total;

        if (l > end || r < start)
            return 0;
        else {
            int mid = start + (end - start) / 2;

            return getSum(segArr, start, mid, l, r, i * 2 + 1) +
                    getSum(segArr, mid + 1, end, l, r, i * 2 + 2);
        }

    }


    void heapify(List<Node> segArr) {
        int size = segArr.size();
        int currentIndex = size - 1;
        int parentIndex = (currentIndex) / 2;


        while (parentIndex >= 0) {
            Node current = segArr.get(currentIndex);
            Node parent = segArr.get(parentIndex);
            if (current.total > parent.total) {
                swapNodes(current, parent);
                currentIndex = parentIndex;
                current.parentIndex = currentIndex;
                parentIndex = (parentIndex - 1) / 2;
                parent.parentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    private void swapNodes(Node current, Node parent) {
        int total = current.total;
        int leftIndex = current.leftIndex;
        int rightIndex = current.rightIndex;
        Node rightNode = current.rightNode;
        Node leftNode = current.leftNode;

        current.total = parent.total;
        current.leftIndex = parent.leftIndex;
        current.rightIndex = parent.rightIndex;
        current.rightNode = parent.rightNode;
        current.leftNode = parent.leftNode;


        parent.total = total;
        parent.leftIndex = leftIndex;
        parent.rightIndex = rightIndex;
        parent.rightNode = rightNode;
        parent.leftNode = leftNode;

    }

    void buildSegmentTree(int[] arr) {
        int n = arr.length;
        int size = getSize(n);

        segmentArr = new Node[size];

        fillSegmentTree(arr, segmentArr, 0, n - 1, 0);


    }

    private int getSizeWithLog(int n){
        int x = (int) Math.ceil(Math.log(n)/Math.log(2));
        return (int) (2*Math.pow(2,x) - 1);
    }

    private int getSize(int n) {
        int size = 2 * n - 1;
        if (!isPowerOfTwoBitManip(n)) {
            int smallestNextPower = smallestNextPower(n);
            size = 2 * smallestNextPower - 1;
        }
        return size;
    }

    static class Node {
        int rightIndex;
        int leftIndex;
        int parentIndex;
        int total;
        Node leftNode = null;
        Node rightNode = null;

        Node(int left, int right, int sum) {
            this.leftIndex = left;
            this.rightIndex = right;
            this.total = sum;
        }
    }
}

class SegmentTree {
    int st[]; // The array that stores segment tree nodes

    /* Constructor to construct segment tree from given array. This
       constructor  allocates memory for segment tree and calls
       constructSTUtil() to  fill the allocated memory */
    SegmentTree(int arr[], int n) {
        // Allocate memory for segment tree
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;

        st = new int[max_size]; // Memory allocation

        constructSTUtil(arr, 0, n - 1, 0);
    }

    // Driver program to test above functions
    public static void main(String args[]) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        SegmentTree tree = new SegmentTree(arr, n);

        // Build segment tree from given array

        // Print sum of values in array from index 1 to 3
        System.out.println("Sum of values in given range = " +
                tree.getSum(n, 1, 3));

        // Update: set arr[1] = 10 and update corresponding segment
        // tree nodes
        tree.updateValue(arr, n, 1, 10);

        // Find sum after the value is updated
        System.out.println("Updated sum of values in given range = " +
                tree.getSum(n, 1, 3));
    }

    // A utility function to get the middle index from corner indexes.
    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    /*  A recursive function to get the sum of values in given range
        of the array.  The following are parameters for this function.

      st    --> Pointer to segment tree
      si    --> Index of current node in the segment tree. Initially
                0 is passed as root is always at index 0
      ss & se  --> Starting and ending indexes of the segment represented
                    by current node, i.e., st[si]
      qs & qe  --> Starting and ending indexes of query range */
    int getSumUtil(int ss, int se, int qs, int qe, int si) {
        // If segment of this node is a part of given range, then return
        // the sum of the segment
        if (qs <= ss && qe >= se)
            return st[si];

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
                getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }

    /* A recursive function to update the nodes which have the given
       index in their range. The following are parameters
        st, si, ss and se are same as getSumUtil()
        i    --> index of the element to be updated. This index is in
                 input array.
       diff --> Value to be added to all nodes which have i in range */
    void updateValueUtil(int ss, int se, int i, int diff, int si) {
        // Base Case: If the input index lies outside the range of
        // this segment
        if (i < ss || i > se)
            return;

        // If the input index is in range of this node, then update the
        // value of the node and its children
        st[si] = st[si] + diff;
        if (se != ss) {
            int mid = getMid(ss, se);
            updateValueUtil(ss, mid, i, diff, 2 * si + 1);
            updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
        }
    }

    // The function to update a value in input array and segment tree.
    // It uses updateValueUtil() to update the value in segment tree
    void updateValue(int arr[], int n, int i, int new_val) {
        // Check for erroneous input index
        if (i < 0 || i > n - 1) {
            System.out.println("Invalid Input");
            return;
        }

        // Get the difference between new value and old value
        int diff = new_val - arr[i];

        // Update the value in array
        arr[i] = new_val;

        // Update the values of nodes in segment tree
        updateValueUtil(0, n - 1, i, diff, 0);
    }

    // Return sum of elements in range from index qs (query start) to
    // qe (query end).  It mainly uses getSumUtil()
    int getSum(int n, int qs, int qe) {
        // Check for erroneous input values
        if (qs < 0 || qe > n - 1 || qs > qe) {
            System.out.println("Invalid Input");
            return -1;
        }
        return getSumUtil(0, n - 1, qs, qe, 0);
    }

    // A recursive function that constructs Segment Tree for array[ss..se].
    // si is index of current node in segment tree st
    int constructSTUtil(int arr[], int ss, int se, int si) {
        // If there is one element in array, store it in current node of
        // segment tree and return
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        // If there are more than one elements, then recur for left and
        // right subtrees and store the sum of values in this node
        int mid = getMid(ss, se);
        st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) +
                constructSTUtil(arr, mid + 1, se, si * 2 + 2);
        return st[si];
    }
}
