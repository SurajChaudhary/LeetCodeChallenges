package datastructures.graphs.directed;

import java.util.*;

public class LowLinkValueTest {
    public static void main(String[] args) {

        Map<Integer, List<Integer>> components = getComponents(getGraph1(8), 8);
        System.out.println(components);

        Map<Integer, List<Integer>> components1 = getComponents(getGraph2(9), 9);
        System.out.println(components1);
    }

    private static Map<Integer, List<Integer>> getComponents(AdjacencyListBasedGraph graph, int nodes) {
        Stack<Integer> stk = new Stack<>();
        boolean[] onStack = new boolean[nodes];
        Arrays.fill(onStack, false);
        boolean[] visited = new boolean[nodes];
        Arrays.fill(visited, false);
        int[] lowLink = new int[nodes];
        Arrays.fill(lowLink, -1);

        for(int node : graph.getNodes()) {
            if (!visited[node]) {
                populateLowLinkValues(node, visited, lowLink, stk, onStack, graph);
            }
        }

        List<String> bridges = getBridges(graph, lowLink);
        System.out.println("Bridges are: " + bridges);
        Map<Integer, List<Integer>> components = new HashMap<>();
        for(int index = 0; index < lowLink.length; index++) {
            if(components.containsKey(lowLink[index])) {
                components.get(lowLink[index]).add(index);
            }else {
                List<Integer> elements = new ArrayList<>();
                elements.add(index);
                components.put(lowLink[index], elements);
            }
        }
        return components;
    }

    private static List<String> getBridges(AdjacencyListBasedGraph graph, int[] lowLink) {
        boolean[] visited = new boolean[graph.getVertices()];
        Arrays.fill(visited, false);
        List<String> bridges = new ArrayList<>();
        for(int node : graph.getNodes()) {
            if (!visited[node]) {
                bridges(node, visited, lowLink, graph, bridges);
            }
        }
        return bridges;
    }

    private static void bridges(int from, boolean[] visited, int[] lowLink, AdjacencyListBasedGraph graph, List<String> bridges) {
        visited[from] = true;
        for(int to : graph.getNeighboursOf(from)) {
            if (!visited[to]) {
                bridges(to, visited, lowLink, graph, bridges);
            }
            if(from < lowLink[to]) {
                bridges.add(from + " - " + to);
            }
        }
    }

    private static AdjacencyListBasedGraph getGraph1(int nodes) {
        AdjacencyListBasedGraph graph = new AdjacencyListBasedGraph(nodes, true);
        graph.addEdge(3,4);
        graph.addEdge(3,7);
        graph.addEdge(7,3);
        graph.addEdge(7,5);
        graph.addEdge(4,5);
        graph.addEdge(5,6);
        graph.addEdge(5,0);
        graph.addEdge(6,0);
        graph.addEdge(6,2);
        graph.addEdge(6,4);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        return graph;
    }

    private static AdjacencyListBasedGraph getGraph2(int nodes) {
        AdjacencyListBasedGraph graph = new AdjacencyListBasedGraph(nodes, true);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        graph.addEdge(2,5);
        graph.addEdge(3,4);
        graph.addEdge(5,6);
        graph.addEdge(6,7);
        graph.addEdge(7,8);
        graph.addEdge(8,5);
        return graph;
    }

    private static void populateLowLinkValues(int node, boolean[] visited, int[] lowLink, Stack<Integer> stk, boolean[] onStack, AdjacencyListBasedGraph graph) {
        visited[node] = true;
        lowLink[node] = node;
        stk.push(node);
        onStack[node] = true;

        for (int neighbour : graph.getNeighboursOf(node)) {
            if (!visited[neighbour]) {
                populateLowLinkValues(neighbour, visited, lowLink, stk, onStack, graph);
            }
            if(onStack[neighbour]) {
                lowLink[node] = Math.min(lowLink[neighbour], lowLink[node]);
            }
        }
        if(lowLink[node] == node) {
            while(true) {
                int top = stk.pop();
                onStack[top] = false;
                if(top == node) {
                    break;
                }
            }
        }
    }
}
