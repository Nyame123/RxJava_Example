package com.example.myrxjava.search.hard;

/**
 * Merge Sort for Linked Lists
 * Difficulty Level : Hard
 * Last Updated : 02 Aug, 2021
 * <p>
 * Merge sort is often preferred for sorting a linked list.
 * The slow random-access performance of a linked list makes some
 * other algorithms (such as quicksort) perform poorly, and others (such as heapsort) completely impossible.
 * <p>
 * sorting image
 * <p>
 * Let the head be the first node of the linked list to be sorted and
 * headRef be the pointer to head. Note that we need a reference to head
 * in MergeSort() as the below implementation changes next links to sort
 * the linked lists (not data at the nodes), so the head node has to be
 * changed if the data at the original head is not the smallest value in the linked list.
 * <p>
 * MergeSort(headRef)
 * 1) If the head is NULL or there is only one element in the Linked List
 * then return.
 * 2) Else divide the linked list into two halves.
 * FrontBackSplit(head, &a, &b); /* a and b are two halves
 * 3)Sort the two halves a and b.
 * MergeSort(a);
 * MergeSort(b);
 * 4)Merge the sorted a and b(using SortedMerge()discussed here)
 * and update the head pointer using headRef.
 * *headRef=SortedMerge(a,b);
 * <p>
 * <p>
 * https://www.geeksforgeeks.org/merge-sort-for-linked-list/
 **/
public class LinkedListMergeSort {
    Node head = null;

    public static void main(String[] args) {
        LinkedListMergeSort li = new LinkedListMergeSort();
        /*
         * Let us create a unsorted linked list to test the functions
         * created. The list shall be a: 2->3->20->5->10->15
         */
        li.push(15);
        li.push(10);
        li.push(5);
        li.push(20);
        li.push(13);
        li.push(32);
        li.push(34);
        li.push(31);
        li.push(37);
        li.push(2);
        li.push(12);
        li.push(22);
        li.push(23);

        // Apply merge Sort
        li.head = li.mergeSort(li.head);
        System.out.print("\n Sorted Linked List is: \n");
        li.printList(li.head);
    }

    // Utility function to get the middle of the linked list
    public static Node getMiddle(Node head) {
        if (head == null)
            return head;

        Node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    Node sortedMerge(Node a, Node b) {
        Node result = null;
        /* Base cases */
        if (a == null)
            return b;
        if (b == null)
            return a;

        /* Pick either a or b, and recur */
        if (a.val <= b.val) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    Node mergeSort(Node h) {
        // Base case : if head is null
        if (h == null || h.next == null) {
            return h;
        }

        // get the middle of the list
        Node middle = getMiddle(h);
        Node nextofmiddle = middle.next;

        // set the next of middle node to null
        middle.next = null;

        // Apply mergeSort on left list
        Node left = mergeSort(h);

        // Apply mergeSort on right list
        Node right = mergeSort(nextofmiddle);

        // Merge the left and right lists
        Node sortedlist = sortedMerge(left, right);
        return sortedlist;
    }

    void push(int newData) {
        /* allocate node */
        Node newNode = new Node(newData);

        /* link the old list off the new node */
        newNode.next = head;

        /* move the head to point to the new node */
        head = newNode;
    }

    // Utility function to print the linked list
    void printList(Node headref) {
        while (headref != null) {
            System.out.print(headref.val + " ");
            headref = headref.next;
        }
    }

    // node a, b;
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

}

class MergedSortLinkedList {
    Node head;

    public static void main(String[] args) {
        MergedSortLinkedList mergedSortLinkedList = new MergedSortLinkedList();

        mergedSortLinkedList.addNode(15);
        mergedSortLinkedList.addNode(5);
        mergedSortLinkedList.addNode(20);
        mergedSortLinkedList.addNode(13);
        mergedSortLinkedList.addNode(32);
        mergedSortLinkedList.addNode(34);
        mergedSortLinkedList.addNode(31);
        mergedSortLinkedList.addNode(37);
        mergedSortLinkedList.addNode(2);
        mergedSortLinkedList.addNode(12);
        mergedSortLinkedList.addNode(22);
        mergedSortLinkedList.addNode(23);

        Node result = mergedSortLinkedList.mergeSort(mergedSortLinkedList.head);

        mergedSortLinkedList.printList(result);
    }


    Node midList(Node head) {
        Node slow = head;
        Node current = head;
        while (current.next != null && current.next.next != null) {
            slow = slow.next;
            current = current.next.next;
        }

        return slow;
    }

    Node mergeSort(Node left) {
        if (left == null || left.next == null) {
            return left;
        }

        Node middle = midList(left);
        Node middleNext = middle.next;
        middle.next = null;
        Node l = mergeSort(left);
        Node h = mergeSort(middleNext);
        Node head = merge(l, h);
        return head;
    }

    Node merge(Node left, Node right) {
        Node head = new Node(0,null);
        Node current = head;
        while (left != null && right != null) {
            if (left.data < right.data) {

                current.next = left;
                left = left.next;

               /* Node temp = left.next;
                left.next = null;
                current = left;
                left = temp;*/

            } else {

                current.next = right;
                right = right.next;
                /*Node temp = right.next;
                right.next = null;
                current = right;
                right = temp;*/

            }
            current = current.next;
            //append(current,head);

        }

        if (left != null) {
            current.next = left;
            //append(left, head);
        }

        if (right != null) {
            current.next = right;
            //append(right, head);
        }

        return head.next;


    }

    private void append(Node left, Node head) {
        Node temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = left;
    }

    void addNode(int data) {
        Node node = new Node(data, null);
        if (head == null)
            head = node;
        else {
            append(node, head);
        }
    }

    void printList(Node head) {
        if (head == null) {
            System.out.println("Null");
        } else {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
        }
    }

    static class Node {
        int data;
        Node next;

        Node(int d, Node n) {
            this.data = d;
            this.next = n;
        }
    }

}