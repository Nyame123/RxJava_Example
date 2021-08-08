package com.example.myrxjava.ordinal.hard.GreedyAlg;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Huffman Coding | Greedy Algo-3
 * Difficulty Level : Hard
 * Last Updated : 23 Mar, 2021
 * Huffman coding is a lossless data compression algorithm. The idea is to assign variable-length
 * codes to input characters, lengths of the assigned codes are based on the frequencies of
 * corresponding characters. The most frequent character gets the smallest code and the
 * least frequent character gets the largest code.
 * The variable-length codes assigned to input characters are Prefix Codes, means
 * the codes (bit sequences) are assigned in such a way that the code assigned to
 * one character is not the prefix of code assigned to any other character. This is
 * how Huffman Coding makes sure that there is no ambiguity when decoding the generated bitstream.
 * Let us understand prefix codes with a counter example. Let there be four
 * characters a, b, c and d, and their corresponding variable length codes be
 * 00, 01, 0 and 1. This coding leads to ambiguity because code assigned to
 * c is the prefix of codes assigned to a and b. If the compressed bit
 * stream is 0001, the de-compressed output may be (cccd) or (ccb) or (acd) or (ab).
 * See this for applications of Huffman Coding.
 * There are mainly two major parts in Huffman Coding
 *
 * Build a Huffman Tree from input characters.
 * Traverse the Huffman Tree and assign codes to characters.
 * Steps to build Huffman Tree
 * Input is an array of unique characters along with their frequency of occurrences and output is Huffman Tree.
 *
 * Create a leaf node for each unique character and build a min heap
 * of all leaf nodes (Min Heap is used as a priority queue. The value
 * of frequency field is used to compare two nodes in min heap. Initially, the least frequent character is at root)
 * Extract two nodes with the minimum frequency from the min heap.
 *
 * Create a new internal node with a frequency equal to the sum of
 * the two nodes frequencies. Make the first extracted node as its
 * left child and the other extracted node as its right child. Add this node to the min heap.
 * Repeat steps#2 and #3 until the heap contains only one node. The
 * remaining node is the root node and the tree is complete.
 * Let us understand the algorithm with an example:
 * character   Frequency
 *     a            5
 *     b           9
 *     c           12
 *     d           13
 *     e           16
 *     f           45
 *
 * https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
 *
 * Time complexity: O(nlogn) where n is the number of unique characters.
 * If there are n nodes, extractMin() is called 2*(n – 1) times.
 * extractMin() takes O(logn) time as it calles minHeapify(). So, overall complexity is O(nlogn).
 * If the input array is sorted, there exists a linear time algorithm. We will soon be discussing in our next post.
 *
 * Applications of Huffman Coding:
 * They are used for transmitting fax and text.
 * They are used by conventional compression formats like PKZIP, GZIP, etc.
 *  It is useful in cases where there is a series of frequently occurring character
 *
 **/
class HuffmanNode{
    int freq;
    char input;
    HuffmanNode left;
    HuffmanNode right;
}

class HuffmanNodeComparator implements Comparator<HuffmanNode>{

    @Override
    public int compare(HuffmanNode o1, HuffmanNode o2) {
        if(o1.freq < o2.freq){
            return -1;
        }else{
            return 1;
        }
    }

}

class HuffmanEncoding {

    public Map<Character,String> huffman(char[] input, int freq[]){
        HuffmanNodeComparator comparator = new HuffmanNodeComparator();
        PriorityQueue<HuffmanNode> heap = new PriorityQueue<HuffmanNode>(input.length,comparator);
        for(int i=0; i < input.length; i++){
            HuffmanNode node = new HuffmanNode();
            node.freq = freq[i];
            node.input = input[i];
            heap.offer(node);
        }

        while(heap.size() > 1){
            HuffmanNode node1 = heap.poll();
            HuffmanNode node2 = heap.poll();
            HuffmanNode node = new HuffmanNode();
            node.left = node1;
            node.right = node2;
            node.freq = node1.freq + node2.freq;
            heap.offer(node);
        }

        Map<Character,String> map = new HashMap<Character,String>();
        StringBuffer buff = new StringBuffer();
        createCode(heap.poll(),map,buff);
        return map;

    }

    public void createCode(HuffmanNode node,Map<Character,String> map,StringBuffer buff){
        if(node == null){
            return;
        }

        if(node.left == null && node.right == null){
            map.put(node.input,buff.toString());
            return;
        }
        buff.append("0");
        createCode(node.left,map,buff);
        buff.deleteCharAt(buff.length()-1);
        buff.append("1");
        createCode(node.right,map,buff);
        buff.deleteCharAt(buff.length()-1);
    }


    public static void main(String args[]){
        HuffmanEncoding he = new HuffmanEncoding();
        char input[] = {'a','b','c','d','e','f'};
        int freq[] = {5,9,12,13,16,45};
        Map<Character,String> code = he.huffman(input, freq);
        System.out.println(code);
    }

}