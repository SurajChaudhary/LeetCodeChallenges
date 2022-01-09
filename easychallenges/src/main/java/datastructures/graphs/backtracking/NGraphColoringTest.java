package datastructures.graphs.backtracking;

import datastructures.graphs.AdjacencyListBasedUndirectedGraph;

import java.util.Arrays;

public class NGraphColoringTest {

    public static void main(String[] args) {
        AdjacencyListBasedUndirectedGraph graph = new AdjacencyListBasedUndirectedGraph(4);
        graph.addEdge(0, 3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        //graph.addEdge(3, 2);

        graph.print();

        int noOfColors = 3;
        boolean canColorGraph = canGraphBeColored(graph, noOfColors);
        System.out.println("Graph " + (canColorGraph ? " can be colored" : "can not be colored") + " with " + noOfColors + " colors.");

    }

    private static boolean canGraphBeColored(AdjacencyListBasedUndirectedGraph graph, int noOfColors) {
        int V = graph.getVertices();
        int[] colors = new int[V];
        Arrays.fill(colors, -1);

        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);

        for (int node : graph.getNodes()) {
            for (int color = 1; color <= noOfColors; color++) {
                if (dfsUtil(node, color, graph, colors, noOfColors, visited)) {
                    return true;
                }
                Arrays.fill(visited, false);
                Arrays.fill(colors, -1);
            }
        }
        System.out.println("Colors: " + Arrays.toString(colors));
        return false;
    }

    private static boolean dfsUtil(
            int from,
            int color,
            AdjacencyListBasedUndirectedGraph graph,
            int[] colors,
            int noOfColors,
            boolean[] visited) {
        colors[from] = color;
        visited[from] = true;
        boolean isColoringComplete = Arrays.stream(colors).allMatch(c -> c != -1);
        if (isColoringComplete) {
            return true;
        } else {
            for (int to : graph.getNeighboursOf(from)) {
                if (visited[to]) continue;
                for (int c = 1; c <= noOfColors; c++) {
                    if (safeToColor(to, c, from, colors, graph)) {
                        if (dfsUtil(to, c, graph, colors, noOfColors, visited)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

    }

    private static boolean safeToColor(int node, int color, int parent, int[] colors, AdjacencyListBasedUndirectedGraph graph) {
        if ((color == colors[parent])
                || (colors[node] == colors[parent])) {
            return false;
        }
        for (int to : graph.getNeighboursOf(node)) {
            if (colors[to] == color) {
                return false;
            }
        }
        return true;
    }
}
