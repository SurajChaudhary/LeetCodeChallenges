package datastructures.graphs.directed;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PrimsAlgoTest {
    public static void main(String[] args) {
        DirectedWeightedGraph e = new DirectedWeightedGraph(9);
        /*graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 0, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 1, 3);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 0, 6);
        graph.addEdge(3, 1, 8);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 1, 5);
        graph.addEdge(4, 2, 7);
        graph.addEdge(4, 3, 9);*/
        e.addEdge(0, 1, 4);
        e.addEdge(0, 7, 8);
        e.addEdge(1, 2, 8);
        e.addEdge(1, 7, 11);
        e.addEdge(2, 3, 7);
        e.addEdge(2, 8, 2);
        e.addEdge(2, 5, 4);
        e.addEdge(3, 4, 9);
        e.addEdge(3, 5, 14);
        e.addEdge(4, 5, 10);
        e.addEdge(5, 6, 2);
        e.addEdge(6, 7, 1);
        e.addEdge(6, 8, 6);
        e.addEdge(7, 8, 7);

        e.print();
        printMinimumSpanningTree(e);
    }

    private static void printMinimumSpanningTree(DirectedWeightedGraph graph) {
        int nodes = graph.nodeCount();
        boolean[] visited = new boolean[nodes];
        int maxEdges = nodes - 1;
        int edgeCount = 0;
        int mstCost = 0;
        WeightedEdge[] mstEdges = new WeightedEdge[maxEdges];
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(WeightedEdge e1, WeightedEdge e2) {
                if (e1.getCost() == e2.getCost()) {
                    return 0;
                } else {
                    return (e1.getCost() - e2.getCost() > 0 ? +1 : -1);
                }
            }
        });

        int start = graph.nodes().iterator().next();
        addEdges(start, pq, visited, graph);

        while (!pq.isEmpty() && edgeCount != maxEdges) {
            WeightedEdge edge = pq.poll();
            if (visited[edge.getTo()]) {
                continue;
            }
            mstEdges[edgeCount++] = edge;
            mstCost += edge.getCost();

            addEdges(edge.getTo(), pq, visited, graph);
        }

        if (edgeCount != maxEdges) {
            System.out.println("No MST exists");
        }

        System.out.println("MST of cost: {" + mstCost + "} is found.");
        System.out.println(Arrays.toString(mstEdges));

    }

    private static void addEdges(int node, PriorityQueue<WeightedEdge> pq, boolean[] visited, DirectedWeightedGraph graph) {
        visited[node] = true;
        for (WeightedEdge edge : graph.getNeighborsOf(node)) {
            if (!visited[edge.getTo()]) {
                pq.offer(edge);
            }
        }
    }
}

