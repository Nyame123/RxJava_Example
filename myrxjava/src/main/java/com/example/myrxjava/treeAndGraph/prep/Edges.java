package com.example.myrxjava.treeAndGraph.prep;

import java.util.Objects;

/**
 * Edges representational model
 **/
public class Edges<T> {
    Vertex<T> vertexA;
    Vertex<T> vertexB;
    int weight;
    boolean isDirected;

    Edges(Vertex<T> a, Vertex<T> b, int data){
        this.vertexA = a;
        this.vertexB = b;
        this.weight = data;
    }

    public Edges(Vertex<T> vertexA, Vertex<T> vertexB, int weight, boolean isDirected) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.weight = weight;
        this.isDirected = isDirected;
    }

    public Vertex<T> getVertexA() {
        return vertexA;
    }

    public void setVertexA(Vertex<T> vertexA) {
        this.vertexA = vertexA;
    }

    public Vertex<T> getVertexB() {
        return vertexB;
    }

    public void setVertexB(Vertex<T> vertexB) {
        this.vertexB = vertexB;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex<T> getOtherVertex(Vertex<T> vertex){
        return this.vertexA.equals(vertex)? this.vertexB : this.vertexA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edges<?> edges = (Edges<?>) o;
        return vertexA.equals(edges.vertexA) &&
                vertexB.equals(edges.vertexB) &&
                weight == (edges.weight) &&
                isDirected == edges.isDirected;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((this.vertexA == null)? 0 : this.vertexA.hashCode());
        result = prime * result + ((this.vertexB == null)? 0 : this.vertexB.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Edges{" +
                "vertexA=" + vertexA +
                ", vertexB=" + vertexB +
                ", data=" + weight +
                '}';
    }
}
