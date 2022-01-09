package datastructures.graphs.directed;

import datastructures.graphs.AdjacencyListBasedDirectedGraph;

public class TransposeOfGraphTest {
    public static void main(String[] args) {
        AdjacencyListBasedDirectedGraph graph = new AdjacencyListBasedDirectedGraph(5, true);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(2, 1);
        graph.addEdge(3, 4);
        graph.print();

        System.out.println("================== Transpose ==============");
        AdjacencyListBasedDirectedGraph transposedGraph = transpose(graph);
        transposedGraph.print();

    }

    private static AdjacencyListBasedDirectedGraph transpose(AdjacencyListBasedDirectedGraph graph) {
        AdjacencyListBasedDirectedGraph transposedGraph = new AdjacencyListBasedDirectedGraph(graph.getVertices(), true);
        for (int node : graph.getNodes()) {
            for (int neighbour : graph.getNeighboursOf(node)) {
                transposedGraph.addEdge(neighbour, node);
            }
        }
        return transposedGraph;
    }
}
