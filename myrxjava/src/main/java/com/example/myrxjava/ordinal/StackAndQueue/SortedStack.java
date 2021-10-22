package com.example.myrxjava.ordinal.StackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Sort the stack without using array data structure.
 * The only data structure to be used is additional stack
 **/
public class SortedStack<T extends Comparable<T>> {
    Deque<T> sortedStack;

    SortedStack(){
        sortedStack = new ArrayDeque<>();
    }

    //Check the size of this queue
    public int size(){
        return sortedStack.size();
    }

    //push data unto a queue
    public void push(T data){
       //check if the top of the sorted stack is less than new data
        T temp = sortedStack.peek();
        sortedStack.addFirst(data);
        if (data.compareTo(temp) > 0){ //if the new data is greater than top
            //sort to bring the right smaller item at the top of the stack
            sort();
        }

    }

    //pop data from a queue
    public T pop() throws Exception{
        if (sortedStack.isEmpty()){
            throw new EmptyStackException("The stack is empty");
        }

        return sortedStack.pop();
    }

    //pop data from a queue
    public T peek() throws Exception{

        if (sortedStack.isEmpty()){
            throw new EmptyStackException("The stack is empty");
        }

        return sortedStack.peek();
    }

    private void sort() {
        Deque<T> newStack = new ArrayDeque<>();
        while (!sortedStack.isEmpty()){
            //pop the min data
            T temp = sortedStack.pop();
            while (!newStack.isEmpty() && (newStack.peek().compareTo(temp) > 0)){
                //add the current large data
                sortedStack.addFirst(newStack.pop());
            }

            //push the min data into new stack
            newStack.addFirst(temp);
        }

        //push the newStack into the sorted stack now
        while (!newStack.isEmpty()){
            sortedStack.addFirst(newStack.pop());
        }
    }
}
