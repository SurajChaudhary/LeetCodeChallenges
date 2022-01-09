package datastructures.graphs;

import java.util.*;
import java.util.stream.Collectors;

public class AdjacencyListBasedUndirectedGraph {
    private final int vertices;
    private final Map<Integer, List<Integer>> graph;

    public AdjacencyListBasedUndirectedGraph(int vertices) {
        if (vertices <= 0) {
            throw new IllegalArgumentException(vertices + " is not a valid number of vertices!!!");
        }
        this.vertices = vertices;
        this.graph = new LinkedHashMap<>(this.vertices);

        for (int i = 0; i < this.vertices; i++) {
            this.graph.put(i, new ArrayList<>());
        }
    }

    public Set<Integer> getNodes() {
        return this.graph.keySet();
    }

    public int getVertices() {
        return this.vertices;
    }

    public List<Integer> getNeighboursOf(int vertex) {
        return this.graph.get(vertex);
    }

    public void addEdge(int source, int destination) {
        if (source < 0 || destination < 0 || source >= this.vertices || destination >= this.vertices) {
            throw new IllegalArgumentException("Not a valid number of argument!!!");
        }
        this.graph.get(source).add(destination);
        this.graph.get(destination).add(source);
    }

    public void print() {
        this.graph.forEach((key, value) -> System.out.println(key + " -> " + value));
    }

    public int[] getDegrees() {
        int[] degrees = new int[this.getVertices()];
        this.graph.forEach((from, neighbors) -> {
            degrees[from] = neighbors.size();
        });
        return degrees;
    }

    public void deleteNode(int node, int[] degrees) {
        for(int to : this.graph.get(node)) {
            degrees[to]--;
            List<Integer> neighbors = this.graph.get(to);
            List<Integer> collect = neighbors.stream().filter(adj -> adj != node).collect(Collectors.toList());
            this.graph.put(to, collect);
        }
        this.graph.remove(node);
        degrees[node] = -1;
    }
}
