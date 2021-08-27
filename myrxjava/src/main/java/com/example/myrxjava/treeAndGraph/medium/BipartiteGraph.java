package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.ordinal.graphs.Graph;
import com.example.myrxjava.ordinal.graphs.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Check whether a given graph is Bipartite or not
 * Difficulty Level : Medium
 * Last Updated : 20 Aug, 2021
 * A Bipartite Graph is a graph whose vertices can be divided
 * into two independent sets, U and V such that every edge (u, v) either
 * connects a vertex from U to V or a vertex from V to U. In other words,
 * for every edge (u, v), either u belongs to U and v to V, or u belongs to V
 * and v to U. We can also say that there is no edge that connects vertices of same set.
 * <p>
 * https://www.geeksforgeeks.org/bipartite-graph/
 * <p>
 * Time Complexity = O(v^2)
 **/
public class BipartiteGraph {

    final static int V = 4; // No. of Vertices

    // Driver program to test above function
    public static void main(String[] args) {
        int G[][] = {
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}
        };
        BipartiteGraph b = new BipartiteGraph();
        if (b.isBipartite(G, 0))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    // This function returns true if
    // graph G[V][V] is Bipartite, else false
    //This works only when the graph is connected
    boolean isBipartite(int G[][], int src) {
        // Create a color array to store
        // colors assigned to all vertices.
        // Vertex number is used as index
        // in this array. The value '-1'
        // of colorArr[i] is used to indicate
        // that no color is assigned
        // to vertex 'i'. The value 1 is
        // used to indicate first color
        // is assigned and value 0 indicates
        // second color is assigned.
        int colorArr[] = new int[V];
        for (int i = 0; i < V; ++i)
            colorArr[i] = -1;

        // Assign first color to source
        colorArr[src] = 1;

        // Create a queue (FIFO) of vertex numbers
        // and enqueue source vertex for BFS traversal
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(src);

        // Run while there are vertices in queue (Similar to BFS)
        while (q.size() != 0) {
            // Dequeue a vertex from queue
            int u = q.poll();

            // Return false if there is a self-loop
            if (G[u][u] == 1)
                return false;

            // Find all non-colored adjacent vertices
            for (int v = 0; v < V; ++v) {
                // An edge from u to v exists
                // and destination v is not colored
                if (G[u][v] == 1 && colorArr[v] == -1) {
                    // Assign alternate color to this adjacent v of u
                    colorArr[v] = 1 - colorArr[u];
                    q.add(v);
                }

                // An edge from u to v exists and destination
                //  v is colored with same color as u
                else if (G[u][v] == 1 && colorArr[v] == colorArr[u])
                    return false;
            }
        }
        // If we reach here, then all adjacent vertices can
        // be colored with alternate color
        return true;
    }
}

//Time Complexity = O(v^2)
class UnconnectedBipartite {

    public static int V = 4;

    // This function returns true if graph
    // G[V][V] is Bipartite, else false
    public static boolean isBipartiteUtil(int G[][], int src, int colorArr[]) {
        colorArr[src] = 1;

        // Create a queue (FIFO) of vertex numbers and
        // enqueue source vertex for BFS traversal
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(src);

        // Run while there are vertices in queue
        // (Similar to BFS)
        while (!q.isEmpty()) {
            // Dequeue a vertex from queue
            // ( Refer http://goo.gl/35oz8 )
            int u = q.getFirst();
            q.pop();

            // Return false if there is a self-loop
            if (G[u][u] == 1)
                return false;

            // Find all non-colored adjacent vertices
            for (int v = 0; v < V; ++v) {
                // An edge from u to v exists and
                // destination v is not colored
                if (G[u][v] == 1 && colorArr[v] == -1) {
                    // Assign alternate color to this
                    // adjacent v of u
                    colorArr[v] = 1 - colorArr[u];
                    q.push(v);
                }

                // An edge from u to v exists and
                // destination v is colored with same
                // color as u
                else if (G[u][v] == 1
                        && colorArr[v] == colorArr[u])
                    return false;
            }
        }

        // If we reach here, then all adjacent vertices
        // can be colored with alternate color
        return true;
    }

    // Returns true if G[][] is Bipartite, else false
    public static boolean isBipartite(int G[][]) {
        // Create a color array to store colors assigned
        // to all vertices. Vertex/ number is used as
        // index in this array. The value '-1' of
        // colorArr[i] is used to indicate that no color
        // is assigned to vertex 'i'. The value 1 is used
        // to indicate first color is assigned and value
        // 0 indicates second color is assigned.
        int colorArr[] = new int[V];
        for (int i = 0; i < V; ++i)
            colorArr[i] = -1;

        // This code is to handle disconnected graoh
        for (int i = 0; i < V; i++)
            if (colorArr[i] == -1)
                if (!isBipartiteUtil(G, i, colorArr))
                    return false;

        return true;
    }

    /* Driver code*/
    public static void main(String[] args) {
        int G[][] = {
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}};

        if (isBipartite(G))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}

