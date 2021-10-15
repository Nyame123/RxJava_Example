package com.example.myrxjava.search.medium;

import com.example.myrxjava.search.Node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PalindromicLinkedList {
    static Node head;

    public static void main(String[] args) {
        head = new Node(2);
        head.next = new Node(12);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(12);
        head.next.next.next.next.next.next = new Node(2);
        //head.next.next.next.next.next.next.next = new Node(6);

        if (isPalindromicRecurse(head)) {
            System.out.println("The List is palindromic");
        } else {
            System.out.println("The List is not palindromic");
        }
    }

    static Node midNode(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    static Node reverseList(Node head) {
        Node cur = head;
        Node prev = null;
        while (cur != null) {
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    //Time Complexity = O(n)
    static boolean isPalindromicList(Node head) {
        Node cur = head;
        Node mid = midNode(head);
        Node reversedNode = reverseList(mid);

        while (reversedNode != null && cur != null) {
            if (reversedNode.data != cur.data) {
                return false;
            }

            reversedNode = reversedNode.next;
            cur = cur.next;
        }

        return true;
    }

    //Time Complexity = O(n)
    //Space Complexity = O(n)
    static boolean isPalindromicUsingStack(Node head){
        Deque<Integer> stack = new LinkedList<>();

        //add to the stack
        Node mid = addToStack(stack,head);
        //remove and match with the list
        while (mid != null){
            int data = mid.data;
            int pop = stack.pop();
            if (data != pop){
                return false;
            }
            mid = mid.next;
        }

        return true;
    }

    private static Node addToStack(Deque<Integer> stack,Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            stack.addFirst(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        //if it is odd, skip the next
        if (fast != null)
            slow = slow.next;

        return slow;
    }


    //Time Complexity = O(n)
    //Space Complexity = O(n)
    static boolean isPalindromicRecurse(Node head){
        int len = lengthOfList(head);
        //pass the mid length to get the midpoint
        Result result = recursiveCheck(head,len);
        return result.result;
    }

    private static Result recursiveCheck(Node head, int len) {
        if (len <= 0){ // when even
            Result result = new Result();
            result.node = head;
            result.result = true;
            return result;
        }

        if (len == 1){  //when it is odd
            Result result = new Result();
            result.node = head.next;
            result.result = true;
            return result;
        }

       Result result = recursiveCheck(head.next,len-2);

        //if the child failed
        if (!result.result || result.node == null){
            return result;
        }

        result.result = head.data == result.node.data;

        //return corresponding node
        result.node = result.node.next;
        return result;
    }

    private static int lengthOfList(Node head) {
        int len = 0;
        Node cur = head;
        while (cur != null){
            len++;
            cur = cur.next;
        }

        return len;
    }

    static class Result{
        boolean result;
        Node node;
    }


}
