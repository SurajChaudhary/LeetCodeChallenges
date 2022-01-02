package datastructures.graphs.undirected;

import datastructures.graphs.WeightedEdge;
import datastructures.graphs.WeightedUndirectedGraph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumCostToConnectAllCitiesTest {
    public static void main(String[] args) {
        // Input 1
        int V = 5;
        int matrix[][] = {
                {0, 1, 2, 3, 4},
                {1, 0, 5, 0, 7},
                {2, 5, 0, 6, 0},
                {3, 0, 6, 0, 0},
                {4, 7, 0, 0, 0}
        };

        WeightedUndirectedGraph graph = getAdjacencyListFrom(matrix, V);
        graph.print();

        getMinimumCostToConnectCities(graph, V);

        // Input 2
        V = 6;
        int matrix2[][] = {
                {0, 1, 1, 100, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {100, 0, 0, 0, 2, 2},
                {0, 0, 0, 2, 0, 2},
                {0, 0, 0, 2, 2, 0}
        };

        graph = getAdjacencyListFrom(matrix2, V);
        graph.print();

        getMinimumCostToConnectCities(graph, V);

    }

    private static void getMinimumCostToConnectCities(WeightedUndirectedGraph graph, int V) {
        int maxEdges = V - 1;
        int mstCost = 0;
        int edgeCount = 0;
        WeightedEdge[] result = new WeightedEdge[maxEdges];
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>(new Comparator<WeightedEdge>() {
            @Override
            public int compare(WeightedEdge e1, WeightedEdge e2) {
                return e1.getCost() - e2.getCost();
            }
        });

        int start = 0;
        boolean[] visited = new boolean[V];
        addEdges(start, pq, visited, graph);

        while (!pq.isEmpty() && edgeCount <= maxEdges) {
            WeightedEdge edge = pq.poll();
            if (visited[edge.getTo()]) continue;
            result[edgeCount++] = edge;
            mstCost += edge.getCost();
            addEdges(edge.getTo(), pq, visited, graph);
        }

        if (edgeCount != maxEdges) {
            System.out.println("Minimum Cost to connect All cities Failed, as NO MST exists!!!!");
        }

        System.out.println("Minimum Cost to connect all cities is: {" + mstCost + "}.");
        System.out.println(Arrays.toString(result));
    }

    private static void addEdges(int from, PriorityQueue<WeightedEdge> pq, boolean[] visited, WeightedUndirectedGraph graph) {
        visited[from] = true;
        for (WeightedEdge edge : graph.getNeighborsOf(from)) {
            int to = edge.getTo();
            if (visited[to]) continue;
            pq.offer(edge);
        }
    }

    private static WeightedUndirectedGraph getAdjacencyListFrom(int[][] matrix, int V) {
        WeightedUndirectedGraph graph = new WeightedUndirectedGraph(V);
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != 0) {
                    graph.addEdge(row, col, matrix[row][col]);
                }
            }
        }
        return graph;
    }
}
