package datastructures.graphs.undirected;

public class GraphTest {
    public static void main(String[] args) {
        AdjacencyMatrixBasedGraph graph = new AdjacencyMatrixBasedGraph(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        graph.print();
        System.out.println("=======================================================");
        AdjacencyListBasedGraph listBasedGraph = new AdjacencyListBasedGraph(5);

        listBasedGraph.addEdge(1, 0);
        listBasedGraph.addEdge(0, 2);
        listBasedGraph.addEdge(2, 1);
        listBasedGraph.addEdge(0, 3);
        listBasedGraph.addEdge(3, 4);

        listBasedGraph.print();
    }
}
