package datastructures.graphs.undirected;

import datastructures.graphs.AdjacencyListBasedUndirectedGraph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchTest {
    public static void main(String[] args) {
        int V = 5;
        AdjacencyListBasedUndirectedGraph listBasedGraph = new AdjacencyListBasedUndirectedGraph(V);

        listBasedGraph.addEdge(1, 0);
        listBasedGraph.addEdge(0, 2);
        listBasedGraph.addEdge(2, 1);
        listBasedGraph.addEdge(0, 3);
        listBasedGraph.addEdge(3, 4);

        listBasedGraph.print();
        System.out.println("=======================================================");
        breadthFirstTraversal(listBasedGraph, V);
    }

    private static void breadthFirstTraversal(AdjacencyListBasedUndirectedGraph graph, int V) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        System.out.print("{ ");
        for (int node : graph.getNodes()) {
            if (!visited[node]) {
                bfsUtil(node, visited, q, graph);
            }
        }
        System.out.println(" }");

    }

    private static void bfsUtil(int node, boolean[] visited, Queue<Integer> q, AdjacencyListBasedUndirectedGraph graph) {
        visited[node] = true;
        q.add(node);

        while (!q.isEmpty()) {
            int curr = q.poll();
            System.out.print(curr + " ");
            for (int neighbour : graph.getNeighboursOf(curr)) {
                if (!visited[node]) {
                    q.add(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
    }
}
