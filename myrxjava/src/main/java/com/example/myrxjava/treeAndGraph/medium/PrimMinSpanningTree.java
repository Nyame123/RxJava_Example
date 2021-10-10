package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.ordinal.graphs.BinaryMinHeap;
import com.example.myrxjava.ordinal.graphs.Edge;
import com.example.myrxjava.ordinal.graphs.Graph;
import com.example.myrxjava.ordinal.graphs.Vertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Find minimum spanning tree using Prim's algorithm
 * <p>
 * Space complexity - O(E + V)
 * Time complexity - O(ElogV)
 * <p>
 * References
 * https://en.wikipedia.org/wiki/Prim%27s_algorithm
 * CLRS book
 */
public class PrimMinSpanningTree {

    public static void main(String args[]) {
        Graph<Integer> graph = new Graph<>(false);
     /* graph.addEdge(0, 1, 4);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(2, 5, 4);
        graph.addEdge(1, 7, 11);
        graph.addEdge(0, 7, 8);
        graph.addEdge(2, 8, 2);
        graph.addEdge(3, 5, 14);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 8, 6);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 7);*/

        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 1, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(4, 5, 6);
        graph.addEdge(5, 6, 2);
        graph.addEdge(3, 5, 5);
        graph.addEdge(3, 6, 4);

        PrimMinSpanningTree prims = new PrimMinSpanningTree();
        Collection<Edge<Integer>> edges = prims.primMST(graph);
        for (Edge<Integer> edge : edges) {
            System.out.println(edge);
        }
    }

    /**
     * Main method of Prim's algorithm.
     */
    public List<Edge<Integer>> primMST(Graph<Integer> graph) {

        //binary heap + map data structure
        BinaryMinHeap<Vertex<Integer>> minHeap = new BinaryMinHeap<>();

        //map of vertex to edge which gave minimum weight to this vertex.
        Map<Vertex<Integer>, Edge<Integer>> vertexToEdge = new HashMap<>();

        //stores final result
        List<Edge<Integer>> result = new ArrayList<>();

        //insert all vertices with infinite value initially.
        for (Vertex<Integer> v : graph.getAllVertex()) {
            minHeap.add(Integer.MAX_VALUE, v);
        }

        //start from any random vertex
       //Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();
        Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();

        //for the start vertex decrease the value in heap + map to 0
        minHeap.decrease(startVertex, 0);

        //iterate till heap + map has elements in it
        while (!minHeap.empty()) {
            //extract min value vertex from heap + map
            Vertex<Integer> current = minHeap.extractMin();

            //get the corresponding edge for this vertex if present and add it to final result.
            //This edge wont be present for first vertex.
            Edge<Integer> spanningTreeEdge = vertexToEdge.get(current);
            if (spanningTreeEdge != null) {
                result.add(spanningTreeEdge);
            }

            //iterate through all the adjacent vertices
            for (Edge<Integer> edge : current.getEdges()) {
                Vertex<Integer> adjacent = getVertexForEdge(current, edge);
                //check if adjacent vertex exist in heap + map and weight attached with this vertex is greater than this edge weight
                if (minHeap.containsData(adjacent) && minHeap.getWeight(adjacent) > edge.getWeight()) {
                    //decrease the value of adjacent vertex to this edge weight.
                    minHeap.decrease(adjacent, edge.getWeight());
                    //add vertex->edge mapping in the graph.
                    vertexToEdge.put(adjacent, edge);
                }
            }
        }
        return result;
    }

    private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e) {
        return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
    }
}

//finding the minimum spanning tree with Prims Algorithm
class PrimMinSpannigTreeImp {

    List<Vertex> graph = new ArrayList<>();
    Map<Integer, Vertex> vertices = new HashMap<>();

    PrimMinSpannigTreeImp() {
    }

    public static void main(String[] args) {
        PrimMinSpannigTreeImp graph = new PrimMinSpannigTreeImp();
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 1, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(4, 5, 6);
        graph.addEdge(5, 6, 2);
        graph.addEdge(3, 5, 5);
        graph.addEdge(3, 6, 4);


        graph.minSpanningTree();
    }

    void addEdge(int u, int v, int w) {

        //first vertex
        Vertex vertex;
        if (vertices.containsKey(u)){
            vertex = vertices.get(u);
        }else {
            vertex = new Vertex();
            vertex.key = u;
        }


        //second vertex
        Vertex vertex1;
        if (vertices.containsKey(v)){
            vertex1 = vertices.get(v);
        }else {
            vertex1 = new Vertex();
            vertex1.key = v;
        }

        Edge edge = new Edge();
        edge.vertex = vertex;
        edge.vertex2 = vertex1;
        edge.weight = w;

        vertex.adjVertices.add(vertex1);
        vertex.adjEdges.add(edge);
        vertex1.adjVertices.add(vertex);
        vertex1.adjEdges.add(edge);

        if (!vertices.containsKey(vertex.key)) {
            graph.add(vertex);
            vertices.put(vertex.key,vertex);
        }
        if (!vertices.containsKey(vertex1.key)){
            graph.add(vertex1);
            vertices.put(vertex1.key,vertex1);
        }


    }

