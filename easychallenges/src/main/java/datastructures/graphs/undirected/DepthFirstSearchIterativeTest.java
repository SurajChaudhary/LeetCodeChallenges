package datastructures.graphs.undirected;

import datastructures.graphs.AdjacencyListBasedUndirectedGraph;

import java.util.Stack;

public class DepthFirstSearchIterativeTest {
    public static void main(String[] args) {
        int vertices = 5;
        AdjacencyListBasedUndirectedGraph listBasedGraph = new AdjacencyListBasedUndirectedGraph(vertices);

        listBasedGraph.addEdge(1, 0);
        listBasedGraph.addEdge(0, 2);
        listBasedGraph.addEdge(2, 1);
        listBasedGraph.addEdge(0, 3);
        listBasedGraph.addEdge(3, 4);

        listBasedGraph.print();
        System.out.println("=======================================================");
        depthFirstTraversal(listBasedGraph, vertices);
    }

    private static void depthFirstTraversal(AdjacencyListBasedUndirectedGraph g, int V) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int node : g.getNodes()) {
            if (!visited[node]) {
                dfsUtil(node, visited, stack, g);
            }
        }

        System.out.print("{ ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println(" }");
    }

    private static void dfsUtil(int node, boolean[] visited, Stack<Integer> stack, AdjacencyListBasedUndirectedGraph g) {
        visited[node] = true;
        stack.push(node);
        for (int neighbour : g.getNeighboursOf(node)) {
            if (!visited[neighbour]) {
                dfsUtil(neighbour, visited, stack, g);
            }
        }

    }
}
