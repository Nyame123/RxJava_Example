package com.example.myrxjava.search.medium;

import com.example.myrxjava.search.Node;

import java.util.HashMap;
import java.util.HashSet;

public class LoopDetection {

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(12);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        Node second = head.next;
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(12);
        head.next.next.next.next.next.next = second;

        Node loop = loopAtUsingRace(head);
        if (loop == null) {
            System.out.println("No Loop!");
        }else {
            System.out.println("Loop starts at "+loop.data);
        }
    }

    //Time Complexity = O(n)
    //Space Complexity = O(1)
    static Node loopAtUsingRace(Node head){
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast){ //collision spot
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return  null;
        }

        //reset slow pointer to the head and keep the fast pointer to the same meeting position
        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    //Time Complexity = O(n)
    //Space Complexity = O(n)
    static Node loopAt(Node head){
        HashSet<Node> nodeHashSet = new HashSet<>();
        Node cur = head;
        while (cur != null){
            if (nodeHashSet.contains(cur)){
                return cur;
            }
            nodeHashSet.add(cur);
            cur = cur.next;
        }

        return null;
    }
}
