package com.example.myrxjava.search.medium;

/**
 * Merge two sorted linked lists
 * Difficulty Level : Medium
 * Last Updated : 03 Aug, 2021
 * Write a SortedMerge() function that takes two lists, each of which is
 * sorted in increasing order, and merges the two together into one list which
 * is in increasing order. SortedMerge() should return the new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * <p>
 * For example if the first linked list a is 5->10->15 and the other linked
 * list b is 2->3->20, then SortedMerge() should return a pointer to the
 * head node of the merged list 2->3->5->10->15->20.
 * <p>
 * There are many cases to deal with: either ‘a’ or ‘b’ may be empty,
 * during processing either ‘a’ or ‘b’ may run out first, and finally,
 * there’s the problem of starting the result list empty, and building it up while going through ‘a’ and ‘b’.
 * <p>
 * <p>
 * <p>
 * https://www.geeksforgeeks.org/merge-two-sorted-linked-lists/
 **/

/* Link list node */
class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

public class MergedSortedList {


    public static void main(String[] args) {
        /*Let us create two sorted linked
        lists to test the methods
        Created lists:
        llist1: 5->10->15,
                llist2: 2->3->20*/

        MergedList llist1 = new MergedList();
        MergedList llist2 = new MergedList();

        // Node head1 = new Node(5);
        llist1.addToTheLast(new Node(5));
        llist1.addToTheLast(new Node(10));
        llist1.addToTheLast(new Node(15));

        // Node head2 = new Node(2);
        llist2.addToTheLast(new Node(2));
        llist2.addToTheLast(new Node(3));
        llist2.addToTheLast(new Node(20));


        llist1.head = sortedMerge(llist1.head, llist2.head);
        llist1.printList();
    }

    /**
     * Takes two lists sorted in
     * increasing order, and splices
     * their nodes together to make
     * one big sorted list which is
     * returned.
     * Time Complexity = O(m + n)
     */
    static Node sortedMerge(Node headA, Node headB) {

    /* a dummy first node to
       hang the result on */
        Node dummyNode = new Node(0);

    /* tail points to the
    last result node */
        Node tail = dummyNode;
        while (true) {

        /* if either list runs out,
        use the other list */
            if (headA == null) {
                tail.next = headB;
                break;
            }

            if (headB == null) {
                tail.next = headA;
                break;
            }

        /* Compare the data of the two
        lists whichever lists' data is
        smaller, append it into tail and
        advance the head to the next Node
        */
            if (headA.data <= headB.data) {
                tail.next = headA;
                headA = headA.next;
            } else {
                tail.next = headB;
                headB = headB.next;
            }

            /* Advance the tail */
            tail = tail.next;
        }

        return dummyNode.next;
    }

    static Node recursiveSortedMerge(Node A, Node B) {

        if (A == null) return B;
        if (B == null) return A;

        if (A.data < B.data) {
            A.next = recursiveSortedMerge(A.next, B);
            return A;
        } else {
            B.next = recursiveSortedMerge(A, B.next);
            return B;
        }

    }


}

class MergedList {
    Node head;

    /* Method to insert a node at
the end of the linked list */
    public void addToTheLast(Node node) {
        if (head == null) {
            head = node;
        } else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = node;
        }
    }

    /* Method to print linked list */
    void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

}

