package com.example.myrxjava.treeAndGraph.prep;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Modeling for adjacency list graph
 **/
public class AdjacencyListGraph<T> {
    boolean isDirected;
    List<Edges<T>> edges;
    Map<Long,Vertex<T>> vertices;



    public AdjacencyListGraph(boolean isDirected){
        this.isDirected = isDirected;
        this.edges = new ArrayList<>();
        this.vertices = new HashMap<>();
    }

    public void addEdges(long src, long des,int weight,T data1,T data2){
        Vertex<T> vertexA = null;
        Vertex<T> vertexB = null;
        if (vertices.containsKey(src)){
            vertexA = vertices.get(src);
        }else{
            vertexA = new Vertex<>(data1,src);
            vertices.put(src,vertexA);
        }

        if (vertices.containsKey(des)) {
            vertexB = vertices.get(des);
        } else {
            vertexB = new Vertex<>(data2,des);
            vertices.put(des,vertexB);
        }

        Edges<T> edge = new Edges<>(vertexA,vertexB,weight,isDirected);
        edges.add(edge);


        //add edges to the corresponding vertex
        vertexA.addEdgeVertex(edge,vertexB);
        if (!isDirected){
            Edges<T> edge1 = new Edges<>(vertexB,vertexA,weight,false);
            edges.add(edge1);
            //add edges to the corresponding vertex
            vertexB.addEdgeVertex(edge1,vertexA);
        }
    }

    public void addEdges(long src, long des,int weight){
        Vertex<T> vertexA = null;
        Vertex<T> vertexB = null;
        if (vertices.containsKey(src)){
            vertexA = vertices.get(src);
        }else{
            vertexA = new Vertex<>(src);
            vertices.put(src,vertexA);
        }

        if (vertices.containsKey(des)) {
            vertexB = vertices.get(des);
        } else {
            vertexB = new Vertex<>(des);
            vertices.put(des,vertexB);
        }

        Edges<T> edge = new Edges<>(vertexA,vertexB,weight,isDirected);
        edges.add(edge);


        //add edges to the corresponding vertex
        vertexA.addEdgeVertex(edge,vertexB);
        if (!isDirected){
            Edges<T> edge1 = new Edges<>(vertexB,vertexA,weight,false);
            edges.add(edge1);
            //add edges to the corresponding vertex
            vertexB.addEdgeVertex(edge1,vertexA);
        }
    }

    public void addVertex(long des,T data){
        Vertex<T> vertex = new Vertex<>(data,des);
        vertices.put(des,vertex);
    }

    public boolean isDirected() {
        return isDirected;
    }

    public List<Edges<T>> getEdges() {
        return edges;
    }

    public Map<Long, Vertex<T>> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Edges<T> edge : edges) {
            stringBuilder.append(edge.getVertexA())
                    .append(" ")
                    .append(edge.getVertexB())
                    .append(" ")
                    .append(edge.getWeight())
                    .append("\n");
        }

        return stringBuilder.toString();
    }
}
