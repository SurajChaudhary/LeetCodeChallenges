package datastructures.graphs.directed;


import datastructures.graphs.AdjacencyListBasedDirectedGraph;

import java.util.Stack;

public class TopologicalSortTest {
    public static void main(String[] args) {
        int vertices = 5;
        AdjacencyListBasedDirectedGraph listBasedGraph = new AdjacencyListBasedDirectedGraph(vertices, true);

        listBasedGraph.addEdge(1, 0);
        listBasedGraph.addEdge(0, 2);
        listBasedGraph.addEdge(1, 2);
        listBasedGraph.addEdge(0, 3);
        listBasedGraph.addEdge(3, 4);

        listBasedGraph.print();
        System.out.println("=======================================================");
        topologicalSort(listBasedGraph, vertices);
    }

    private static void topologicalSort(AdjacencyListBasedDirectedGraph g, int V) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int node : g.getNodes()) {
            if (!visited[node]) {
                sort(node, visited, stack, g);
            }
        }

        System.out.print("{ ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println(" }");
    }

    private static void sort(int node, boolean[] visited, Stack<Integer> stack, AdjacencyListBasedDirectedGraph g) {
        visited[node] = true;

        for (int neighbour : g.getNeighboursOf(node)) {
            if (!visited[neighbour]) {
                sort(neighbour, visited, stack, g);
            }
        }
        stack.push(node);

    }
}
