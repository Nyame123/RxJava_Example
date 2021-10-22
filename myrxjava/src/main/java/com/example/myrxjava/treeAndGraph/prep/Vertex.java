package com.example.myrxjava.treeAndGraph.prep;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A vertex representation
 **/
public class Vertex<T> {
    long id;
    T data;
    List<Vertex<T>> adjacentVerts;
    List<Edges<T>> adjacentEdges;

    Vertex(long id){
        this.id = id;
        adjacentEdges = new ArrayList<>();
        adjacentVerts = new ArrayList<>();
    }

    Vertex(T data,long id){
        this.id = id;
        this.data = data;
        adjacentEdges = new ArrayList<>();
        adjacentVerts = new ArrayList<>();
    }

    public void addEdgeVertex(Edges<T> edge, Vertex<T> vertex){
        adjacentVerts.add(vertex);
        adjacentEdges.add(edge);
    }

    public T getData() {
        return data;
    }

    public List<Vertex<T>> getAdjacentVerts() {
        return adjacentVerts;
    }

    public List<Edges<T>> getAdjacentEdges() {
        return adjacentEdges;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return data.equals(vertex.data) &&
                id == vertex.id;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result =  result * prime + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                '}';
    }
}
