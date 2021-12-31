package datastructures.graphs.directed.sssp;

import java.util.*;

public class StringWeightedGraph {
    private int nodes;
    private Map<String, List<StringEdge>> graph;

    public StringWeightedGraph(int nodes) {
        this.nodes = nodes;
        this.graph = new HashMap<>(this.nodes);
    }

    public void addEdge(String from, String to, int cost) {
        StringEdge edge = new StringEdge(from, to, cost);
        if (this.graph.containsKey(from)) {
            this.graph.get(from).add(edge);
        } else {
            List<StringEdge> edges = new ArrayList<>();
            edges.add(edge);
            this.graph.put(from, edges);
        }
    }

    public List<StringEdge> getOutwardEdgesOf(String from) {
        return this.graph.getOrDefault(from, new ArrayList<>());
    }

    public Set<String> getNodes() {
        return this.graph.keySet();
    }

    public void print() {
        this.graph.forEach((key, value) -> {
            System.out.println("Edges from " + key + " are: " + value);
        });
    }
}
