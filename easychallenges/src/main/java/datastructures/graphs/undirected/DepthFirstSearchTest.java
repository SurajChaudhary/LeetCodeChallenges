package datastructures.graphs.undirected;

import datastructures.graphs.AdjacencyListBasedUndirectedGraph;

public class DepthFirstSearchTest {
    public static void main(String[] args) {

        AdjacencyListBasedUndirectedGraph graph = new AdjacencyListBasedUndirectedGraph(5);

        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        graph.print();
        System.out.println("=======================================================");
        depthFirstTraversal(graph);

    }

    private static void depthFirstTraversal(AdjacencyListBasedUndirectedGraph graph) {
        int vertices = graph.getVertices();
        boolean[] visited = new boolean[vertices];
        for (int vertex : graph.getNodes()) {
            if (!visited[vertex]) {
                dfs(vertex, graph, visited);
            }
            System.out.println(" ");
        }
    }

    private static void dfs(int vertex, AdjacencyListBasedUndirectedGraph graph, boolean[] visited) {
        System.out.print(vertex + " ");
        visited[vertex] = true;

        for (int neighbour : graph.getNeighboursOf(vertex)) {
            if (!visited[neighbour]) {
                dfs(neighbour, graph, visited);
            }
        }
    }
}
