package com.example.myrxjava.ordinal.StackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * A stack that can do push, pop and  min operation in O(1)
 */
public class StackWithMin<T extends Comparable<T>> extends Stack<T> {

    //Stack for min values
    Deque<T> minStack;
    T currentMin = (T) new Object();

    StackWithMin(){
        minStack = new ArrayDeque<>();
    }

    @Override
    public T push(T item) {

        int compare = currentMin.compareTo(item);
        if (compare > 0) { // if the item is less than the minvalue
            minStack.addFirst(item);
            //update the min value
            currentMin = item;
        }

        return super.push(item);
    }

    @Override
    public synchronized T pop() {

        T data = super.pop();
        if (currentMin == data) {
            minStack.pop(); //remove from the min stack
            //update the min value to next on the min stack
            if (minStack.isEmpty())
                currentMin = (T) new Object();
            else
                currentMin = minStack.peek();
        }
        return data;
    }

    public T getCurrentMin() {
        return currentMin;
    }
}
