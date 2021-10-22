package com.example.myrxjava.ordinal.StackAndQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * A stack that mimic a scenario of stacks exceeding a threshold and
 * then starts a new stack
 **/
public class SetOfStack<T>{

    private List<Deque<T>> stackSet;
    private int thresholdCapacity;
    private int currentPointer = 0;

    SetOfStack(int threshold) {
        this.thresholdCapacity = threshold;
        stackSet = new ArrayList<>();
        Deque<T> stack = new ArrayDeque<>(threshold);
        stackSet.add(stack);
    }

    SetOfStack(){
        this(10);
    }

    //check if the current is full
    private boolean isCurrentStackFull(){
        return stackSet.get(currentPointer).size() == thresholdCapacity;
    }

    //check if the current stack is empty
    private boolean isCurrentStackEmpty(){
        return stackSet.get(currentPointer).isEmpty();
    }

    //check if the current stack is empty
    private boolean isStackEmptyAt(int index){
        return stackSet.get(index).isEmpty();
    }


    //push to stack
    public T push(T data){
        //check if the current stack is full
        if (isCurrentStackFull()){
            // create a new stack
            Deque<T> newStack = new ArrayDeque<>(thresholdCapacity);
            stackSet.add(newStack);
            currentPointer++;
        }

        stackSet.get(currentPointer).addFirst(data);
        return stackSet.get(currentPointer).peek();
    }


    //pop from the stack
    public T pop() throws EmptyStackException{
        //check if the current stack is empty
        if (isCurrentStackEmpty()){
            //clear the empty stack from memory and decrease the pointer
            stackSet.remove(currentPointer);
            currentPointer--;
        }

        //check if the stack set is empty
        if (currentPointer < 0){
            throw new EmptyStackException("The Stack Set is empty");
        }

        return stackSet.get(currentPointer).pop();
    }

    //pop from the stack
    public T popAt(int index) throws Exception{
        //check if the index in within range
        if (index < 0 || index > currentPointer){
            throw new IllegalArgumentException("The index is outside the range of stack set range");
        }

        //check if the current stack is empty
        if (isStackEmptyAt(index)){
            throw new EmptyStackException("The current index is empty");
        }

        return stackSet.get(index).pop();
    }

    //peek from the stack
    public T peek() throws EmptyStackException{

        //check if the stack set is empty
        if (currentPointer < 0){
            throw new EmptyStackException("The Stack Set is empty");
        }

        return stackSet.get(currentPointer).peek();
    }


}
