package com.example.myrxjava.treeAndGraph.hard;

import com.example.myrxjava.ordinal.graphs.Graph;
import com.example.myrxjava.ordinal.graphs.Vertex;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Tarjan’s Algorithm to find Strongly Connected Components
 * Difficulty Level : Hard
 * Last Updated : 29 Jul, 2021
 * A directed graph is strongly connected if there is a path between all pairs of vertices.
 * A strongly connected component (SCC) of a directed graph is a maximal strongly connected subgraph.
 * For example, there are 3 SCCs in the following graph.
 * <p>
 * Tarjan Algorithm is based on following facts:
 * 1. DFS search produces a DFS tree/forest
 * 2. Strongly Connected Components form subtrees of the DFS tree.
 * 3. If we can find the head of such subtrees, we can print/store all
 * the nodes in that subtree (including head) and that will be one SCC.
 * 4. There is no back edge from one SCC to another (There can be cross edges,
 * but cross edges will not be used while processing the graph).
 * <p>
 * https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/
 **/
public class TarjanAlgorithmSCC {

    // No. of vertices
    private int V;

    //Adjacency Lists
    private LinkedList<Integer> adj[];
    private int Time;

    // Constructor
    @SuppressWarnings("unchecked")
    TarjanAlgorithmSCC(int v) {
        V = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();

        Time = 0;
    }

    // Driver code
    public static void main(String args[]) {

        // Create a graph given in the above diagram
        TarjanAlgorithmSCC g1 = new TarjanAlgorithmSCC(5);

        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        System.out.println("SSC in first graph ");
        g1.SCC();

        TarjanAlgorithmSCC g2 = new TarjanAlgorithmSCC(4);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        System.out.println("\nSSC in second graph ");
        g2.SCC();

        TarjanAlgorithmSCC g3 = new TarjanAlgorithmSCC(7);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 0);
        g3.addEdge(1, 3);
        g3.addEdge(1, 4);
        g3.addEdge(1, 6);
        g3.addEdge(3, 5);
        g3.addEdge(4, 5);
        System.out.println("\nSSC in third graph ");
        g3.SCC();

        TarjanAlgorithmSCC g4 = new TarjanAlgorithmSCC(11);
        g4.addEdge(0, 1);
        g4.addEdge(0, 3);
        g4.addEdge(1, 2);
        g4.addEdge(1, 4);
        g4.addEdge(2, 0);
        g4.addEdge(2, 6);
        g4.addEdge(3, 2);
        g4.addEdge(4, 5);
        g4.addEdge(4, 6);
        g4.addEdge(5, 6);
        g4.addEdge(5, 7);
        g4.addEdge(5, 8);
        g4.addEdge(5, 9);
        g4.addEdge(6, 4);
        g4.addEdge(7, 9);
        g4.addEdge(8, 9);
        g4.addEdge(9, 8);
        System.out.println("\nSSC in fourth graph ");
        g4.SCC();

        TarjanAlgorithmSCC g5 = new TarjanAlgorithmSCC(5);
        g5.addEdge(0, 1);
        g5.addEdge(1, 2);
        g5.addEdge(2, 3);
        g5.addEdge(2, 4);
        g5.addEdge(3, 0);
        g5.addEdge(4, 2);
        System.out.println("\nSSC in fifth graph ");
        g5.SCC();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // A recursive function that finds and prints strongly
// connected components using DFS traversal
// u --> The vertex to be visited next
// disc[] --> Stores discovery times of visited vertices
// low[] -- >> earliest visited vertex (the vertex with
//             minimum discovery time) that can be reached
//             from subtree rooted with current vertex
// st -- >> To store all the connected ancestors (could be part
//         of SCC)
// stackMember[] --> bit/index array for faster check
//                   whether a node is in stack
    void SCCUtil(int u, int low[], int disc[],
                 boolean stackMember[],
                 Stack<Integer> st) {

        // Initialize discovery time and low value
        disc[u] = Time;
        low[u] = Time;
        Time += 1;
        stackMember[u] = true;
        st.push(u);

        int n;

        // Go through all vertices adjacent to this
        Iterator<Integer> i = adj[u].iterator();

        while (i.hasNext()) {
            n = i.next();

            if (disc[n] == -1) {
                SCCUtil(n, low, disc, stackMember, st);

                // Check if the subtree rooted with v
                // has a connection to one of the
                // ancestors of u
                // Case 1 (per above discussion on
                // Disc and Low value)
                low[u] = Math.min(low[u], low[n]);
            } else if (stackMember[n]) {

                // Update low value of 'u' only if 'v' is
                // still in stack (i.e. it's a back edge,
                // not cross edge).
                // Case 2 (per above discussion on Disc
                // and Low value)
                low[u] = Math.min(low[u], disc[n]);
            }
        }

        // head node found, pop the stack and print an SCC
        // To store stack extracted vertices
        int w = -1;
        if (low[u] == disc[u]) {
            while (w != u) {
                w = (int) st.pop();
                System.out.print(w + " ");
                stackMember[w] = false;
            }
            System.out.println();
        }
    }