//Time Complexity = O(V + E)
class AdjacentListImp {
    static boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {

        // vector to store colour of vertex
        // assiging all to -1 i.e. uncoloured
        // colours are either 0 or 1
        // for understanding take 0 as red and 1 as blue
        int col[] = new int[V];
        Arrays.fill(col, -1);

        // queue for BFS storing {vertex , colour}
        Queue<Pair> q = new LinkedList<Pair>();

        //loop incase graph is not connected
        for (int i = 0; i < V; i++) {

            // if not coloured
            if (col[i] == -1) {

                // colouring with 0 i.e. red
                q.add(new Pair(i, 0));
                col[i] = 0;

                while (!q.isEmpty()) {
                    Pair p = q.peek();
                    q.poll();

                    //current vertex
                    int v = p.first;

                    // colour of current vertex
                    int c = p.second;

                    // traversing vertexes connected to current vertex
                    for (int j : adj.get(v)) {

                        // if already coloured with parent vertex color
                        // then bipartite graph is not possible
                        if (col[j] == c)
                            return false;

                        // if uncooloured
                        if (col[j] == -1) {

                            // colouring with opposite color to that of parent
                            col[j] = (c == 1) ? 0 : 1;
                            q.add(new Pair(j, col[j]));
                        }
                    }
                }
            }
        }

        // if all vertexes are coloured such that
        // no two connected vertex have same colours
        return true;
    }

    // Driver Code Starts.
    public static void main(String args[]) {

        int V, E;
        V = 4;
        E = 8;

        // adjacency list for storing graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Integer>());
        }

        adj.get(0).add(1);
        adj.get(0).add(3);

        adj.get(1).add(0);
        adj.get(1).add(2);

        adj.get(2).add(1);
        adj.get(2).add(3);

        adj.get(3).add(0);
        adj.get(3).add(2);

        boolean ans = isBipartite(V, adj);

        // returns 1 if bipatite graph is possible
        if (ans)
            System.out.println("Yes");

            // returns 0 if bipartite graph is not possible
        else
            System.out.println("No");

    }

    static class Pair {
        int first, second;

        Pair(int f, int s) {
            first = f;
            second = s;
        }
    }
}

class UsingDFS {
    static final int V = 4;

    static boolean colorGraph(int G[][],
                              int color[],
                              int pos, int c) {
        if (color[pos] != -1 &&
                color[pos] != c)
            return false;

        // color this pos as c and
        // all its neighbours as 1-c
        color[pos] = c;
        boolean ans = true;
        for (int i = 0; i < V; i++) {
            if (G[pos][i] == 1) {
                if (color[i] == -1)
                    ans &= colorGraph(G, color, i, 1 - c);

                if (color[i] != -1 && color[i] != 1 - c)
                    return false;
            }
            if (!ans)
                return false;
        }
        return true;
    }

    static boolean isBipartite(int G[][]) {
        int[] color = new int[V];
        for (int i = 0; i < V; i++)
            color[i] = -1;

        // start is vertex 0;
        int pos = 0;

        // two colors 1 and 0
        return colorGraph(G, color, pos, 1);
    }

    // Driver Code
    public static void main(String[] args) {
        int G[][] = {{0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}};

        if (isBipartite(G))
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}


/**
 http://www.geeksforgeeks.org/bipartite-graph/
 Includes both DFS and BFS method
 */
class BiparteGraph {

    public boolean isBiparte(Graph<Integer> graph){

        Set<Vertex<Integer>> redSet = new HashSet<>();
        Set<Vertex<Integer>> blueSet = new HashSet<>();

        Queue<Vertex<Integer>> queue = new LinkedList<>();

        for(Vertex<Integer> vertex : graph.getAllVertex()){
            if(!redSet.contains(vertex) && !blueSet.contains(vertex)){
                queue.add(vertex);
                redSet.add(vertex);
                while(!queue.isEmpty()){
                    vertex = queue.poll();
                    for(Vertex<Integer> v : vertex.getAdjacentVertexes()){
                        if(redSet.contains(vertex)){
                            if(redSet.contains(v)){
                                return false;
                            }
                            if(blueSet.contains(v)){
                                continue;
                            }
                            blueSet.add(v);
                        }
                        else if(blueSet.contains(vertex)){
                            if(blueSet.contains(v)){
                                return false;
                            }if(redSet.contains(v)){
                                continue;
                            }
                            redSet.add(v);
                        }
                        queue.add(v);
                    }
                }
            }
        }
        System.out.println(redSet);
        System.out.println(blueSet);
        return true;
    }

    public boolean isBiparteDFS(Graph<Integer> graph){
        Set<Vertex<Integer>> redSet = new HashSet<Vertex<Integer>>();
        Set<Vertex<Integer>> blueSet = new HashSet<Vertex<Integer>>();
        for(Vertex<Integer> vertex : graph.getAllVertex()){
            if(redSet.contains(vertex) || blueSet.contains(vertex)){
                continue;
            }
            boolean flag = isBiparteDFS(vertex, redSet, blueSet, true);
            if(!flag){
                return false;
            }
        }
        return true;
    }

    private boolean isBiparteDFS(Vertex<Integer> vertex, Set<Vertex<Integer>> redSet,
                                 Set<Vertex<Integer>> blueSet, boolean wasRed){

        if(wasRed){
            if(redSet.contains(vertex)){
                return false;
            }
            else if(blueSet.contains(vertex)){
                return true;
            }
            blueSet.add(vertex);
        }

        else if(!wasRed){
            if(blueSet.contains(vertex)){
                return false;
            }
            if(redSet.contains(vertex)){
                return true;
            }
            redSet.add(vertex);
        }

        for(Vertex<Integer> adj : vertex.getAdjacentVertexes()){
            boolean flag = isBiparteDFS(adj, redSet, blueSet, !wasRed);
            if(!flag){
                return false;
            }
        }
        return true;

    }

    public static void main(String argsp[]){
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(7, 9);
        graph.addEdge(7, 8);
        BiparteGraph bi = new BiparteGraph();
        boolean result = bi.isBiparteDFS(graph);
        System.out.print(result);
    }
}