package datastructures.graphs.undirected;

import datastructures.graphs.AdjacencyListBasedUndirectedGraph;

import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectionUsingBFSTest {
    public static void main(String[] args) {
        AdjacencyListBasedUndirectedGraph graph = new AdjacencyListBasedUndirectedGraph(4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);

        graph.print();
        System.out.println("=============================================");
        boolean isCyclic = isCyclic(graph, 4);
        System.out.println("The graph is " + (isCyclic ? "cyclic!" : "not cyclic!"));
    }

    private static boolean isCyclic(AdjacencyListBasedUndirectedGraph graph, int V) {
        for (int node : graph.getNodes()) {
            if (cyclicUtil(node, graph, -1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean cyclicUtil(int node, AdjacencyListBasedUndirectedGraph graph, int parent) {
        Queue<Integer> nodeQueue = new LinkedList<>();
        Queue<Integer> parentQueue = new LinkedList<>();
        boolean[] visited = new boolean[graph.getVertices()];

        nodeQueue.offer(node);
        parentQueue.offer(parent);
        visited[node] = true;

        while (!nodeQueue.isEmpty()) {
            int from = nodeQueue.poll();
            int par = parentQueue.poll();

            for (int to : graph.getNeighboursOf(from)) {
                if (!visited[to]) {
                    nodeQueue.offer(to);
                    parentQueue.offer(from);
                } else {
                    if (to != par) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
