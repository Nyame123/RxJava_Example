package com.example.myrxjava.search.medium;

public class LinkedList {

    static Node head;

    public LinkedList(Node head) {
        this.head = head;
    }

    public static void main(String[] args) {
        addItem(1);
        addItem(2);
        addItem(3);
        addItem(4);
        addItem(5);
        addItem(8);
        addItem(9);
        addItem(78);
        addItem(90);
        addItem(5);

       // printList(head);

        //delete the item at kth position
        /*deleteKthItem(head,1);
        System.out.println();
        printList(head);
        System.out.println();
        insertAt(head,4,80);*/
        printList(head);
        System.out.println();
        printList(reverseList(head));

       /* Node mid = midList(head);
        System.out.println("\nMiddle Number of List "+mid.data);

        System.out.println("Actual List");
        printList(head);

        System.out.println("\nReversed List");
        Node node = head;
        Node prev = reverseList(node);
        printList(prev);*/


    }

    static void insertAt(Node head,int k,int item){
        Node node = new Node(item,null);

        Node current = head;
        int start = 0;
        while (current != null) {
            start++;
            if (k == start) {
                break;
            }
            current = current.next;

        }

        if (current != null && current.next != null){
            node.next = current.next;
            current.next = node;
        }


    }

    static void deleteKthItem(Node head, int k) {
        Node current = head;
        int start = 0;
        while (current != null) {
            start++;
            if (k == start) {
                break;
            }
            current = current.next;

        }

        if (current.next != null && current.next.next != null)
            current.next = current.next.next;
    }

    static void addItem(int item) {
        Node node = new Node(item, null);
        if (head == null) {
            head = node;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
        }
    }

    static void printList(Node head) {

        Node current = head;
        while (current != null) {

            System.out.print(current.data + "  ");
            current = current.next;
        }
    }

    static Node midList(Node head) {
        Node slow = head;
        Node current = head;
        while (current.next != null && current.next.next != null) {
            slow = slow.next;
            current = current.next.next;

        }
        return slow;
    }


    static Node reverseList(Node head) {
        Node prev = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}


