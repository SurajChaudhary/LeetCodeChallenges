package datastructures.graphs.directed;

import datastructures.graphs.AdjacencyListBasedDirectedGraph;

import java.util.Arrays;
import java.util.Set;
import java.util.Stack;

public class KosarajuAlgorithmTest {
    public static void main(String[] args) {
        AdjacencyListBasedDirectedGraph graph = new AdjacencyListBasedDirectedGraph(9, true);
        graph.addEdge(3, 0);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        graph.addEdge(7, 6);
        graph.addEdge(7, 8);

        graph.print();

        System.out.println("================== SCCs ==============");
        printSCC(graph);

    }

    private static void printSCC(AdjacencyListBasedDirectedGraph graph) {
        int sccCount = 0;
        Stack<Integer> stk = new Stack<>();
        boolean[] visited = new boolean[graph.getVertices()];

        //Step:1 - Populated Stack by performing DFS in topological order
        for (int node : graph.getNodes()) {
            if (!visited[node]) {
                populateStack(node, visited, stk, graph);
            }
        }

        //Step:2 - Transpose the graph
        AdjacencyListBasedDirectedGraph transposedGraph = transpose(graph);

        Arrays.fill(visited, false);

        //Step:3 - Process all vertices in order defined in stack
        while (!stk.isEmpty()) {
            int topNode = stk.pop();
            if (!visited[topNode]) {
                ++sccCount;
                System.out.print("{ ");
                dfsToPrint(topNode, visited, transposedGraph);
                System.out.print("}");
                System.out.println(" ");
            }
        }

        System.out.println("Total SCCs found are : " + sccCount);
    }

    private static void dfsToPrint(int node, boolean[] visited, AdjacencyListBasedDirectedGraph graph) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbour : graph.getNeighboursOf(node)) {
            if (!visited[neighbour]) {
                dfsToPrint(neighbour, visited, graph);
            }
        }
    }

    private static void populateStack(int node, boolean[] visited, Stack<Integer> stk, AdjacencyListBasedDirectedGraph graph) {
        visited[node] = true;

        for (int neighbour : graph.getNeighboursOf(node)) {
            if (!visited[neighbour]) {
                populateStack(neighbour, visited, stk, graph);
            }
        }
        stk.push(node);
    }

    private static AdjacencyListBasedDirectedGraph transpose(AdjacencyListBasedDirectedGraph graph) {
        AdjacencyListBasedDirectedGraph transposedGraph = new AdjacencyListBasedDirectedGraph(graph.getVertices(), true);
        Set<Integer> nodes = graph.getNodes();
        for (int node : nodes) {
            for (int neighbour : graph.getNeighboursOf(node)) {
                transposedGraph.addEdge(neighbour, node);
            }
        }
        return transposedGraph;

    }
}
