package datastructures.graphs.directed.sssp;

import java.util.*;

public class WeightedGraph {
    private int nodes;
    private Map<Integer, List<Edge>> graph;

    public WeightedGraph(int nodes) {
        this.nodes = nodes;
        this.graph = new HashMap<>(this.nodes);
    }

    public Set<Integer> getNodes() {
        return graph.keySet();
    }

    public void addEdge(int from, int to, int cost) {
        Edge edge = new Edge(from, to, cost);
        if (this.graph.containsKey(from)) {
            this.graph.get(from).add(edge);
        } else {
            List<Edge> edges = new ArrayList<>();
            edges.add(edge);
            this.graph.put(from, edges);
        }
    }

    public List<Edge> getOutwardEdgesOf(int from) {
        return this.graph.getOrDefault(from, new ArrayList<>());
    }

    public void print() {
        this.graph.forEach((key, value) -> {
            System.out.println("Edges from " + key + " are: " + value);
        });
    }
}
