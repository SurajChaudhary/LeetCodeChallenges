package datastructures.graphs.undirected;

public class DepthFirstSearchTest {
    public static void main(String[] args) {

        AdjacencyListBasedGraph graph = new AdjacencyListBasedGraph(5);

        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        graph.print();
        System.out.println("=======================================================");
        depthFirstTraversal(graph);

    }

    private static void depthFirstTraversal(AdjacencyListBasedGraph graph) {
        int vertices = graph.getVertices();
        boolean[] visited = new boolean[vertices];
        for (int vertex : graph.getNodes()) {
            if (!visited[vertex]) {
                dfs(vertex, graph, visited);
            }
            System.out.println(" ");
        }
    }

    private static void dfs(int vertex, AdjacencyListBasedGraph graph, boolean[] visited) {
        System.out.print(vertex + " ");
        visited[vertex] = true;

        for (int neighbour : graph.getNeighboursOf(vertex)) {
            if (!visited[neighbour]) {
                dfs(neighbour, graph, visited);
            }
        }
    }
}
