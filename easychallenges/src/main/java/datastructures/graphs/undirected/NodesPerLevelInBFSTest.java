package datastructures.graphs.undirected;

import datastructures.graphs.AdjacencyListBasedUndirectedGraph;

import java.util.*;

public class NodesPerLevelInBFSTest {
    public static void main(String[] args) {
        AdjacencyListBasedUndirectedGraph graphOne = getFirstGraph();
        graphOne.print();
        System.out.println("=======================================================");
        boolean[] visited = new boolean[graphOne.getVertices()];
        Arrays.fill(visited, false);
        Map<Integer, List<Integer>> levels = new HashMap<>();
        for (int node : graphOne.getNodes()) {
            if (!visited[node]) {
                levelOrderPrint(node, visited, graphOne, levels);
            }
        }
        System.out.println("=======================================================");
        AdjacencyListBasedUndirectedGraph secondGraph = getSecondGraph();
        secondGraph.print();
        System.out.println("=======================================================");
        visited = new boolean[secondGraph.getVertices()];
        Arrays.fill(visited, false);
        levels = new HashMap<>();
        for (int node : secondGraph.getNodes()) {
            if (!visited[node]) {
                levelOrderPrint(node, visited, secondGraph, levels);
            }
        }
        System.out.println("=======================================================");
        visited = new boolean[secondGraph.getVertices()];
        Arrays.fill(visited, false);
        for (int node : secondGraph.getNodes()) {
            if (!visited[node]) {
                levelOrderPrint1(node, visited, secondGraph);
            }
        }
    }

    private static void levelOrderPrint(int start, boolean[] visited, AdjacencyListBasedUndirectedGraph graph, Map<Integer, List<Integer>> levels) {
        Queue<Integer> queue = new LinkedList<>();
        int level = 0;
        queue.add(start);
        visited[start] = true;
        List<Integer> nodes = new ArrayList<>();
        nodes.add(start);
        levels.put(level, nodes);
        queue.add(null);
        level++;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            if (node == null) {
                if (queue.peek() == null) {
                    break;
                } else {
                    queue.add(null);
                    level++;
                    continue;
                }
            }

            for (Integer to : graph.getNeighboursOf(node)) {
                if (!visited[to]) {
                    queue.add(to);
                    visited[to] = true;
                    if (levels.containsKey(level)) {
                        levels.get(level).add(to);
                    } else {
                        List<Integer> children = new ArrayList<>();
                        children.add(to);
                        levels.put(level, children);
                    }
                }
            }
        }
        System.out.println(levels);


    }

    private static void levelOrderPrint1(int start, boolean[] visited, AdjacencyListBasedUndirectedGraph graph) {
        Queue<Integer> queue = new LinkedList<>();
        int[] levels = new int[graph.getVertices()];
        levels[start] = 0;
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer to : graph.getNeighboursOf(node)) {
                if (!visited[to]) {
                    queue.add(to);
                    visited[to] = true;
                    levels[to] = levels[node] + 1;
                }
            }
        }
        System.out.println(Arrays.toString(levels));
    }

    private static AdjacencyListBasedUndirectedGraph getFirstGraph() {
        AdjacencyListBasedUndirectedGraph listBasedGraph = new AdjacencyListBasedUndirectedGraph(5);

        listBasedGraph.addEdge(1, 0);
        listBasedGraph.addEdge(0, 2);
        listBasedGraph.addEdge(2, 1);
        listBasedGraph.addEdge(0, 3);
        listBasedGraph.addEdge(3, 4);
        return listBasedGraph;
    }

    private static AdjacencyListBasedUndirectedGraph getSecondGraph() {
        AdjacencyListBasedUndirectedGraph graph = new AdjacencyListBasedUndirectedGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        return graph;
    }
}
