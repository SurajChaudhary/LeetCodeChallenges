package datastructures.graphs.directed;

import datastructures.graphs.AdjacencyListBasedDirectedGraph;

import java.util.Arrays;

public class TransitiveClosureByDFSTest {
    public static void main(String[] args) {
        AdjacencyListBasedDirectedGraph graph = new AdjacencyListBasedDirectedGraph(5, true);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(2,1);
        graph.addEdge(3,2);
        graph.addEdge(3,4);
        graph.addEdge(4,2);

        graph.print();

        printTransitiveClosureOf(graph);
    }

    private static void printTransitiveClosureOf(AdjacencyListBasedDirectedGraph graph) {
        int V = graph.getVertices();
        int[][] reachabilityMatrix = new int[V][V];
        for (int[] row : reachabilityMatrix) {
            Arrays.fill(row, 0);
        }

        for (int node : graph.getNodes()) {
            boolean[] visited = new boolean[V];
            Arrays.fill(visited, false);
            dfsUtil(node, node, visited, graph, reachabilityMatrix);
        }
        for (int[] row : reachabilityMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void dfsUtil(int node, int source, boolean[] visited, AdjacencyListBasedDirectedGraph graph, int[][] reachabilityMatrix) {
        visited[node] = true;
        reachabilityMatrix[source][node] = 1;

        for(int to : graph.getNeighboursOf(node)) {
            if(!visited[to]) {
                dfsUtil(to, source, visited, graph, reachabilityMatrix);
            }
        }
    }
}