    // The function to do DFS traversal.
// It uses SCCUtil()
    void SCC() {

        // Mark all the vertices as not visited
        // and Initialize parent and visited,
        // and ap(articulation point) arrays
        int disc[] = new int[V];
        int low[] = new int[V];
        for (int i = 0; i < V; i++) {
            disc[i] = -1;
            low[i] = -1;
        }

        boolean stackMember[] = new boolean[V];
        Stack<Integer> st = new Stack<Integer>();

        // Call the recursive helper function
        // to find articulation points
        // in DFS tree rooted with vertex 'i'
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1)
                SCCUtil(i, low, disc,
                        stackMember, st);
        }
    }
}

class TarjanStronglyConnectedComponent {

    private Map<Vertex<Integer>, Integer> visitedTime;
    private Map<Vertex<Integer>, Integer> lowTime;
    private Set<Vertex<Integer>> onStack;
    private Deque<Vertex<Integer>> stack;
    private Set<Vertex<Integer>> visited;
    private List<Set<Vertex<Integer>>> result;
    private int time;

    public static void main(String args[]) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        graph.addEdge(7, 6);
        graph.addEdge(7, 8);
        graph.addEdge(8, 7);

        TarjanStronglyConnectedComponent tarjanStronglyConnectedComponent = new TarjanStronglyConnectedComponent();
        List<Set<Vertex<Integer>>> result = tarjanStronglyConnectedComponent.scc(graph);

        result.forEach(scc -> {
            scc.forEach(vertex -> System.out.print(vertex + " "));
            System.out.println();
        });

    }

    public List<Set<Vertex<Integer>>> scc(Graph<Integer> graph) {

        //keeps the time when every vertex is visited
        time = 0;
        //keeps map of vertex to time it was visited
        visitedTime = new HashMap<>();

        //keeps map of vertex and time of first vertex visited in current DFS
        lowTime = new HashMap<>();

        //tells if a vertex is in stack or not
        onStack = new HashSet<>();

        //stack of visited vertices
        stack = new LinkedList<>();

        //tells if vertex has ever been visited or not. This is for DFS purpose.
        visited = new HashSet<>();

        //stores the strongly connected components result;
        result = new ArrayList<>();

        //start from any vertex in the graph.
        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex)) {
                continue;
            }
            sccUtil(vertex);
        }

        return result;
    }

    private void sccUtil(Vertex<Integer> vertex) {

        visited.add(vertex);
        visitedTime.put(vertex, time);
        lowTime.put(vertex, time);
        time++;
        stack.addFirst(vertex);
        onStack.add(vertex);

        for (Vertex child : vertex.getAdjacentVertexes()) {
            //if child is not visited then visit it and see if it has link back to vertex's ancestor. In that case update
            //low time to ancestor's visit time
            if (!visited.contains(child)) {
                sccUtil(child);
                //sets lowTime[vertex] = min(lowTime[vertex], lowTime[child]);
                lowTime.compute(vertex, (v, low) ->
                        Math.min(low, lowTime.get(child)));
            } //if child is on stack then see if it was visited before vertex's low time. If yes then update vertex's low time to that.
            else if (onStack.contains(child)) {
                //sets lowTime[vertex] = min(lowTime[vertex], visitedTime[child]);
                lowTime.compute(vertex, (v, low) -> Math.min(low, visitedTime.get(child)));
            }
        }

        //if vertex low time is same as visited time then this is start vertex for strongly connected component.
        //keep popping vertices out of stack still you find current vertex. They are all part of one strongly
        //connected component.
        if (visitedTime.get(vertex) == lowTime.get(vertex)) {
            Set<Vertex<Integer>> stronglyConnectedComponenet = new HashSet<>();
            Vertex v;
            do {
                v = stack.pollFirst();
                onStack.remove(v);
                stronglyConnectedComponenet.add(v);
            } while (!vertex.equals(v));
            result.add(stronglyConnectedComponenet);
        }
    }
}
