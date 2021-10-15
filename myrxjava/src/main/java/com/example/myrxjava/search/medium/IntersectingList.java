package com.example.myrxjava.search.medium;

import com.example.myrxjava.search.Node;

public class IntersectingList {

    public static void main(String[] args) {

        Node head = new Node(2);
        head.next = new Node(12);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(12);
        head.next.next.next.next.next.next = new Node(2);
        Node second =new Node(12);
        second.next = new Node(12);
        second.next.next = new Node(3);
        second.next.next.next = new Node(4);
        second.next.next.next.next = new Node(3);
        second.next.next.next.next.next = new Node(12);
        second.next.next.next.next.next.next = new Node(2);


        if (isIntersecting(head,second)){
            System.out.println("There is an intersection");
        }else{
            System.out.println("There is no intersection");
        }
    }

    static boolean isIntersecting(Node head1, Node head2) {
        //check the length of the lists
        Result len1 = lengthOfList(head1);
        Result len2 = lengthOfList(head2);

        if (len1.node != len2.node)
            return false;

        if (len1.size < len2.size) {
            head1 = padList(len2.size - len1.size, head1);
        } else if (len1.size > len2.size) {
            head2 = padList(len1.size - len2.size, head2);
        }

        while (head1 != null && head2 != null) {
            if (head1 == head2) {
                return true;
            }

            head1 = head1.next;
            head2 = head2.next;
        }

        return false;
    }

    static Node padList(int len, Node node) {
        while (len > 0) {
            Node temp = new Node(0);
            temp.next = node;
            node = temp;

            len--;
        }

        return node;
    }

    private static Result lengthOfList(Node head) {
        if (head == null)
            return new Result(0,head);
        int len = 1;
        Node cur = head;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }

        return new Result(len,cur);
    }

    static class Result{
        int size;
        Node node;

        Result(int s, Node n){
            this.size = s;
            this.node = n;
        }
    }
}
