package com.example.myrxjava.search.easy;


import com.example.myrxjava.search.Node;

import static com.example.myrxjava.search.Node.printList;

public class DeleteMiddleNode {
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

        printList(head);
        Node mid = head.next.next.next.next.next;
        deleteMiddleNode(mid);
    }

    static void deleteMiddleNode(Node mid){
        if (mid == null || mid.next == null){
            return;
        }
        Node next = mid.next;
        //duplicate the node
        mid.data = next.data;
        //remove next duplicate
        mid.next = next.next;
        printList(head);
    }

    //Time Complexity = O(n)
    static void deleteMiddleNodeFromHead(Node mid){

        Node cur = head;
        while (cur != null && cur.next != null){
            if (mid.data == cur.next.data){
                cur.next = cur.next.next;
                break;
            }
            cur = cur.next;
        }

        printList(head);
    }

}
