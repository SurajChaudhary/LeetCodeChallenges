package datastructures.graphs.directed;

import java.util.*;

public class TarjanAlgorithmTest {
    private static int id = 0;
    private static int sccCount = 0;
    private static AdjacencyListBasedGraph graph;
    private static Stack<Integer> stk = new Stack<>();
    private static boolean[] onStack;
    private static int[] low;
    private static int[] ids;
    private static int nodes;
    private static boolean[] visited;

    public static void main(String[] args) {
        nodes = 9;
        visited = new boolean[nodes];
        graph = new AdjacencyListBasedGraph(nodes, true);
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

    private static void printSCC(AdjacencyListBasedGraph graph) {
        ids = new int[nodes];
        Arrays.fill(ids, -1);
        low = new int[nodes];
        Arrays.fill(low, -1);
        onStack = new boolean[nodes];
        Arrays.fill(onStack, false);

        findScc();

        print(low);
    }

    private static void print(int[] low) {
        Map<Integer, List<Integer>> components = new HashMap<>();
        for (int i = 0; i < low.length; i++) {
            List<Integer> list;
            if (components.containsKey(low[i])) {
                list = components.get(low[i]);
            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            components.put(low[i], list);
        }
        components.forEach((key, value) -> System.out.println(value));
        System.out.println("Total SCCs found are: " + components.size());
    }

    private static void findScc() {
        for (int node : graph.getNodes()) {
            if (ids[node] == -1) {
                dfs(node);
            }
        }
    }

    private static void dfs(int node) {
        stk.push(node);
        onStack[node] = true;
        visited[node] = true;
        ids[node] = low[node] = id++;

        for (int neighbour : graph.getNeighboursOf(node)) {
            if (ids[neighbour] == -1) {
                dfs(neighbour);
            }
            if (visited[neighbour] && onStack[neighbour]) {
                low[node] = Math.min(low[node], low[neighbour]);
            }
        }

        if (ids[node] == low[node]) {
            for(int n = stk.pop();;n=stk.pop()) {
                onStack[n] = false;
                low[n] = ids[node];
                if (node == n) break;
            }
            sccCount++;
        }
    }
}
