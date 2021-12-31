package datastructures.graphs.directed.dag;

import datastructures.graphs.directed.DirectedGraph;

import java.util.Arrays;
import java.util.Stack;

public class AllTopologicalSortsTest {
    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(6);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        graph.print();
        System.out.println("InDegree of nodes are: " + Arrays.toString(graph.inDegreeOfNodes()));
        sortTopologically(graph);
    }

    private static void sortTopologically(DirectedGraph graph) {
        boolean[] visited = new boolean[graph.nodeCount()];
        int[] inDegreeOfNodes = graph.inDegreeOfNodes();
        Stack<Integer> list = new Stack<>();
        allTopologicalSortUtil(visited, inDegreeOfNodes, list, graph);
    }

    private static void allTopologicalSortUtil(boolean[] visited, int[] inDegreeOfNodes, Stack<Integer> list, DirectedGraph graph) {
        boolean canPrintList = true;

        for (int start : graph.nodes()) {
            // Check if we can start to topo-traversal
            if ((!visited[start]) && (inDegreeOfNodes[start] == 0)) {
                list.push(start);
                visited[start] = true;

                //reduce the in-degree of all neighbouring nodes
                for (int to : graph.getNeighborsOf(start)) {
                    inDegreeOfNodes[to]--;
                }
                allTopologicalSortUtil(visited, inDegreeOfNodes, list, graph);
                visited[start] = false;
                list.pop();
                //increase the in-degree of all neighbouring nodes
                for (int to : graph.getNeighborsOf(start)) {
                    inDegreeOfNodes[to]++;
                }
                canPrintList = false;
            }
        }
        if (canPrintList) {
            System.out.println(list);
        }
    }

    private static void allTopologicalSortUtilArticleRef(boolean[] visited, int[] inDegreeOfNodes, Stack<Integer> list, DirectedGraph graph) {
        boolean flag = false;
        for (int node : graph.nodes()) {
            // Checking if the node is not visited and most importantly for topological order
            // that node should have in-degree 0, in order to become source node or starting node of sort
            if ((!visited[node]) && (inDegreeOfNodes[node] == 0)) {
                // Marking the starting node visited
                visited[node] = true;
                // Adding node to list
                list.add(node);
                for (int neighbor : graph.getNeighborsOf(node)) {
                    // Reducing in-degree of neighbors by 1
                    inDegreeOfNodes[neighbor]--;
                }
                // Going into recursion with starting node as visited and reduced in-degrees of neighbors
                allTopologicalSortUtil(visited, inDegreeOfNodes, list, graph);
                // Resetting the visited status of starting node
                visited[node] = false;
                // Removing the node from list
                list.remove(list.size() - 1);
                for (int neighbor : graph.getNeighborsOf(node)) {
                    // Since we have un-visited the node, we are increasing in-degree of neighbors by 1
                    inDegreeOfNodes[neighbor]++;
                }
                // Setting the flag to avoid printing partial list
                flag = true;
            }
        }
        if (!flag) {
            // Printing the list at the end of dfs
            list.forEach(integer -> System.out.print(integer + " "));
            System.out.println();
        }
    }
}
