package datastructures.graphs.undirected;

import datastructures.graphs.directed.DirectedWeightedGraph;
import datastructures.graphs.directed.WeightedEdge;

public class LongestPathBetweenAnyTwoCitiesInUndirectedTest {
    public static void main(String[] args) {

        DirectedWeightedGraph graph = new DirectedWeightedGraph(6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 1, 3);
        graph.addEdge(2, 6, 2);
        graph.addEdge(6, 2, 2);
        graph.addEdge(6, 5, 5);
        graph.addEdge(5, 6, 5);
        graph.addEdge(6, 4, 6);
        graph.addEdge(4, 6, 6);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 2, 4);
        graph.print();

        System.out.println("Longest path is: " + getLongestPath(graph));
    }

    private static String getLongestPath(DirectedWeightedGraph graph) {
        int max = Integer.MIN_VALUE;
        String result = "";
        for (int start : graph.nodes()) {
            boolean[] visited = new boolean[graph.nodeCount() + 1];
            int[] dist = new int[graph.nodeCount() + 1];
            dfsUtil(start, visited, graph, 0, dist);
            for (int i = 0; i < dist.length; i++) {
                if (dist[i] > max) {
                    max = dist[i];
                    result = "between " + start + " and " + i + " which has cost: " + max;
                }
            }
        }
        return result;
    }

    private static void dfsUtil(int dest, boolean[] visited, DirectedWeightedGraph graph, int cost, int[] dist) {
        visited[dest] = true;
        dist[dest] = cost;
        for (WeightedEdge outEdge : graph.getNeighborsOf(dest)) {
            if (!visited[outEdge.getTo()]) {
                dfsUtil(outEdge.getTo(), visited, graph, cost + outEdge.getCost(), dist);
            }
        }
    }
}