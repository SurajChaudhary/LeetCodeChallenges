package datastructures.graphs.directed.dag;

import datastructures.graphs.directed.DirectedGraph;

import java.util.Arrays;
import java.util.Stack;

public class TopologicalSortTest {
    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(6);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        graph.print();

        int[] topologicallySortedNodes = sortTopologically(graph);
        System.out.println("Topological order of nodes is: ");
        System.out.print(Arrays.toString(topologicallySortedNodes));

    }

    /**
     * Time Complexity: O(V+E).
     * The above algorithm is simply DFS with an extra stack. So time complexity is the same as DFS which is.
     * Auxiliary space: O(V).
     * The extra space is needed for the stack.
     *
     * @param graph
     * @return
     */
    private static int[] sortTopologically(DirectedGraph graph) {
        boolean[] visited = new boolean[graph.nodeCount()];
        Stack<Integer> stk = new Stack<>();

        for (int node : graph.nodes()) {
            if (!visited[node]) {
                sort(node, visited, stk, graph);
            }
        }
        int[] result = new int[graph.nodeCount()];
        int index = 0;
        while (!stk.isEmpty()) {
            result[index++] = stk.pop();
        }
        return result;
    }

    private static void sort(int from, boolean[] visited, Stack<Integer> stk, DirectedGraph graph) {
        visited[from] = true;
        for (int neighbor : graph.getNeighborsOf(from)) {
            if (!visited[neighbor]) {
                sort(neighbor, visited, stk, graph);
            }
        }
        stk.push(from);
    }
}
