package datastructures.graphs.directed;

import datastructures.graphs.AdjacencyListBasedDirectedGraph;

import java.util.Arrays;

public class MotherVertexTest {
    public static void main(String[] args) {
        AdjacencyListBasedDirectedGraph graph = new AdjacencyListBasedDirectedGraph(7, true);
        graph.addEdge(5, 6);
        graph.addEdge(5, 2);
        graph.addEdge(6, 0);
        graph.addEdge(6, 4);
        graph.addEdge(4, 1);
        graph.addEdge(1, 3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        graph.print();

        System.out.println("================== SCCs ==============");
        int motherVertex = getMotherVertex(graph);
        System.out.println("Mother vertex is: " + motherVertex);

    }

    private static int getMotherVertex(AdjacencyListBasedDirectedGraph graph) {
        int nodes = graph.getVertices();
        boolean[] visited = new boolean[nodes];
        Arrays.fill(visited, false);
        int lastVisitedNode = -1;
        for (int node : graph.getNodes()) {
            if (!visited[node]) {
                performDfs(node, graph, visited);
                lastVisitedNode = node;
            }
        }

        Arrays.fill(visited, false);
        performDfs(lastVisitedNode, graph, visited);
        for (boolean ele : visited) {
            if (!ele) {
                System.out.println("No mother vertex exists!");
                return -1;
            }
        }
        return lastVisitedNode;
    }

    private static void performDfs(int node, AdjacencyListBasedDirectedGraph graph, boolean[] visited) {
        visited[node] = true;
        for (int at : graph.getNeighboursOf(node)) {
            if (!visited[at]) {
                performDfs(at, graph, visited);
            }
        }
    }
}
