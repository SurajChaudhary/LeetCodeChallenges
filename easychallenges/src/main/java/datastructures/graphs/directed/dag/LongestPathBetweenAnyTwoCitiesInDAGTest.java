package datastructures.graphs.directed.dag;

import datastructures.graphs.directed.DirectedWeightedGraph;
import datastructures.graphs.directed.WeightedEdge;

import java.util.*;

public class LongestPathBetweenAnyTwoCitiesInDAGTest {
    public static void main(String[] args) {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(7);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 6, 2);
        graph.addEdge(6, 5, 5);
        graph.addEdge(6, 4, 6);
        graph.addEdge(2, 3, 4);
        graph.print();

        System.out.println("Longest path is: " + getLongestPath(graph));
    }
    private static String getLongestPath(DirectedWeightedGraph graph) {
        // Get topological order
        int[] topo = getTopologicalOrder(graph);
        //Create distance array
        double[] dist = new double[graph.nodeCount()];
        // Initialize distance array wit negative infinity
        Arrays.fill(dist, Double.NEGATIVE_INFINITY);
        int from = topo[0];
        // Initialize the starting node's distance as 0
        dist[from] = 0;
        int dest = -1;
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < topo.length; i++) {
            for (WeightedEdge edge : graph.getNeighborsOf(topo[i])) {
                // Checking and updating the distance
                double newDistance = (dist[topo[i]] + edge.getCost());
                int to = edge.getTo();
                if (newDistance > dist[to]) {
                    dist[to] = newDistance;
                    if (newDistance > max) {
                        max = Math.max(max, newDistance);
                        dest = to;
                    }
                }
            }
        }
        return "between " + from + " and " + dest + " which has cost: " + max;
    }

    private static int[] getTopologicalOrder(DirectedWeightedGraph graph) {
        int[] degree = graph.inDegreeOfNodes();
        Queue<Integer> q = new LinkedList<>();
        for (int node : graph.nodes()) {
            if (degree[node] == 0) {
                q.add(node);
            }
        }
        List<Integer> list = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            list.add(node);
            for (WeightedEdge edge : graph.getNeighborsOf(node)) {
                degree[edge.getTo()]--;
                if (degree[edge.getTo()] == 0) {
                    q.add(edge.getTo());
                }
            }
        }
        //Collections.reverse(list);
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            result[i] = list.get(i);

        return result;
    }


}