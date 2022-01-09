package datastructures.graphs.directed;

import datastructures.graphs.AdjacencyListBasedDirectedGraph;

import java.util.*;

public class CycleDetectionUsingBFSUsingKahnAlgorithmTest {
    public static void main(String[] args) {
        AdjacencyListBasedDirectedGraph graph = new AdjacencyListBasedDirectedGraph(9, false);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 6);
        graph.addEdge(4, 5);
        graph.addEdge(6, 5);
        graph.addEdge(7, 2);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        graph.addEdge(9, 7);

        graph.print();
        System.out.println("=============================================");
        boolean isCyclic = isCyclic(graph, 9);
        System.out.println("The graph is " + (isCyclic ? "cyclic!" : "not cyclic!"));
    }

    private static boolean isCyclic(AdjacencyListBasedDirectedGraph graph, int V) {
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegreeOfNodes = graph.inDegreeOfNodes();

        List<Integer> list = new ArrayList<>();

        for (int index = 1; index < inDegreeOfNodes.length; index++) {
            if (inDegreeOfNodes[index] == 0) {
                queue.add(index);
            }
        }
        int nodesInTopologicalOrderCount = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            list.add(node);
            nodesInTopologicalOrderCount++;
            for (int to : graph.getNeighboursOf(node)) {
                inDegreeOfNodes[to]--;
                if (inDegreeOfNodes[to] == 0) {
                    queue.add(to);
                }
            }
        }

        if (nodesInTopologicalOrderCount != V) {
            System.out.println("Cycle exists in graph!!!");
            return true;
        }
        int[] result = new int[V];
        for (int i = 0; i < list.size(); i++)
            result[i] = list.get(i);
        System.out.println("Topological order: " + Arrays.toString(result));
        return false;
    }
}
