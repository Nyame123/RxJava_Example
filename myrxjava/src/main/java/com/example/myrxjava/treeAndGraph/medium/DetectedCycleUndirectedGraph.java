package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.ordinal.graphs.Edge;
import com.example.myrxjava.ordinal.graphs.Graph;
import com.example.myrxjava.ordinal.graphs.Vertex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Detect cycle in an undirected graph
 * Difficulty Level : Medium
 * Last Updated : 01 Jul, 2021
 * Given an undirected graph, how to check if there is a cycle in the graph?
 * Example,
 * <p>
 * Input: n = 4, e = 4
 * Output: Yes
 * Explanation:
 * 0 1, 1 2, 2 3, 0 2
 * Diagram:
 * <p>
 * <p>
 * <p>
 * <p>
 * The diagram clearly shows a cycle 0 to 2 to 1 to 0
 * Input:n = 4, e = 3
 * 0 1, 1 2, 2 3
 * Output:No
 * Explanation:
 * Diagram:
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * The diagram clearly shows no cycle
 * <p>
 * <p>
 * https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 **/
public class DetectedCycleUndirectedGraph {

    // No. of vertices
    private int V;

    // Adjacency List Represntation
    private LinkedList<Integer> adj[];

    // Constructor
    DetectedCycleUndirectedGraph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Driver method to test above methods
    public static void main(String args[]) {

        // Create a graph given
        // in the above diagram
        DetectedCycleUndirectedGraph g1 = new DetectedCycleUndirectedGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        if (g1.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn 't contains cycle");

        DetectedCycleUndirectedGraph g2 = new DetectedCycleUndirectedGraph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        if (g2.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn 't contains cycle");
    }

    // Function to add an edge
    // into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // A recursive function that
    // uses visited[] and parent to detect
    // cycle in subgraph reachable
    // from vertex v.
    Boolean isCyclicUtil(int v, Boolean visited[], int parent) {
        // Mark the current node as visited
        visited[v] = true;
        Integer i;

        // Recur for all the vertices
        // adjacent to this vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();

            // If an adjacent is not
            // visited, then recur for that
            // adjacent
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v))
                    return true;
            }

            // If an adjacent is visited
            // and not parent of current
            // vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }

    // Returns true if the graph
    // contains a cycle, else false.
    Boolean isCyclic() {

        // Mark all the vertices as
        // not visited and not part of
        // recursion stack
        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Call the recursive helper
        // function to detect cycle in
        // different DFS trees
        for (int u = 0; u < V; u++) {

            // Don't recur for u if already visited
            if (!visited[u])
                if (isCyclicUtil(u, visited, -1))
                    return true;
        }

        return false;
    }

}

class CycleUndirectedGraph<T> {

    public static void main(String args[]) {

        CycleUndirectedGraph<Integer> cycle = new CycleUndirectedGraph<Integer>();
        Graph<Integer> graph = new Graph<Integer>(false);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 1);
        boolean isCycle = cycle.hasCycleDFS(graph);
        System.out.println(isCycle);
        isCycle = cycle.hasCycleUsingDisjointSets(graph);
        System.out.print(isCycle);

    }

    public boolean hasCycleUsingDisjointSets(Graph<T> graph) {
        DisjointSet disjointSet = new DisjointSet();

        for (Vertex<T> vertex : graph.getAllVertex()) {
            disjointSet.makeSet(vertex.getId());
        }

        for (Edge<T> edge : graph.getAllEdges()) {
            long parent1 = disjointSet.findSet(edge.getVertex1().getId());
            long parent2 = disjointSet.findSet(edge.getVertex2().getId());
            if (parent1 == parent2) {
                return true;
            }
            disjointSet.union(edge.getVertex1().getId(), edge.getVertex2().getId());
        }
        return false;
    }

    public boolean hasCycleDFS(Graph<T> graph) {
        Set<Vertex<T>> visited = new HashSet<Vertex<T>>();
        for (Vertex<T> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex)) {
                continue;
            }
            boolean flag = hasCycleDFSUtil(vertex, visited, null);
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycleDFSUtil(Vertex<T> vertex, Set<Vertex<T>> visited, Vertex<T> parent) {
        visited.add(vertex);
        for (Vertex<T> adj : vertex.getAdjacentVertexes()) {
            if (adj.equals(parent)) {
                continue;
            }
            if (visited.contains(adj)) {
                return true;
            }
            boolean hasCycle = hasCycleDFSUtil(adj, visited, vertex);
            if (hasCycle) {
                return true;
            }
        }
        return false;
    }

}
