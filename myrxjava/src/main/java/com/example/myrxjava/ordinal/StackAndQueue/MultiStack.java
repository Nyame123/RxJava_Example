package com.example.myrxjava.ordinal.StackAndQueue;

import java.util.Objects;

/**
 * Using one single array as a multiple stack
 **/
public class MultiStack<T> {
    int stackCapacity;
    int numOfStack;
    int size[];
    T values[];

    MultiStack(int capacity,int stacks){
        this.stackCapacity = capacity;
        this.numOfStack = stacks;

        //total Array size
        int totalSize = stacks * stackCapacity;
        values = (T[]) new Object[totalSize];
        size = new int[stacks];
    }

    //Check if a particular stack is full
    boolean isFull(int stack){
        return size[stack] == stackCapacity;
    }

    //Check if a particular stack is empty
    boolean isEmpty(int stack){
        return size[stack] == 0;
    }

    //get the index of the top of the stack
    int indexOfTop(int which){
        return which * stackCapacity + size[which] - 1;
    }

    //push to a stack
    void push(int which,T data) throws FullStackException{
        //check if the stack is full
        if (isFull(which)){
            throw new FullStackException("The stack is overflow");
        }

        //increase the stack size
        size[which]++;
        //calculate the offset of the data according to the stack
        int top = indexOfTop(which);
        values[top] = data;
    }

    //pop from a stack
    T pop(int which) throws EmptyStackException{
        //check if the stack is empty
        if (isEmpty(which)){
            throw new EmptyStackException("The stack is empty");
        }

        //calculate the offset of the stack to be popped
        int top = indexOfTop(which);
        T data = values[top];
        values[top] = (T) new Object();
        size[which]--;

        return data;
    }

    //peek from a stack
    T peek(int which){
        //calculate the offset of the stack to be popped
        int top = indexOfTop(which);
        return values[top];
    }




}

 class FullStackException extends Exception{
    String message;

    FullStackException(String mes){
        super(mes);
        this.message = mes;
    }
}

 class EmptyStackException extends Exception{
    String message;

    EmptyStackException(String mes){
        super(mes);
        this.message = mes;
    }
}
