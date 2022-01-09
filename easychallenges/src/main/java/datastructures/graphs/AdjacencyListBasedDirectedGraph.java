package datastructures.graphs;

import java.util.*;

public class AdjacencyListBasedDirectedGraph {
    private final int vertices;
    private final boolean isZeroBased;
    private final Map<Integer, List<Integer>> graph;

    public AdjacencyListBasedDirectedGraph(int vertices, boolean isZeroBased) {
        if (vertices <= 0) {
            throw new IllegalArgumentException(vertices + " is not a valid number of vertices!!!");
        }
        this.graph = new LinkedHashMap<>();
        this.isZeroBased = isZeroBased;
        if (isZeroBased) {
            this.vertices = vertices;
            for (int i = 0; i < this.vertices; i++) {
                this.graph.put(i, new ArrayList<>());
            }
        } else {
            this.vertices = vertices;
            for (int i = 1; i <= vertices; i++) {
                this.graph.put(i, new ArrayList<>());
            }
        }
    }

    public int[] inDegreeOfNodes() {
        if (this.graph == null || this.graph.size() == 0) {
            return null;
        }
        int[] inDegrees = new int[this.getVertices()+1];
        Arrays.fill(inDegrees, 0);
        for (int node : this.getNodes()) {
            for (int to : this.getNeighboursOf(node)) {
                inDegrees[to]++;
            }
        }
        return inDegrees;
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
        if (isZeroBased) {
            if (source < 0 || destination < 0 || source >= this.vertices || destination >= this.vertices) {
                throw new IllegalArgumentException("Not a valid number of argument!!!");
            }
        } else {
            if (source < 1 || destination < 1 || source > this.vertices || destination > this.vertices) {
                throw new IllegalArgumentException("Not a valid number of argument!!!");
            }
        }
        this.graph.get(source).add(destination);
        this.graph.putIfAbsent(destination, new ArrayList<>());
    }

    public void print() {
        this.graph.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
