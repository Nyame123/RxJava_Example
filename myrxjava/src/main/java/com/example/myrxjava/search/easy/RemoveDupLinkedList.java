package com.example.myrxjava.search.easy;

import com.example.myrxjava.search.Node;

import java.util.HashMap;

import static com.example.myrxjava.search.Node.printList;


public class RemoveDupLinkedList {
    static Node head;

    public static void main(String[] args) {
        head = new Node(2);
        head.next = new Node(12);
        head.next.next = new Node(2);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(29);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next.next = new Node(6);

        System.out.println("Duplicated dataset");
        printList(head);
        System.out.println("Duplicates removed from dataset");
        //Node res = mergeSort(head);
        removeTwoPass();

    }


    //Using two passes
    //Time Complexity = O(n^2)
    //Space Complexity = O(1)
    static void removeTwoPass() {
        removeTwoPassAlt();
        //removeTwoPassAlt1();

        printList(head);
    }

    private static void removeTwoPassAlt1() {
        Node current = head;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }

            current = current.next;
        }
    }

    private static void removeTwoPassAlt() {

        for (Node cur = head; cur != null; cur = cur.next) {
            for (Node c = cur; c.next != null; ) {
                if (c.next.data == cur.data) {
                    removeNode(c);
                } else {
                    c = c.next;
                }
            }

        }
    }


    //using sorted List
    //Time Complexity = O(nlogn)
    //Space Complexity = O(1)
    static void removeDupSortedList() {
        Node res = mergeSort(head);
        Node current = res;
        while (current != null && current.next != null) {

            if (current.data == current.next.data) {
                removeNode(current);
            } else {
                current = current.next;
            }

        }

        printList(res);
    }

    //Using hashtable
    //Time Complexity = O(n)
    //Space Complexity = O(n)
    static void removeDupLinkedList() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Node current = head;
        Node prev = head;
        while (prev != null && current != null) {
            int key = current.data;
            if (hashMap.containsKey(key)) {
                removeNode(prev);
            } else {
                hashMap.put(key, 1);
                //update the pointer
                prev = current;

            }
            current = current.next;
        }

        /*current = head;
        while (current.next != null) {
            int key = current.next.data;
            if (hashMap.containsKey(key) && hashMap.get(key) > 1) { //there is a duplicate
                //remove the node here
                removeNode(current);
                hashMap.put(key, hashMap.get(key) - 1);
            }else{
                //update the pointer
                current = current.next;
            }


        }*/

        printList(head);
    }

    private static Node removeNode1(Node current) {
        current = current.next;
        return current;
    }

    private static void removeNode(Node current) {
        current.next = current.next.next;
    }




    static Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node mid = midPoint(head);
        Node right = mid.next;
        mid.next = null;

        Node leftNode = mergeSort(head);
        Node rightNode = mergeSort(right);
        return merge(leftNode, rightNode);
    }

    static Node merge(Node l, Node r) {
        //create a hanger node
        Node hanger = new Node(0);
        Node pointer = hanger;
        while (l != null && r != null) {
            if (l.data < r.data) {
                pointer.next = l;
                l = l.next;
            } else {
                pointer.next = r;
                r = r.next;
            }
            pointer = pointer.next;
        }

        //update the list with left list
        if (l != null) {
            pointer.next = l;
        }

        //update the list with left list
        if (r != null) {
            pointer.next = r;
        }

        return hanger.next;
    }

    static Node midPoint(Node node) {
        Node slow, fast;
        slow = fast = node;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

}

