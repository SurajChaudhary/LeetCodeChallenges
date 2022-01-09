package datastructures.graphs.undirected;

import datastructures.graphs.AdjacencyListBasedUndirectedGraph;

import java.util.Arrays;

public class CycleDetectionByColorsTest {
    private static final int BLUE = 0;
    private static final int GREEN = 1;
    private static final int RED = 2;

    public static void main(String[] args) {
        int nodes = 4;
        AdjacencyListBasedUndirectedGraph graph = new AdjacencyListBasedUndirectedGraph(nodes);
        graph.addEdge(1, 2);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);

        graph.print();
        System.out.println("=============================================");
        boolean isCyclic = isCyclic(graph, nodes);
        System.out.println("The graph is " + (isCyclic ? "cyclic!" : "not cyclic!"));
    }

    private static boolean isCyclic(AdjacencyListBasedUndirectedGraph graph, int nodes) {
        int[] colors = new int[nodes];
        Arrays.fill(colors, BLUE);
        for (int node : graph.getNodes()) {
            colors[node] = GREEN;
            for (int neighbour : graph.getNeighboursOf(node)) {
                if (isCyclicUtil(neighbour, colors, graph)) {
                    return true;
                }
            }
            colors[node] = BLUE;
        }
        return false;
    }

    private static boolean isCyclicUtil(int node, int[] colors, AdjacencyListBasedUndirectedGraph graph) {
        if (colors[node] == RED) {
            return true;
        }
        colors[node] = GREEN;
        for (int neighbour : graph.getNeighboursOf(node)) {
            if (colors[neighbour] == GREEN) {
                colors[neighbour] = RED;
            } else {
                if (isCyclicUtil(neighbour, colors, graph)) {
                    return true;
                }
            }

        }
        return false;
    }
}
