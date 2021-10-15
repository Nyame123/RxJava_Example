package com.example.myrxjava.search.medium;

import com.example.myrxjava.search.Node;

import static com.example.myrxjava.search.Node.printList;

public class SumList {
   static int carry = 0;

    public static void main(String[] args) {
        Node first = new Node(6);
        first.next = new Node(1);
        first.next.next = new Node(7);

        Node second = new Node(2);
        second.next = new Node(9);
        second.next.next = new Node(5);
        second.next.next.next = new Node(5);

        sumListForwardRec(first, second);
    }

    static void sumListForwardRec(Node first, Node second) {
        int len1 = length(first);
        int len2 = length(second);
        if (len1 > len2) {
            second = padList(len1 - len2, second);
        } else if (len1 < len2) {
            first = padList(len2 - len1, first);
        }

        SumCarry sumCarry = recurseSum(first, second);

        if (sumCarry.carry > 0) {
            Node temp = new Node(sumCarry.carry);
            temp.next = sumCarry.node;
            sumCarry.node = temp;
        }
        printList(sumCarry.node);
    }

    private static SumCarry recurseSum(Node first, Node second) {

        if (first == null && second == null) {
            return null;
        }

        SumCarry sumCarry = recurseSum(first == null ? null : first.next, second == null ? null : second.next);
        int value = 0;
        if (sumCarry != null) {
            value = sumCarry.carry;
        }

        if (first != null)
            value += first.data;

        if (second != null) {
            value += second.data;
        }

        Node sum = new Node(value % 10);

        if (sumCarry != null)
            sum.next = sumCarry.node;

        SumCarry newSum = new SumCarry();
        newSum.node = sum;
        newSum.carry = (value >= 10) ? 1 : 0;
        return newSum;

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


    private static int length(Node first) {
        Node cur = first;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        return len;
    }


    //Time Complexity = O(n)
    static void sumListForward(Node first, Node second) {
        StringBuilder fr = new StringBuilder();
        StringBuilder sc = new StringBuilder();
        while (first != null || second != null) {
            Node firstNext = null;
            Node secondNext = null;
            if (first != null) {
                firstNext = first.next;
                fr.append(first.data);
            }

            if (second != null) {
                secondNext = second.next;
                sc.append(second.data);
            }


            //update
            first = firstNext;
            second = secondNext;
        }

        int firRes = 0;
        if (!fr.toString().equalsIgnoreCase(""))
            firRes = Integer.parseInt(fr.toString());
        int scRes = 0;
        if (!sc.toString().equalsIgnoreCase(""))
            scRes = Integer.parseInt(sc.toString());
        int result = firRes + scRes;
        System.out.print(result);
    }

    //Time Complexity = O(n)
    static void sumListReverse(Node first, Node second) {
        Node result = null;
        while (first != null || second != null) {
            Node firstNext = null;
            Node secondNext = null;
            if (first != null) {
                firstNext = first.next;
            }

            if (second != null) {
                secondNext = second.next;
            }
            int sum = ((second == null ? 0 : second.data) + (first == null ? 0 : first.data) + carry);
            carry = sum / 10;
            int rem = sum % 10;
            //hang to the result
            Node temp = new Node(rem);
            temp.next = result;
            result = temp;

            //update
            first = firstNext;
            second = secondNext;
        }

        if (carry > 0) {
            Node temp = new Node(carry);
            temp.next = result;
            result = temp;
        }
        printList(result);
    }

    static class SumCarry {
        Node node;
        int carry;
    }
}


