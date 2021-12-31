package datastructures.graphs.directed.dag;

import datastructures.graphs.directed.DirectedGraph;

import java.util.*;

public class TopoByBFSKahnAlgorithmTest {
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
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegreeOfNodes = graph.inDegreeOfNodes();
        int count = 0;
        List<Integer> list = new ArrayList<>();

        for (int index = 0; index < inDegreeOfNodes.length; index++) {
            if (inDegreeOfNodes[index] == 0) {
                queue.add(index);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            list.add(node);
            for (int to : graph.getNeighborsOf(node)) {
                inDegreeOfNodes[to]--;
                if (inDegreeOfNodes[to] == 0) {
                    queue.add(to);
                }
            }
            count++;
        }

        if (count != graph.nodeCount()) {
            System.out.println("Cycle exists in graph!!!");
            return null;
        }
        int[] result = new int[graph.nodeCount()];
        for (int i = 0; i < list.size(); i++)
            result[i] = list.get(i);
        return result;
    }
}
