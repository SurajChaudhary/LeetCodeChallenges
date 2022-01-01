package datastructures.graphs;

import datastructures.graphs.directed.DirectedWeightedGraph;
import datastructures.graphs.directed.WeightedEdge;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalAlgoTest {
    public static void main(String[] args) {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(5);
        graph.addEdge(0, 1, 2);
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
        graph.addEdge(4, 3, 9);

        graph.print();
        printMinimumSpanningTree(graph);
    }

    private static void printMinimumSpanningTree(DirectedWeightedGraph graph) {
        WeightedEdge[] result = new WeightedEdge[graph.nodeCount()];
        List<WeightedEdge> edges = graph.edges();
        Collections.sort(edges, new Comparator<WeightedEdge>() {
            @Override
            public int compare(WeightedEdge e1, WeightedEdge e2) {
                if (e1.getCost() == e2.getCost()) {
                    return 0;
                } else {
                    return (e1.getCost() - e2.getCost() > 0 ? +1 : -1);
                }
            }
        });

        int[] parent = new int[graph.nodeCount()];
        for (int node : graph.nodes()) {
            parent[node] = node;
        }
        int[] rank = new int[graph.nodeCount()];
        Arrays.fill(rank, 0);

        int e = 0;
        int i = 0;

        while (e < graph.nodeCount() - 1) {
            WeightedEdge edge = edges.get(i++);
            int fromParent = find(parent, edge.getFrom());
            int toParent = find(parent, edge.getTo());

            if (fromParent != toParent) {
                result[e++] = edge;
                union(parent, rank, fromParent, toParent);
            }
        }

        System.out.println("Following are the edges in the constructed MST");
        int mstCost = 0;
        for (WeightedEdge edge : result) {
            if (edge == null) continue;
            System.out.println(edge.getFrom() + " -- "
                    + edge.getTo()
                    + " == " + edge.getCost());
            mstCost += edge.getCost();
        }
        System.out.println("MST of cost: {" + mstCost + "} is found.");
    }

    private static void union(int[] parent, int[] rank, int fromParent, int toParent) {
        int x = find(parent, fromParent);
        int y = find(parent, toParent);

        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else if (rank[y] < rank[x]) {
            parent[y] = x;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }

    private static int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }
        return parent[node];
    }

}

