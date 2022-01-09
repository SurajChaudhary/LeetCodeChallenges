package datastructures.graphs.undirected;


import datastructures.graphs.AdjacencyListBasedUndirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BridgeTest {

    private static AdjacencyListBasedUndirectedGraph getGraph(int nodes) {
        AdjacencyListBasedUndirectedGraph graph = new AdjacencyListBasedUndirectedGraph(nodes);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 5);
        return graph;
    }

    public static void main(String[] args) {
        int nodes = 9;
        AdjacencyListBasedUndirectedGraph graph = getGraph(nodes);

        boolean[] visited = new boolean[nodes];
        Arrays.fill(visited, false);
        int[] lowLink = new int[nodes];
        Arrays.fill(lowLink, -1);
        List<String> bridges = new ArrayList<>();

        for (int from : graph.getNodes()) {
            if (!visited[from]) {
                dfs(from, -1, visited, lowLink, bridges, graph);
            }
        }
        System.out.println(Arrays.toString(lowLink));
        System.out.println(bridges);
    }

    private static void dfs(int from, int parent, boolean[] visited, int[] lowLink, List<String> bridges, AdjacencyListBasedUndirectedGraph graph) {
        visited[from] = true;
        lowLink[from] = from;

        for (int to : graph.getNeighboursOf(from)) {
            if (to == parent) continue;
            if (!visited[to]) {
                dfs(to, from, visited, lowLink, bridges, graph);
                lowLink[from] = Math.min(lowLink[from], lowLink[to]);
                if (from < lowLink[to]) {
                    bridges.add(from + "--" + to);
                }
            } else {
                lowLink[from] = Math.min(lowLink[from], to);
            }
        }
    }
}
