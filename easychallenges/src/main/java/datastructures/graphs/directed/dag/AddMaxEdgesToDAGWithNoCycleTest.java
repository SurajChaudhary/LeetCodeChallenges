package datastructures.graphs.directed.dag;

import datastructures.graphs.directed.DirectedGraph;

import java.util.*;

public class AddMaxEdgesToDAGWithNoCycleTest {
    public static void main(String[] args) {
        DirectedGraph g = new DirectedGraph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        g.print();
        listEdgesThatCanBeAddedWithoutFormingCycles(g);
    }

    private static void listEdgesThatCanBeAddedWithoutFormingCycles(DirectedGraph g) {
        int[] topo = topoSortByBFS(g);
        //int[] topo = topoSortByDFS(g);
        for (int i = 0; i < topo.length; i++) {
            int node = topo[i];
            boolean[] visited = new boolean[topo.length];
            Arrays.fill(visited, false);
            for (int to : g.getNeighborsOf(node)) {
                visited[to] = true;
            }

            for (int j = i + 1; j < topo.length; j++) {
                if (!visited[topo[j]]) {
                    System.out.println("Adding edge from " + node + " to " + topo[j] + " [" + node + "-->" + topo[j] + "].");
                    visited[topo[j]] = true;
                }
            }
        }
    }

    private static int[] topoSortByBFS(DirectedGraph g) {
        int[] degree = g.inDegreeOfNodes();
        Queue<Integer> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        for (int node : g.nodes()) {
            if (degree[node] == 0) q.add(node);
        }

        while (!q.isEmpty()) {
            int from = q.poll();
            list.add(from);
            for (int to : g.getNeighborsOf(from)) {
                degree[to]--;
                if (degree[to] == 0) {
                    q.add(to);
                }
            }
        }
        System.out.println(list);
        int[] topo = new int[g.nodeCount()];
        int i = 0;
        for (int ele : list) {
            topo[i] = ele;
            i++;
        }
        return topo;
    }

    private static int[] topoSortByDFS(DirectedGraph g) {
        boolean[] visited = new boolean[g.nodeCount()];
        List<Integer> list = new ArrayList<>();

        for (int node : g.nodes()) {
            if (!visited[node]) {
                dfsUtil(node, visited, list, g);
            }
        }
        Collections.reverse(list);
        System.out.println(list);
        int[] topo = new int[g.nodeCount()];
        int i = 0;
        for (int ele : list) {
            topo[i] = ele;
            i++;
        }
        return topo;
    }

    private static void dfsUtil(int from, boolean[] visited, List<Integer> list, DirectedGraph g) {
        visited[from] = true;
        for (int to : g.getNeighborsOf(from)) {
            if (!visited[to]) {
                dfsUtil(to, visited, list, g);
            }
        }
        list.add(from);
    }
}
