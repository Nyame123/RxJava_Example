package com.example.myrxjava.ordinal.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data structure to support following operations
 * extracMin - O(logn)
 * addToHeap - O(logn)
 * containsKey - O(1)
 * decreaseKey - O(logn)
 * getKeyWeight - O(1)
 * <p>
 * It is a combination of binary heap and hash map
 */
public class BinaryMinHeap<T> {

    private List<Node> allNodes = new ArrayList<>();
    private Map<T, Integer> nodePosition = new HashMap<>();

    public static void main(String args[]) {
        BinaryMinHeap<String> heap = new BinaryMinHeap<String>();
        heap.add(3, "Tushar");
        heap.add(4, "Ani");
        heap.add(8, "Vijay");
        heap.add(10, "Pramila");
        heap.add(5, "Roy");
        heap.add(6, "NTF");
        heap.add(2, "AFR");
        //heap.decrease("Pramila", 1);
        heap.printHeap();
        heap.printPositionMap();
    }

    /**
     * Checks where the key exists in heap or not
     */
    public boolean containsData(T key) {
        return nodePosition.containsKey(key);
    }

    /**
     * Add key and its weight to they heap
     */
    public void add(int weight, T key) {
        Node node = new Node();
        node.weight = weight;
        node.key = key;
        allNodes.add(node);
        int size = allNodes.size();
        int current = size - 1;
        int parentIndex = (current - 1) / 2;
        nodePosition.put(node.key, current);

        while (parentIndex >= 0) {
            Node parentNode = allNodes.get(parentIndex);
            Node currentNode = allNodes.get(current);
            if (parentNode.weight > currentNode.weight) {
                swap(parentNode, currentNode);
                updatePositionMap(parentNode.key, currentNode.key, parentIndex, current);
                current = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Get the heap min without extracting the key
     */
    public T min() {
        return allNodes.get(0).key;
    }

    /**
     * Checks with heap is empty or not
     */
    public boolean empty() {
        return allNodes.size() == 0;
    }

    /**
     * Decreases the weight of given key to newWeight
     */
    public void decrease(T data, int newWeight) {
        Integer position = nodePosition.get(data);
        allNodes.get(position).weight = newWeight;
        int parent = (position - 1) / 2;
        while (parent >= 0) {
            if (allNodes.get(parent).weight > allNodes.get(position).weight) {
                swap(allNodes.get(parent), allNodes.get(position));
                updatePositionMap(allNodes.get(parent).key, allNodes.get(position).key, parent, position);
                position = parent;
                parent = (parent - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Get the weight of given key
     */
    public Integer getWeight(T key) {
        Integer position = nodePosition.get(key);
        if (position == null) {
            return null;
        } else {
            return allNodes.get(position).weight;
        }
    }

    /**
     * Returns the min node of the heap
     */
    public Node extractMinNode() {
        int size = allNodes.size() - 1;
        Node minNode = new Node();
        minNode.key = allNodes.get(0).key;
        minNode.weight = allNodes.get(0).weight;

        int lastNodeWeight = allNodes.get(size).weight;
        allNodes.get(0).weight = lastNodeWeight;
        allNodes.get(0).key = allNodes.get(size).key;
        nodePosition.remove(minNode.key);
        nodePosition.remove(allNodes.get(0));
        nodePosition.put(allNodes.get(0).key, 0);
        allNodes.remove(size);

        int currentIndex = 0;
        size--;
        while (true) {
            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if (left > size) {
                break;
            }
            if (right > size) {
                right = left;
            }
            int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left : right;
            if (allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight) {
                swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
                updatePositionMap(allNodes.get(currentIndex).key, allNodes.get(smallerIndex).key, currentIndex, smallerIndex);
                currentIndex = smallerIndex;
            } else {
                break;
            }
        }
        return minNode;
    }

    /**
     * Extract min value key from the heap
     */
    public T extractMin() {
        Node node = extractMinNode();
        return node.key;
    }

    private void printPositionMap() {
        System.out.println(nodePosition);
    }

    private void swap(Node node1, Node node2) {
        int weight = node1.weight;
        T data = node1.key;

        node1.key = node2.key;
        node1.weight = node2.weight;

        node2.key = data;
        node2.weight = weight;
    }

    private void updatePositionMap(T data1, T data2, int pos1, int pos2) {
        nodePosition.remove(data1);
        nodePosition.remove(data2);
        nodePosition.put(data1, pos1);
        nodePosition.put(data2, pos2);
    }

    public void printHeap() {
        for (Node n : allNodes) {
            System.out.println(n.weight + " " + n.key);
        }
    }

    public class Node {
        public int weight;
        public T key;
    }
}

class BinaryMinHeap1{

    static class Node{
        int data;
        Node left = null;
        Node right = null;

        Node(int d){
            this.data = d;
        }
    }

    public static void main(String[] args) {
        BinaryMinHeap1  heap = new BinaryMinHeap1();
        heap.addToHeap(3);
        heap.addToHeap(4);
        heap.addToHeap(8);
        heap.addToHeap(10);
        heap.addToHeap(5);
        heap.addToHeap(6);
        heap.addToHeap(2);
        heap.decrease(5, 1);
        heap.extractMin();
        heap.print();
    }

    List<Node> heap = new ArrayList<>();

    //swap contents
    static void swap(Node n1, Node n2){
        int temp = n1.data;
        n1.data = n2.data;
        n2.data = temp;

    }

    //add data to heap
    void addToHeap(int data){
        Node newNode = new Node(data);
        heap.add(newNode);
        int size = heap.size();
        int current = size - 1;
        int parentIndex = (current - 1) / 2;

        while(parentIndex >= 0){
            Node currentNode = heap.get(current);
            Node parentNode = heap.get(parentIndex);
            if(parentNode.data > currentNode.data){
                //swap the contents
                swap(currentNode,parentNode);
                current = parentIndex;
                parentIndex = (parentIndex - 1) / 2;

            }else{
                break;
            }

        }
    }

    //Decrease data
    void decrease(int pos,int data){
        Node temp = heap.get(pos);
        temp.data = data;

        int current = pos;
        int parentIndex = (current - 1) / 2;

        while(parentIndex >= 0){
            Node currentNode = heap.get(current);
            Node parentNode = heap.get(parentIndex);
            if(parentNode.data > currentNode.data){
                //swap the contents
                swap(currentNode,parentNode);
                current = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            }else{
                break;
            }

        }

    }

    //extract Min
    Node extractMin(){
        int size = heap.size();
        Node newNode = new Node(heap.get(0).data);
        newNode.left = heap.get(0).left;
        newNode.right = heap.get(0).right;

        Node lastNode = heap.get(size-1);
        heap.get(0).data = lastNode.data;
        heap.get(0).left = lastNode.left;
        heap.get(0).right = lastNode.right;

        heap.remove(size-1);
        size--;
        int currentIndex = 0;
        while(true){
            int left = 2*currentIndex + 1;
            int right = 2*currentIndex + 2;

            if(left >= size){
                break;
            }

            if(right >= size){
                right = left;
            }


            int leftMost;
            if (heap.get(left).data <= heap.get(right).data){
                leftMost = left;
            }else{
                leftMost = right;
            }
            if(heap.get(currentIndex).data > heap.get(leftMost).data){
                swap(heap.get(currentIndex),heap.get(leftMost));
                currentIndex = leftMost;

            }else{
                break;
            }

        }

        return newNode;

    }

    void print(){
        for (int i = 0; i < heap.size(); i++){
            System.out.print(heap.get(i).data +" ");
        }
    }


}