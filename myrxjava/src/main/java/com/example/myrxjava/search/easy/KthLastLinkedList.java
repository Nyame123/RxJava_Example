package com.example.myrxjava.search.easy;

import com.example.myrxjava.search.Node;

public class KthLastLinkedList {

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

        int pos = 5;
        findKthTwoPoinnter(pos);
    }


    static void findKthLastRecurse(int k) {
        kthRecurse(head, k);
    }

    //Time Complexity = O(n)
    //Space Complexity = O(n)
    private static int kthRecurse(Node head, int k) {


        if (head == null) {
            return 0;
        }

        int c = 1 + kthRecurse(head.next, k);
        if (c == k) {
            System.out.print(head.data + " ");
        }
        return c;
    }

    //Time Complexity = O(n)
    static void findKthTwoPoinnter(int pos) {
        Node p = head;
        Node kthP = head;
        int count = pos;
        while (kthP != null) {
            if (count == 0) {
                p = p.next;
                kthP = kthP.next;
            } else {
                kthP = kthP.next;
                count--;
            }
        }

        if (p != null)
            System.out.print(p.data);
    }

    //Time Complexity = O(n)
    static void findKthLast(int pos) {
        findKthLast(pos, head, 0);
    }

    static void findKthLast(int k, Node head, int c) {

        Node kthPointer = reverseList(head);
        while (kthPointer != null) {
            c++;
            if (k == c) {
                System.out.print(kthPointer.data + " ");
                break;
            }
            kthPointer = kthPointer.next;
        }

    }

    private static Node reverseList(Node head) {
        Node cur = head;
        Node prev = null;
        while (cur != null) {
            Node temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        return prev;
    }

    static void findKthForwardRec(int k) {
        recurse(head, k, 0);
    }

    static void recurse(Node node, int k, int count) {
        if (count >= k) {
            if (node == null) {
                return;
            }
            System.out.print(node.data + " ");
        }
        recurse(node.next, k, count + 1);
    }

    //Time Complexity = O(n)
    static void findKthForward(int k) {
        Node kthPointer = head;
        int count = 0;
        while (kthPointer != null) {

            if (k == count) {
                break;
            }
            kthPointer = kthPointer.next;
            count++;
        }

        Node.printList(kthPointer);
    }
}
