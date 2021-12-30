package datastructures.graphs.directed;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTreeTest {
    private final List<List<Edge>> graph;
    private int nodes;
    private boolean[] visited;
    private boolean mstExists;
    private long minCostSum;
    private Edge[] mstEdges;
    private PriorityQueue<Edge> pq;

    public MinimumSpanningTreeTest(List<List<Edge>> graph) {
        this.nodes = graph.size();
        this.graph = graph;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return cost - other.cost;
        }
    }

    static void addDirectedEdge(List<List<Edge>> g, int from, int to, int cost) {
        g.get(from).add(new Edge(from, to, cost));
    }

    static void addUndirectedEdge(List<List<Edge>> g, int from, int to, int cost) {
        addDirectedEdge(g, from, to, cost);
        addDirectedEdge(g, to, from, cost);
    }

    static List<List<Edge>> createEmptyGraph(int n) {
        List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        return g;
    }

    public static void main(String[] args) {
        int n = 8;
        List<List<Edge>> g = createEmptyGraph(n);

        addDirectedEdge(g, 0, 1, 10);
        addDirectedEdge(g, 0, 2, 1);
        addDirectedEdge(g, 0, 3, 4);

        addDirectedEdge(g, 2, 1, 3);
        addDirectedEdge(g, 2, 5, 8);
        addDirectedEdge(g, 2, 3, 2);
        addDirectedEdge(g, 2, 0, 1);

        addDirectedEdge(g, 3, 2, 2);
        addDirectedEdge(g, 3, 5, 2);
        addDirectedEdge(g, 3, 6, 7);
        addDirectedEdge(g, 3, 0, 4);

        addDirectedEdge(g, 5, 2, 8);
        addDirectedEdge(g, 5, 4, 1);
        addDirectedEdge(g, 5, 7, 9);
        addDirectedEdge(g, 5, 6, 6);
        addDirectedEdge(g, 5, 3, 2);

        addDirectedEdge(g, 4, 1, 0);
        addDirectedEdge(g, 4, 5, 1);
        addDirectedEdge(g, 4, 7, 8);

        addDirectedEdge(g, 1, 0, 10);
        addDirectedEdge(g, 1, 2, 3);
        addDirectedEdge(g, 1, 4, 0);

        addDirectedEdge(g, 6, 3, 7);
        addDirectedEdge(g, 6, 5, 6);
        addDirectedEdge(g, 6, 7, 12);

        addDirectedEdge(g, 7, 4, 8);
        addDirectedEdge(g, 7, 5, 9);
        addDirectedEdge(g, 7, 6, 12);
        MinimumSpanningTreeTest test = new MinimumSpanningTreeTest(g);
        Long cost = test.getMstCost();

        if (cost == null) {
            System.out.println("No MST does not exists");
        } else {
            System.out.println("MST cost: " + cost);
            for (Edge e : test.getMst()) {
                System.out.println(String.format("from: %d, to: %d, cost: %d", e.from, e.to, e.cost));
            }
        }

    }

    private Edge[] getMst() {
        solve(nodes);
        return mstExists ? mstEdges : null;
    }

    public Long getMstCost() {
        solve(nodes);
        return mstExists ? minCostSum : null;
    }

    private void solve(int n) {
        int maxEdge = n-1;
        int edgeCount = 0;
        pq = new PriorityQueue<>();
        visited = new boolean[n];
        mstEdges = new Edge[maxEdge];

        // Add starting edge to priority queue
        addEdges(0);

        // Loop while the MST is not complete.
        while (!pq.isEmpty() && edgeCount != maxEdge) {
            Edge edge = pq.poll();
            int nodeIndex = edge.to;

            // Skip any edge pointing to an already visited node.
            if (visited[nodeIndex]) continue;

            mstEdges[edgeCount++] = edge;
            minCostSum += edge.cost;

            addEdges(nodeIndex);
        }

        // Check if MST spans entire graph.
        mstExists = (edgeCount == maxEdge);
    }

    private void addEdges(int node) {
        visited[node] = true;

        // edges will never be null if the createEmptyGraph method was used to build the graph.
        List<Edge> edges = graph.get(node);
        for (Edge e : edges)
            if (!visited[e.to]) {
                // System.out.printf("(%d, %d, %d)\n", e.from, e.to, e.cost);
                pq.offer(e);
            }
    }
}
