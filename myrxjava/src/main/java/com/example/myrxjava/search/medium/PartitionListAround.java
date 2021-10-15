package com.example.myrxjava.search.medium;

import com.example.myrxjava.search.Node;

import static com.example.myrxjava.search.Node.printList;

public class PartitionListAround {

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

        partitionUsingAuxDT(head, 6);
    }

    static void partitionUsingHT(Node head, int partition){
        Node first = head;
        Node last = head;

        while(head != null){
            int data = head.data;
            Node next = head.next;
            if (data < partition){
                //insert before first
                head.next = first;
                first = head;
            }else{
                //insert at the last
                last.next = head;
                last = head;
            }
            head = next;
        }
        //last.next = null;
        printList(first);
    }


    //Time complexity =  O(n)
    static void partitionUsingAuxDT(Node head, int partition) {
        //dummy node as a hanger
        Node hanger = new Node(100);
        Node hangerP = hanger;
        Node cur = head;
        while (cur != null) {
            int data = cur.data;
            if (data < partition) {
                Node temp = new Node(cur.data);
                hangerP.next = temp;
                hangerP = hangerP.next;

                //duplicate the node data
                cur.data = cur.next.data;
                //then delete the forward dup node
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        hangerP.next = head;
        printList(hanger.next);
    }
}
