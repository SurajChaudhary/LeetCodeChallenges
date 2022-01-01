package datastructures.graphs.directed.dag;

import datastructures.graphs.directed.DirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TopologicalSortUsingDepartureTimeTest {
    static int TIME = 0;

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

    private static int[] sortTopologically(DirectedGraph graph) {
        int[] arrival = new int[graph.nodeCount()];
        int[] departure = new int[graph.nodeCount()];
        boolean[] visited = new boolean[graph.nodeCount()];
        List<Integer> list = new ArrayList<>();
        for (int node : graph.nodes()) {
            if (!visited[node]) {
                sort(node, visited, list, arrival, departure, graph);
            }
        }
        Collections.reverse(list);
        System.out.println(list);
        return getSortedArray(departure);
    }

    private static int[] getSortedArray(int[] departure) {
        int[] result = new int[departure.length];
        System.out.println(Arrays.toString(departure));
        int j = 0;
        do {
            int index = 0;
            int max = -1;
            for (int i = 0; i < departure.length; i++) {
                if (max < departure[i]) {
                    max = departure[i];
                    index = i;
                }
            }
            result[j++] = index;
            departure[index] = -1;
        } while (j < departure.length);
        return result;
    }

    private static void sort(int from, boolean[] visited, List<Integer> list, int[] arrival, int[] departure, DirectedGraph graph) {
        visited[from] = true;
        arrival[from] = TIME;
        TIME++;

        for (int to : graph.getNeighborsOf(from)) {
            if (!visited[to]) {
                sort(to, visited, list, arrival, departure, graph);
            }
        }
        list.add(from);
        departure[from] = TIME;
        TIME++;
    }
}
