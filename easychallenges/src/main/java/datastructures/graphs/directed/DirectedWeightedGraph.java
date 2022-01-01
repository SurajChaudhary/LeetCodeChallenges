package datastructures.graphs.directed;

import java.util.*;

public class DirectedWeightedGraph {
    int V;
    Map<Integer, List<WeightedEdge>> adjList;

    public DirectedWeightedGraph(int V) {
        this.V = V;
        this.adjList = new HashMap<>();
    }

    public int nodeCount() {
        return this.V;
    }

    public Set<Integer> nodes() {
        return this.adjList.keySet();
    }

    public List<WeightedEdge> edges() {
        List<WeightedEdge> edges = new ArrayList<>();
        for (List<WeightedEdge> list : this.adjList.values()) {
            edges.addAll(list);
        }
        return edges;
    }

    public void addEdge(int from, int to, int cost) {
        WeightedEdge edge = new WeightedEdge(from, to, cost);
        if (this.adjList.containsKey(from)) {
            this.adjList.get(from).add(edge);
        } else {
            List<WeightedEdge> neighbors = new ArrayList<>();
            neighbors.add(edge);
            this.adjList.put(from, neighbors);
        }
        this.adjList.putIfAbsent(to, new ArrayList<>());
    }

    public int[] inDegreeOfNodes() {
        if (this.adjList == null || this.adjList.size() == 0) {
            return null;
        }
        int[] inDegrees = new int[this.nodeCount()];
        Arrays.fill(inDegrees, 0);
        for (int node : this.nodes()) {
            for (WeightedEdge edge : this.getNeighborsOf(node)) {
                inDegrees[edge.getTo()]++;
            }
        }
        return inDegrees;
    }


    public void print() {
        System.out.println("================ Printing Graph ================");
        this.adjList.forEach((from, neighbors) -> System.out.println("Adjacent nodes of " + from + " are: " + neighbors));
        System.out.println("================ Printing Complete ================");
    }

    public List<WeightedEdge> getNeighborsOf(int from) {
        return this.adjList.getOrDefault(from, new ArrayList<>());
    }
}

