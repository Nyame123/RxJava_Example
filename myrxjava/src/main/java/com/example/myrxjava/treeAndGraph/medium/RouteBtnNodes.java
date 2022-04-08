package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.treeAndGraph.prep.AdjacencyListGraph;
import com.example.myrxjava.treeAndGraph.prep.Vertex;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class RouteBtnNodes {

    static AdjacencyListGraph<Integer> graph;
    static HashMap<Long, Boolean> visited;
    static HashMap<Long, Vertex<Integer>> path;

    public static void main(String[] args) {

        graph = new AdjacencyListGraph<>(true);
        graph.addEdges(1, 2, 4);
        graph.addEdges(1, 4, 5);
        graph.addEdges(2, 5, 1);
        graph.addEdges(2, 3, 3);
        graph.addEdges(4, 5, 10);
        graph.addEdges(5, 3, 8);

        int graphSize = graph.getVertices().size();
        visited = new HashMap<>(graphSize);
        path = new HashMap<>(graphSize);

        long src = 1;
        long des = 3;
        if (isRouteBetweenBFS(src, des)) {
            System.out.println("There is a route between the nodes");
            System.out.print(des + " ");
            for (Vertex<Integer> i = path.get(des); i != null; i = path.get(i.getId())) {
                System.out.print(i.getId() + " ");
            }
        } else {
            System.out.println("There is no route between the nodes");
        }
    }

    //Time Complexity = O(E + V)
    //Space Complexity = O(V)
    static boolean isRouteBetweenBFS(long src, long des) {

        if (src == des)
            return true;

        Vertex<Integer> from = graph.getVertices().get(src);

        if (from == null)
            return false;

        path.put(src, null);
        Queue<Long> queue = new ArrayDeque<>();
        //add the src node
        queue.add(src);
        while (!queue.isEmpty()) {

            //current vertex
            Long rootVert = queue.poll();
            from = graph.getVertices().get(rootVert);

            //make the current vertex visited
            visited.put(from.getId(), true);

            //visit the neighbouring vertices
            for (Vertex<Integer> to : from.getAdjacentVerts()) {
                path.put(to.getId(), from);
                if (des == to.getId()) {
                    //path.put(rootVert, null);
                    return true;
                } else {
                    if (visited.get(to.getId()) == null) { //not visited
                        queue.add(to.getId());
                    }
                }
            }
        }

        return false;
    }

    //Time Complexity = O(E + V)
    //Space Complexity = O(V)
    static boolean isRouteBetweenDFS(long src, long des) {
        //Get the source vertex
        Vertex<Integer> sourceVertex = graph.getVertices().get(src);

        //Do a Depth First Search on the source vertex
        if (sourceVertex == null)
            return false;

        if (src == des)
            return true;

        path.put(src, null);
        return dfs(sourceVertex, des);
    }

    static boolean dfs(Vertex<Integer> sourceVertex, long des) {

        boolean seen = false;
        if (sourceVertex.getAdjacentVerts().size() == 0) {
            return false;
        }

        //make the current vertex visited
        visited.put(sourceVertex.getId(), true);

        for (int i = 0; i < sourceVertex.getAdjacentVerts().size(); i++) {
            Vertex<Integer> desVertex = sourceVertex.getAdjacentVerts().get(i);

            path.put(desVertex.getId(), sourceVertex);
            //check if the destination vertex is id looking for
            if (desVertex.getId() == des) {
                return true;
            } else {
                if (visited.get(desVertex.getId()) == null) { //not visited
                    //map the parent of the destination vertex
                    seen = seen || dfs(desVertex, des);

                }
            }
        }
        return seen;
    }


}
