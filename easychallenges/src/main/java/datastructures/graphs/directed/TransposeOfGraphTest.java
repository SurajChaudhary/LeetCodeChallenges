package datastructures.graphs.directed;

import java.util.Set;

public class TransposeOfGraphTest {
    public static void main(String[] args) {
        AdjacencyListBasedGraph graph = new AdjacencyListBasedGraph(5, true);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(2, 1);
        graph.addEdge(3, 4);
        graph.print();

        System.out.println("================== Transpose ==============");
        AdjacencyListBasedGraph transposedGraph = transpose(graph);
        transposedGraph.print();

    }

    private static AdjacencyListBasedGraph transpose(AdjacencyListBasedGraph graph) {
        AdjacencyListBasedGraph transposedGraph = new AdjacencyListBasedGraph(graph.getVertices(), true);
        Set<Integer> nodes = graph.getNodes();
        for (int node : nodes) {
            for (int neighbour : graph.getNeighboursOf(node)) {
                transposedGraph.addEdge(neighbour, node);
            }
        }
        return transposedGraph;

    }
}