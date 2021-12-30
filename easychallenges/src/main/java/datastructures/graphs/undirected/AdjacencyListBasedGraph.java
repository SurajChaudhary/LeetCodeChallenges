package datastructures.graphs.undirected;

import java.util.*;

public class AdjacencyListBasedGraph {
    private int vertices;
    private Map<Integer, List<Integer>> graph;

    public AdjacencyListBasedGraph(int vertices) {
        if (vertices <= 0) {
            throw new IllegalArgumentException(vertices + " is not a valid number of vertices!!!");
        }
        this.vertices = vertices;
        this.graph = new LinkedHashMap<>(this.vertices);

        for (int i = 0; i < this.vertices; i++) {
            this.graph.put(i, new ArrayList<>());
        }
    }

    Set<Integer> getNodes() {
        return this.graph.keySet();
    }

    int getVertices() {
        return this.vertices;
    }

    List<Integer> getNeighboursOf(int vertex) {
        return this.graph.get(vertex);
    }

    void addEdge(int source, int destination) {
        if (source < 0 || destination < 0 || source >= this.vertices || destination >= this.vertices) {
            throw new IllegalArgumentException("Not a valid number of argument!!!");
        }
        this.graph.get(source).add(destination);
        this.graph.get(destination).add(source);
    }

    void print() {
        this.graph.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
