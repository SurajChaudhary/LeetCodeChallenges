package datastructures.graphs.undirected;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraphUsingBFSTest {
    static final int NO_COLOR = -1;
    static final int BLUE = 0;
    static final int GREEN = 1;
    public static void main(String[] args) {
        int V = 8;
        AdjacencyListBasedGraph listBasedGraph = new AdjacencyListBasedGraph(V);

        listBasedGraph.addEdge(0, 1);
        listBasedGraph.addEdge(1, 2);
        listBasedGraph.addEdge(1, 3);
        listBasedGraph.addEdge(2, 4);
        listBasedGraph.addEdge(3, 5);
        listBasedGraph.addEdge(4, 5);
        listBasedGraph.addEdge(5, 6);
        listBasedGraph.addEdge(6, 7);

        listBasedGraph.print();
        System.out.println("=======================================================");
        boolean isBipartite = isGraphBipartite(0,listBasedGraph, V);
        System.out.println("The graph " + (isBipartite ? "is a Bipartite graph!!!" : "is not a Bipartite graph!!!"));
    }

    private static boolean isGraphBipartite(int start, AdjacencyListBasedGraph graph, int V) {
        Queue<Integer> q = new LinkedList<>();
        int[] colors = new int[V];
        Arrays.fill(colors, NO_COLOR);
        q.add(start);
        colors[start] = BLUE;

        while(!q.isEmpty()) {
            int currNode = q.poll();
            for(int adj : graph.getNeighboursOf(currNode)) {
                if(colors[adj] == NO_COLOR) {
                    int color = getColor(currNode,colors);
                    colors[adj] = color;
                    q.add(adj);
                } else if(colors[adj] == colors[currNode]) {
                    return false;
                }else {
                    continue;
                }
            }

        }
        return true;
    }

    private static int getColor(int node, int[] colors) {
        if(colors[node] == BLUE) return GREEN;
        if(colors[node] == GREEN) return BLUE;
        return NO_COLOR;
    }
}
