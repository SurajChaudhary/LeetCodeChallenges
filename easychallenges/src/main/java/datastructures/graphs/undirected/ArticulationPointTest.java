package datastructures.graphs.undirected;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPointTest {

    static int outEdgeCount = 0;

    private static AdjacencyListBasedGraph getGraph(int nodes) {
        AdjacencyListBasedGraph graph = new AdjacencyListBasedGraph(nodes);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 5);
        return graph;
    }

    public static void main(String[] args) {
        int nodes = 9;
        AdjacencyListBasedGraph graph = getGraph(nodes);

        boolean[] visited = new boolean[nodes];
        Arrays.fill(visited, false);
        int[] lowLink = new int[nodes];
        Arrays.fill(lowLink, -1);

        boolean[] articulationPoint = new boolean[nodes];
        Arrays.fill(articulationPoint, false);

        List<String> bridges = new ArrayList<>();

        for (int from : graph.getNodes()) {
            if(!visited[from]) {
                outEdgeCount = 0;
                dfs(from, from, -1, visited, lowLink, bridges, articulationPoint, graph);
                articulationPoint[from] = (outEdgeCount > 1);
            }
        }
        System.out.println(Arrays.toString(lowLink));
        System.out.println(bridges);
        System.out.println(Arrays.toString(articulationPoint));
    }

    private static void dfs(int root, int from, int parent, boolean[] visited, int[] lowLink, List<String> bridges, boolean[] articulationPoint, AdjacencyListBasedGraph graph) {
        if(root == parent) outEdgeCount++;

        visited[from] = true;
        lowLink[from] = from;

        for (int to : graph.getNeighboursOf(from)) {
            if (to == parent) continue;
            if(!visited[to]) {
                dfs(root, to, from, visited, lowLink, bridges, articulationPoint, graph);
                lowLink[from] = Math.min(lowLink[from], lowLink[to]);
                if(from < lowLink[to]) {
                    bridges.add(from + "--" + to);
                }
                if(from <= lowLink[to]) {
                    articulationPoint[from] = true;
                }
            } else {
                lowLink[from] = Math.min(lowLink[from], to);
            }
        }
    }
}
