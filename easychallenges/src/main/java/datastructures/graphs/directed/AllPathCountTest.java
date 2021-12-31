package datastructures.graphs.directed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllPathCountTest {
    static int count = 0;
    static int index = 0;

    public static void main(String[] args) {
        AdjacencyListBasedGraph graph = new AdjacencyListBasedGraph(5, false);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);

        graph.addEdge(4, 3);

        graph.print();
        System.out.println("=============================================");
        int paths = getPaths(graph, 5, 1, 5);
        System.out.println("Total number of paths are: " + paths);
    }

    private static int getPaths(AdjacencyListBasedGraph graph, int nodes, int source, int dest) {
        boolean[] visited = new boolean[nodes + 1];
        Arrays.fill(visited, false);
        List<Integer> path = new ArrayList<>();
        dfs(graph, source, dest, visited, path);
        return count;
    }

    private static void dfs(AdjacencyListBasedGraph graph, int source, int dest, boolean[] visited, List<Integer> path) {
        path.add(index, source);
        visited[source] = true;
        if (source == dest) {
            count++;
            System.out.println(path);
        } else {
            for (int node : graph.getNeighboursOf(source)) {
                if (!visited[node]) {
                    index++;
                    dfs(graph, node, dest, visited, path);
                    visited[node] = false;
                    path.remove(index--);
                }
            }
        }
    }
}
