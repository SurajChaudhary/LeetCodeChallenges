package datastructures.graphs.directed;

import java.util.*;

public class DirectedGraph {
    int V;
    Map<Integer, List<Integer>> adjList;

    public DirectedGraph(int V) {
        this.V = V;
        this.adjList = new HashMap<>();
    }

    public int nodeCount() {
        return this.V;
    }

    public Set<Integer> nodes() {
        return this.adjList.keySet();
    }

    public void addEdge(int from, int to) {
        if (this.adjList.containsKey(from)) {
            this.adjList.get(from).add(to);
        } else {
            List<Integer> neighbors = new ArrayList<>();
            neighbors.add(to);
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
            for (int to : this.getNeighborsOf(node)) {
                inDegrees[to]++;
            }
        }
        return inDegrees;
    }


    public void print() {
        System.out.println("================ Printing Graph ================");
        this.adjList.forEach((from, neighbors) -> System.out.println("Adjacent nodes of " + from + " are: " + neighbors));
        System.out.println("================ Printing Complete ================");
    }

    public List<Integer> getNeighborsOf(int from) {
        return this.adjList.getOrDefault(from, new ArrayList<>());
    }
}

