package datastructures.graphs.directed;


import datastructures.graphs.AdjacencyListBasedDirectedGraph;

public class PathBetweenTwoVerticesTest {
    public static void main(String[] args) {
        AdjacencyListBasedDirectedGraph graph = new AdjacencyListBasedDirectedGraph(4, true);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        int source = 1;
        int dest = 3;

        boolean pathPresent = isPathBetweenVerticesPresent(graph, source, dest, 4);
        System.out.println("There is " + (pathPresent ? "a path between vertices!" : "no path between vertices!"));
    }

    private static boolean isPathBetweenVerticesPresent(AdjacencyListBasedDirectedGraph graph, int source, int dest, int V) {
        boolean[] visited = new boolean[V];
        return pathUtil(source, dest, visited, graph);
    }

    private static boolean pathUtil(int node, int dest, boolean[] visited, AdjacencyListBasedDirectedGraph graph) {
        visited[node] = true;
        if (node == dest) {
            return true;
        }

        for (int a : graph.getNeighboursOf(node)) {
            if (!visited[a]) {
                if (pathUtil(a, dest, visited, graph)) {
                    return true;
                }
            }
        }
        return false;
    }
}
