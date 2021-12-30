package datastructures.graphs.directed.sssp;

import java.util.Arrays;

public class BellmanFordAlgorithmTest {
    public static void main(String[] args) {
        int nodes = 8;
        WeightedGraph graph = new WeightedGraph(nodes);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 4, 1);
        graph.addEdge(4, 3, -3);
        graph.addEdge(3, 2, 1);
        graph.addEdge(1, 5, 4);
        graph.addEdge(1, 6, 4);
        graph.addEdge(5, 6, 5);
        graph.addEdge(6, 7, 4);
        graph.addEdge(5, 7, 3);

        graph.print();

        System.out.println("===================== Bellman Ford =======================");

        int start = 0;
        double[] d = bellmanFord(graph, nodes, start);

        for (int i = 0; i < nodes; i++)
            System.out.printf("The cost to get from node %d to %d is %.2f\n", start, i, d[i]);
    }

    private static double[] bellmanFord(WeightedGraph graph, int nodes, int start) {
        double[] distance = new double[nodes];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[start] = 0;
        // Relax nodes for V-1 times
        for (int iteration = 0; iteration < nodes - 1; iteration++) {
            // Process edges from each node
            for (int node : graph.getNodes()) {
                // Relax outward edges of the node
                for (Edge edge : graph.getOutwardEdgesOf(node)) {
                    double newDistance = (distance[node] + edge.getCost());
                    if (newDistance < distance[edge.getTo()]) {
                        distance[edge.getTo()] = newDistance;
                    }
                }
            }
        }

        for (int iteration = 0; iteration < nodes - 1; iteration++) {
            // Process edges from each node
            for (int node : graph.getNodes()) {
                // Relax outward edges of the node
                for (Edge edge : graph.getOutwardEdgesOf(node)) {
                    double newDistance = (distance[node] + edge.getCost());
                    if (newDistance < distance[edge.getTo()]) {
                        distance[edge.getTo()] = Double.NEGATIVE_INFINITY;
                    }
                }
            }
        }
        return distance;
    }
}
