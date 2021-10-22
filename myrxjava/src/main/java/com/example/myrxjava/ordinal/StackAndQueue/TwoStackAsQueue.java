package com.example.myrxjava.ordinal.StackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Using two stacks as a queue
 **/
public class TwoStackAsQueue<T> {
    Deque<T> firstStack, endStack;

    TwoStackAsQueue(){
        firstStack = new ArrayDeque<>();
        endStack = new ArrayDeque<>();
    }

    //Check the size of this queue
    public int size(){
        return firstStack.size() + endStack.size();
    }

    //push data unto a queue
    public void push(T data){
        //when adding data into a queue of this nature
        //we use the end stack as container to add the data
        endStack.addFirst(data);
    }

    //pop data from a queue
    public T pop() throws Exception{
        //when popping a data from this queue
        //we check if the first stack is empty then we
        //roll over all data from end stack into the first stack
        //in a reverse order
        inAReverseOrder();

        if (firstStack.isEmpty()){
            throw new EmptyStackException("The stack is empty");
        }

        return firstStack.pop();
    }

    //pop data from a queue
    public T peek() throws Exception{
        //when popping a data from this queue
        //we check if the first stack is empty then we
        //roll over all data from end stack into the first stack
        //in a reverse order
        inAReverseOrder();

        if (firstStack.isEmpty()){
            throw new EmptyStackException("The stack is empty");
        }

        return firstStack.peek();
    }

    private void inAReverseOrder() {
        if (firstStack.isEmpty()) {
            while (!endStack.isEmpty()) {
                firstStack.addFirst(endStack.pop());
            }
        }
    }


}