    void minSpanningTree() {
        List<Edge> results = new ArrayList<>();
        Map<Vertex, Edge> vertexEdge = new HashMap<>();

        BinaryMinHeapImp<Vertex> binaryMinHeapImp = new BinaryMinHeapImp<>();

        for (int i = 0; i < graph.size(); i++) {
            binaryMinHeapImp.addToHeap(graph.get(i), Integer.MAX_VALUE);
        }

        binaryMinHeapImp.decrease(graph.get(0), 0);

        while (!binaryMinHeapImp.isEmpty()) {
            BinaryMinHeapImp<Vertex>.Node node = binaryMinHeapImp.extractMin();
            Vertex vertex = node.key;
            if (vertexEdge.containsKey(vertex)) {
                results.add(vertexEdge.get(vertex));
            }

            for (int i = 0; i < vertex.adjEdges.size(); i++) {
                Edge destinationEdge = vertex.adjEdges.get(i);
                Vertex destination = getOtherVertex(destinationEdge, vertex);

                int distanceRes = destinationEdge.weight;
                if (binaryMinHeapImp.containData(destination) && distanceRes < binaryMinHeapImp.getWeight(destination)) {
                    binaryMinHeapImp.decrease(destination, distanceRes);
                    vertexEdge.put(destination, destinationEdge);
                }
            }

        }

        for (int i = 0; i < results.size(); i++) {
            Edge edge = results.get(i);
            System.out.printf("%d -- %d -- %d\n", edge.vertex.key, edge.vertex2.key, edge.weight);
        }

    }

    Vertex getOtherVertex(Edge edge, Vertex vertex) {
        return (edge.vertex == vertex) ? edge.vertex2 : edge.vertex;
    }

    static class Vertex {
        int key;
        List<Vertex> adjVertices = new ArrayList<>();
        List<Edge> adjEdges = new ArrayList<>();
    }

    static class Edge {
        int weight;
        Vertex vertex;
        Vertex vertex2;
    }
}

class BinaryMinHeapImp<T> {

    List<Node> heaps = new ArrayList<>();
    Map<T, Integer> maps = new HashMap<>();

    int getWeight(T key) {
        int pos = maps.get(key);
        return heaps.get(pos).weight;
    }

    int getSize() {
        return heaps.size();
    }

    boolean isEmpty() {
        return heaps.isEmpty();
    }

    boolean containData(T k) {
        return maps.containsKey(k);
    }

    void swapNodes(Node n1, Node n2, int pIndex, int currentIndex) {
        int w = n1.weight;
        T k = n1.key;
        n1.weight = n2.weight;
        n1.key = n2.key;
        n2.weight = w;
        n2.key = k;

        maps.put(n2.key, pIndex);
        maps.put(n1.key, currentIndex);

    }

    void addToHeap(T it, int weight) {
        Node item = new Node();
        item.weight = weight;
        item.key = it;
        heaps.add(item);
        int size = heaps.size();
        int current = size - 1;
        int parentIndex = (current - 1) / 2;
        maps.put(item.key, current);

        while (parentIndex >= 0) {
            Node currentNode = heaps.get(current);
            Node parentNode = heaps.get(parentIndex);
            if (currentNode.weight < parentNode.weight) {
                //swap the nodes
                swapNodes(currentNode, parentNode, parentIndex, current);
                current = parentIndex;
                parentIndex = (current - 1) / 2;
            } else {
                break;
            }
        }
    }

    void decrease(T item, int weight) {
        int pos = maps.get(item);
        heaps.get(pos).weight = weight;
        int parentIndex = (pos - 1) / 2;

        while (parentIndex >= 0) {
            Node parentNode = heaps.get(parentIndex);
            Node currentNode = heaps.get(pos);
            if (currentNode.weight < parentNode.weight) {
                swapNodes(currentNode, parentNode, parentIndex, pos);
                pos = parentIndex;
                parentIndex = (pos - 1) / 2;
            } else {
                break;
            }
        }

    }

    Node extractMin() {
        int size = heaps.size();
        Node lastNode = heaps.get(size - 1);
        Node node = new Node();
        node.weight = heaps.get(0).weight;
        node.key = heaps.get(0).key;

        heaps.get(0).key = lastNode.key;
        heaps.get(0).weight = lastNode.weight;

        maps.remove(node.key);
        maps.remove(lastNode.key);
        maps.put(heaps.get(0).key,0);
        heaps.remove(lastNode);
        size--;
        int parentIndex = 0;
        while (true) {
            int left = 2 * parentIndex + 1;
            int right = 2 * parentIndex + 2;
            if (left >= size) {
                break;
            }

            if (right >= size) {
                right = left;
            }

            int minIndex = (heaps.get(left).weight < heaps.get(right).weight) ? left : right;
            if (heaps.get(minIndex).weight < heaps.get(parentIndex).weight) {
                Node currentNode = heaps.get(minIndex);
                Node parentNode = heaps.get(parentIndex);
                swapNodes(currentNode, parentNode, parentIndex, minIndex);
                parentIndex = minIndex;
            } else {
                break;
            }
        }

        return node;
    }

    public class Node {
        T key;
        int weight;

        Node() {

        }

        Node(T k) {
            this.key = k;
        }
    }
}
