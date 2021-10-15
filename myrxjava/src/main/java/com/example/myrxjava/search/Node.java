package com.example.myrxjava.search;

public class Node {
    public Node next;
    public int data;

    public Node(int d) {
        this.data = d;
    }

   public Node(Node n, int d) {
        this.next = n;
        this.data = d;
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }


}

