package datastructures.graphs.directed;

import datastructures.graphs.AdjacencyListBasedDirectedGraph;

import java.util.Arrays;
import java.util.Stack;

public class ArticulationPointTest {
    static int time = 0;
    public static void main(String[] args) {
        AdjacencyListBasedDirectedGraph graph = new AdjacencyListBasedDirectedGraph(6, false);
        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(4, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(3, 6);
        graph.addEdge(5, 6);
        graph.print();

        int[] discoveryTime = getDiscoveryTimeOfNodes(graph, 7);
        System.out.println("Discovery time of node: " + Arrays.toString(discoveryTime));

        int[] lowLinkValues = getLowLinkValues(graph, 7);
        System.out.println("Low link value of node: " + Arrays.toString(lowLinkValues));
    }

    private static int[] getDiscoveryTimeOfNodes(AdjacencyListBasedDirectedGraph graph, int V) {
        boolean[] visited = new boolean[V];
        int[] discoveryTime = new int[V];
        for(int from : graph.getNodes()) {
            if(!visited[from]) {
                dfsUtil(from, visited,discoveryTime, graph);
            }
        }
        return discoveryTime;
    }

    private static void dfsUtil(int from, boolean[] visited, int[] discoveryTime, AdjacencyListBasedDirectedGraph graph) {
        visited[from] = true;
        time++;
        discoveryTime[from] = time;
        for(int to : graph.getNodes()) {
            if(!visited[to]) {
                dfsUtil(to, visited,discoveryTime, graph);
            }
        }
    }

    private static int[] getLowLinkValues(AdjacencyListBasedDirectedGraph graph, int V) {
        Stack<Integer> stk = new Stack<>();

        boolean[] onStack = new boolean[V];
        Arrays.fill(onStack, false);

        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);

        int[] lowLink = new int[V];
        Arrays.fill(lowLink, -1);

        for (int node : graph.getNodes()) {
            if (!visited[node]) {
                populateLowLinkValues(node, visited, lowLink, stk, onStack, graph);
            }
        }
        return lowLink;
    }

    private static void populateLowLinkValues(int node, boolean[] visited, int[] lowLink, Stack<Integer> stk, boolean[] onStack, AdjacencyListBasedDirectedGraph graph) {
        visited[node] = true;
        stk.push(node);
        onStack[node] = true;
        lowLink[node] = node;

        for(int to : graph.getNeighboursOf(node)) {
            if(!visited[to]) {
                populateLowLinkValues(to, visited, lowLink, stk, onStack, graph);
            }
            if(onStack[to]) {
                lowLink[node] = Math.min(lowLink[to], lowLink[node]);
            }

        }

        if(node == lowLink[node]) {
            while(true) {
                int top = stk.pop();
                onStack[top] = false;
                if (top == node) {
                    break;
                }
            }
        }
    }
}
