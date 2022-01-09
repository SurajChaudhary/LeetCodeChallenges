package datastructures.graphs.undirected;

import datastructures.graphs.AdjacencyListBasedUndirectedGraph;

import java.util.Arrays;

public class BipartiteGraphUsingDFSTest {
    static final int NO_COLOR = -1;
    static final int BLUE = 0;
    static final int GREEN = 1;

    public static void main(String[] args) {
        int V = 8;
        AdjacencyListBasedUndirectedGraph listBasedGraph = new AdjacencyListBasedUndirectedGraph(V);

        listBasedGraph.addEdge(0, 1);
        listBasedGraph.addEdge(1, 2);
        listBasedGraph.addEdge(1, 3);
        listBasedGraph.addEdge(2, 4);
        listBasedGraph.addEdge(3, 5);
        listBasedGraph.addEdge(4, 5);
        listBasedGraph.addEdge(5, 6);
        listBasedGraph.addEdge(6, 7);

        listBasedGraph.print();
        System.out.println("=======================================================");
        boolean isBipartite = isGraphBipartite(0, listBasedGraph, V);
        System.out.println("The graph " + (isBipartite ? "is a Bipartite graph!!!" : "is not a Bipartite graph!!!"));
    }

    private static boolean isGraphBipartite(int start, AdjacencyListBasedUndirectedGraph graph, int V) {

        int[] colors = new int[V];
        Arrays.fill(colors, NO_COLOR);

        for (int node : graph.getNodes()) {
            if (colors[node] == NO_COLOR) {
                if (dfsUtil(node, colors, BLUE, graph)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfsUtil(int node, int[] colors, int color, AdjacencyListBasedUndirectedGraph graph) {
        colors[node] = color;
        for (int adj : graph.getNeighboursOf(node)) {
            if (colors[adj] == NO_COLOR) {
                int colorForChild = getColor(node, colors);
                if (dfsUtil(adj, colors, colorForChild, graph)) {
                    return true;
                }
            } else if (colors[adj] == colors[node]) {
                return false;
            } else {
                continue;
            }
        }

        return false;
    }

    private static int getColor(int node, int[] colors) {
        if (colors[node] == BLUE) return GREEN;
        if (colors[node] == GREEN) return BLUE;
        else return NO_COLOR;
    }
}
